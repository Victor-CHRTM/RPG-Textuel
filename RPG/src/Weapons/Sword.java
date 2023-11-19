package Weapons;

import Entities.Destructible;
import Entities.Monster;

public class Sword extends Weapon {
	static final double MONSTER_DAMAGE_RATIO = 2;
	static final double OBSTACLE_DAMAGE_RATIO = 2;
	
	//Constructeur
	public Sword(String name, double price, int damage, ItemTier tier) {
		super(name,price,damage,tier);
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	//Méthodes
	public void attack(Destructible destructible) { //Attaquer avec une épée
		if(destructible instanceof Monster) {
			destructible.hit_me((damage + tier.getMinLvl()) * MONSTER_DAMAGE_RATIO);
		}else {
			destructible.hit_me((damage + tier.getMinLvl()) * OBSTACLE_DAMAGE_RATIO);
		}
	}
	
	
	
	@Override
	public String ascii_art() {
		return  "      | ________________  \n"+
				"O|===|* ________________| \n"+
			    "      |\n";
	}
	//////////////////////////////////////////////////////////////////////////////////
}
