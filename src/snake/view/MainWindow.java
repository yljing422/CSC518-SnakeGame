package snake.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
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
	private ImageIcon optionButton;  
	private ImageIcon onButton;
	private ImageIcon offButton;
	private ImageIcon gridLabel;
	private ImageIcon mapLabel;
	private ImageIcon map1Label;
	private ImageIcon map2Label;
	private ImageIcon treeLabel;
	private ImageIcon newGame;
	private ImageIcon start;
	private ImageIcon pause;
	private ImageIcon bg2;
	private ImageIcon bg1;
	private ImageIcon bg3;
	private ImageIcon bg4;
	private Image bg1_img;
	private Image bg2_img;
	private Image bg3_img;
	private Image bg4_img;
	Boolean isPause = false;
	    
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
		
		optionButton = new ImageIcon("images/option.png");

		JPanel panel_1 = new JPanel();
		
		panel_1.setFocusable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 795, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 205, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		bg4 = new ImageIcon("images/background4.png");
		bg4_img = bg4.getImage();
		JPanel lable = new JPanel() {
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg4_img, 0, -3, null);
            }
		};
		lable.setFocusable(false);
		lable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		bg3 = new ImageIcon("images/background3.png");
		bg3_img = bg3.getImage();
		JPanel panel_control = new JPanel(){
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg3_img, 0, 0, null);
            }
		};
		panel_control.setFocusable(false);
		panel_control.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		bg2 = new ImageIcon("images/background2.png");
		bg2_img = bg2.getImage();
		JPanel panel_set = new JPanel(){
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg2_img, 0, 0, null);
            }
        };
		
		panel_set.setFocusable(false);
		panel_set.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		bg1 = new ImageIcon("images/background1.png");
		bg1_img = bg1.getImage();
		JPanel panel_display = new JPanel(){
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg1_img, 0, 0, null);
            }
		};
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
					.addComponent(lable, GroupLayout.DEFAULT_SIZE,
							GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
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
							.addComponent(panel_control, 
									GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
						.addComponent(lable, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
					.addContainerGap())
		);

		JPanel panel_setSpeed = new JPanel();
		panel_setSpeed.setFocusable(false);
		panel_setSpeed.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		JLabel label_5 = new JLabel("SPEED:");
		label_5.setFont(new Font("American Typewriter", Font.BOLD, 13));
		label_5.setForeground(Color.GREEN);
		label_5.setFocusable(false);

		JLabel label_slow_stars = new JLabel(new ImageIcon("images/slow_stars.png"));
		JLabel label_medium_stars = new JLabel(new ImageIcon("images/medium_stars.png"));
		JLabel label_fast_stars = new JLabel(new ImageIcon("images/fast_stars.png"));
		
		JRadioButton radioButton_speed1 = new JRadioButton("Slow");
		radioButton_speed1.setFont(new Font("American Typewriter", Font.BOLD, 13));
		radioButton_speed1.setForeground(Color.GREEN);
		radioButton_speed1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					snake.speed = 300;
					txt_speed.setText(snake.speed + " ms/grid");
			}
		});
		radioButton_speed1.setSelected(true);
		radioButton_speed1.setFocusable(false);
		
		JRadioButton radioButton_speed2 = new JRadioButton("Medium");
		radioButton_speed2.setFont(new Font("American Typewriter", Font.BOLD, 13));
		radioButton_speed2.setForeground(Color.GREEN);
		radioButton_speed2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					snake.speed = 200;
					txt_speed.setText(snake.speed + " ms/grid");
			}
		});
		radioButton_speed2.setFocusable(false);
		
		JRadioButton radioButton_speed3 = new JRadioButton("Fast");
		radioButton_speed3.setFont(new Font("American Typewriter", Font.BOLD, 13));
		radioButton_speed3.setForeground(Color.GREEN);
		radioButton_speed3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					snake.speed = 100;
					txt_speed.setText(snake.speed + " ms/grid");
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
		label_speed.setFont(new Font("American Typewriter", Font.BOLD, 14));
		label_speed.setForeground(Color.blue);
		label_speed.setFocusable(false);
		
		txt_speed = new JTextField();
		txt_speed.setText(snake.speed + "ms/grid");
		txt_speed.setFont(new Font("American Typewriter", Font.ITALIC, 12));
		txt_speed.setForeground(Color.blue);
		txt_speed.setEditable(false);
		txt_speed.setFocusable(false);
		lable_score.setLabelFor(txt_speed);
		txt_speed.setColumns(10);
		GroupLayout gl_panel_display = new GroupLayout(panel_display);
		gl_panel_display.setHorizontalGroup(
			gl_panel_display.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_display.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel_display.createParallelGroup(Alignment.LEADING)
						.addComponent(radioButton_speed1, 
								GroupLayout.DEFAULT_SIZE, 50, GroupLayout.DEFAULT_SIZE)
						.addComponent(radioButton_speed2, 
								GroupLayout.DEFAULT_SIZE, 50, GroupLayout.DEFAULT_SIZE)
						.addComponent(radioButton_speed3, 
								GroupLayout.DEFAULT_SIZE, 50, GroupLayout.DEFAULT_SIZE)
						.addComponent(label_speed, 
								GroupLayout.DEFAULT_SIZE, 75, GroupLayout.DEFAULT_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_display.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_slow_stars,  
								GroupLayout.DEFAULT_SIZE, 75, GroupLayout.DEFAULT_SIZE)
						.addComponent(txt_speed, 
								GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addComponent(label_fast_stars, GroupLayout.DEFAULT_SIZE, 75, GroupLayout.DEFAULT_SIZE)
						.addComponent(label_medium_stars, GroupLayout.DEFAULT_SIZE, 75, GroupLayout.DEFAULT_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_display.setVerticalGroup(
			gl_panel_display.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_display.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addGroup(gl_panel_display.createParallelGroup(Alignment.TRAILING)
							.addGap(10)
							.addGroup(gl_panel_display.createSequentialGroup()
							    //.addContainerGap(50, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(txt_speed, GroupLayout.PREFERRED_SIZE,
										18, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label_slow_stars, GroupLayout.PREFERRED_SIZE,
										18, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								//.addGap(10)
								.addComponent(label_medium_stars, GroupLayout.PREFERRED_SIZE, 
										18, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label_fast_stars, GroupLayout.PREFERRED_SIZE, 
										18, GroupLayout.PREFERRED_SIZE)							
							    .addGap(20))
						    .addGroup(gl_panel_display.createSequentialGroup()
								.addGap(5)
								.addComponent(label_speed, GroupLayout.PREFERRED_SIZE,
										18, GroupLayout.PREFERRED_SIZE)
								.addComponent(radioButton_speed1, GroupLayout.DEFAULT_SIZE,
										18, GroupLayout.DEFAULT_SIZE)
								.addGap(8)
								.addComponent(radioButton_speed2, GroupLayout.PREFERRED_SIZE,
										18, GroupLayout.PREFERRED_SIZE)
								.addGap(8)
								.addComponent(radioButton_speed3, GroupLayout.PREFERRED_SIZE,
										18, GroupLayout.PREFERRED_SIZE)
								.addGap(16))))
		);
		panel_display.setLayout(gl_panel_display);
		
		JLabel label_set = new JLabel(optionButton, JLabel.CENTER);
		label_set.setFocusable(false);
		
		JSeparator separator = new JSeparator();
		
		onButton = new ImageIcon("images/on.png");
		offButton = new ImageIcon("images/off.png");
		JToggleButton checkBox_isGriding = new JToggleButton(offButton);
		
		checkBox_isGriding.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_isGriding.isSelected()) {
					checkBox_isGriding.setIcon(onButton);
					ground.drawGriding();
				}else {
					checkBox_isGriding.setIcon(offButton);
					ground.notDrawGriding();
				}
				
			}
		});
		checkBox_isGriding.setFocusable(false);
		
		JSeparator separator_1 = new JSeparator();
		
		gridLabel = new ImageIcon("images/show_grid.png");
		JLabel label_isGriding = new JLabel(gridLabel);
		label_isGriding.setFocusable(false);
		
		JSeparator separator_2 = new JSeparator();
		
		JPanel panel_setMap1 = new JPanel();
		JPanel panel_setMap2 = new JPanel();
		panel_setMap1.setFocusable(false);
		panel_setMap2.setFocusable(false);
		
		GroupLayout gl_panel_set = new GroupLayout(panel_set);
		gl_panel_set.setHorizontalGroup(
			gl_panel_set.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_set.createSequentialGroup()
					.addGroup(gl_panel_set.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_set.createSequentialGroup()
							.addGap(100)
							.addComponent(label_set))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addGap(40)
							.addComponent(label_isGriding)
							.addGap(20)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox_isGriding))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addGap(45)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE,
									2, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addComponent(separator_2, GroupLayout.PREFERRED_SIZE,
									2, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_set.createSequentialGroup())
						.addGroup(gl_panel_set.createSequentialGroup()
							.addContainerGap()
						    .addGap(40)
							.addComponent(panel_setMap1, GroupLayout.DEFAULT_SIZE,
									GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_set.createSequentialGroup()
							.addContainerGap()
							.addGap(40)
							.addComponent(panel_setMap2, GroupLayout.DEFAULT_SIZE,
									GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_set.createSequentialGroup()
						.addContainerGap())))
				.addGroup(gl_panel_set.createSequentialGroup()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
		);
		gl_panel_set.setVerticalGroup(
			gl_panel_set.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_set.createSequentialGroup()
					.addGap(15)
					.addComponent(label_set)
					.addGap(5)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_set.createParallelGroup()
					    .addGap(5)
					    .addComponent(label_isGriding)
						.addComponent(checkBox_isGriding))
					.addComponent(separator_2, GroupLayout.DEFAULT_SIZE,
							GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(panel_setMap1, GroupLayout.DEFAULT_SIZE,
							0, 0)
					.addComponent(separator_1, GroupLayout.DEFAULT_SIZE,
							0, 0)
					.addComponent(panel_setMap2, GroupLayout.DEFAULT_SIZE,
							0, 0)
					.addComponent(separator_1, GroupLayout.DEFAULT_SIZE,
							0, GroupLayout.DEFAULT_SIZE))
		);
		
		mapLabel = new ImageIcon("images/show_map.png");
		JLabel label_setMap = new JLabel(mapLabel);
		label_setMap.setFocusable(false);
		
		map1Label = new ImageIcon("images/map1.png");
		JLabel label_map1 = new JLabel(map1Label);
		JRadioButton radioButton_map1 = new JRadioButton();
	
		radioButton_map1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ground.MAP = 1;
				ground.init();
			}
		});
		radioButton_map1.setSelected(true);
		
		radioButton_map1.setFocusable(false);
		
		map2Label = new ImageIcon("images/map2.png");
		JLabel label_map2 = new JLabel(map2Label);
		JRadioButton radioButton_map2 = new JRadioButton();
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

		treeLabel = new ImageIcon("images/treeLabel.png");
		JLabel label_tree = new JLabel(treeLabel);
		JRadioButton radioButton_map3 = new JRadioButton();
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
	
		panel_setMap1.add(label_setMap);
		panel_setMap2.add(radioButton_map1);
		panel_setMap2.add(label_map1);
		panel_setMap2.add(radioButton_map2);
		panel_setMap2.add(label_map2);
		panel_setMap1.add(radioButton_map3);
		panel_setMap1.add(label_tree);
		panel_set.setLayout(gl_panel_set);
        
		start = new ImageIcon("images/start.png");
		pause = new ImageIcon("images/pause.png");
		JToggleButton button_pause = new JToggleButton(start);
		button_pause.setFocusable(false);
		button_pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    isPause = !isPause;
				if (isPause) {
					button_pause.setIcon(pause);
				} else {
					button_pause.setIcon(start);
				}
				snake.changePause();
			}
		});
		button_pause.setFocusPainted(false);
		

		newGame = new ImageIcon("images/newGame.png");
		JButton button_newGame = new JButton(newGame);
		button_newGame.setFocusable(false);
		button_newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.newGame();
				button_pause.setIcon(start);
				isPause = false;
				
			}
		});
		button_newGame.setFocusPainted(false);
		GroupLayout gl_panel_control = new GroupLayout(panel_control);
		gl_panel_control.setHorizontalGroup(
			gl_panel_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_control.createSequentialGroup()
					.addContainerGap()
					.addGap(30)
					.addComponent(button_newGame, GroupLayout.PREFERRED_SIZE,
							95, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(button_pause, GroupLayout.PREFERRED_SIZE,
							40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_control.setVerticalGroup(
			gl_panel_control.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_control.createSequentialGroup()
					.addContainerGap()
					.addGap(20)
					.addGroup(gl_panel_control.createParallelGroup(Alignment.LEADING)
						.addComponent(button_newGame, GroupLayout.PREFERRED_SIZE,
								44, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_pause, GroupLayout.PREFERRED_SIZE, 
								44, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_control.setLayout(gl_panel_control);
		
		JLabel lblNewLabel = new JLabel("INSTRUCTIONS");
		lblNewLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFocusable(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JLabel label = new JLabel("Use arrow keys to control direction");
		label.setFont(new Font("American Typewriter", Font.PLAIN, 11));
		label.setFocusable(false);

		JLabel label_A = new JLabel("Press A to slow down");
		label_A.setFont(new Font("American Typewriter", Font.PLAIN, 11));
		label_A.setFocusable(false);

		JLabel label_D = new JLabel("Press D to speed up");
		label_D.setFont(new Font("American Typewriter", Font.PLAIN, 11));
		label_D.setFocusable(false);
		
		JLabel label_S = new JLabel("Press S to pause/continue");
		label_S.setFont(new Font("American Typewriter", Font.PLAIN, 11));
		label_S.setFocusable(false);
		
		JLabel label_W = new JLabel("Press W to restart");
		label_W.setFont(new Font("American Typewriter", Font.PLAIN, 11));
		label_W.setFocusable(false);
		
		JLabel label_2 = new JLabel("Tree map generate tree when eat food");
		label_2.setFont(new Font("American Typewriter", Font.PLAIN, 11));
		label_2.setFocusable(false);

		JLabel label_C = new JLabel("Press C to cheat, only 3 chances");
		label_C.setFont(new Font("American Typewriter", Font.PLAIN, 11));
		label_C.setFocusable(false);

		/*label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setInheritsPopupMenu(false);
		label_2.setFocusTraversalKeysEnabled(false);
		label_2.setAlignmentX(Component.CENTER_ALIGNMENT);*/

		GroupLayout gl_lable = new GroupLayout(lable);
		gl_lable.setHorizontalGroup(
			gl_lable.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_lable.createSequentialGroup()
					.addGroup(gl_lable.createParallelGroup(Alignment.CENTER)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
								120, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(label_C)
							)))
					.addContainerGap())
		);
		gl_lable.setVerticalGroup(
			gl_lable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_lable.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
							21, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE,
							21, GroupLayout.PREFERRED_SIZE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE,
							21, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_A, GroupLayout.PREFERRED_SIZE,
							21, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_D, GroupLayout.PREFERRED_SIZE,
							21, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_W, GroupLayout.PREFERRED_SIZE,
							21, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_S,GroupLayout.PREFERRED_SIZE,
							22, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_C,GroupLayout.PREFERRED_SIZE,
							22, GroupLayout.PREFERRED_SIZE)
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
				txt_score.setText(
						controller.isCountingDown
								? "Count Down: " + controller.countdownNumber
								: "" + controller.score);
				txt_score.setForeground(controller.isCountingDown ? Color.red : Color.BLACK);
				txt_speed.setText(snake.speed + " ms/grid");
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
