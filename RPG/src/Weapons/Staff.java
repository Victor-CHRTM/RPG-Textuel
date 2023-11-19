package Weapons;

import Entities.Destructible;
import Entities.Monster;

public class Staff extends Weapon {
	static final double MONSTER_DAMAGE_RATIO = 3;
	static final double OBSTACLE_DAMAGE_RATIO = 0.6;
	
	//Constructeur
	public Staff(String name, double price, int damage, ItemTier tier) {
		super(name,price,damage,tier);
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	//Méthodes
	public void attack(Destructible destructible) { //Attaquer avec un baton magique
		if(destructible instanceof Monster) {
			destructible.hit_me((damage + tier.getMinLvl()) * MONSTER_DAMAGE_RATIO);
		}else {
			destructible.hit_me((damage + tier.getMinLvl()) * OBSTACLE_DAMAGE_RATIO);
		}
	}
		
		
	@Override
	public String ascii_art() {
		return " ___ \n"+
			   " | | \n"+
			   " |-| \n"+
			   " | | \n"+
			   " |-| \n"+
			   " | | \n"+
			   " |-| \n"+
			   " | | \n"+
			   " |-| \n"+
			   " |_|  ";
	}
}
