import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Population {

    private Individual[] individuals;
    private int genesPerPop;
    private Crosstype crosstype;
    private float mutationChance;

    /**
     * Representation of a population of pseudo-randomly generated individuals
     * @param popSize set the size of this population
     * @param genesPerPop sets the gene size of each individual in the pool
     * @param crosstype the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(int popSize, int genesPerPop, Crosstype crosstype, float mutationChance)
    {
        this.individuals = new Individual[popSize];
        this.genesPerPop = genesPerPop;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
        for(int i=0; i<popSize; i++)
            this.individuals[i] = new Individual(genesPerPop);
    }

    /**
     * Representation of a population of pre-computed individuals
     * @param individuals an array of individuals
     * @param crosstype the crosstype to be used by this population
     * @param mutationChance chance for an individual to mutate at birth
     */
    public Population(Individual[] individuals, Crosstype crosstype, float mutationChance)
    {
        assert individuals.length > 0;
        this.individuals = individuals;
        this.genesPerPop = individuals[0].getGenes().length;
        this.crosstype = crosstype;
        this.mutationChance = mutationChance;
    }

    /**
     * Creates a new population using this generation's individuals
     * @return the newly generated population
     */
    public Population generateNewPopulation()
    {
        //Utilisez les CROSSTYPE ici pour différencier le type de sélection
        Individual[]  listeIndividus = new Individual[this.getIndividuals().length];


        int compteur =0;
        if(this.crosstype == Crosstype.ROULETTE)
        {
            for (int i =0; i<this.getIndividuals().length-1; i++)
            {
                Random rand = new Random();
                int alea = rand.nextInt(genesPerPop);

               Individual premierParent = roulette(this);
               Individual secondParent = roulette(this);

              /*  System.out.println("1er parent : "+premierParent);
                System.out.println("2eme parent : "+secondParent);*/

               Individual[] enfants = this.reproduceIndividuals(premierParent, secondParent, alea);
               listeIndividus[compteur] = enfants[0];
               listeIndividus[compteur+1] = enfants[1];
               compteur++;
            }

        }
        else{
            for (int i =0; i<this.getIndividuals().length-1; i++)
            {
                Random rand = new Random();
                int alea = rand.nextInt(genesPerPop);

                Individual premierParent = tournois(this);
                Individual secondParent = tournois(this);

                Individual[] enfants = this.reproduceIndividuals(premierParent, secondParent, alea);
                listeIndividus[compteur] = enfants[0];
                listeIndividus[compteur+1] = enfants[1];
                compteur++;
            }

        }

        /////////////////////////////////////MUTATION
        for(int i=0; i<this.getIndividuals().length; i++)
        {
            Random rand2 = new Random();
            float alea = (float)Math.round(rand2.nextFloat()*100)/100;
            //System.out.println("alea : "+alea +" et mutationChance "+this.mutationChance);
            if (this.mutationChance >=alea)
            {
                System.out.println("MUTATION");
                Random rand = new Random();
                int alea2 = rand.nextInt(genesPerPop);
                this.getIndividuals()[i].geneFlip(alea2);
            }
        }
        this.setIndividuals(listeIndividus);
        return this ;
    }

    /**
     * Takes 2 individuals and create 2 children using their genes
     * @param firstParent the first selected individual
     * @param secondParent the second selected individual
     * @param crosspoint index of the crosspoint
     * @return an array of 2 individuals
     */
    public Individual[] reproduceIndividuals(Individual firstParent, Individual secondParent, int crosspoint)
    {
        Individual[] offsprings = new Individual[2];

       int[] firstChildGenes = new int[genesPerPop];
        int[] secondChildGenes = new int[genesPerPop];

        for (int i=0; i<crosspoint; i++)
        {
            firstChildGenes[i] = firstParent.getGenes()[i];
            secondChildGenes[i] = secondParent.getGenes()[i];
        }

        for (int i=crosspoint; i<genesPerPop; i++)
        {
            firstChildGenes[i] = secondParent.getGenes()[i];
            secondChildGenes[i] = firstParent.getGenes()[i];
        }

        offsprings[0] = new Individual(firstChildGenes);
        offsprings[1] = new Individual(secondChildGenes);

        return offsprings;
    }



    @Override
    public String toString()
    {
        return "Population{" +
                "individuals=" + Arrays.toString(individuals) +
                ", genesPerPop=" + genesPerPop +
                '}';
    }


    //////////////////////////////////METHODES AJOUTEES


    public Individual[] getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Individual[] newListe) {
        this.individuals = newListe;
    }

    public int calculSomme(Population pop)
    {
        int somme =0;
        for(int i =0 ; i<pop.getIndividuals().length-1; i++)
        {
            somme = somme + pop.getIndividuals()[i].computeFitnessScore();
        }

        return somme;
    }

    /*NOTES
2 x la roulette, pour avoir deux parents puis reproduction
Dès qu'il y a 50 enfants, tu t'arrêtes
Puis pourcentage de chance de mutation
Si mute, prend un gene aléatoire
     */
    public Individual roulette( Population pop)
    {
        int somme = this.calculSomme(pop) ;
        //System.out.println("Somme : "+somme);
        int cumul =0;
        int index =0;

        Random rand = new Random();
        int alea = rand.nextInt(somme);

        System.out.println("Alea : "+alea);

        while (cumul + pop.getIndividuals()[index].computeFitnessScore() < alea)
        {
            cumul += pop.getIndividuals()[index].computeFitnessScore();
            index ++;
        }

        return pop.getIndividuals()[index];
    }


    //////////////////////////TOURNOIS
    public Population melangerPop(Population pop)
    {
        for(int i =0 ; i<pop.getIndividuals().length; i++)
        {
            Random rand = new Random();
            int alea1 = rand.nextInt(pop.getIndividuals().length-1);
            int alea2 = rand.nextInt(pop.getIndividuals().length-1);

            Individual temp = pop.getIndividuals()[alea1];
            pop.getIndividuals()[alea1] = pop.getIndividuals()[alea2];
            pop.getIndividuals()[alea2] = temp;
        }
        return pop;
    }

    public Individual tournois(Population p)
    {
        p = this.melangerPop(p);
        Individual select1 = p.getIndividuals()[0];
        Individual select2 = p.getIndividuals()[1];

        Individual meilleur  = select1;

        if(select2.computeFitnessScore() > select1.computeFitnessScore())
        {
            meilleur = select2;
        }
        return meilleur;

    }

}
