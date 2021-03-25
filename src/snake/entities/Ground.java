package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import snake.util.Global;

public class Ground {
	/*
	 *Define the array for storing the stone coordinates, 1 is the tree, 0 is the blank area
	 *Be sure to use global static variables to always take up space. Otherwise it will make mistakes when producing food
	 */
	private static final int trees[][] =
			new int[Global.WIDTH][Global.HEIGHT];
	//The number of stored trees
	public int treesCount = 0;
	//Whether to draw a grid
	private boolean isDrawGriding;
	//Choose map to use
	public int MAP = 1;
	//Constructor, initialize the map
	public Ground() {
		init();
	}
	//Clear all trees
	public void clear() {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				trees[x][y] = 0;
	}
	//Initialize the tree position
	public void init() {
		//Clear all trees
		clear();
		//Select map
		switch(MAP) {
			case 1:
				map1();
				getTreesCount();
				break;
			case 2:
				map2(); //map2
				getTreesCount();
				break;
			case 3:
				map3(); //Random map
				getTreesCount();
				break;
			default :
				map1(); //Default map 1
				getTreesCount();
				break;
		}
	}
	//The first set of default map tree coordinates
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
	//Second map
	public void map2() {
		for(int x = 5; x < Global.WIDTH-5; x++) {
			trees[x][5] = 1;
			trees[x][Global.HEIGHT-4] = 1;
		}
		for(int y = 9; y < Global.HEIGHT-8; y++) {
			trees[9][y] = 1;
			trees[Global.WIDTH-9][y] = 1;
		}
	}
	//Random map, get 40 coordinate seat trees randomly
	public void map3() {
		Random random = new Random();
		int x = 0,y = 0;
		for(int i = 0; i < 40; i++) {
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
			trees[x][y] = 1;
		}
	}

	//Get the total number of trees
	public void getTreesCount() {
		//Cleared every time the map is changed, regained
		treesCount = 0;
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				if (trees[x][y] == 1) {
					treesCount++;
				}
	}

	//Judge whether the snake crash the tree
	//
	//Compare all the nodes of the snake with the coordinates of the tree. If you want to wait, it will prove that you have crash the tree.
	public boolean isSnakeCrashTree(Snake snake) {
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
	//Get random coordinates that will not overlap with the tree
	public Point getPoint() {
		Random random = new Random();
		int x = 0, y = 0;
		do{
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
		}while(trees[x][y] == 1);
		return new Point(x, y);
	}
	//Painting trees and grids
	public void drawMe(Graphics g) {
		drawTrees(g);
		if (isDrawGriding) {
			drawGriding(g);
		}
	}
	//Place tree
	public void drawTrees(Graphics g) {
		for(int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (trees[x][y] == 1) {
					g.setColor(Color.DARK_GRAY);
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE,
							Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	//Draw grid
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
	//Need to draw grid
	public void drawGriding() {
		isDrawGriding = true;
	}
	//No need to draw grid
	public void notDrawGriding() {
		isDrawGriding = false;
	}
}
