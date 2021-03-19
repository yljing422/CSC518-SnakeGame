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

/*������
* ����Ground, Snake, Food<BR>
* ������Ϸ���߼�
* �������¼�
* ʵ����SnakeListener�ӿ�, ���Դ���Snake �������¼�
*/
public class Controller extends KeyAdapter implements SnakeListener {
	
	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;
    //��ŵ�����Ϸ�÷�
	public int score = 0;
	//�����ʷ��ߵ÷֣��������ͨ����ȡ�ļ�����ֵ
	public int maxScore;
	public Thread thread;

	//���췽������ʼ��
	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		super();
		this.snake = snake; 
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
		//ÿ�ο�ʼ��Ϸ��ȡ�ļ�����maxScore��ֵ
		readFile();
	}
	
	@Override
	//�������¼�
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP://����
			snake.chanceDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN://����
			snake.chanceDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT://����
			snake.chanceDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT://����
			snake.chanceDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_SPACE://�ո����ʵ����Ϸ��ͣ
			snake.changePause();
			break;
		case KeyEvent.VK_SHIFT://Shift����ʵ�ֿ�ʼ����Ϸ
			newGame();
			break;
		}
	}
   //����Snake ������ snakeMoved �¼�
	@Override
	public void snakeMove(Snake snake){
		/*
		 * �ж��Ƿ񻹿��Է���ʳ��
		 * ������ռ��ȫ����λ��û�еط��ٿ��Է�ʳ��ʱ
		 * ��Ϸ����
		 * Global.count : ȫ����Ϸ���������꣬Ĭ��1000
		 * this.snake.snakeBodyCount �� �ߵ������ܳ���
		 * ground.rocksCount �� ʯͷ����
		 * 
		 */
		if (Global.count - this.snake.snakeBodyCount - ground.treesCount < 3) {
			snake.die();
			writeMaxScore();
			//������Ϣ����ʾ��Ϸ����������ʾ�÷�
			JOptionPane.showMessageDialog(gamePanel, "���ѻ����߷֣���Ϸ������\n       ��Ϸ�÷֣�"+ score);
		}
		//����߳Ե�ʳ��������߳Ե�ʳ��ķ�����������µ�ʳ��
		if (food.isSnakeEatFood(snake)) {
			snake.eatFood();
			food.newFood(snake.getFoodPoint());
			this.score +=10;
			
		}
		//�ж��Ƿ�Ե�ʯͷ������Ե�ʯͷ����������
		if (ground.isSnakeHitTree(snake)) {
			snake.die();
			//�����Ϸ�÷ִ�����ʷ��¼��߷֣��ѵ�ǰ�÷ָ�����߷֣���д���ļ�
			writeMaxScore();
			//������Ϣ����ʾ��Ϸ����������ʾ�÷�
			JOptionPane.showMessageDialog(gamePanel, "��ײǽ��������Ϸ������\n       ��Ϸ�÷֣�"+ score);
		}
		//����߳Ե�����Ҳ����
		if(snake.isEatBody()) {
			snake.die();
			writeMaxScore();
			JOptionPane.showMessageDialog(gamePanel, "��ҧ���Լ���������Ϸ������\n       ��Ϸ�÷֣�"+ score);
		}
		//��������������һ�β�ˢ�»��棬���ˢ�£���ͷ����ʯͷ�ص�
		if (!(ground.isSnakeHitTree(snake) | snake.isEatBody())) {
			gamePanel.display(snake, food, ground);
		}
	}
	//��ʼ��Ϸ
	public void beginGame() {
		//��ʼ��Ϸʱ���÷ֹ���
		score = 0;
		//ÿ�ο�ʼ��Ϸ�Ƕ�ȡ�ļ��������ʷ��߷�
		readFile();
		//����µ�ʳ������
		food.newFood(snake.getFoodPoint());
		//��ʼ���������߳�
		snake.start();
		//�������������ˢ�µ��̣߳��������·���
		new Thread(thread).start();
	}
	
	//��ʼ����Ϸ
	public void newGame() {
		//��ʼ����Ϸ������ߵ�����
		snake.bodyClear();
		//���³�ʼ����
		snake.init();
		//�÷ֹ���
		score = 0;
		//�����ʳ������
		food.newFood(snake.getFoodPoint());
		/*
		 * �ж����Ƿ�������״̬������ǣ�
		 * �������������Ѿ�����ѭ�������ᴥ���ߵļ���
		 * ��ʱ�ٿ�ʼ���ÿ�ʼ��Ϸ�����³�ʼ����Ϸ�����¼������˶�
		 * 
		 * ����߲�������״̬����ִ�п�ʼ��Ϸ��ʼ������ʱ�ߴ�����������״̬
		 * ֻ���³�ʼ���ߺ�ʳ��������ɿ�ʼ����Ϸ��
		 */
		if (snake.isDie) {
			beginGame();
			snake.isDie = false;
		}
	}
	
	//���ļ�����ȡ��ʷ��߷�
	public void readFile(){
		File file = new File("MaxScore.txt");
		//����ļ������ڣ��ļ���������Զ������ļ�
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//��ȡ�ļ�
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
		//����ļ������ڣ��ļ���������Զ������ļ�
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//д�ļ�
		try {

			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(file), "UTF-8"));
			bw.write(maxScore);//���ļ�д����߷�
			bw.close();//�ر���

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//������������ˢ�½�����߳�
	public Thread startRefresh(Thread thread) {
		this.thread = thread;
		return this.thread;
	}
}
