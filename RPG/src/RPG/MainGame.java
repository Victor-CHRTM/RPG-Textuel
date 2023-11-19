package RPG;

import java.util.Random;
import java.util.Scanner;

import Entities.Monster;
import Entities.Obstacle;
import Entities.Player;
import Weapons.Axe;
import Weapons.ItemTier;
import Weapons.Staff;
import Weapons.Sword;
import Weapons.WeaponStore;

public class MainGame {
	public static void main(String[] args) {
		//////////////////////////////////////////////////////////////////
		// Cr�ation des tiers 
		ItemTier tier1 = new ItemTier("D�butant", 1);
		ItemTier tier2 = new ItemTier("Interm�diaire", 5);
		ItemTier tier3 = new ItemTier("Expert", 10);
		ItemTier tier4 = new ItemTier("L�gende", 15);
		//////////////////////////////////////////////////////////////////
		
		
		
		//////////////////////////////////////////////////////////////////
		// Cr�ation des armes 
		Sword swordT1 = new Sword("Beginner's Sword",25,5,tier1);
		Sword swordT2 = new Sword("Doran's Blade",400,15,tier2);
		Sword swordT3 = new Sword("Serpent's Fang",1500,50,tier3);
		Sword swordT4 = new Sword("Infinity Edge",3000,120,tier4);
		
		Staff staffT1 = new Staff("Beginner's Staff",20,4,tier1);
		Staff staffT2 = new Staff("",220,13,tier2);
		Staff staffT3 = new Staff("",1400,48,tier3);
		Staff staffT4 = new Staff("Void Staff",1800,80,tier4);
		
		Axe axeT1 = new Axe("Beginner's Axe",15,3,tier1);
		Axe axeT2 = new Axe("",200,10,tier2);
		Axe axeT3 = new Axe("",770,40,tier3);
		Axe axeT4 = new Axe("Executioner's Axe",1340,70,tier4);
		//////////////////////////////////////////////////////////////////
		
		
		
		//////////////////////////////////////////////////////////////////
		// Cr�ation du magasin
		WeaponStore shop = new WeaponStore();
		shop.add(swordT1); shop.add(swordT2); shop.add(swordT3); shop.add(swordT4);
		shop.add(staffT1); shop.add(staffT2); shop.add(staffT3); shop.add(staffT4);
		shop.add(axeT1); shop.add(axeT2); shop.add(axeT3); shop.add(axeT4);
		//////////////////////////////////////////////////////////////////
		
		
		
		//////////////////////////////////////////////////////////////////
		// Cr�ation du joueur
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Bienvenue dans le jeu ! Veuillez entrer votre nom :\n");
        String playerName = scanner.nextLine();

        System.out.println("\n Choisissez une classe :\n");
        System.out.println("1. Sabreur");
        System.out.println("2. Sorcier");
        System.out.println("3. Viking");

        int classChoice = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne restante apr�s le nombre

        Player player1 = new Player(playerName);

        switch (classChoice) {
            case 1:
                player1.addWeaponToStuff(swordT1);
                break;
            case 2:
                player1.addWeaponToStuff(staffT1);
                break;
            case 3:
                player1.addWeaponToStuff(axeT1);
                break;
            default:
                System.out.println(" Choix de classe invalide. Sabreur choisi par d�faut.");
                player1.addWeaponToStuff(swordT1);
                break;
        }

        System.out.println("\nBienvenue, " + playerName + " !");
        System.out.println("Classe : " + (classChoice == 1 ? "Sabreur" : (classChoice == 2 ? "Sorcier" : "Viking")));
        System.out.println("Arme �quip�e : " + player1.getPlayerStuff().get(0).getWeaponName());
        System.out.println("\nR�gles du jeu :\n" +
                " Vous pouvez vous d�placer sur la carte en utilisant les touches z/q/s/d\n" +
                " Vous rencontrez des monstres, des obstacles, des magasins et d'autres �l�ments pour progresser dans le jeu.\n" +
                " Affrontez les monstres pour gagner de l'exp�rience et de l'argent.\n" +
                " Bonne chance !");
        System.out.println("\n Appuyez sur Entr�e pour commencer le jeu \n");
        scanner.nextLine(); // Attendez que l'utilisateur appuie sur Entr�e pour commencer le jeu
		//////////////////////////////////////////////////////////////////
		
        
		
		//////////////////////////////////////////////////////////////////
		// Cr�ation de la map
		Map map = new Map("map.txt");
		//////////////////////////////////////////////////////////////////
		
		
		

		
		
		
		
		
		
		//////////////////////////////////////////////////////////////////
		// Boucle de jeu
		while (!player1.isDead()) {
			
			map.afficherMap();
		    char caseActuelle = map.deplacerJoueur();
		    
		    // Cr�er une instance de la classe Random
	        Random random = new Random();
	        
		    // Obtenir la position actuelle du joueur
		    int[] positionJoueur = map.trouverJoueur();
		    int ligneJoueur = positionJoueur[0];
		    int colonneJoueur = positionJoueur[1];

		    // Interactions sp�cifiques
		    switch (caseActuelle) {
		        case 'M': // Magasin rencontr�
		            shop.weaponsListShop(player1);
		            break;
		            
		        case 'H': // Heal rencontr�
		        	player1.heal();
		            break;
		            
		        case 'R': // Money rencontr�
		        	player1.winMoney(200);
		            break;
		            
		        case 'O': // Obstacle rencontr�
		        	
		        	int randomInt = random.nextInt((2) + 1) + 1;
		        	if (randomInt == 1) {
		        		Obstacle mannequin = new Obstacle("Mannequin",50);
		        		Battle.startBattle(player1, mannequin);
		        	}
		        	else if (randomInt == 2) {
		        		Obstacle arbre = new Obstacle("Arbre",100);
		        		Battle.startBattle(player1, arbre);
		        	}
		        	else {
		        		Obstacle rocher = new Obstacle("Rocher",200);
		        		Battle.startBattle(player1, rocher);
		        	}
		            break;
		            
		        case 'G': // Gobelin rencontr�
		        	Monster gobelin = new Monster("Gobelin",30,1);
		        	Battle.startBattle(player1, gobelin);
		            break;
		            
		        case 'N': // Nagosy rencontr�
		        	Monster nagosy = new Monster("Nagosy",80,10);
		        	Battle.startBattle(player1, nagosy);
		            break;
		            
		        case 'A': // Alphorithien rencontr�
		        	Monster alphorithien = new Monster("Alphorithien",800,70);
		        	Battle.startBattle(player1, alphorithien);
		            break;
		            
		        case 'B': // Boss rencontr�
		        	Monster boss = new Monster("Boss",1500,120);
		        	Battle.startBattle(player1, boss);
		            break;
		            
		        case 'F': // Fin du jeu !
		            System.out.println("\n\n\n Vous avez fini le jeu ! F�licitations !!! \n\n\n");
		            return;
		    }
	    }
		//////////////////////////////////////////////////////////////////
	}

}
