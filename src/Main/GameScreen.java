package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.*;
import javax.swing.JPanel;

import Classes.Characters;

@SuppressWarnings("serial")
public class GameScreen extends JPanel implements KeyListener {
	
	static final int sideLength = 30;
	static final BufferedImage playerImage = getImage("assets/playerIcon.png");
	static final BufferedImage treasureImage = getImage("assets/treasureImage.png");
	static final BufferedImage opponentImage = getImage("assets/opponentImage.png");
	private int[][] map;
	int playerX;
	int playerY;
	int fightNum = 0;
	private Game parent;
	private Characters player;
	
	
	
	public GameScreen(Game parent, Characters player) {
		this.parent = parent;
		this.player = player;
		map = new int[15][15];
		
		//try catch statement to read map file to map array
		//also sets the player location from the map file
		try {
			Scanner s = new Scanner(new File("assets/map1.csv"));
			String[] split;
			int arrayLine=0;
			while (s.hasNextLine()) {
				split = s.nextLine().split(",");
				for (int i=0; i<15; i++) {
					map[arrayLine][i] = Integer.parseInt(split[i]);
					if(split[i].equals("3")) {
						playerX = i;
						playerY = arrayLine;
					}
				}
				arrayLine++;
			}			
			s.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		repaint();
		
		addKeyListener(this);
	}
	
	//gameScreen constructor for continuing the game
	public GameScreen(Game parent, Characters player, int[][] map, int playerX, int playerY) {
		this.parent = parent;
		this.player = player;
		this.map = map;
		this.playerX = playerX;
		this.playerY = playerY;
				
		repaint();
		
		addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		paintMap(g2);
		
	}
	
	//creates the graphics of the map from the map array
	public void paintMap(Graphics2D g2) {
		for (int i=0; i<15; i++){
			for (int j=0; j<15; j++) {
				
				if (i>=playerX-1 && i<=playerX+1 && j>=playerY-1 && j<=playerY+1) {
				switch (map[j][i]) {
				case 0: g2.setPaint(Color.cyan);
						g2.fill(new Rectangle2D.Double(i*sideLength+25,j*sideLength+15,sideLength,sideLength));
						break;
				case 1: g2.setPaint(Color.white);
						g2.fill(new Rectangle2D.Double(i*sideLength+25,j*sideLength+15,sideLength,sideLength));
						break;
				case 5: g2.drawImage(opponentImage, i*sideLength+25, j*sideLength+15, null);
						break;
				case 2: g2.drawImage(treasureImage, i*sideLength+25, j*sideLength+15, null);
						break;
				case 3: g2.drawImage(playerImage,i*sideLength+25,j*sideLength+15, null);
						break;
						
				case 4: g2.setPaint(Color.green);
						g2.fill(new Rectangle2D.Double(i*sideLength+25,j*sideLength+15,sideLength,sideLength));
						break;
				}
				} else {
					g2.setPaint(Color.black);
					g2.fill(new Rectangle2D.Double(i*sideLength+25,j*sideLength+15,sideLength,sideLength));
				}
			}
		}	
	}
	
	//Keylisteners for player moving around the map
	//if a key is pressed checks that you are not at the end of the map
	//then calls movePlayer() with where the player moves
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (playerY==0) System.out.println("You cannot go up");
			else movePlayer(0, -1); 
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (playerY==14) System.out.println("You cannot go down");
			else movePlayer(0, 1);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX==0) System.out.println("You cannot go left");
			else movePlayer(-1,0);			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX==14 && playerY==13) System.out.println("You have won!");
			else movePlayer(1,0);
		}
	}
	
	//move player first checks if the move is valid, then if you have moved to a fight location, if so creates new fight
	//and changes the panel to the new fight, then checks for treasure. then moves the player and repaints the map
	public void movePlayer(int xChange, int yChange) {
		if (map[playerY+yChange][playerX+xChange]==0) System.out.println("You cannot go that way");
		else {
			int x = map[playerY+yChange][playerX+xChange];
			if (x==5) {
				Fight fight = new Fight(playerX, playerY, fightNum, parent, player, this, map);
				parent.changePanel(fight);
			} else if (map[playerY+yChange][playerX+xChange]==4) System.out.println("Found Treasure");
			map[playerY][playerX]=1;
			playerX+=xChange;
			playerY+=yChange;
			map[playerY][playerX]=3;
		} 
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}
	
	//reads image from file into a buffered image
	static BufferedImage getImage(String path) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Image cannot be read from " + path);
		}
		return img;
	}
}
