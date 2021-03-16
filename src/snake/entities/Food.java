package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import snake.util.Global;

@SuppressWarnings("serial")
public class Food extends Point{
	Point point = null;
	//����ʳ��λ������
	public void newFood(Point p) {
		this.point = p;
		this.setLocation(p);
	}
	//�ж����Ƿ�Ե�ʳ��
	public boolean isSnakeEatFood(Snake snake) {
		return this.equals(snake.getHead());
	}
	//��ʾʳ��
	public void drawMe(Graphics g) {
		g.setColor(Color.GREEN);
		g.fill3DRect(point.x * Global.CELL_SIZE, point.y * Global.CELL_SIZE,
				Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
}
