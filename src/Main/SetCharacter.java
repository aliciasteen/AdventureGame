package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Classes.Mage;
import Classes.Rogue;
import Classes.Warrior;

@SuppressWarnings("serial")
public class SetCharacter extends JPanel {
	
	private Game parent;
	private JTextField name;
	private JButton warrior, mage, rogue;
	private ActionListener action = new ActionListener() {
		
		//if a button is pressed the appropriate type of player is created
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton clicked = (JButton) e.getSource();
			String charName = (name.getText());
			if (charName.equals("")) {
				JOptionPane.showMessageDialog(parent, "Please input name");
				return;
			}
			
			if (clicked.equals(warrior)) {
				parent.setPlayer(new Warrior(charName));
			} else if (clicked.equals(mage)) {
				parent.setPlayer(new Mage(charName));
			} else {
				parent.setPlayer(new Rogue(charName));
			}
		}
	};
	
	public SetCharacter(Game parent) {
		this.parent = parent;
		
		setLayout(new BorderLayout());
		
		//north panel asks for name and saves it
		JPanel north = new JPanel();
		north.add(new JLabel("Character Name:"));
		name = new JTextField(25);
		north.add(name);
		
		add(north, BorderLayout.NORTH);
		
		JPanel center = new JPanel(new GridLayout(1,3));
		TitledBorder title = new TitledBorder("Select Your Character Class");
		title.setTitleJustification(TitledBorder.CENTER);
		center.setBorder(title);
		
		//Warrior panel contains warrior icon, description and button to chose that class
		
		JPanel warriorPanel = new JPanel(new BorderLayout());
		
		warrior = new JButton("Warrior");
		warrior.addActionListener(action);
		
		JPanel warriorIconPanel = new JPanel(new BorderLayout());
		ImageIcon warriorIcon = new ImageIcon("assets/warrior.png");
		warriorIconPanel.add(new JLabel(warriorIcon), BorderLayout.CENTER);
		warriorIconPanel.setBorder(new EmptyBorder(15,5,5,5));
		warriorIconPanel.setBackground(null);
		warriorPanel.add(warriorIconPanel, BorderLayout.NORTH);
		
		JLabel warriorLabel = new JLabel("<html>warrior<br/> they do stuff like fight</html>");
		warriorLabel.setForeground(Color.BLACK);
		warriorPanel.add(warriorLabel, BorderLayout.CENTER);
		warriorPanel.add(warrior,  BorderLayout.SOUTH);
		warriorPanel.setBorder(new EmptyBorder(5,5,5,5));
		warriorPanel.setBackground(new Color(204,0,0));
		
		center.add(warriorPanel);
		
		// Mage panel contains mage icon, description and button to chose that class
		
		JPanel magePanel = new JPanel(new BorderLayout());
		
		mage = new JButton("Mage");
		mage.addActionListener(action);
		
		JPanel mageIconPanel = new JPanel(new BorderLayout());
		ImageIcon mageIcon = new ImageIcon("assets/mage.png");
		mageIconPanel.add(new JLabel(mageIcon), BorderLayout.CENTER);
		mageIconPanel.setBorder(new EmptyBorder(15, 5, 5, 5));
		mageIconPanel.setBackground(null);
		magePanel.add(mageIconPanel,BorderLayout.NORTH);
		
		JLabel mageLabel = new JLabel("<html><p>mages are very cool. they do stuff like make magic</p></html>");
		mageLabel.setForeground(Color.BLACK);
		magePanel.add(mageLabel, BorderLayout.CENTER);
		magePanel.add(mage,  BorderLayout.SOUTH);
		magePanel.setBorder(new EmptyBorder(5,5,5,5));
		magePanel.setBackground(new Color(100,150,255));
		
		center.add(magePanel);
		
		//Rogue panel contains rogue icon, description and button to chose that class
		
		JPanel roguePanel = new JPanel(new BorderLayout());
		
		rogue = new JButton("Rogue");
		rogue.addActionListener(action);
		
		JPanel rogueIconPanel = new JPanel(new BorderLayout());
		ImageIcon rogueIcon = new ImageIcon("assets/rogue.png");
		rogueIconPanel.add(new JLabel(rogueIcon), BorderLayout.CENTER);
		rogueIconPanel.setBorder(new EmptyBorder(15, 5, 5, 5));
		rogueIconPanel.setBackground(null);		
		roguePanel.add(rogueIconPanel, BorderLayout.NORTH);
		
		JLabel rogueLabel = new JLabel("<html><p>rogues are very cool. they do stuff like fuck shit up</p></html>");
		rogueLabel.setHorizontalAlignment(JLabel.CENTER);
		rogueLabel.setForeground(Color.BLACK);
		roguePanel.add(rogueLabel, BorderLayout.CENTER);
		roguePanel.add(rogue,  BorderLayout.SOUTH);
		roguePanel.setBorder(new EmptyBorder(5,5,5,5));
		roguePanel.setBackground(new Color(0,200,0));
		center.add(roguePanel);
				
		add(center, BorderLayout.CENTER);
				
	}
}
