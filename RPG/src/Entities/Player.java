package Entities;

import java.util.ArrayList;

import Weapons.Weapon;

public class Player {
	//Attributs
	private String name;
	private int lifePoints;
	private double money;
	private int level;
	private float xp;
	private float maxXp = 100;
	private ArrayList<Weapon> Stuff;
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//Constructeur
	public Player(String name) {
		this.name = name;
		this.lifePoints = 100;
		this.money = 100;
		this.level = 1;
		this.xp = 0;
		this.Stuff = new ArrayList<Weapon>();
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//Getters
	public String getPlayerName() { return name; }
	public int getPlayerLifePoints() { return lifePoints; }
	public double getPlayerMoney() { return money; }
	public int getPlayerLevel() { return level; }
	public float getXp() { return xp; };
	public ArrayList<Weapon> getPlayerStuff() { return Stuff; }
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//Méthodes
	// Ajouter une arme au stuff du joueur
	public void addWeaponToStuff(Weapon weapon) {this.Stuff.add(weapon); };
	
	// Le joueur peut acheter un objet
	public void buyWeapon(Weapon weapon) {
		if(weapon.getWeaponPrice() > this.getPlayerMoney()) {
			System.out.println(" Vous n'avez pas assez de money !");
		}else {
			addWeaponToStuff(weapon);
			this.money -= weapon.getWeaponPrice();
			System.out.println(" Vous venez d'acheter: " +weapon.getWeaponName()+ 
					"\n Votre stuff: " +this.Stuff+ "\n");
		}
	}
	
	
	// Le joueur peut vendre un objet
	public void sellWeapon(Weapon weapon) {
		if (Stuff.contains(weapon)) {
	        Stuff.remove(weapon); // Enlever l'arme du stuff
	        money += weapon.getWeaponPrice()*0.5; // rembourser 50% du prix de l'arme
	        System.out.println(" Vous venez de vendre: " +weapon.getWeaponName()+
	        		"\n Votre inventaire: " +this.Stuff);
	    }
	}
	
	
	//Le joueur se fait  attaquer
	public void hit_me(double damage) { 
		lifePoints -= damage; 
	}
	
	
	public void heal() { // Le joueur reçois de la vie
		int lifeWin = 300;
		lifePoints += lifeWin;
		System.out.println(" Vous gagnez: " +lifeWin+ "hp ! Ce qui monte votre vie à " +lifePoints+ "hp\n" );
	}
	
	
	public void winMoney(double moneyW) { // Le joueur gagne de la money
		money += moneyW;
		System.out.println(" Vous avez remporté " +moneyW+ "€ ce qui monte votre économie à " +money+ "€ \n");
	}
	
	
	public void winXpLevel() {
		float xpWin = 60;
		while (xpWin != 0 ) {
			if (xp < maxXp) { // Si l'xp du joueur n'est pas au max pour son niveau
				// Le joueur gagne 1 point d'xp
				xp += 1 * 1.1;
				xpWin -= 1;
			}else { // Si l'xp du joueur a atteint le niveau max selon le niveau du joueur
				// Le joueur gagne un niveau 
				level += 1;
				System.out.println(" Vous montez au level " +level+ " !\n");
				// Il gagne de la vie
				lifePoints += 20 * this.getPlayerLevel();
				System.out.println(" Vous avez désormais " +lifePoints+ "hp !\n");
				// On remet à 0 son xp
				xp = 0;
				// On augmente l'xp max pouvant être obtenu (xp max + 20)
				maxXp += 20;
			}
		}
		System.out.println(" Prochain niveau dans " +(maxXp-xp)+ " points d'xp\n");
	}
	
	//Le joueur est il mort ?
	public boolean isDead() {
		if(this.getPlayerLifePoints() < 1){
			return true;
		}
		else{ 
			return false; 
		}
	}
	
	@Override
	public String toString() {
		return "|--------------------------|\n"+
			   "| Nom: " +name+ " |\n" + 
			   "| Points de vie: " +lifePoints+ "pv |\n" +
			   "| Money : " +money+ "|\n" +
			   "| Level : " +level+ "|\n" +
			   "|--------------------------|\n";
	}
	//////////////////////////////////////////////////////////////////////////////////
}
