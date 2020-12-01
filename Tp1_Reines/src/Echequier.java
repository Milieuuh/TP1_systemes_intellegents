import java.util.ArrayList;

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

    public void placerReine(int x, int y, int typeMenace) {
        //si case libre
        if ((echequier[x][y].getTypeOccupation() == 0)) {
            //case occupée par la reine
            echequier[x][y].setTypeOccupation(1);

            //MAj Menaces
            int nb = 1;
            int nbDiag2 = 1;
            for (int ligne = 0; ligne < taille; ligne++) {
                for (int col = 0; col < taille; col++) {
                    //ligne colonnes
                    if (((ligne == x) && (echequier[ligne][col].getTypeOccupation() == 0))
                            || ((col == y) && (echequier[ligne][col].getTypeOccupation() == 0))) {
                        echequier[ligne][col].setTypeOccupation(typeMenace);
                    }

                    //diagonale droite bas
                    if ((ligne == x + nb && col == y + nb) && echequier[ligne][col].getTypeOccupation() == 0) {
                        echequier[ligne][col].setTypeOccupation(typeMenace);
                        nb++;
                    }

                    //diagonale gauche bas
                    if ((ligne == x + nbDiag2 && col == y - nbDiag2) && echequier[ligne][col].getTypeOccupation() == 0) {
                        echequier[ligne][col].setTypeOccupation(typeMenace);
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
                            echequier[i][j].setTypeOccupation(typeMenace);
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
                            echequier[ligne][col].setTypeOccupation(typeMenace);
                            nbDiag2++;
                        }
                    }
                }

        }
    }


    public int calculNombreCaseMenace(int x, int y)
    {

        placerReine(x, y,2);

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

    public void placerReineDefitivement()
    {
        int compteur =0;
        ArrayList<Cellule> listeCellules = new ArrayList<>();

        for(int i=0; i<taille;i++)
        {
            for (int j = 0; j < taille; j++)
            {
                if(echequier[i][j].getTypeOccupation()==0)
                {
                    listeCellules.add(new Cellule(i,j));
                }
            }
        }

        while (listeCellules.size()!=0)
        {
            Cellule laBest = listeCellules.get(0);
            int nb1 = this.calculNombreCaseMenace(laBest.getX(), laBest.getY());
            for (Cellule c : listeCellules)
            {
                int nb2 = this.calculNombreCaseMenace(c.getX(), c.getY());
                if(nb2<nb1)
                {
                    laBest = c;
                }
            }

            compteur++;
            this.afficherPositionLaReine(laBest, compteur);
            listeCellules.removeAll(listeCellules);
            this.placerReine(laBest.getX(), laBest.getY(), 4);

            for(int i=0; i<taille;i++)
            {
                for (int j = 0; j < taille; j++)
                {
                    if(echequier[i][j].getTypeOccupation()==0)
                    {
                        listeCellules.add(new Cellule(i,j));
                    }
                }
            }
        }
    }

    public void afficherPositionLaReine(Cellule reine, int compteur)
    {
        System.out.println("Reine n°"+compteur+" : x = "+reine.getX()+", y = "+reine.getY());
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
