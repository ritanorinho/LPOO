package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import dkeep.cli.GuiInteraction;
import dkeep.cli.UserInteraction;
import dkeep.logic.Hero;
import dkeep.logic.Ogre;

import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;

public class PlayPanel {

	public JFrame frame;
	private String[][] map;
	private GamePanel gameArea;
	public GuiInteraction game;
	private JButton mainMenu;
	private JLabel lblYou;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;
	private String guard="";
	private int ogresNumber=1;
	private JTextField numberOgres;
	private JComboBox guardPersonality;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					PlayPanel window = new PlayPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public PlayPanel() throws IOException {
		this.game=new GuiInteraction();
		initialize();
	}

	/** 
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		GamePanel.loadImages();
		frame = new JFrame();

		frame.setBounds(100, 100, 450, 313);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 2));

		JPanel settings = new JPanel();
		GridBagLayout gbl_settings = new GridBagLayout();
		gbl_settings.columnWidths = new int[]{108, 0, 108, 0};
		gbl_settings.rowHeights = new int[]{68, 0, 0, 0, 0, 68, 0};
		gbl_settings.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_settings.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		settings.setLayout(gbl_settings);
		JLabel lblNumberOfOgres = new JLabel("Number of ogres");
		GridBagConstraints gbc_lblNumberOfOgres = new GridBagConstraints();
		gbc_lblNumberOfOgres.fill = GridBagConstraints.BOTH;
		gbc_lblNumberOfOgres.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfOgres.gridx = 0;
		gbc_lblNumberOfOgres.gridy = 1;
		settings.add(lblNumberOfOgres, gbc_lblNumberOfOgres);
		//frame.getContentPane().add(lblNumberOfOgres);

		numberOgres = new JTextField();
		GridBagConstraints gbc_numberOgres = new GridBagConstraints();
		gbc_numberOgres.gridwidth = 2;
		gbc_numberOgres.fill = GridBagConstraints.BOTH;
		gbc_numberOgres.insets = new Insets(0, 0, 5, 0);
		gbc_numberOgres.gridx = 2;
		gbc_numberOgres.gridy = 1;
		settings.add(numberOgres, gbc_numberOgres);
		//frame.getContentPane().add(numberOgres);

		numberOgres.setColumns(10);
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		GridBagConstraints gbc_lblGuardPersonality = new GridBagConstraints();
		gbc_lblGuardPersonality.fill = GridBagConstraints.BOTH;
		gbc_lblGuardPersonality.insets = new Insets(0, 0, 5, 5);
		gbc_lblGuardPersonality.gridx = 0;
		gbc_lblGuardPersonality.gridy = 2;
		settings.add(lblGuardPersonality, gbc_lblGuardPersonality);
		guardPersonality = new JComboBox();
		guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardPersonality.setSelectedIndex(0);
		guardPersonality.setToolTipText("");
		GridBagConstraints gbc_guardPersonality = new GridBagConstraints();
		gbc_guardPersonality.gridwidth = 2;
		gbc_guardPersonality.insets = new Insets(0, 0, 5, 0);
		gbc_guardPersonality.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardPersonality.gridx = 2;
		gbc_guardPersonality.gridy = 2;
		settings.add(guardPersonality, gbc_guardPersonality);
		
		guardPersonality.setEnabled(false);
	
		guardPersonality.setEnabled(true);
		frame.getContentPane().add(settings);
		JPanel moveButtons = new JPanel();
		GridBagLayout gbl_moveButtons = new GridBagLayout();
		gbl_moveButtons.columnWidths = new int[]{101, 0, 0, 0, 106, 0};
		gbl_moveButtons.rowHeights = new int[]{38, 0, 0, 0, 0, 0, 0, 43, 0};
		gbl_moveButtons.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_moveButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		moveButtons.setLayout(gbl_moveButtons);
		frame.getContentPane().add(moveButtons);
				
						btnUp = new JButton("Up");
						GridBagConstraints gbc_btnUp = new GridBagConstraints();
						gbc_btnUp.fill = GridBagConstraints.BOTH;
						gbc_btnUp.insets = new Insets(0, 0, 5, 5);
						gbc_btnUp.gridx = 2;
						gbc_btnUp.gridy = 3;
						moveButtons.add(btnUp, gbc_btnUp);
						btnUp.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								buttonPressed("U");
							}
						});
		
				btnLeft = new JButton("Left");
				GridBagConstraints gbc_btnLeft = new GridBagConstraints();
				gbc_btnLeft.fill = GridBagConstraints.BOTH;
				gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
				gbc_btnLeft.gridx = 1;
				gbc_btnLeft.gridy = 4;
				moveButtons.add(btnLeft, gbc_btnLeft);
				btnLeft.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonPressed("L");
					}
				});
		btnRight = new JButton("Right");
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnRight.fill = GridBagConstraints.BOTH;
		gbc_btnRight.gridx = 3;
		gbc_btnRight.gridy = 4;
		moveButtons.add(btnRight, gbc_btnRight);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				buttonPressed("R");
			}
		});
		
				btnDown = new JButton("Down");
				btnDown.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonPressed("D");
					}
				});
				GridBagConstraints gbc_btnDown = new GridBagConstraints();
				gbc_btnDown.insets = new Insets(0, 0, 5, 5);
				gbc_btnDown.fill = GridBagConstraints.BOTH;
				gbc_btnDown.gridx = 2;
				gbc_btnDown.gridy = 5;
				moveButtons.add(btnDown, gbc_btnDown);
		JPanel exitButtons= new JPanel();
		gameArea = new GamePanel();
		gameArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		gameArea.setLayout(new GridLayout(1, 0, 0, 0));
		frame.getContentPane().add(gameArea);
		//frame.getContentPane().add(lblYou);
		frame.getContentPane().add(exitButtons);
		GridBagLayout gbl_exitButtons = new GridBagLayout();
		gbl_exitButtons.columnWidths = new int[]{217, 0};
		gbl_exitButtons.rowHeights = new int[]{30, 0, 0, 22, 0, 23, 0, 0};
		gbl_exitButtons.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_exitButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		exitButtons.setLayout(gbl_exitButtons);
								mainMenu = new JButton("Restart");
								GridBagConstraints gbc_Restart = new GridBagConstraints();
								gbc_Restart.insets = new Insets(0, 0, 5, 0);
								gbc_Restart.gridx = 0;
								gbc_Restart.gridy = 2;
								exitButtons.add(mainMenu, gbc_Restart);
								mainMenu.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										MenuPanel other=null;
										try {
											other = new MenuPanel();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										other.frame.setVisible(true);
										frame.setVisible(false);
										gameArea.requestFocusInWindow();
									}
								});
						
						
						
						
								lblYou = new JLabel("You can start a new game");
								GridBagConstraints gbc_lblYou = new GridBagConstraints();
								gbc_lblYou.insets = new Insets(0, 0, 5, 0);
								gbc_lblYou.gridx = 0;
								gbc_lblYou.gridy = 5;
								exitButtons.add(lblYou, gbc_lblYou);
		gameArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressedKey(e);
			}
		});
		gameArea.requestFocusInWindow();
		JPanel game_1 = new JPanel();
		switch (this.guard) {
		case "Rookie":
			guardPersonality.setSelectedIndex(0);
			setGuard("Rookie");
			break;
		case "Drunken":
			guardPersonality.setSelectedIndex(1);
			setGuard("Drunken");
			break;
		case "Suspicious":
			guardPersonality.setSelectedIndex(2);
			setGuard("Suspicious");
			break;
		default:
			guardPersonality.setSelectedIndex(0);
		}

		newGamePressed();
	}

	private void newGamePressed() {
		this.game.start(guard,1);
		guardPersonality.setEnabled(false);
		gameArea.setMaze(game.getGame().getMap());
		lblYou.setText("You can play now");
	}
	private void buttonPressed(String move) {
		if (!game.checkGameStatus(move)) {
			JOptionPane.showMessageDialog(frame, "GAME OVER!");
			System.exit(0);
		}
		if (game.getGame().gameWon) {
			JOptionPane.showMessageDialog(frame, "GAME WON!");
			System.exit(0);
		}

		gameArea.setMaze(game.getGame().getMap());
		gameArea.requestFocusInWindow();
	}

	public void pressedKey(KeyEvent e) {
		switch(e.getKeyCode()){

		case KeyEvent.VK_LEFT:
			buttonPressed("L");
			break;
		case KeyEvent.VK_RIGHT:  
			buttonPressed("R");
			break;
		case KeyEvent.VK_UP:  
			buttonPressed("U");
			break;
		case KeyEvent.VK_DOWN: 
			buttonPressed("D");
			break;
		} 
	}
	public void setGuard(String guard) {
		this.guard=guard;
	}

	public void setOgresNumber(int ogresNumber) {
		this.ogresNumber=ogresNumber;
	}
	public void editableLevel(String[][]map) {
		this.map=map;
		game.getGame().setMap(map);
		for (int i =0;i<map.length;i++) {
			for (int j = 0; j<map.length;j++)
				switch (map[i][j]) {
				case "H":
					Hero hero =new Hero();
					hero.setX(i);
					hero.setY(j);
					game.getGame().setHero(hero);
					break;
				case "O":
					Ogre ogre =new Ogre();
					ogre.setX(i);
					ogre.setY(j);
					game.getGame().setOgre(ogre);
					break;
				}
		}
		gameArea.setMaze(map);
	}
}
