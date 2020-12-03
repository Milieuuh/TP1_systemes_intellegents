import java.util.ArrayList;

public class EchiquierTheLast {

    //ATTRIBUTS
    private Cellule[][] echiquier;
    private int taille;
    private boolean test=false;

    private ArrayList<Cellule> celluleMenace;
    private ArrayList<Cellule> celulleOccupeeParReine;

    //constructeur
    public EchiquierTheLast(int taille)
    {
        this.taille = taille;
        this.echiquier = new Cellule[taille][taille];

        initialiserEchequier();
    }

    //METHODES

    //INITIALISATION DE L'ECHIQUIER
    public void initialiserEchequier()
    {
        for(int x=0;x<taille;x++)
        {
            for(int y=0;y<taille;y++)
            {
                echiquier[x][y] = new Cellule(x,y);
            }
        }
    }

    public void modifierCellule(int x,int y, int valeur)
    {
        echiquier[x][y].setTypeOccupation(valeur);
    }

    public void placementAutomatique(int ligne)
    {
        ArrayList<Integer> nbDansEchiquier = new ArrayList<>();
        for(int x=0;x<taille;x++)
        {
            for (int col = 0; col < taille; col++)
            {
                nbDansEchiquier.add(this.echiquier[x][col].getTypeOccupation());
            }
        }

        if(!nbDansEchiquier.contains(0))
        {
            test=true;
        }

        int colonne = (int)(Math.random()*7);
        while(test==false)
        {
            System.out.println(ligne);

            if(echiquier[ligne][colonne].getTypeOccupation()==0)
            {
                this.placerReine(ligne, colonne);
                this.placementAutomatique(ligne+1);
            }
            else
            {
                ArrayList<Integer> listeNbDansLigne = new ArrayList<>();
                for(int x=0;x<taille;x++)
                {
                    if(echiquier[ligne][x].getTypeOccupation()==0)
                    {
                        listeNbDansLigne.add(x);
                    }
                }
                System.out.println("-->"+listeNbDansLigne.size());
                if(listeNbDansLigne.size()==1)
                {
                    colonne=listeNbDansLigne.get(0);
                }
                else
                {
                    int alea = (int)(Math.random()*listeNbDansLigne.size());
                    colonne = listeNbDansLigne.get(alea);
                    System.out.println("col : "+colonne);
                }

            }




        }
    }

    public void placerReine(int x, int y) {
        //si case libre
        if ((echiquier[x][y].getTypeOccupation() == 0)) {
            //case occupée par la reine
            echiquier[x][y].setTypeOccupation(1);

            //MAj Menaces
            int nb = 1;
            int nbDiag2 = 1;
            for (int ligne = 0; ligne < taille; ligne++) {
                for (int col = 0; col < taille; col++) {
                    //ligne colonnes
                    if (((ligne == x) && (echiquier[ligne][col].getTypeOccupation() == 0))
                            || ((col == y) && (echiquier[ligne][col].getTypeOccupation() == 0))) {
                        echiquier[ligne][col].setTypeOccupation(2);
                    }

                    //diagonale droite bas
                    if ((ligne == x + nb && col == y + nb) && echiquier[ligne][col].getTypeOccupation() == 0) {
                        echiquier[ligne][col].setTypeOccupation(2);
                        nb++;
                    }

                    //diagonale gauche bas
                    if ((ligne == x + nbDiag2 && col == y - nbDiag2) && echiquier[ligne][col].getTypeOccupation() == 0) {
                        echiquier[ligne][col].setTypeOccupation(2);
                        nbDiag2++;
                    }

                }
            }

            nb = 1;
            nbDiag2 = 1;
            if (x != 0 || y != 0) {

                for (int i = x; i >= 0; i--) {
                    for (int j = y; j >= 0; j--) {
                        //diagonale gauche haut
                        if ((i == x - nb && j == y - nb) && echiquier[i][j].getTypeOccupation() == 0) {
                            echiquier[i][j].setTypeOccupation(2);
                            nb++;
                        }

                    }
                }

            }

            for (int col = 0; col < taille; col++) {
                for (int ligne = taille; ligne >=0; ligne--) {
                    int  newx=x - nbDiag2;
                    //diagonale droit haut
                    if ((ligne == x - nbDiag2 && col == y + nbDiag2) && echiquier[ligne][col].getTypeOccupation() == 0)
                    {
                        echiquier[ligne][col].setTypeOccupation(2);
                        nbDiag2++;
                    }
                }
            }

        }
    }


    public void afficherPositionLaReine(Cellule reine, int compteur)
    {
        System.out.println("Reine n°"+compteur+" : x = "+reine.getX()+", y = "+reine.getY());
    }

    public String toString()
    {
        String s="";

        for(int ligne=0;ligne<taille;ligne++)
        {
            for (int col = 0; col < taille; col++)
            {
                s+= this.echiquier[ligne][col].getTypeOccupation()+" ";
            }
            s+="\n";
        }

        return s;
    }

}
