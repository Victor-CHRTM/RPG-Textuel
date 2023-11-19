package Weapons;

import Entities.Destructible;

public abstract class Weapon {
	//Attributs
	private String name;
	protected double price;
	protected int damage;
	protected ItemTier tier;
	
	public abstract String ascii_art();
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//Constructeur pour construire une nouvelle arme
	public Weapon(String name, double price, int damage, ItemTier tier) {
		this.name = name;
		this.price = price;
		this.damage = damage;
		this.tier = tier;
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//Getters
	public String getWeaponName() { return name; } //Obtenir le nom de l'arme
	public double getWeaponPrice() { return price; } //Obtenir le prix de l'arme
	public int getWeaponDamage() { return damage; } //Obtenir les dégats de l'arme
	public ItemTier getWeaponTier() { return tier; } //Obtenir le tier de l'arme
	//////////////////////////////////////////////////////////////////////////////////
	
	public abstract void attack(Destructible destructible);
	
	
	@Override
	public String toString() {
		return name;
	}
}
