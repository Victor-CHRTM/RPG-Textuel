package Entities;

public abstract class Destructible {
	//Attributs
	protected String name;
	protected double lifePoints;
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//Constructeur
	public Destructible (String name, double lifePoints) {
		this.name = name;
		this.lifePoints = lifePoints;
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	
	//Getters
	public String getName() { return name; }
	public double getLifePoints() { return lifePoints; }
	//////////////////////////////////////////////////////////////////////////////////
	
	//M�thodes
	public void hit_me(double damage) { lifePoints -= damage; } //Le monstre o� l'obstacle se fait attaquer
	//////////////////////////////////////////////////////////////////////////////////
}
