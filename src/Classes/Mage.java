package Classes;
public class Mage extends Characters {
    int strength;
    
    //Constructor for playerMage
    public Mage(String name) {
        super(30, 2, 2, name, 1);
       }
    
    //Constructor for opponentMage
    public Mage(int health, int damage, int agility) {
    	super(health, damage, agility, 1);
    }
       
    public void specialPower() {
    	//magic attack
    }
}