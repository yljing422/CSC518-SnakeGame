package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.util.Random;

import snake.util.Global;

import javax.swing.*;

public class Ground {
	/*
	define a new int array to store trees,if there is a tree,the value is 1,otherwise is 0
	it must be static to avoid cause collision when generate food
    */
	private static final int trees[][] =
			new int[Global.WIDTH][Global.HEIGHT];
	// Count the number of trees
	public int treesCount = 0;
	// draw grid or not
	private boolean isDrawGriding;
	//map = 1, means the map is selected
	public int MAP = 1;
	private ImageObserver img;

	//initial the map
	public Ground() {
		init();
	}
	//clear all trees
	public void clear() {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				trees[x][y] = 0;
	}
	//initial the position of all trees
	public void init() {
		//call clear stone method
		clear();
		//switch to map mode
		switch(MAP) {
			case 1:
				map1(); //mode 1 use map 1
				//get the number of trees
				getTreesCount();
				break;
			case 2:
				map2(); //mode 2 use map 2get the number of trees
				getTreesCount();
				break;
			case 3:
				map3(); //mode 3 random map get the number of trees
				getTreesCount();
				break;
			default :
				map1(); //?????1
				getTreesCount();
				break;
		}
	}
	//map 1 set the position of trees(as the wall of game panel)
	public void map1() {
		for(int x = 0; x < Global.WIDTH; x++) {
			trees[x][0] = 1;
			trees[x][Global.HEIGHT-1] = 1;
		}
		for(int y = 0; y < Global.HEIGHT; y++) {
			trees[0][y] = 1;
			trees[Global.WIDTH-1][y]  = 1;
		}
	}
	//map 2 set the position of trees(as the wall of game panel)
	public void map2() {
		clear();
	}
	//map 3  set 40 trees randomly
	public void map3() {
		Random random = new Random();
		int x = random.nextInt(Global.WIDTH);
		int y = random.nextInt(Global.HEIGHT);
		trees[x][y] = 1;
	}

	//get the total number of trees
	public void getTreesCount() {
		//clear all trees when switch map
		treesCount = 0;
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				if (trees[x][y] == 1) {
					treesCount++;
				}
	}

	// use random sampling to improve
	public boolean createNewTree(Snake snake) {
		Random random = new Random();
		while(true) {
			int p = random.nextInt(Global.WIDTH);
			int q = random.nextInt(Global.HEIGHT);
			trees[p][q] = 1;
			boolean ishit = isSnakeHitTree(snake);
			if(!ishit) {
				break;
			}
			trees[p][q] = 0;
		}
		return true;
	}
	//check whether the snake hit a tree
	//by comparing the snake's body with positions of all trees
	public boolean isSnakeHitTree(Snake snake) {
		for(int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (trees[x][y] == 1
						&& x == snake.getHead().x
						&& y == snake.getHead().y) {
					return true;
				}
			}
		}
		return false;
	}
	//Get the point which hasn't occupied by trees
	public Point getPoint() {
		Random random = new Random();
		int x = 0, y = 0;
		do{
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
		}while(trees[x][y] == 1);
		return new Point(x, y);
	}
	//draw grid and trees
	public void drawMe(Graphics g) {
		drawtrees(g);
		if (isDrawGriding) {
			drawGriding(g);
		}
	}
	//draw trees
	public void drawtrees(Graphics g) {
		for(int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (trees[x][y] == 1) {
					ImageIcon icon2 =new ImageIcon("images/tree.jpeg");
					g.drawImage(icon2.getImage(),x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE,img);
				}
			}
		}
	}
	//draw grid
	public void drawGriding(Graphics g) {
		for(int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				g.setColor(Color.GRAY);
				g.fillRect(x * Global.CELL_SIZE , y * Global.CELL_SIZE,
						1 , Global.HEIGHT * Global.CELL_SIZE);
				g.fillRect(x * Global.CELL_SIZE , y * Global.CELL_SIZE,
						Global.HEIGHT * Global.CELL_SIZE, 1 );

			}
		}
	}
	//check whether 'drawing grid' is chosen by player
	public void drawGriding() {
		isDrawGriding = true;
	}
	public void notDrawGriding() {
		isDrawGriding = false;
	}
}
