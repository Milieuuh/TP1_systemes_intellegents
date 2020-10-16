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

    public void placerReine(int x, int y)
    {
        //si case libre
        if((echequier[x][y].getTypeOccupation()==0))
        {
            //case occupée par la reine
            echequier[x][y].setTypeOccupation(1);

            //MAj Menaces
            for(int ligne=0;ligne<taille;ligne++)
            {
                for(int col=0;col<taille;col++)
                {
                    if(((ligne==x)&&(echequier[ligne][col].getTypeOccupation()==0))
                            ||((col==y)&&(echequier[ligne][col].getTypeOccupation()==0)))
                    {
                        echequier[ligne][col].setTypeOccupation(2);
                    }
                }

            }
        }
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
