package RPG;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Map {
    private char[][] gameMap; // Utilisé pour stocker la position actuelle des éléments
    private char[][] displayMap; // Utilisé pour l'affichage avec le joueur déplacé
    private int nbLignes;
    private int nbColonnes;

    // Constructeur pour initialiser le tableau en fonction du fichier
    public Map(String cheminFichier) {
        determinerDimensions(cheminFichier);
        gameMap = new char[nbLignes][nbColonnes];
        displayMap = new char[nbLignes][nbColonnes];
        creaMap(cheminFichier);
    }

    // Méthode pour déterminer les dimensions du tableau en fonction du fichier
    private void determinerDimensions(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            nbLignes = 0;
            nbColonnes = 0;
            String ligne;

            while ((ligne = br.readLine()) != null) {
                nbLignes++;

                if (ligne.length() > nbColonnes) {
                    nbColonnes = ligne.length();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour charger le contenu du fichier dans le tableau
    private void creaMap(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int i = 0;

            while ((ligne = br.readLine()) != null) {
                char[] ligneArray = ligne.toCharArray();

                // Assurez-vous que la ligne ne dépasse pas le nombre de colonnes du tableau
                for (int j = 0; j < Math.min(ligneArray.length, nbColonnes); j++) {
                    gameMap[i][j] = ligneArray[j];
                    displayMap[i][j] = ligneArray[j];
                }

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour la copie d'affichage
    private void updateDisplayMap() {
        for (int i = 0; i < nbLignes; i++) {
            displayMap[i] = Arrays.copyOf(gameMap[i], gameMap[i].length);
        }
    }

    public void afficherMap() {
        updateDisplayMap(); // Mettre à jour la copie d'affichage
        // Afficher la copie d'affichage avec la position du joueur
        displayMap[trouverJoueur()[0]][trouverJoueur()[1]] = 'J';

        for (char[] ligne : displayMap) {
            for (char cellule : ligne) {
                System.out.print(cellule);
            }
            System.out.println();
        }
    }

    public int[] trouverJoueur() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (gameMap[i][j] == 'J') {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Retourne null si le joueur n'est pas trouvé
    }

    public char getCase(int ligne, int colonne) {
        return gameMap[ligne][colonne];
    }

    public char deplacerJoueur() {
        Scanner scanner = new Scanner(System.in);

        int[] positionJoueur = trouverJoueur();
        int ligneJoueur = positionJoueur[0];
        int colonneJoueur = positionJoueur[1];

        System.out.println("\n\n Choisissez une direction (z pour monter, q pour aller à gauche, s pour descendre, d pour aller à droite) :\n");
        char direction = scanner.next().charAt(0);

        int nouvelleLigne = ligneJoueur;
        int nouvelleColonne = colonneJoueur;

        switch (direction) {
            case 'z':
                nouvelleLigne--;
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                break;
            case 'q':
                nouvelleColonne--;
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                break;
            case 's':
                nouvelleLigne++;
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                break;
            case 'd':
                nouvelleColonne++;
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                break;
            default:
            	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
            			+" Commande de déplacement invalide !\n");
                return ' '; // Retourner un caractère vide pour indiquer un déplacement invalide
        }

        // Vérifier si la nouvelle position est valide
        if (nouvelleLigne >= 0 && nouvelleLigne < nbLignes && nouvelleColonne >= 0 && nouvelleColonne < nbColonnes
                && gameMap[nouvelleLigne][nouvelleColonne] != '*') {

            // Récupérer la case correspondant à la nouvelle position sur la map originale
            char caseNouvellePosition = gameMap[nouvelleLigne][nouvelleColonne];

            // Effacer la position actuelle du joueur sur la copie de la carte
            gameMap[ligneJoueur][colonneJoueur] = ' ';

            // Mettre à jour la nouvelle position du joueur sur la copie de la carte
            gameMap[nouvelleLigne][nouvelleColonne] = 'J';

            // Retourner le caractère correspondant à la case de la nouvelle position
            return caseNouvellePosition;
        } else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
            		+ "Déplacement impossible dans cette direction ! \n\n");
            
            return ' '; // Retourner un caractère vide pour indiquer un déplacement invalide
        }
    }
}