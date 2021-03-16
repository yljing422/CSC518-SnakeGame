package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import snake.util.Global;

public class Ground {
	/*
	*������ʯͷ��������飬1Ϊʯͷ��0Ϊ�հ����� 
	*һ��Ҫ��ȫ�־�̬������ʼ��ռ�ÿռ䡣������ڲ���ʳ��ʱ����
    */
	private static final int rocks[][] =
			new int[Global.WIDTH][Global.HEIGHT];
	//���ʯͷ�ĸ���
	public int rocksCount = 0;
	//�Ƿ�����
	private boolean isDrawGriding;
	//ѡ���ͼ��ʹ��
	public int MAP = 1;
	//���췽������ʼ����ͼ
	public Ground() {
		init();
	}
	//�������ʯͷ
	public void clear() {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				rocks[x][y] = 0;
	}
	//��ʼ��ʯͷλ��
	public void init() {
		//�������ʯͷ
		clear();
		//ѡ���ͼ
		switch(MAP) {
		case 1:
			map1(); //��ͼ1
			//���ʯͷ����
			getScoksCount();
			break;
		case 2:
			map2(); //��ͼ2
			getScoksCount();
			break;
		case 3:
			map3(); //�����ͼ
			getScoksCount();
			break;
		default :
			map1(); //Ĭ�ϵ�ͼ1
			getScoksCount();
			break;
		}
	}
	//��һ��Ĭ�ϵ�ͼʯͷ����
	public void map1() {
		for(int x = 0; x < Global.WIDTH; x++) {
			rocks[x][0] = 1;
			rocks[x][Global.HEIGHT-1] = 1;
		}
		for(int y = 0; y < Global.HEIGHT; y++) {
			rocks[0][y] = 1;
			rocks[Global.WIDTH-1][y]  = 1;
		}
	}
	//�ڶ�����ͼ
	public void map2() {
		for(int x = 5; x < Global.WIDTH-5; x++) {
			rocks[x][5] = 1;
			rocks[x][Global.HEIGHT-4] = 1;
		}
		for(int y = 9; y < Global.HEIGHT-8; y++) {
			rocks[9][y] = 1;
			rocks[Global.WIDTH-9][y] = 1;
		}
	}
	//�����ͼ��������40��������λʯͷ
	public void map3() {
		Random random = new Random();
		int x = 0,y = 0;
		for(int i = 0; i < 40; i++) {
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
			rocks[x][y] = 1;
		}
	}
	
	//���ʯͷ�ܹ���Ŀ
	public void getScoksCount() {
		//ÿ�θ�����ͼʱ���㣬���»��
		rocksCount = 0;
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				if (rocks[x][y] == 1) {
					rocksCount++;
				}
	}
	//�ж����Ƿ�Ե�ʯͷ
	//���ߵ����нڵ���ʯͷ������бȽ���������֤���Ե�ʯͷ
	public boolean isSnakeEatRock(Snake snake) {
		for(int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (rocks[x][y] == 1 
						&& x == snake.getHead().x  
							&& y == snake.getHead().y) {
					return true;
				}
			}
		}
		return false;
	}
	//��ò�����ʯͷ�ص����������
	public Point getPoint() {
		Random random = new Random();
		int x = 0, y = 0;
		do{
			 x = random.nextInt(Global.WIDTH);
			 y = random.nextInt(Global.HEIGHT);
		}while(rocks[x][y] == 1);
		return new Point(x, y);
	}
	//��ʯͷ������
	public void drawMe(Graphics g) {
		drawRocks(g);
		if (isDrawGriding) {
			drawGriding(g);
		}
	}
	//��ʯͷ
	public void drawRocks(Graphics g) {
		for(int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (rocks[x][y] == 1) {
					g.setColor(Color.DARK_GRAY);
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE,
							Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	//������
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
	//��ҪҪ������
	public void drawGriding() {
		isDrawGriding = true;
	}
	//����Ҫ������
	public void notDrawGriding() {
		isDrawGriding = false;
	}
}
