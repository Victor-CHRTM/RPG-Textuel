package Weapons;


public class ItemTier {
	//Attributs
	private String tier;
	private int minLvl;
	
	
	// Constructeur 
	public ItemTier (String tier, int minLvl){
		this.tier = tier;
		this.minLvl = minLvl;
	}
	
	//Getters & Setters
	public String getTier() { return tier; } //Retourne le tier d'une arme
	//public void setTier(String tier) { this.tier = tier; }
	public int getMinLvl() { return minLvl; } //Retourne le level minimum pour utiliser une arme
	//public void setMinLvl(int minLvl) { this.minLvl = minLvl; }
	
	@Override
	public String toString() {
		return "'"+tier+"' | Level minimum pour utilisation: "+minLvl;
	}		   
}
