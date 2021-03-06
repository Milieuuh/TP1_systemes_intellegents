public class Cellule {

    //ATTRIBUTS
    private int x;
    private int y;

    private int typeOccupation; //peut être libre, avec une reine, menacée par une reine

    final static int LIBRE=0;
    final static int REINE=1;
    final static int MENACEE=2;
    
    final static int REINEDEF=3;
    final static int MENACEDEF=4;


    //CONSTRUCTEUR
    public Cellule (int x, int y)
    {
        this.x = x;
        this.y = y;
        this.typeOccupation = LIBRE;  
    }


    //GETTER ET SETTER
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTypeOccupation() {
        return typeOccupation;
    }

    public void setTypeOccupation(int typeOccupation) {
        this.typeOccupation = typeOccupation;
    }


}
