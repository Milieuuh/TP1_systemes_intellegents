public class main {

    public static void main(String[] args) {

        Echequier test = new Echequier(8);

        //System.out.println(test);

        //test.placerReine(4,2);

        //System.out.println("Placement reine\n");

        /*System.out.println("Placement reine en 4/2\n");
        System.out.println("Nombre cases menacées :"+test.calculNombreCaseMenace(4,2));

        System.out.println("\n-------------------------Placement DES reines---------------------");
        test.placerReineDefitivement();

        System.out.println(test);*/

        Echiquier2emeSoluce test2 = new Echiquier2emeSoluce(8);

        /*test2.poserReineCellule(0,0);
        test2.poserReineCellule(1,2);

        test2.remplirDiagBasDroit(0);*/
        test2.poserReine();


    }
}
