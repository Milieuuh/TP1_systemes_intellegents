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

}
