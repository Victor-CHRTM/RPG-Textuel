package RPG;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Entities.Destructible;
import Entities.Monster;
import Entities.Player;
import Weapons.Sword;
import Weapons.Weapon;

public class Battle {
    
    // M�thode pour simuler une bataille entre le joueur et un destructible (obstacle ou monstre)
    public static void startBattle(Player player, Destructible destructible) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" D�but du combat !\n");
        System.out.println(player.getPlayerName() + " VS " + destructible.getName() + "\n");

        while (player.getPlayerLifePoints() > 0 && destructible.getLifePoints() > 0) {
            // Demander au joueur de choisir une arme
            System.out.println(" Choisissez une arme pour attaquer le " + destructible.getName() + " :\n");
            ArrayList<Weapon> playerStuff = player.getPlayerStuff();

            for (int i = 0; i < playerStuff.size(); i++) {
                System.out.println(i + 1 + ". " + playerStuff.get(i).getWeaponName() +"\n");
            }

            try {
                // Lire ce que l'utilisateur �crit
                int choice = Integer.parseInt(scanner.next());

                if (choice >= 1 && choice <= playerStuff.size()) {
                    Weapon selectedWeapon = playerStuff.get(choice - 1);

                    // Attaque sur le destructible
                    selectedWeapon.attack(destructible);

                    // Affichage des �tats apr�s l'attaque
                    System.out.println("\n Vous attaquez avec " + selectedWeapon.getWeaponName() +
                            " le " + destructible.getName() + "\n");
                    System.out.println(" Il reste: " + destructible.getLifePoints() +
                            " points de vie au " + destructible.getName() + "\n");

                    // V�rifier si le destructible est d�truit
                    if (destructible.getLifePoints() <= 0) {
                        break;
                    }

                    // Attaque du destructible sur le joueur (si c'est un monstre)
                    if (destructible instanceof Monster) {
                        Monster monster = (Monster) destructible;
                        if (Math.random() < 0.5) { // 50% de chance de rater l'attaque
                            player.hit_me(monster.getMonsterAttackDmg());
                            
                            // V�rifier si le joueur est mort
                            if (player.isDead() ) {
                                break;
                            }
                            
                            System.out.println(" Le " + monster.getName() +
                                    " vous attaque avec " + monster.getMonsterAttackDmg() + " points de d�gats\n");
                            System.out.println(" Il vous reste: " + player.getPlayerLifePoints() + " points de vie\n");
                        } else {
                            System.out.println(" Le " + monster.getName() + " rate son attaque ! \n");
                        }
                    }
                } else {
                    System.out.println(" Choix invalide. Veuillez choisir une arme valide ! \n");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                // G�rer l'exception si la conversion d'une chaine en entier �choue
                System.out.println("\n\n Erreur : Veuillez entrer un nombre entier valide !\n\n");
                
            }
        }

        // Afficher le r�sultat du combat
        if (player.getPlayerLifePoints() > 0) {
            System.out.println(" Vous avez gagn� le combat contre le " + destructible.getName() + " ! \n");
            player.winXpLevel();
            player.winMoney(10 * player.getPlayerLevel());
        } else {
            System.out.println("\n Fin du jeu car vous �tes mort !!\n");
        }
    }
}