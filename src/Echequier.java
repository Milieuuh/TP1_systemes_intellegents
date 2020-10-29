public class Echequier {

    //attributs
    private Cellule[][] echequier;
    private int taille;

    //constructeur
    public Echequier(int taille)
    {
        this.taille = taille;
        echequier = new Cellule[taille][taille];
        initialiserEchequier();
    }

    //METHODES
    public void initialiserEchequier()
    {
        for(int x=0;x<taille;x++)
        {
            for(int y=0;y<taille;y++)
            {
                echequier[x][y] = new Cellule(x,y);

            }
        }
    }

    public void modifierCellule(int x,int y, int valeur)
    {
        echequier[x][y].setTypeOccupation(valeur);
    }

    public void placerReine(int x, int y) {
        //si case libre
        if ((echequier[x][y].getTypeOccupation() == 0)) {
            //case occupée par la reine
            echequier[x][y].setTypeOccupation(1);

            //MAj Menaces
            int nb = 1;
            int nbDiag2 = 1;
            int nbDiag3 = 1;
            for (int ligne = 0; ligne < taille; ligne++) {
                for (int col = 0; col < taille; col++) {
                    //ligne colonnes
                    if (((ligne == x) && (echequier[ligne][col].getTypeOccupation() == 0))
                            || ((col == y) && (echequier[ligne][col].getTypeOccupation() == 0))) {
                        echequier[ligne][col].setTypeOccupation(2);
                    }

                    //diagonale droite bas
                    if ((ligne == x + nb && col == y + nb) && echequier[ligne][col].getTypeOccupation() == 0) {
                        echequier[ligne][col].setTypeOccupation(2);
                        nb++;
                    }

                    //diagonale gauche bas
                    if ((ligne == x + nbDiag2 && col == y - nbDiag2) && echequier[ligne][col].getTypeOccupation() == 0) {
                        echequier[ligne][col].setTypeOccupation(2);
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
                        if ((i == x - nb && j == y - nb) && echequier[i][j].getTypeOccupation() == 0) {
                            echequier[i][j].setTypeOccupation(2);
                            nb++;
                        }

                    }
                }

            }

            for (int col = 0; col < taille; col++) {
                for (int ligne = taille; ligne >=0; ligne--) {
                        int  newx=x - nbDiag2;
                    //diagonale droit haut
                        if ((ligne == x - nbDiag2 && col == y + nbDiag2) && echequier[ligne][col].getTypeOccupation() == 0)
                        {
                            echequier[ligne][col].setTypeOccupation(2);
                            nbDiag2++;
                        }
                    }
                }

        }
    }


    public int calculNombreCaseMenace(int x, int y)
    {

        placerReine(x, y);

        int nbCasesMenacees = 0;

        for(int i=0; i<taille;i++)
        {
            for(int j=0; j<taille; j++)
            {
                if(echequier[i][j].getTypeOccupation()==2)
                {
                    nbCasesMenacees++;
                    echequier[i][j].setTypeOccupation(0);
                }
            }
        }

        deleteReine(x,y);

        return nbCasesMenacees;
    }


    public void deleteReine(int x, int y)
    {
        echequier[x][y].setTypeOccupation(0);
    }

    public String toString()
    {
        String s="";

        for(int ligne=0;ligne<taille;ligne++)
        {
            for (int col = 0; col < taille; col++)
            {
                s+= this.echequier[ligne][col].getTypeOccupation()+" ";
            }
            s+="\n";
        }

        return s;
    }
}
