package Classes;
public class Rogue extends Characters {
    int strength;
    
    //Constructor for player rogue
    public Rogue(String name) {
        super(30, 3, 4, name, 2);
       }
    
    //Constructor for opponent rogue
    public Rogue(int health, int damage, int agility) {
    	super(health, damage, agility, 2);
    }
    
    public void specialPower() {
    	//Attack 3 times in a row
    }
}