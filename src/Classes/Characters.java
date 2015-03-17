package Classes;

import javax.swing.JOptionPane;

public abstract class Characters {
    private int health, damage, agility, special, type;
    private String name;
    @SuppressWarnings("unused")
	private Boolean dead;
    
    //Constructor for player character
    public Characters(int health, int damage, int agility, String name, int type){
        this.health = health;
        this.damage = damage;
        this.agility = agility;        
        this.name = name;
        this.type = type;
    }
    
    //Constructor for opponent character
    public Characters(int health, int damage, int agility, int type){
        this.health = health;
        this.damage = damage;
        this.agility = agility;
        this.type = type;
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public int getAgility() {
        return agility;
    }
    
    public int getSpecial() {
    	return special;
    }    
    
    public abstract void specialPower();
    
    public void increaseSpecial(int increment) {
    	special += increment;
    }
    
    public void useSpecial() {
    	special -= 10;
    }
    
    //decreases health by amount given, if the amount given is more than the health then calls the dead method and returns dead as true
    public boolean playerTakeDamage(int dmg) {
    	if (dmg >= health) {
    		playerDead();
    		return dead = true;
    	} else {
    		health -= dmg;
    		return dead = false;
    	}
    }

	private void playerDead() {
		JOptionPane.showMessageDialog(null, "You are Dead");
		
	}
	
    //decreases health by amount given, if the amount given is more than the health then calls the dead method and returns dead as true
	public boolean opponentTakeDamage(int dmg) {
    	if (dmg >= health) {
    		 opponentDead();
    		return dead = true;
    	} else {
    		health -= dmg;
    		return dead = false;
    	}
    	
    }

	private void opponentDead() {
		JOptionPane.showMessageDialog(null, "You have defeated your opponent");
		
	}
	
	public String getName() {
		return name;
	}
    
    public int getType() {
    	return type;
    }
}