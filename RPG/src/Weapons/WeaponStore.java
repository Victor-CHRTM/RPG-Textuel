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
	
	// Méthodes
	
	// Ajouter des armes dans le magasins 
	public void add(Weapon weapon) {
		this.weaponsList.add(weapon);
	}
	
	// Voir les épées disponibles en fonction du niveau du joueur
	public void showShopSword(int level){
		System.out.println(" Swords :\n");
		System.out.println(" ---------");
		for(Weapon weapon : this.weaponsList) {
			if(weapon instanceof Sword) {
				if(weapon.getWeaponTier().getMinLvl() <= level) {
					System.out.println(" "+weapon.getWeaponName()+
							" - "+weapon.getWeaponPrice()+"€"+
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
							" - "+weapon.getWeaponPrice()+"€"+
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
							" - "+weapon.getWeaponPrice()+"€"+
					        " - "+weapon.getWeaponDamage()+" ad");
				}
			}
		}
		System.out.println(" ---------\n\n");	
	}
	
	
	
	// Affichage du Magasin en fonction du niveau du joueur passé en paramètre
	public void weaponsListShop(Player player1) {
        System.out.println("\n\n Voici les armes disponibles à l'achat pour le niveau " + player1.getPlayerLevel() + ":\n");

        this.showShopSword(player1.getPlayerLevel()); // Affichera les épées disponibles à l'achat
        this.showShopStaff(player1.getPlayerLevel()); // Affichera les batons disponibles à l'achat
        this.showShopAxe(player1.getPlayerLevel()); // Affichera les haches disponibles à l'achat

        Scanner scanner = new Scanner(System.in);
        while (true) {
        	System.out.println(" Votre money: " +player1.getPlayerMoney()+ "€ \n");
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
                    return; // Quitte la méthode si le joueur choisit de quitter
                default:
                    System.out.println(" Choix invalide. Veuillez entrer 'a', 'v' ou 'q' : \n");
            }
        }
    }

	
	
    // Méthode d'achat d'arme
	private void acheterArme(Player player1) {
	    System.out.println("Veuillez entrer le numéro de l'arme que vous souhaitez acheter : ");
	    Scanner scanner = new Scanner(System.in);

	    // Créer une liste des armes disponibles pour l'achat (celles que le joueur ne possède pas)
	    List<Weapon> armesDisponibles = new ArrayList<>();
	    for (Weapon arme : weaponsList) {
	        if (arme.getWeaponTier().getMinLvl() <= player1.getPlayerLevel() && !player1.getPlayerStuff().contains(arme)) {
	            armesDisponibles.add(arme);
	        }
	    }

	    // Afficher les armes disponibles avec des numéros
	    int numeroArme = 1;
	    for (Weapon arme : armesDisponibles) {
	        System.out.println(numeroArme + ". " + arme.getWeaponName() +
	                " - " + arme.getWeaponPrice() + "€" +
	                " - " + arme.getWeaponDamage() + " ad");
	        numeroArme++;
	    }

	    // Laisser l'utilisateur choisir l'arme par son numéro
	    int choixArme = scanner.nextInt();
	    if (choixArme >= 1 && choixArme <= numeroArme - 1) {
	        // Récupérer l'arme sélectionnée
	        Weapon armeChoisie = armesDisponibles.get(choixArme - 1);

	        // Vérifier si le joueur a assez d'argent pour acheter l'arme
	        player1.buyWeapon(armeChoisie);  
	    } else {
	        System.out.println("Numéro d'arme invalide.");
	    }
	}

	
	
	
    // Méthode de vente d'arme
    private void vendreArme(Player player1) {
    	if (player1.getPlayerStuff().isEmpty()) { // Le joueur ne peut pas vendre
            System.out.println(" Votre inventaire est vide. Vous ne pouvez donc rien vendre \n");
            return;
        }
    	else { // Le joueur peut vendre
    	    int numeroArme = 1;
    	    for (Weapon arme : player1.getPlayerStuff()) {
    	        System.out.println(numeroArme + ". " + arme.getWeaponName() +
    	                " - " + arme.getWeaponPrice() + "€" +
    	                " - " + arme.getWeaponDamage() + " ad");
    	        numeroArme++;
    	    }
    	    
    	    System.out.println(" Veuillez entrer le numéro de l'arme que vous souhaitez vendre : ");
    	    Scanner scanner = new Scanner(System.in);
    	    int numeroArmeAVendre = scanner.nextInt();

    	    // Vérifier si le numéro d'arme est valide
    	    if (numeroArmeAVendre >= 1 && numeroArmeAVendre <= player1.getPlayerStuff().size()) {
    	        // Récupérer l'arme sélectionnée
    	        Weapon armeAVendre = player1.getPlayerStuff().get(numeroArmeAVendre - 1);

    	        // Appeler la méthode sellWeapon pour vendre l'arme
    	        player1.sellWeapon(armeAVendre);
    	    } else {
    	        System.out.println(" Arme introuvable...");
    	    }
    	}
    }
	//////////////////////////////////////////////////////////////////////////////////
}
