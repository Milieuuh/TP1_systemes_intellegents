import java.util.ArrayList;

public class Echiquier2emeSoluce {
    private Cellule[][] echequier;
    private int taille;

    private ArrayList<Integer> numColonnesSansReine;
    private ArrayList<Integer> numColonneDiagReineGauche;
    private ArrayList<Integer> numColonneDiagReineDroite;
    private ArrayList<Integer> numLigneDiagReineDroite;

    //constructeur
    public Echiquier2emeSoluce(int taille)
    {
        this.taille = taille;
        echequier = new Cellule[taille][taille];

        numColonnesSansReine=new ArrayList<>();
        numColonneDiagReineGauche=new ArrayList<>();
        numColonneDiagReineDroite=new ArrayList<>();
        numLigneDiagReineDroite=new ArrayList<>();

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

                if(!numColonnesSansReine.contains(y))
                {

                    numColonnesSansReine.add(y);
                }
            }
        }
    }

    public void poserReineCellule(int x,int y)
    {
        echequier[x][y].setTypeOccupation(1);

        ArrayList<Integer> temp = new ArrayList<>();

        for(int i : this.numColonnesSansReine)
        {
            if(i!=y)
            {
                temp.add(i);
            }
        }

        numColonnesSansReine=temp;
    }

    public void poserReine()
    {
        if(numColonnesSansReine.size()!=0)
        {
            for(int ligne=0;ligne<taille;ligne++)
            {
                for (int colonne = 0; colonne < taille; colonne++)
                {
                    if (numColonnesSansReine.contains(colonne) )
                    {
                        remplirDiagBasDroit(colonne);

                        if(lignePasPrise(ligne)==true  &&testDiagDroit(colonne,ligne)==false)
                        {
                            poserReineCellule(ligne,colonne);
                            poserReine();
                        }

                    }
                }
            }
        }
        else
        {
            System.out.println(toString());
        }

    }

    public void remplirDiagBasDroit(int colonne)
    {
        int nb =0;


        for (int ligne = 0; ligne < taille; ligne++) {
            for (int col = 0; col < taille; col++) {

                if ((col==colonne+nb)) {

                    if(!this.numColonneDiagReineDroite.contains(col)&&(col!=colonne))
                    {
                        this.numColonneDiagReineDroite.add(col);
                        this.numLigneDiagReineDroite.add(ligne); //MARCHE PAS !!!!!!!!!!!!!!!!!!!
                    }
                    nb++;
                }
            }
        }

    }

    public boolean testDiagDroit(int colonne, int ligne)
    {
        boolean res=false;

        if(this.numColonneDiagReineDroite.contains(colonne)&&this.numLigneDiagReineDroite.contains(ligne))
        {
            res=true;
        }

        return res;
    }


    public boolean lignePasPrise(int ligne)
    {
        boolean res=true;

        for(int colonne=0;colonne<taille;colonne++)
        {
            if(echequier[ligne][colonne].getTypeOccupation()==1)
            {
                res=false;
            }
        }

        return res;
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
