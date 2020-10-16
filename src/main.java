public class main {

    public static void main(String[] args) {

        Echequier test = new Echequier(8);

        System.out.println(test);

        test.placerReine(1,1);

        System.out.println("Placement reine\n");

        System.out.println(test);
    }
}
