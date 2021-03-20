package snake.entities;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import snake.util.Global;

@SuppressWarnings("serial")
public class Food extends Point{
	Point point = null;
	private ImageIcon food;

	public void newFood(Point p) {
		this.point = p;
		this.setLocation(p);
	}

	public boolean isSnakeEatFood(Snake snake) {
		return this.equals(snake.getHead());
	}

	public void drawMe(Graphics g) {
        // draw the food
		food = new ImageIcon("images/apple.png");
		food.paintIcon(null, g, point.x * Global.CELL_SIZE, point.y * Global.CELL_SIZE);
	}
}
