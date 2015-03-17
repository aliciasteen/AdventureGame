package Main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Classes.Characters;

@SuppressWarnings("serial")
public class Game extends JFrame{
	@SuppressWarnings("unused")
	private Characters player;
	
	public static void main(String[] args) {
		new Game();
	} 
	
	public Game() {
		//create game window		
		setSize(500, 500);
		setTitle("Alicia's Adventure Game");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//calls setCharacter and adds to window		
		add(new SetCharacter(this));
		setVisible(true);
	}
	
	public void setPlayer(Characters player) {
		//calls GameScreen and changes panel to it
		this.player = player;
		getContentPane().removeAll();
		GameScreen gs = new GameScreen(this,player);
		changePanel(gs);
	}
	
	public void changePanel(JPanel p) {
		//changes the visible panel to the panel passed to this method
		getContentPane().removeAll();
		getContentPane().add(p);
		getContentPane().invalidate();
		getContentPane().validate();
		p.setFocusable(true);
		p.requestFocusInWindow();
	}
	
}
