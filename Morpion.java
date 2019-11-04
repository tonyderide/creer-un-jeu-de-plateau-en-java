import java.util.Scanner;

public class Morpion {
    //parametre
    private char[][] grille = new char[][] { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
    private String caseDeGrille = "-1";
    private Scanner sc = new Scanner(System.in);
    private char joueur1 = 'O';
    private char joueur2 = 'X';
    private char joueurCourant = joueur1;

    //method
    public void afficherGrille() {
        for (int j = 0; j < this.grille.length; ++j) {
            for (int i = 0; i < this.grille[0].length; ++i) {
                System.out.print(this.grille[j][i]);
            }
            System.out.println();
        }
    }

    public void selectionCase() {
        while (true) {
            System.out.print("joueur " + joueurCourant + " selectionner une case de 1 a 9: ");
            this.caseDeGrille = sc.nextLine();
            // test si c'est un chiffre compris entre 1 et 9 et que ce n'est pas un String
            try {
                int caseDeGrilleNum = (Integer.parseInt(caseDeGrille) - 1); // transformation case de la grille en numerique
                if (caseDeGrilleNum >= 0 && caseDeGrilleNum <= 8 
                && grille[caseDeGrilleNum / 3][caseDeGrilleNum % 3]!= joueur1//test pour savoir si case deja occupé
                && grille[caseDeGrilleNum / 3][caseDeGrilleNum % 3]!= joueur2) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("on a dit un chiffre entre 1 et 9!!");
            }
        }
    }

    public void placerJeton() {
        int caseDeGrilleNum = (Integer.parseInt(caseDeGrille) - 1); // transformation case de la grille en numerique
        int ligne = caseDeGrilleNum / 3;
        int col = caseDeGrilleNum % 3;


        if (this.grille[ligne][col] != joueur1 && this.grille[ligne][col] != joueur2) {// test si case deja occupé
            this.grille[ligne][col] = joueurCourant;

        }

    }

    public boolean verifierGagnant() {
        
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                if (this.grille[0][j] == joueurCourant && this.grille[1][j] == joueurCourant
                        && this.grille[2][j] == joueurCourant) {
                    // verification colonne
                    return true;
                    
                }
                if (this.grille[i][0] == joueurCourant && this.grille[i][1] == joueurCourant
                        && this.grille[i][2] == joueurCourant) {
                    // verification ligne
                    return true;
                    
                }
                if (this.grille[0][0] == joueurCourant && this.grille[1][1] == joueurCourant
                        && this.grille[2][2] == joueurCourant) {
                    // verification diagonal
                    return true;
                }
                if (this.grille[i][2] == joueurCourant && this.grille[i + 1][j + 1] == joueurCourant
                        && this.grille[2][j] == joueurCourant) {
                    // verification diagonal
                    return true;
                }
            }
        }
        return false;
    }

    public void changerJoueur() {
        if (verifierGagnant() == false) {

            if (joueurCourant == joueur1) {
                joueurCourant = joueur2;
                System.out.println("tour du joueur " + joueur2);
            } else {
                joueurCourant = joueur1;
                System.out.println("tour du joueur " + joueur1);
            }
        }else{ 
            afficherGrille();
            return;}
        
    }

    public static void main(String[] args) {
        Morpion game = new Morpion();
        while (game.verifierGagnant()==false) {
            game.afficherGrille();
            game.selectionCase();
            game.placerJeton();
            game.verifierGagnant();
            game.changerJoueur();
        }
        System.out.println("le joueur " + game.joueurCourant + " est le gagnant");
        System.out.println("fin du programme!!");
    }
}