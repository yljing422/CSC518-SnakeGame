package snake.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.ImageIcon;

import snake.listener.SnakeListener;
import snake.util.Global;

public class Snake {
	
	//Define the direction variable to control the direction of the snake
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	/*
	 * Define an old direction, and a new direction. Used when changing direction
	 * Determine whether the new direction is the same as the old direction, if the same, then explain
	 * It is an invalid direction and is ignored. If it changes in different directions
	 */
	private int oldDirection, newDirection;
	 
	Ground ground = new Ground();
	//Define a coordinate to store food coordinates
	public Point point = null;
	//Store the total length of the snake body and the number of coordinates occupied
	public int snakeBodyCount;

	// Images variables for the snake
	private ImageIcon righthead;
	private ImageIcon lefthead;
	private ImageIcon uphead;
	private ImageIcon downhead;
	private ImageIcon snakebody;

	private Point oldTail;//Store the coordinates of the tail
	private boolean life; //Judge whether the snake is alive
	private boolean pause; //Does the snake pause
	private boolean isPause; //Snake is suspended every time the game starts
	private int INCREASE_SPEED = 5; // speed increase for every food that the snake eats
	public boolean isDie; //Whether the snake is dead
	public int speed = 500; //Initialize snake speed： 500ms/grid

	//Store the coordinates of the snake body node
	private LinkedList<Point> body =
			new LinkedList<Point>();
	//Define the snake listening list
	private Set<SnakeListener> listener =
			new HashSet<SnakeListener>();

	//Constructor method to initialize the snake
	public Snake() {
		init();
	}
	/*
	 * Initialize the position of the snake so that the snake head appears in the center of the game interface
	 */
	public void init() {
		int x = Global.WIDTH/ 2 - 3;
		int y = Global.HEIGHT / 2 ;
		//Initialize the snake, add three nodes to the snake
		for(int i = 0; i < 3; i++) {
			body.addLast(new Point(x--, y));
		}
		//Initialization direction, right
		oldDirection = newDirection = RIGHT;
		life = true;
		pause = false;
		isPause = true;
		
	}

	public Point getNextMove() {
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch (oldDirection) {
			case UP: //Move up
				y--;
				//When you get to the side, you can appear from the other side
				if (y < 0) {
					y = Global.HEIGHT - 1;
				}
				break;
			case DOWN:
				y++;
				//When you get to the side, you can appear from the other side
				if (y >= Global.HEIGHT) {
					y = 0;
				}
				break;
			case LEFT:
				x--;
				if (x < 0) {
					x = Global.WIDTH - 1;
				}
				break;
			case RIGHT:
				x++;
				if (x >= Global.WIDTH) {
					x = 0;
				}
				break;
		}
		return new Point(x, y);
	}

	/*
	 * Snake moves, first judge whether the new and old directions are the same, and ignore if they are the same
	 * Different, change direction. The snake moves, by adding a head node,
	 * Remove the last node to achieve the purpose of moving
	 */
	public void move() {
		if (!(oldDirection + newDirection == 0)) {
			oldDirection = newDirection;
		}
		//Tail off
		oldTail = body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch(oldDirection) {
		case UP: //Move up
			y--;
			//When you get to the side, you can appear from the other side
			if (y < 0) {
				y = Global.HEIGHT - 1;
			}
			break;
		case DOWN:
			y++;
			//When you get to the side, you can appear from the other side
			if (y >= Global.HEIGHT) {
				y = 0;
			}
			break;
		case LEFT:
			x--;
			if (x < 0) {
				x = Global.WIDTH - 1;
			}
			break;
		case RIGHT:
			x++;
			if (x >= Global.WIDTH) {
				x = 0;
			}
			break;
		
		}
		//Record the coordinates of the snake head
		Point newHead = new Point(x, y);
		//Add head
		body.addFirst(newHead);
	}

	public void backwardOneStep() {
		body.removeFirst();
		body.addLast(oldTail);
	}

