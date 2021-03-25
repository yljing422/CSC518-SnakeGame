package snake.game;

import java.awt.EventQueue;

import snake.view.MainWindow;
/*
 * As the main method of the game, start the game,
 * through the start window, realize the start procedure
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

		