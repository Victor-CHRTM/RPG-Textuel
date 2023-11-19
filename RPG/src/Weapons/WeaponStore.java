package Weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entities.Player;

public class WeaponStore {
	// Attributs
	private ArrayList<Weapon> weaponsList;
	//////////////////////////////////////////////////////////////////////////////////
	
	// Constructeur
	public WeaponStore(){
		this.weaponsList = new ArrayList<Weapon>();
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	// M�thodes
	
	// Ajouter des armes dans le magasins 
	public void add(Weapon weapon) {
		this.weaponsList.add(weapon);
	}
	
	// Voir les �p�es disponibles en fonction du niveau du joueur
	public void showShopSword(int level){
		System.out.println(" Swords :\n");
		System.out.println(" ---------");
		for(Weapon weapon : this.weaponsList) {
			if(weapon instanceof Sword) {
				if(weapon.getWeaponTier().getMinLvl() <= level) {
					System.out.println(" "+weapon.getWeaponName()+
							" - "+weapon.getWeaponPrice()+"�"+
					        " - "+weapon.getWeaponDamage()+" ad");
				}
			}
		}
		System.out.println(" ---------\n\n");
	}
	
	// Voir les batons disponibles en fonction du niveau du joueur
	public void showShopStaff(int level){
		System.out.println(" Staffs :\n");
		System.out.println(" ---------");
		for(Weapon weapon : this.weaponsList) {
			if(weapon instanceof Staff) {
				if(weapon.getWeaponTier().getMinLvl() <= level) {
					System.out.println(" "+weapon.getWeaponName()+
							" - "+weapon.getWeaponPrice()+"�"+
					        " - "+weapon.getWeaponDamage()+" ad");
				}
			}
		}
		System.out.println(" ---------\n\n");
	}
	
	// Voir les haches disponibles en fonction du niveau du joueur
	public void showShopAxe(int level){
		System.out.println(" Axes :\n");
		System.out.println(" ---------");
		for(Weapon weapon : this.weaponsList) {
			if(weapon instanceof Axe) {
				if(weapon.getWeaponTier().getMinLvl() <= level) {
					System.out.println(" "+weapon.getWeaponName()+
							" - "+weapon.getWeaponPrice()+"�"+
					        " - "+weapon.getWeaponDamage()+" ad");
				}
			}
		}
		System.out.println(" ---------\n\n");	
	}
	
	
	
	// Affichage du Magasin en fonction du niveau du joueur pass� en param�tre
	public void weaponsListShop(Player player1) {
        System.out.println("\n\n Voici les armes disponibles � l'achat pour le niveau " + player1.getPlayerLevel() + ":\n");

        this.showShopSword(player1.getPlayerLevel()); // Affichera les �p�es disponibles � l'achat
        this.showShopStaff(player1.getPlayerLevel()); // Affichera les batons disponibles � l'achat
        this.showShopAxe(player1.getPlayerLevel()); // Affichera les haches disponibles � l'achat

        Scanner scanner = new Scanner(System.in);
        while (true) {
        	System.out.println(" Votre money: " +player1.getPlayerMoney()+ "� \n");
            System.out.println(" Souhaitez-vous acheter ou vendre une arme ? (Achat (a), Vente (v), ou Quitter (q)) : \n");
            char choix = scanner.next().charAt(0);

            switch (choix) {
                case 'a':
                    acheterArme(player1);
                    break;
                case 'v':
                    vendreArme(player1);
                    break;
                case 'q':
                    System.out.println(" Merci de votre visite ! \n");
                    return; // Quitte la m�thode si le joueur choisit de quitter
                default:
                    System.out.println(" Choix invalide. Veuillez entrer 'a', 'v' ou 'q' : \n");
            }
        }
    }

	
	
    // M�thode d'achat d'arme
	private void acheterArme(Player player1) {
	    System.out.println("Veuillez entrer le num�ro de l'arme que vous souhaitez acheter : ");
	    Scanner scanner = new Scanner(System.in);

	    // Cr�er une liste des armes disponibles pour l'achat (celles que le joueur ne poss�de pas)
	    List<Weapon> armesDisponibles = new ArrayList<>();
	    for (Weapon arme : weaponsList) {
	        if (arme.getWeaponTier().getMinLvl() <= player1.getPlayerLevel() && !player1.getPlayerStuff().contains(arme)) {
	            armesDisponibles.add(arme);
	        }
	    }

	    // Afficher les armes disponibles avec des num�ros
	    int numeroArme = 1;
	    for (Weapon arme : armesDisponibles) {
	        System.out.println(numeroArme + ". " + arme.getWeaponName() +
	                " - " + arme.getWeaponPrice() + "�" +
	                " - " + arme.getWeaponDamage() + " ad");
	        numeroArme++;
	    }

	    // Laisser l'utilisateur choisir l'arme par son num�ro
	    int choixArme = scanner.nextInt();
	    if (choixArme >= 1 && choixArme <= numeroArme - 1) {
	        // R�cup�rer l'arme s�lectionn�e
	        Weapon armeChoisie = armesDisponibles.get(choixArme - 1);

	        // V�rifier si le joueur a assez d'argent pour acheter l'arme
	        player1.buyWeapon(armeChoisie);  
	    } else {
	        System.out.println("Num�ro d'arme invalide.");
	    }
	}

	
	
	
    // M�thode de vente d'arme
    private void vendreArme(Player player1) {
    	if (player1.getPlayerStuff().isEmpty()) { // Le joueur ne peut pas vendre
            System.out.println(" Votre inventaire est vide. Vous ne pouvez donc rien vendre \n");
            return;
        }
    	else { // Le joueur peut vendre
    	    int numeroArme = 1;
    	    for (Weapon arme : player1.getPlayerStuff()) {
    	        System.out.println(numeroArme + ". " + arme.getWeaponName() +
    	                " - " + arme.getWeaponPrice() + "�" +
    	                " - " + arme.getWeaponDamage() + " ad");
    	        numeroArme++;
    	    }
    	    
    	    System.out.println(" Veuillez entrer le num�ro de l'arme que vous souhaitez vendre : ");
    	    Scanner scanner = new Scanner(System.in);
    	    int numeroArmeAVendre = scanner.nextInt();

    	    // V�rifier si le num�ro d'arme est valide
    	    if (numeroArmeAVendre >= 1 && numeroArmeAVendre <= player1.getPlayerStuff().size()) {
    	        // R�cup�rer l'arme s�lectionn�e
    	        Weapon armeAVendre = player1.getPlayerStuff().get(numeroArmeAVendre - 1);

    	        // Appeler la m�thode sellWeapon pour vendre l'arme
    	        player1.sellWeapon(armeAVendre);
    	    } else {
    	        System.out.println(" Arme introuvable...");
    	    }
    	}
    }
	//////////////////////////////////////////////////////////////////////////////////
}