	//Snake changes direction
	public void changeDirection(int direction) {
		newDirection = direction;
	}
	//Snake eats food
	public void eatFood() {
		//By adding and deleting the last tail node, the purpose of eating food is achieved
		body.addLast(oldTail);
		speed -= INCREASE_SPEED;
	}

	//Determine whether the snake has eaten the body
	public boolean isEatBody() {
		//body.get(0)The coordinates of the snake’s head are stored,
		//To exclude snake heads, start comparing from i=1
		for (int i = 1; i < body.size(); i++) {
			if (body.get(i).equals(getHead())) {
				return true;
			}
		}
		return false;
	}
	
	 /**
     * Get the snakeBody list of the snake so that the food does not overlap with the snake body
     * body represents the linked list of the snake body
     * Return coordinates that do not overlap with the coordinates of the snake's body
     */
    public Point getFood(LinkedList<Point> body) {
    	//Obtain coordinates that do not overlap with the tree
    	point = ground.getPoint();
        while (checkPoints(body)) {
        	point = ground.getPoint();
        }
        // If the location of the food is found to overlap with the body of the snake, the location of the food will be re-randomized
        return point;
		//Return the object itself, which is convenient when creating an instance
    }
	//Get food coordinates
    public Point getFoodPoint() {
		return getFood(body);
	}

    /**
     * Check whether there is a piece in the snake body link list that is the same as the current food coordinate
	 * @return returns true if there are duplicates
	 * Otherwise return false
     */
    public boolean checkPoints(LinkedList<Point> body) {
    	
        for (Point p : body)
            if (p.getX() == point.getX() && p.getY() == point.getY())
                return true;
        // Whether the loop traverse is repeated
        return false;
    }

	//Draw a snake
	public void drawMe(Graphics g) {
		for(Point p : body) {
			snakebody = new ImageIcon("images/snakebody.png");
		    snakebody.paintIcon(null, g, p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE);
		}
		// draw the head according to the direction
		if (oldDirection == RIGHT) {
			righthead = new ImageIcon("images/righthead.png");
			righthead.paintIcon(null, g, getHead().x * Global.CELL_SIZE, getHead().y * Global.CELL_SIZE);
		}

		if (oldDirection == LEFT) {
			lefthead = new ImageIcon("images/lefthead.png");
			lefthead.paintIcon(null, g, getHead().x * Global.CELL_SIZE, getHead().y * Global.CELL_SIZE);
		}

		if (oldDirection == UP) {
			uphead = new ImageIcon("images/uphead.png");
			uphead.paintIcon(null, g, getHead().x * Global.CELL_SIZE, getHead().y * Global.CELL_SIZE);
		}

		if (oldDirection == DOWN) {
			downhead = new ImageIcon("images/downhead.png");
			downhead.paintIcon(null, g, getHead().x * Global.CELL_SIZE, getHead().y * Global.CELL_SIZE);
		}
	}
	
	//Get the coordinates of the snake head
	public Point getHead() {
		return body.getFirst();
	}
	//Snake dies, life is changed to false
	public void die() {
		life = false;
		isDie = true;
		
	}

	//An internal class that drives the snake to move regularly
	public class SnakerDriver implements Runnable{
		
		public void run() {
			//The snake circulates when it is alive
			while(life) {
				//Incoming snakes can move without pause
				if (!pause) {
					move();
					//Each time the snake moves, get the total length of the snake’s body
					getSnakeBodyCount();
					//Trigger the state change event of SnakeListener
					for(SnakeListener l : listener) {
						l.snakeMove(Snake.this);
					}
					//Let the snake start to pause
					if (isPause) {
						pause = true;
						isPause = false;
					}
				}
				try {
					//Timed move
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//Let the snake start to move, start a new thread
	public void start() {
		new Thread(new SnakerDriver()).start();
	}

	//Add listener
	public void addSnakeListener(SnakeListener l) {
		if(l != null) {
			this.listener.add(l);
		}
	}
	
	public void getSnakeBodyCount() {
		snakeBodyCount = body.size();
	}
	//Change snake pause state
	public void changePause() {
		pause = !pause;
	}
	//Clear all nodes in the body
	public void bodyClear() {
		body.clear();
	}
}
