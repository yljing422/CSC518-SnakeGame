package snake.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import snake.controller.Controller;
import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;
import snake.util.Global;
/*
 * These codes are basically directly involved in the form, and all the codes can basically be ignored.
 * Just look at the key places with annotations
 * In the design, except for the GamePanel to get the focus, other components can not get the focus
 */

@SuppressWarnings("serial")
public class MainWindow extends JFrame{

	protected static final Object SnakeListener = null;

	private JPanel contentPane;

	Snake snake = new Snake();
	Food food = new Food();
	Ground ground = new Ground();
	public JTextField txt_score;
	private JTextField txt_speed;
	private JTextField txt_maxScore;
	// Message bar
	private JTextField txt_message;
	private JTextField txt_score1;
	private JTextField txt_maxScore1;
	    
	GamePanel gamePanel = new GamePanel();
	Controller controller = new Controller(snake, food, ground, gamePanel);
	
	public MainWindow() {
		setResizable(false);
		setTitle("Snake Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Center the form
		setLocation(getToolkit().getScreenSize().width / 2 - Global.CELL_SIZE * Global.WIDTH / 2,
				getToolkit().getScreenSize().height / 2 - Global.CELL_SIZE * Global.WIDTH / 2);
 
		setSize(821, 760);
		addKeyListener(controller);
		contentPane = new JPanel();
		contentPane.setFocusCycleRoot(true);
		contentPane.setFocusTraversalPolicyProvider(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Message Panel
		JPanel message_panel = new JPanel();

		txt_message = new JTextField();
		txt_message.setText("");
		txt_message.setHorizontalAlignment(SwingConstants.LEFT);
		txt_message.setEditable(false);
		txt_message.setFocusable(false);
		txt_message.setBorder(BorderFactory.createEmptyBorder());
		txt_message.setColumns(65);

		JLabel main_label_score1 = new JLabel("Score:");
		main_label_score1.setFocusable(false);

		txt_score1 = new JTextField();
		txt_score1.setText("0");
		txt_score1.setEditable(false);
		txt_score1.setFocusable(false);
		txt_score1.setBorder(BorderFactory.createEmptyBorder());
		txt_score1.setColumns(5);

		JLabel main_label_maxScore1 = new JLabel("Top Score:");
		main_label_maxScore1.setFocusable(false);

		txt_maxScore1 = new JTextField();
		txt_maxScore1.setText(controller.maxScore + "");
		txt_maxScore1.setEditable(false);
		txt_maxScore1.setFocusable(false);
		txt_maxScore1.setBorder(BorderFactory.createEmptyBorder());
		txt_maxScore1.setColumns(5);

		message_panel.setFocusable(false);
		GroupLayout mg_group = new GroupLayout(message_panel);
		mg_group.setHorizontalGroup(
				mg_group.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.LEADING, mg_group.createSequentialGroup()
								.addComponent(txt_message, GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
								.addContainerGap()
								.addComponent(main_label_score1, GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
								.addContainerGap()
								.addComponent(txt_score1, GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
								.addContainerGap()
								.addComponent(main_label_maxScore1, GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
								.addContainerGap()
								.addComponent(txt_maxScore1, GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
								.addContainerGap()
						)
		);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setFocusCycleRoot(true);
		panel.setFocusTraversalPolicyProvider(true);
		gamePanel.setFocusTraversalPolicyProvider(true);
		gamePanel.setFocusCycleRoot(true);
		
		
		gamePanel.setSize(Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT);
		gamePanel.setLayout(new BorderLayout(0, 0));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFocusable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(message_panel, GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
						.addGap(2))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
						.addGap(10))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 795, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(message_panel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 205, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel lable = new JPanel();
		lable.setFocusable(false);
		lable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_control = new JPanel();
		panel_control.setFocusable(false);
		panel_control.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_set = new JPanel();
		panel_set.setFocusable(false);
		panel_set.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_display = new JPanel();
		panel_display.setFocusable(false);
		panel_display.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_set, GroupLayout.PREFERRED_SIZE,
							302, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_display, GroupLayout.PREFERRED_SIZE,
								216, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_control, 0, 0, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lable, GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_set, 
								GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(panel_display, 
									GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_control, 
									GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
						.addComponent(lable, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lable_score = new JLabel("Score:");
		lable_score.setFocusable(false);
		lable_score.setHorizontalAlignment(SwingConstants.LEFT);
		lable_score.setHorizontalTextPosition(SwingConstants.CENTER);
		lable_score.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		txt_score = new JTextField();
		txt_score.setText("0");
		txt_score.setEditable(false);
		txt_score.setFocusable(false);
		txt_score.setColumns(10);
		
		JLabel label_maxScore = new JLabel("Top score:");
		label_maxScore.setFocusable(false);
		
		txt_maxScore = new JTextField();
		txt_maxScore.setText(controller.maxScore + "");
		txt_maxScore.setEditable(false);
		txt_maxScore.setFocusable(false);
		txt_maxScore.setColumns(10);
		
		JLabel label_speed = new JLabel("Speed:");
		label_speed.setFocusable(false);
		
		txt_speed = new JTextField();
		txt_speed.setText(snake.speed + "ms / grid");
		txt_speed.setEditable(false);
		txt_speed.setFocusable(false);
		lable_score.setLabelFor(txt_speed);
		txt_speed.setColumns(10);
		GroupLayout gl_panel_display = new GroupLayout(panel_display);
		gl_panel_display.setHorizontalGroup(
			gl_panel_display.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_display.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_display.createParallelGroup(Alignment.LEADING)
						.addComponent(lable_score, 
								GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_maxScore, 
								GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_speed, 
								GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_display.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txt_maxScore, 
								GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(txt_speed, 
								GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(txt_score))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_display.setVerticalGroup(
			gl_panel_display.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_display.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_panel_display.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_display.createSequentialGroup()
							.addComponent(txt_score, GroupLayout.PREFERRED_SIZE,
									21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txt_maxScore, GroupLayout.PREFERRED_SIZE, 
									GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txt_speed, GroupLayout.PREFERRED_SIZE,
									GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(16))
						.addGroup(gl_panel_display.createSequentialGroup()
							.addComponent(lable_score, GroupLayout.PREFERRED_SIZE,
									18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_maxScore, GroupLayout.PREFERRED_SIZE,
									25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_speed)
							.addGap(21))))
		);
		panel_display.setLayout(gl_panel_display);
		
		JLabel label_set = new JLabel("Setting items:");
		label_set.setFocusable(false);
		
		JSeparator separator = new JSeparator();
		
		JCheckBox checkBox_isGriding = new JCheckBox("Show grid");
		
		checkBox_isGriding.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_isGriding.isSelected()) {
					ground.drawGriding();
				}else {
					ground.notDrawGriding();
				}
				
			}
		});
		checkBox_isGriding.setFocusable(false);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel label_isGriding = new JLabel(" Display the grid:");
		label_isGriding.setFocusable(false);
		
		JSeparator separator_2 = new JSeparator();
		
		JPanel panel_setMap = new JPanel();
		panel_setMap.setFocusable(false);
		
		JPanel panel_setSpeed = new JPanel();
		panel_setSpeed.setFocusable(false);
		panel_setSpeed.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GroupLayout gl_panel_set = new GroupLayout(panel_set);
		gl_panel_set.setHorizontalGroup(
			gl_panel_set.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_set.createSequentialGroup()
					.addGroup(gl_panel_set.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_set.createSequentialGroup()
							.addGap(10)
							.addComponent(label_set))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addGap(20)
							.addComponent(label_isGriding)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_isGriding))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addGap(20)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE,
									222, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addGap(20)
							.addComponent(separator_2, GroupLayout.PREFERRED_SIZE,
									224, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addGap(14)
							.addComponent(panel_setSpeed, GroupLayout.PREFERRED_SIZE,
									264, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_setMap, GroupLayout.DEFAULT_SIZE,
									GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(10))
				.addGroup(gl_panel_set.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_set.setVerticalGroup(
			gl_panel_set.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_set.createSequentialGroup()
					.addGap(10)
					.addComponent(label_set)
					.addGap(10)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_set.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_isGriding)
						.addComponent(checkBox_isGriding))
					.addGap(12)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_setMap, GroupLayout.PREFERRED_SIZE,
							37, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE,
							2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_setSpeed, GroupLayout.DEFAULT_SIZE,
							34, Short.MAX_VALUE)
					.addGap(14))
		);
		
		JLabel label_5 = new JLabel("Speed:");
		label_5.setFocusable(false);
		
		JRadioButton radioButton_speed1 = new JRadioButton("Slow");
		radioButton_speed1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					snake.speed = 500;
					txt_speed.setText(snake.speed + " ms / grid");
			}
		});
		radioButton_speed1.setSelected(true);
		radioButton_speed1.setFocusable(false);

		JRadioButton radioButton_speed2 = new JRadioButton("Medium");
		radioButton_speed2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					snake.speed = 300;
					txt_speed.setText(snake.speed + " ms / grid");
			}
		});
		radioButton_speed2.setFocusable(false);
		
		JRadioButton radioButton_speed3 = new JRadioButton("Fast");
		radioButton_speed3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					snake.speed = 200;
					txt_speed.setText(snake.speed + " ms / grid");
			}
		});
		radioButton_speed3.setFocusable(false);
		
		ButtonGroup groupSpeed = new ButtonGroup();
		groupSpeed.add(radioButton_speed1);
		groupSpeed.add(radioButton_speed2);
		groupSpeed.add(radioButton_speed3);



		
		panel_setSpeed.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
		panel_setSpeed.add(label_5);
		panel_setSpeed.add(radioButton_speed1);
		panel_setSpeed.add(radioButton_speed2);
		panel_setSpeed.add(radioButton_speed3);
		
		
		JLabel label_setMap = new JLabel("Show map:");
		label_setMap.setFocusable(false);
		
		JRadioButton radioButton_map1 = new JRadioButton("Map1");
	
		radioButton_map1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ground.MAP = 1;
				ground.init();
			}
		});
		radioButton_map1.setSelected(true);
		
		radioButton_map1.setFocusable(false);
		
		JRadioButton radioButton_map2 = new JRadioButton("Map2");
		radioButton_map2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//After clicking the mouse, set to map 2
				ground.MAP = 2;
				//Reinitialize the map
				ground.init();
			}
		});
		radioButton_map2.setFocusable(false);

		JRadioButton radioButton_map3 = new JRadioButton("Trees");
		radioButton_map3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ground.MAP = 3;
				ground.init();
			}
		});
		radioButton_map3.setFocusable(false);
		
		ButtonGroup groupMap = new ButtonGroup();
		groupMap.add(radioButton_map1);
		groupMap.add(radioButton_map2);
		groupMap.add(radioButton_map3);
		
		panel_setMap.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_setMap.add(label_setMap);
		panel_setMap.add(radioButton_map1);
		panel_setMap.add(radioButton_map2);
		panel_setMap.add(radioButton_map3);
		panel_set.setLayout(gl_panel_set);
		
		JButton button_pause = new JButton("S/P");
		button_pause.setFocusable(false);
		button_pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.changePause();
			}
		});
		button_pause.setFocusPainted(false);

		JButton button_newGame = new JButton("New game");
		button_newGame.setFocusable(false);
		button_newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.newGame();
				
			}
		});
		button_newGame.setFocusPainted(false);
		GroupLayout gl_panel_control = new GroupLayout(panel_control);
		gl_panel_control.setHorizontalGroup(
			gl_panel_control.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_control.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(button_newGame, GroupLayout.PREFERRED_SIZE,
							95, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(button_pause, GroupLayout.PREFERRED_SIZE,
							94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_control.setVerticalGroup(
			gl_panel_control.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_control.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_control.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_newGame, GroupLayout.PREFERRED_SIZE,
								44, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_pause, GroupLayout.PREFERRED_SIZE, 
								44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_control.setLayout(gl_panel_control);
		
		JLabel lblNewLabel = new JLabel("Description:");
		lblNewLabel.setFocusable(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JLabel label = new JLabel("Use arrow keys to control direction");
		label.setFocusable(false);

		JLabel label_A = new JLabel("Press A to slow down");
		label_A.setFocusable(false);

		JLabel label_D = new JLabel("Press D to speed up");
		label_D.setFocusable(false);
		
		JLabel label_S = new JLabel("Press S to pause/continue");
		label_S.setFocusable(false);
		
		JLabel label_W = new JLabel("Press W to restart");
		label_W.setFocusable(false);
		
		JLabel label_2 = new JLabel("Tree map generate tree when eat food");
		label_2.setFocusable(false);

		/*label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setInheritsPopupMenu(false);
		label_2.setFocusTraversalKeysEnabled(false);
		label_2.setAlignmentX(Component.CENTER_ALIGNMENT);*/

		GroupLayout gl_lable = new GroupLayout(lable);
		gl_lable.setHorizontalGroup(
			gl_lable.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_lable.createSequentialGroup()
					.addGroup(gl_lable.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
								80, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_lable.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_lable.createParallelGroup(Alignment.LEADING)
								.addComponent(label,
										GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
								.addComponent(label_A)
								.addComponent(label_S)
								.addComponent(label_D)
								.addComponent(label_W)
								.addComponent(label_2)
							)))
					.addContainerGap())
		);
		gl_lable.setVerticalGroup(
			gl_lable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lable.createSequentialGroup()
					.addGap(2)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
							26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE,
							26, GroupLayout.PREFERRED_SIZE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE,
							26, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_A, GroupLayout.PREFERRED_SIZE,
							26, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_D, GroupLayout.PREFERRED_SIZE,
							26, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_W, GroupLayout.PREFERRED_SIZE,
							26, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_S,GroupLayout.PREFERRED_SIZE,
							26, GroupLayout.PREFERRED_SIZE)
				)
		);
		lable.setLayout(gl_lable);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		//Add a listener to the game panel and snake
		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);
		//Start a new thread to update the score
		controller.startRefresh(new Thread(new refresh()));
		//Start the game
		controller.beginGame();
	}
	//Create a thread to keep refreshing the score
	public class refresh implements Runnable{
		@Override
		public void run() {
			//The snake circulates when it is alive
			while(!snake.isDie) {
				txt_score1.setText(controller.score + "");
				txt_maxScore1.setText(controller.maxScore + "");
				txt_message.setText(controller.message);

//				txt_score.setText(
//						controller.isCountingDown
//								? "Count Down: " + controller.countdownNumber
//								: "" + controller.score);

				txt_score1.setForeground(controller.isCountingDown ? Color.red : Color.BLACK);
				txt_score.setForeground(controller.isCountingDown ? Color.red : Color.BLACK);

				txt_message.setForeground(controller.isCountingDown ? Color.red : Color.BLACK);
				txt_speed.setText(snake.speed + " ms / grid");
				txt_maxScore.setText(controller.maxScore + "");
				try {
					Thread.sleep(snake.speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
