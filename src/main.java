public class main {

    public static void main(String[] args) {

        Echequier test = new Echequier(8);

        System.out.println(test);

        //test.placerReine(4,2);

        //System.out.println("Placement reine\n");

        System.out.println("Placement reine en 4/2\n");
        System.out.println("Nombre cases menacées :"+test.calculNombreCaseMenace(4,2));

        System.out.println("\n-------------------------Placement DES reines---------------------");
        test.placerReineDefitivement();

        System.out.println(test);
    }
}
