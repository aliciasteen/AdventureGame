package Classes;
public class Warrior extends Characters {
    int strength;
    
    //constructor for player warrior
    public Warrior(String name) {
        super(30, 4, 2, name, 0);
       }
    
    //constructor for opponent warrior
    public Warrior(int health, int damage, int agility) {
    	super(health, damage, agility, 0);
    }
    
    public void specialPower() {
    	//Strong attack
    }
}