package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Classes.Characters;
import Classes.Mage;
import Classes.Rogue;
import Classes.Warrior;

@SuppressWarnings("serial")
public class Fight extends JPanel implements ActionListener {

	int playerX, playerY, fightNum, playerType, opponentType;
	private Game parent;
	private Characters player;
	@SuppressWarnings("unused")
	private GameScreen gameScreen;
	private JButton attackButton = new JButton("Attack");
	private JButton useSpecialButton = new JButton("Use Special Ability");
	private JButton useItemButton = new JButton("Use Item");
	private JProgressBar playerHealthBar, opponentHealthBar;
	ImageIcon warriorIcon = new ImageIcon("assets/warrior.png");
	ImageIcon mageIcon = new ImageIcon("assets/mage.png");
	ImageIcon rogueIcon = new ImageIcon("assets/rogue.png");
	private JTextArea fightEventsTextArea = new JTextArea(25,15);
	private final static String newline = "\n";
	private Boolean playerDead, opponentDead;
	private int[][] map;
	private Characters opponent;
	
	public Fight(int playerX, int playerY, int fightNum, Game parent, Characters player, GameScreen gameScreen, int[][] map) {
		this.playerX = playerX;
		this.playerY = playerY;
		this.fightNum = fightNum;
		this.player = player;
		this.parent = parent;
		this.gameScreen = gameScreen;
		this.map = map;
		
		createOpponent();
		
		
		playerType = player.getType();
		
		setLayout(new BorderLayout());
		
		JPanel playerPanel = new JPanel(new BorderLayout());

		playerPanel.add(new JLabel(player.getName()), BorderLayout.NORTH);
		
		switch (playerType) {
		case 0:
			playerPanel.add(new JLabel(warriorIcon));
			break;
		case 1:
			playerPanel.add(new JLabel(mageIcon));
			break;
		case 2: 
			playerPanel.add(new JLabel(rogueIcon));
			break;
		default: 
			playerPanel.add(new JLabel(warriorIcon));
			break;
		}
		
		playerPanel.add(playerHealthBar = new JProgressBar(0, 30), BorderLayout.SOUTH);
		playerHealthBar.setStringPainted(true);
		playerHealthBar.setForeground(Color.RED);
		playerHealthBar.setString(Integer.toString(player.getHealth()));
		playerHealthBar.setValue(player.getHealth());
		
			
		add(playerPanel, BorderLayout.LINE_START);
		
		JPanel opponentPanel = new JPanel(new BorderLayout());
		
		switch (opponentType) {
		case 0:
			opponentPanel.add(new JLabel(warriorIcon));
			break;
		case 1: 
			opponentPanel.add(new JLabel(mageIcon));
			break;
		case 2: 
			opponentPanel.add(new JLabel(rogueIcon));
			break;
		default: 
			opponentPanel.add(new JLabel(warriorIcon));
			break;
		}
		
		System.out.println(opponent.getHealth());
		opponentPanel.add(opponentHealthBar = new JProgressBar(0, opponent.getHealth()), BorderLayout.SOUTH);
		opponentHealthBar.setStringPainted(true);
		opponentHealthBar.setForeground(Color.RED);
		opponentHealthBar.setString(Integer.toString(opponent.getHealth()));
		opponentHealthBar.setValue(opponent.getHealth());
		
			
		add(opponentPanel, BorderLayout.LINE_END);
		
		//fight button panel
		JPanel fightPanel = new JPanel();
		
		fightPanel.add(attackButton);
		fightPanel.add(useSpecialButton);
		fightPanel.add(useItemButton);
		
		add(fightPanel, BorderLayout.SOUTH);
		
		//fight events pane;
		JPanel fightEvents = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(fightEventsTextArea);
		scrollPane.setOpaque(false);
		fightEventsTextArea.setEditable(false);
		fightEventsTextArea.setLineWrap(true);
		fightEvents.add(new JLabel("Fight"), BorderLayout.NORTH);
		fightEvents.add(scrollPane, BorderLayout.CENTER);
		
		
		add(fightEvents, BorderLayout.CENTER);				
		
		attackButton.addActionListener(this);
		useSpecialButton.addActionListener(this);
		useItemButton.addActionListener(this);
		
		
	}


	public void createOpponent() {
		Random random = new Random();
		opponentType = random.nextInt(3);
		if (opponentType==0) opponent = new Warrior(20+fightNum,3+fightNum,3);
		else if (opponentType==1) opponent = new Mage(20+fightNum,3+fightNum,3);
		else opponent = new Rogue(20+fightNum,3+fightNum,3);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(attackButton)) {
			playerAttack();
			opponentAttack();
		}
						
	}
	
	private void opponentAttack() {
		Random random = new Random();
		int attackAmount = random.nextInt(opponent.getDamage())+1;
		System.out.println(attackAmount + " opponent attacks");
		boolean hit = random.nextBoolean();
		if (hit==true) {
			playerDead = player.playerTakeDamage(attackAmount);
			playerHealthBar.setValue(player.getHealth());
			playerHealthBar.setString(Integer.toString(player.getHealth()));
			fightEventsTextArea.append(newline + " Opponent hit for " + attackAmount);
		} else 
			fightEventsTextArea.append(newline + " Opponent missed");
		if (playerDead == true) playerDead();
	}


	public void playerAttack() {
		Random random = new Random();
		int attackAmount = random.nextInt(player.getDamage()+1);
		opponentDead = opponent.opponentTakeDamage(attackAmount);
		opponentHealthBar.setValue(opponent.getHealth());
		opponentHealthBar.setString(Integer.toString(opponent.getHealth()));
		fightEventsTextArea.append(newline + " " + player.getName() + " hit for " + attackAmount);
		if (opponentDead == true) continueGame();
	}


	private void continueGame() {
		GameScreen gs = new GameScreen(parent, player, map, playerX, playerY);
		parent.changePanel(gs);		
	}
	
	private void playerDead() {
		JPanel playerDeadPanel = new JPanel(new BorderLayout());
		
		playerDeadPanel.add(new JLabel("you are dead"));
		System.exit(0);
	}
}
