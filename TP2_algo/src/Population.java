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
    public Population generateNewPopulation() //throws ExecutionControl.NotImplementedException
    {
        //throw new ExecutionControl.NotImplementedException("Method generateNewPopulation has not been implemented yet.");

        //Utilisez les CROSSTYPE ici pour différencier le type de sélection

        if(this.crosstype == Crosstype.ROULETTE)
        {
            Population temp = this;
            Individual[] listeIndividu = new Individual[temp.getIndividuals().length];
            for (int i =0; i<temp.getIndividuals().length; i++)
            {
                Individual individu  = this.roulette(this);
                listeIndividu[i] = individu;
            }

            this.setIndividuals(listeIndividu);
        }
        else{
            //ToDo generate using a TOURNOI crosstype
        }

        //System.out.println(this);
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
            throws ExecutionControl.NotImplementedException
    {
        Individual[] offsprings = new Individual[2];

//        int[] firstChildGenes = new int[genesPerPop];
//        int[] secondChildGenes = new int[genesPerPop];
//        ToDo compute the genes
//        ToDo compute a possible mutation of a gene
//        offsprings[0] = new Individual(firstChildGenes);
//        offsprings[1] = new Individual(secondChildGenes);

        throw new ExecutionControl.NotImplementedException("Method reproduceIndividuals has not been implemented yet.");
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
        for(int i =0 ; i< pop.getIndividuals().length ; i++)
        {
            somme = somme + pop.getIndividuals()[i].computeFitnessScore();
        }

        return somme;
    }

    public Individual roulette( Population pop)
    {
        int somme = this.calculSomme(pop) ;
        //System.out.println("Somme : "+somme);
        int cumul =0;
        int index =0;

        Random rand = new Random();
        int alea = rand.nextInt(somme);

        //System.out.println("Alea : "+alea);

        while (cumul + pop.getIndividuals()[index].computeFitnessScore() < alea)
        {
            cumul += pop.getIndividuals()[index].computeFitnessScore();
            index ++;
        }

        return pop.getIndividuals()[index];
    }

}
