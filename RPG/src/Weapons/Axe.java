package Weapons;

import Entities.Destructible;
import Entities.Monster;

public class Axe extends Weapon {
	static final double MONSTER_DAMAGE_RATIO = 1.5;
	static final double OBSTACLE_DAMAGE_RATIO = 5;
	
	//Constructeur
	public Axe(String name, double price, int damage, ItemTier tier) {
		super(name,price,damage,tier);
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	//Méthodes
	public void attack(Destructible destructible) { //Attaquer avec une hache
		if(destructible instanceof Monster) {
			destructible.hit_me((damage + tier.getMinLvl()) * MONSTER_DAMAGE_RATIO);
		}else {
			destructible.hit_me((damage + tier.getMinLvl()) * OBSTACLE_DAMAGE_RATIO);
		}
	}
	
	@Override
	public String ascii_art() {
		return "(>|"+
			   "  |"+
			   "  !";
	}
}
