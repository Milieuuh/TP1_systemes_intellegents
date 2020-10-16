public class main {

    public static void main(String[] args) {

        Echequier test = new Echequier(8);

        test.toString();

        test.placerReine(1,1);

        System.out.println("Placement reine\n");

        test.toString();
    }
}
