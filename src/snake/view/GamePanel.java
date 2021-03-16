package snake.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;
import snake.util.Global;
//��Ϸ����ʾ����
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	private Snake snake;
	private Food food;
	private Ground ground;
	//��ʾ����
	public void display(Snake snake, Food food, Ground ground) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;		
		//��������ʾ���˷������������ķ���
		this.repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		//������ʾ
		//���ñ�����ɫ
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE, 
				Global.HEIGHT * Global.CELL_SIZE);
		if(ground != null && snake != null && food != null ) {
			this.ground.drawMe(g);
			this.snake.drawMe(g);
			this.food.drawMe(g);
		}
		
	}
}
