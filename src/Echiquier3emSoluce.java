import java.util.ArrayList;

public class Echiquier3emSoluce {

    //ATTRIBUTS
    private Cellule[][] echiquier;
    private int taille;

    private ArrayList<Cellule> celluleMenace;
    private ArrayList<Cellule> celulleOccupeeParReine;

    //constructeur
    public Echiquier3emSoluce(int taille)
    {
        this.taille = taille;
        echiquier = new Cellule[taille][taille];

        this.celluleMenace=new ArrayList<>();
        this.celulleOccupeeParReine=new ArrayList<>();

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

    /////////////////////////////////////////////////////Poser reine
    public void PlacerReine()
    {
        boolean aPoser=false;


        for(int ligne=0;ligne<taille;ligne++)
        {
            for(int colonne=0;colonne<taille;colonne++)
            {
                if(!this.celluleMenace.contains(echiquier[ligne][colonne]) && aPoser==false)
                {
                    this.celulleOccupeeParReine.add(echiquier[ligne][colonne]);
                    miseAJourCaseMenace(echiquier[ligne][colonne]);
                    aPoser=true;
                }
            }
        }


        if(aPoser==false)
        {
            System.out.println(toString());

            if(celulleOccupeeParReine.size()<taille-1)
            {

                System.out.println("Pas optimisé");
                int alea = (int)(Math.random()*7);

                celluleMenace= new ArrayList<>();
                celulleOccupeeParReine = new ArrayList<>();

                this.celulleOccupeeParReine.add(echiquier[0][alea]);
                miseAJourCaseMenace(echiquier[0][alea]);

                PlacerReine();
            }
            else if (celulleOccupeeParReine.size()==taille-1)
            {
                System.out.println("Presque optimisé");

                int alea = (int)(Math.random()*7);

                celluleMenace= new ArrayList<>();
                ArrayList<Cellule>temp = new ArrayList<>();

                for(Cellule c : this.celulleOccupeeParReine)
                {
                    if(c.getX()==0 || c.getX()==1   )
                    {
                        temp.add(c);
                        miseAJourCaseMenace(echiquier[c.getX()][c.getY()]);
                    }

                }

                this.celulleOccupeeParReine=temp;

                this.celulleOccupeeParReine.add(echiquier[2][alea]);
                miseAJourCaseMenace(echiquier[2][alea]);

                PlacerReine();
            }
        }
        else
        {
            PlacerReine();
        }

    }

    public void miseAJourCaseMenace(Cellule caseReine)
    {
        int nb=0;
        int nb2=0;

        for(int ligne=caseReine.getX();ligne<taille;ligne++)
        {
            for(int colonne=0;colonne<taille;colonne++)
            {
                //LIGNE ET COLONNES CONDAMNEES
                if(ligne==caseReine.getX()&&!this.celluleMenace.contains(echiquier[ligne][colonne])&&!this.celulleOccupeeParReine.contains(echiquier[ligne][colonne]))
                {
                    this.celluleMenace.add(echiquier[ligne][colonne]);
                    this.modifierCellule(ligne,colonne,2);
                }
                if(colonne==caseReine.getY()&&!this.celluleMenace.contains(echiquier[ligne][colonne])&&!this.celulleOccupeeParReine.contains(echiquier[ligne][colonne]))
                {
                    this.celluleMenace.add(echiquier[ligne][colonne]);
                    this.modifierCellule(ligne,colonne,2);
                }
                //DIAGONALES
                int col = caseReine.getY()+nb;
                int li = caseReine.getX()+nb;

                if(colonne==col && ligne==li && !this.celluleMenace.contains(echiquier[ligne][colonne]))
                {
                    this.celluleMenace.add(echiquier[ligne][colonne]);
                    this.modifierCellule(ligne,colonne,2);
                }

                int col2 = caseReine.getY()-nb2;

                if(colonne==col2 && ligne==li && !this.celluleMenace.contains(echiquier[ligne][colonne]))
                {
                    this.celluleMenace.add(echiquier[ligne][colonne]);
                    this.modifierCellule(ligne,colonne,2);
                }
            }
            nb++;
            nb2++;
        }


    }

    public void modifierCellule(int x,int y, int valeur)
    {
        echiquier[x][y].setTypeOccupation(valeur);
    }


    /////////////////////////////////////////////////////////////TO string
    public String toString()
    {
        String res="Cellules occupées par les reines ";
        for(Cellule c: this.celulleOccupeeParReine)
        {
            res+=c.getX()+"/"+c.getY()+" - " ;
        }


        /*res+="\nPLATEAU : \n";
        for(int ligne=0;ligne<taille;ligne++)
        {
            for (int col = 0; col < taille; col++)
            {

                res+= this.echiquier[ligne][col].getTypeOccupation()+" ";
            }
            res+="\n";
        }
*/

        return res;
    }
}
