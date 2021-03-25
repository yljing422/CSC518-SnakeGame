package snake.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;
import snake.listener.SnakeListener;
import snake.util.Global;
import snake.view.GamePanel;

/*Controller
* Control Ground, Snake, Food<BR>
* Responsible for the logic of the game
* Handling key events
* Implemented the SnakeListener interface, which can handle events triggered by Snake
*/
public class Controller extends KeyAdapter implements SnakeListener {
	
	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;
	//Store this game score
	public int score = 0;
	//Store the highest score in history, this data is assigned by reading the file
	public int maxScore;
	public Thread thread;
	public boolean isDeductingScore = false;

	//Construction method, initialization
	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		super();
		this.snake = snake; 
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
		//Read the file every time when you start the game and then assign a value to maxScore
		readFile();
	}
	
	@Override
	//Handling key events
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.chanceDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.chanceDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.chanceDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.chanceDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_S://Space bar to pause the game
			snake.changePause();
			break;
		case KeyEvent.VK_W://Shift key to start a new game
			newGame();
			break;
		case KeyEvent.VK_A:	//A to slow down
			snake.speed += 5;
			break;
		case KeyEvent.VK_D:	//D to speed up
			snake.speed -= 5;
			break;
		}
	}
   //Handle the snakeMoved event triggered by Snake
	@Override
	public void snakeMove(Snake snake){
		/*
		 * Determine whether you can put down the food
		 * When the body occupies all the space and there is no place to put food
		 * game over
		 * Global.count : The total coordinates of the global game window, the default is 1000
		 * this.snake.snakeBodyCount �� The total length of the snake's body
		 * ground.rocksCount �� Total number of stones
		 * 
		 */
		if (Global.count - this.snake.snakeBodyCount - ground.treesCount < 3) {
			snake.die();
			writeMaxScore();
			//A message box will pop up, prompting that the game is over and showing the score
			JOptionPane.showMessageDialog(gamePanel, "You have got the highest score, the game is over!\n       Game score: "+ score);
		}
		//If the snake eats food, how to deal with the food that the snake eats and get new food
		if (food.isSnakeEatFood(snake)) {
			snake.eatFood();
			food.newFood(snake.getFoodPoint());
			if(ground.MAP == 3) { 
				ground.createNewTree(snake);
			}	
			this.score +=10;
			
		}

		if ((ground.isSnakeHitTree(snake) || snake.isEatBody()) && this.score > 0) {
			// Each time the snake crashes into something, it wiil not die immediately. Instead, a 'crash countdown'
			// begins and reduces by one each move time of the game. The player sees this count down via the score
			// message bar. If the snake is moved away before the count down reaches zero, it has escaped death.
			this.score--;
			this.isDeductingScore = true;
			snake.backwardOneStep();
		} else {
			this.isDeductingScore = false;
		}

		//Determine whether to eat the stone, if the stone is eaten, the snake will die
		if (ground.isSnakeHitTree(snake)) {
			if (this.score > 0) {
				snake.backwardOneStep();
			} else {
				snake.die();
				//If the game score is greater than the highest score in the history, the current score is assigned to the highest score and written to the file
				writeMaxScore();
				//A message box will pop up, prompting that the game is over and showing the score
				JOptionPane.showMessageDialog(gamePanel, "Snake hits the wall and died, the game is over!\n       Game score: " + score);
			}
		}

		if(snake.isEatBody()) { //If the snake eats the body, it will die
			if (this.score > 0) {
				snake.backwardOneStep();
			} else {
				snake.die();
				writeMaxScore();
				JOptionPane.showMessageDialog(gamePanel, "The snake bites to death and the game is over!\n       Game score: " + score);
			}
		}
		//
		//If the snake dies, the screen will not be refreshed for the last time. If refreshed, the snake head will overlap the stone
		if (!(ground.isSnakeHitTree(snake) | snake.isEatBody())) {
			gamePanel.display(snake, food, ground);
		}
	}
	//Start the game
	public void beginGame() {
		//When starting the game, the score is reset to zero
		score = 0;
		//Every time you start the game, you read the file and get the highest score in history
		readFile();
		//Get new food coordinates
		food.newFood(snake.getFoodPoint());
		//Start the snake-driven thread
		snake.start();
		//Start the main window interface refresh thread, used to update the score
		new Thread(thread).start();
	}
	
	//Start a new game
	public void newGame() {
		//After starting a new game, clear the snake��s body
		snake.bodyClear();
		//Reinitialize snake
		snake.init();
		//Zero score
		score = 0;
		//Reset the speed
		snake.speed = 500;
		//Get new food coordinates
		food.newFood(snake.getFoodPoint());
		/*
		 * Determine whether the snake is dead, if it is,
		 * Then the loop has been jumped out of the snake drive and will not trigger the snake��s monitoring
		 * At this point, start calling to start the game, re-initialize the game, and re-monitor the snake movement
		 * 
		 * If the snake is not in a dead state, the start game initialization is not performed, and the snake is in a normal monitoring state at this time
		 * Just reinitialize the snake and food, score and start a new game
		 */
		if (snake.isDie) {
			beginGame();
			snake.isDie = false;
		}
	}

	//Read the file to get the highest score in history
	public void readFile(){
		File file = new File("MaxScore.txt");
		//If the file does not exist, the file output stream will automatically create the file
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Read file
		BufferedReader br;
		try {
			br = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(file), "UTF-8"));
			maxScore = br.read();
			br.close();

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeMaxScore() {
		if (score > maxScore) {
			maxScore = score;
			writeFile();
		}
	}
	public void writeFile() {

		File file = new File("MaxScore.txt");
		//If the file does not exist, the file output stream will automatically create the file
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Write file
		try {

			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(file), "UTF-8"));
			bw.write(maxScore);//Write the highest score to the file
			bw.close();//Close stream

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//Receive the thread that refreshes the interface in the main form
	public Thread startRefresh(Thread thread) {
		this.thread = thread;
		return this.thread;
	}
}
