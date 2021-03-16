package snake.game;

import java.awt.EventQueue;

import snake.view.MainWindow;
/*
 * ��Ϊ��Ϸ����������������Ϸ��ͨ���������壬ʵ����������
 */
public class Game {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

		