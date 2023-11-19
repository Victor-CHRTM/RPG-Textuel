package Entities;

public class Monster extends Destructible {
	//Attributs
	private int attackDmg; //D�gats du monstre
	//////////////////////////////////////////////////////////////////////////////////
	
	//Constructeur
	public Monster(String name, double lifePoints, int attackDmg) {
		super(name,lifePoints);
		this.attackDmg = attackDmg;
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//M�thodes
	public int getMonsterAttackDmg() {
		return attackDmg;
	}
	
	//Attaquer le joueur
	public void attack(Player joueur) {
		System.out.println("Le monstre vous attaque ! \n");
		joueur.hit_me(attackDmg);
	}
	
	@Override
	public String toString() {
		return "|--------------------------|\n"+
			   "| Nom: " +name+ " |\n" + 
			   "| Points de vie: " +lifePoints+ "pv |\n" +
			   "| D�gats : " +attackDmg+ "|\n" +
			   "|--------------------------|\n";
	}
	//////////////////////////////////////////////////////////////////////////////////
}
