package snake.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;

//The display interface of the game
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	private Snake snake;
	private Food food;
	private Ground ground;
	private Image bg;
	public GamePanel(){
		try {
			String path1 = "images/bg.png";
			bg = ImageIO.read(new File (path1));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	//Display screen
	public void display(Snake snake, Food food, Ground ground) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		//Will be redisplayed, this method will call the following method
		this.repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {

		//Set background 
		g.drawImage(bg, 0, 0, null);
		if(ground != null && snake != null && food != null ) {
			this.ground.drawMe(g);
			this.snake.drawMe(g);
			this.food.drawMe(g);
		}
		
	}
}
