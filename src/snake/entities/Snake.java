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
	
	//瀹氫箟鏂瑰悜鍙橀噺锛岀敤鏉ユ帶鍒惰泧鐨勬柟鍚�
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	/*
	 *    瀹氫箟涓�涓棫鏂瑰悜锛屽拰鏂版柟鍚戙�傜敤鏉ュ湪鏀瑰彉鏂瑰悜鏃�
	 * 鍒ゆ柇鏂版柟鍚戜笌鏃ф柟鍚戞槸鍚︾浉鍚岋紝濡傛灉鐩稿悓鍒欒鏄�
	 * 鏄棤鏁堟柟鍚戯紝蹇界暐銆傚鏋滀笉鍚屾柟鍚戞敼鍙� 
	 */
	private int oldDirection, newDirection;
	 
	Ground ground = new Ground();
	//瀹氫箟涓�涓潗鏍囷紝鐢ㄦ潵瀛樻斁椋熺墿鍧愭爣
	public Point point = null;
	//瀛樻斁铔囪韩浣撴�婚暱搴︼紝鍗犵敤鍧愭爣涓暟
	public int snakeBodyCount;

	// Images varialbes for the snake	
	private ImageIcon righthead;
	private ImageIcon lefthead;
	private ImageIcon uphead;
	private ImageIcon downhead;
	private ImageIcon snakebody;

	private Point oldTail;//存放尾巴的坐标
	private boolean life; //判断蛇是否活着
	private boolean pause; //蛇是否暂停
	private boolean isPause; //每次开开局蛇为暂停状态
	private int INCREASE_SPEED = 10; // speed increase for every food that the snake eats
	public boolean isDie; //蛇是否死亡
	public int speed = 500; //初始化蛇速度： 500ms/格

	//存放蛇身体节点坐标
	private LinkedList<Point> body =
			new LinkedList<Point>();
	//瀹氫箟铔囩洃鍚垪琛�
	private Set<SnakeListener> listener =
			new HashSet<SnakeListener>();
	
	//鏋勯�犳柟娉曪紝杩涜铔囩殑鍒濆鍖�
	public Snake() {
		init();
	}
	/*
	 * 鍒濆鍖栬泧鐨勪綅缃紝璁╄泧澶村嚭鐜板湪娓告垙鐣岄潰涓績锛�
	 */
	public void init() {
		int x = Global.WIDTH/ 2 - 3;
		int y = Global.HEIGHT / 2 ;
		//鍒濆鍖栬泧锛岀粰铔囨坊鍔犱笁涓妭鐐�
		for(int i = 0; i < 3; i++) {
			body.addLast(new Point(x--, y));
		}
		//鍒濆鍖栨柟鍚戯紝鍚戝彸
		oldDirection = newDirection = RIGHT;
		life = true;
		pause = false;
		isPause = true;
		
	}
	/*
	 * 铔囩Щ鍔紝鍏堝垽鏂柊鏃ф柟鍚戞槸鍚︾浉鍚岋紝鐩稿悓鍒欏拷鐣�
	 * 涓嶅悓锛岃繘琛屾敼鍙樻柟鍚戙�傝泧绉诲姩锛岄�氳繃娣诲姞涓�涓ご鑺傜偣锛�
	 * 鍘婚櫎涓�涓渶鍚庝竴涓妭鐐癸紝杈惧埌绉诲姩鐨勭洰鐨�
	 */
	public void move() {
		if (!(oldDirection + newDirection == 0)) {
			oldDirection = newDirection;
		}
		//鍘诲熬
		oldTail = body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch(oldDirection) {
		case UP: //鍚戜笂绉诲姩
			y--;
			//鍒拌竟涓婁簡鍙互浠庡彟涓�杈瑰嚭鐜� 
			if (y < 0) {
				y = Global.HEIGHT - 1;
			}
			break;
		case DOWN:
			y++;
			//鍒拌竟涓婁簡鍙互浠庡彟涓�杈瑰嚭鐜� 
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
		//璁板綍铔囧ご鐨勫潗鏍�
		Point newHead = new Point(x, y);
		//鍔犲ご
		body.addFirst(newHead);
	}
	//铔囨敼鍙樻柟鍚�
	public void chanceDirection(int direction) {
		newDirection = direction;
		
	}
	//铔囧悆椋熺墿
	public void eatFood() {
		//閫氳繃娣诲姞鍒犲幓鐨勬渶鍚庣殑灏捐妭鐐癸紝杈惧埌鍚冮鐗╃殑鐩殑
		body.addLast(oldTail);
		speed -= INCREASE_SPEED;
	}
	
	//鍒ゆ柇铔囨槸鍚﹀悆鍒拌韩浣�
	public boolean isEatBody() {
		//body.get(0)瀛樻斁鐨勪负铔囧ご鐨勫潗鏍囷紝
		//鎵�鏈夎鎺掗櫎铔囧ご锛屼粠i=1寮�濮嬫瘮杈�
		for (int i = 1; i < body.size(); i++) {
			if (body.get(i).equals(getHead())) {
				return true;
			}
		}
		return false;
	}
	
	 /**
     * 鑾峰彇铔囩殑snakeBody閾捐〃锛岃椋熺墿涓庤泧韬笉閲嶅彔
     *        body    琛ㄧず铔囪韩浣撶殑閾捐〃
     * 杩斿洖涓庤泧韬綋鍧愭爣涓嶉噸澶嶇殑鍧愭爣
     */
    public Point getFood(LinkedList<Point> body) {
    	//鑾峰緱涓庣煶澶翠笉閲嶅彔鐨勫潗鏍�
    	point = ground.getPoint();
        while (checkPoints(body)) {
        	point = ground.getPoint();
        }
        // 濡傛灉鍙戠幇椋熺墿鐨勪綅缃拰铔囪韩浣撻噸鍙狅紝鍒欓噸鏂伴殢鏈洪鐗╃殑浣嶇疆
        return point;
        // 杩斿洖杩欎釜瀵硅薄鏈韩锛屼负鍒涘缓瀹炰緥鏃跺甫鏉ユ柟渚�
    }
    //鑾峰緱椋熺墿鍧愭爣
    public Point getFoodPoint() {
		return getFood(body);
	}

    /**
     * 妫�鏌ヨ泧韬綋閾捐〃涓槸鍚︽湁涓�鍧椾笌褰撳墠椋熺墿鍧愭爣鐩稿悓
     * @return 濡傛灉鏈夐噸澶嶈繑鍥瀟rue
     * 鍚﹀垯杩斿洖 false
     */
    public boolean checkPoints(LinkedList<Point> body) {
    	
        for (Point p : body)
            if (p.getX() == point.getX() && p.getY() == point.getY())
                return true;
        // 寰幆閬嶅巻鏄惁鏈夐噸澶�
        return false;
    }


	//鐢昏泧
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
	
	//鑾峰緱铔囧ご鐨勫潗鏍�
	public Point getHead() {
		return body.getFirst();
	}
	//铔囨浜★紝鐢熷懡鏀逛负false
	public void die() {
		life = false;
		isDie = true;
		
	}
	
	//涓�涓唴閮ㄧ被, 椹卞姩铔囧畾鏃剁Щ鍔�
	public class SnakerDriver implements Runnable{
		
		public void run() {
			//褰撹泧娲荤潃鐨勬椂鍊欐墠杩涜寰幆
			while(life) {
				//鍏ヤ紮铔囨病鏈夋殏鍋滄墠鑳界Щ鍔�
				if (!pause) {
					move();
					//铔囨瘡娆＄Щ鍔ㄥ悗锛岃幏寰楄泧韬綋鎬婚暱搴�
					getSnakeBodyCount();
					//瑙﹀彂 SnakeListener 鐨勭姸鎬佹敼鍙樹簨浠�
					for(SnakeListener l : listener) {
						l.snakeMove(Snake.this);
					}
					//璁╄泧寮�寮�濮嬫椂涓烘殏鍋滅姸鎬�
					if (isPause) {
						pause = true;
						isPause = false;
					}
				}
				try {
					//瀹氭椂绉诲姩
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//璁╄泧寮�濮嬭繍鍔紝 寮�鍚竴涓柊鐨勭嚎绋�
	public void start() {
		new Thread(new SnakerDriver()).start();
	}
	
	//娣诲姞鐩戝惉鍣�
	public void addSnakeListener(SnakeListener l) {
		if(l != null) {
			this.listener.add(l);
		}
	}
	
	public void getSnakeBodyCount() {
		snakeBodyCount = body.size();
	}
	//鏀瑰彉铔囨殏鍋滅姸鎬�
	public void changePause() {
		pause = !pause;
	}
	//娓呴櫎韬綋鎵�鏈夎妭鐐�
	public void bodyClear() {
		body.clear();
	}
}
