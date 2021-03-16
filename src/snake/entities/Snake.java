package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import snake.listener.SnakeListener;
import snake.util.Global;

public class Snake {
	
	//���巽����������������ߵķ���
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	/*
	 *    ����һ���ɷ��򣬺��·��������ڸı䷽��ʱ
	 * �ж��·�����ɷ����Ƿ���ͬ�������ͬ��˵��
	 * ����Ч���򣬺��ԡ������ͬ����ı� 
	 */
	private int oldDirection, newDirection;
	 
	Ground ground = new Ground();
	//����һ�����꣬�������ʳ������
	public Point point = null;
	//����������ܳ��ȣ�ռ���������
	public int snakeBodyCount;
	private Point oldTail;//���β�͵�����
	private boolean life; //�ж����Ƿ����
	private boolean pause; //���Ƿ���ͣ
	private boolean isPause; //ÿ�ο�������Ϊ��ͣ״̬
	public boolean isDie; //���Ƿ�����
	public int speed = 500; //��ʼ�����ٶȣ� 500ms/��

	//���������ڵ�����
	private LinkedList<Point> body =
			new LinkedList<Point>();
	//�����߼����б�
	private Set<SnakeListener> listener =
			new HashSet<SnakeListener>();
	
	//���췽���������ߵĳ�ʼ��
	public Snake() {
		init();
	}
	/*
	 * ��ʼ���ߵ�λ�ã�����ͷ��������Ϸ�������ģ�
	 */
	public void init() {
		int x = Global.WIDTH/ 2 - 3;
		int y = Global.HEIGHT / 2 ;
		//��ʼ���ߣ�������������ڵ�
		for(int i = 0; i < 3; i++) {
			body.addLast(new Point(x--, y));
		}
		//��ʼ����������
		oldDirection = newDirection = RIGHT;
		life = true;
		pause = false;
		isPause = true;
		
	}
	/*
	 * ���ƶ������ж��¾ɷ����Ƿ���ͬ����ͬ�����
	 * ��ͬ�����иı䷽�����ƶ���ͨ�����һ��ͷ�ڵ㣬
	 * ȥ��һ�����һ���ڵ㣬�ﵽ�ƶ���Ŀ��
	 */
	public void move() {
		if (!(oldDirection + newDirection == 0)) {
			oldDirection = newDirection;
		}
		//ȥβ
		oldTail = body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch(oldDirection) {
		case UP: //�����ƶ�
			y--;
			//�������˿��Դ���һ�߳��� 
			if (y < 0) {
				y = Global.HEIGHT - 1;
			}
			break;
		case DOWN:
			y++;
			//�������˿��Դ���һ�߳��� 
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
		//��¼��ͷ������
		Point newHead = new Point(x, y);
		//��ͷ
		body.addFirst(newHead);
	}
	//�߸ı䷽��
	public void chanceDirection(int direction) {
		newDirection = direction;
		
	}
	//�߳�ʳ��
	public void eatFood() {
		//ͨ�����ɾȥ������β�ڵ㣬�ﵽ��ʳ���Ŀ��
		body.addLast(oldTail);
	
	}
	
	//�ж����Ƿ�Ե�����
	public boolean isEatBody() {
		//body.get(0)��ŵ�Ϊ��ͷ�����꣬
		//����Ҫ�ų���ͷ����i=1��ʼ�Ƚ�
		for (int i = 1; i < body.size(); i++) {
			if (body.get(i).equals(getHead())) {
				return true;
			}
		}
		return false;
	}
	
	 /**
     * ��ȡ�ߵ�snakeBody������ʳ���������ص�
     *        body    ��ʾ�����������
     * ���������������겻�ظ�������
     */
    public Point getFood(LinkedList<Point> body) {
    	//�����ʯͷ���ص�������
    	point = ground.getPoint();
        while (checkPoints(body)) {
        	point = ground.getPoint();
        }
        // �������ʳ���λ�ú��������ص������������ʳ���λ��
        return point;
        // �������������Ϊ����ʵ��ʱ��������
    }
    //���ʳ������
    public Point getFoodPoint() {
		return getFood(body);
	}

    /**
     * ����������������Ƿ���һ���뵱ǰʳ��������ͬ
     * @return ������ظ�����true
     * ���򷵻� false
     */
    public boolean checkPoints(LinkedList<Point> body) {
    	
        for (Point p : body)
            if (p.getX() == point.getX() && p.getY() == point.getY())
                return true;
        // ѭ�������Ƿ����ظ�
        return false;
    }


	//����
	public void drawMe(Graphics g) {
		for(Point p : body) {
			g.setColor(Color.PINK);//����������ɫ
			g.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE,
					Global.CELL_SIZE, Global.CELL_SIZE, true);
			//���һ��������raised �Ƿ�͹��ģ�trueΪ�ǡ�
		}
		//����ͷ��������ͷλ��
		g.setColor(Color.RED);
		g.fill3DRect(getHead().x * Global.CELL_SIZE, getHead().y * Global.CELL_SIZE,
				Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
	
	//�����ͷ������
	public Point getHead() {
		return body.getFirst();
	}
	//��������������Ϊfalse
	public void die() {
		life = false;
		isDie = true;
		
	}
	
	//һ���ڲ���, �����߶�ʱ�ƶ�
	public class SnakerDriver implements Runnable{
		
		public void run() {
			//���߻��ŵ�ʱ��Ž���ѭ��
			while(life) {
				//�����û����ͣ�����ƶ�
				if (!pause) {
					move();
					//��ÿ���ƶ��󣬻���������ܳ���
					getSnakeBodyCount();
					//���� SnakeListener ��״̬�ı��¼�
					for(SnakeListener l : listener) {
						l.snakeMove(Snake.this);
					}
					//���߿���ʼʱΪ��ͣ״̬
					if (isPause) {
						pause = true;
						isPause = false;
					}
				}
				try {
					//��ʱ�ƶ�
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//���߿�ʼ�˶��� ����һ���µ��߳�
	public void start() {
		new Thread(new SnakerDriver()).start();
	}
	
	//��Ӽ�����
	public void addSnakeListener(SnakeListener l) {
		if(l != null) {
			this.listener.add(l);
		}
	}
	
	public void getSnakeBodyCount() {
		snakeBodyCount = body.size();
	}
	//�ı�����ͣ״̬
	public void changePause() {
		pause = !pause;
	}
	//����������нڵ�
	public void bodyClear() {
		body.clear();
	}
}
