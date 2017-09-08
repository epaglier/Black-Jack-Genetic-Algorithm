public class GeneticAlgorithm
{
  private Organism[] population;
  private Blackjack game;
  
  public static void main(String[] args)
  {
    GeneticAlgorithm g = new GeneticAlgorithm();
    g.loop();
  }
  
  public GeneticAlgorithm()
  {
    population = new Organism[100];
    for (int i = 0; i < population.length; i++)
      population[i] = new Organism();
    game = new Blackjack();
  }
  
  public void loop()
  {
    int number = 0;
    while (true)
    {
      for (int i = 0; i < population.length; i++)
      {
        //second peramiter is number of games to play CHANGE THIS TO SLOW IT DOWN
        population[i].play(game, 1000);
      }
      
      sortPopulationByWins(population);
      
      number++;
      
      //pick winners and go again
      System.out.println("Generation: " + number + ": Wins " + population[99].getWins());
      
      //selection CHANGE THE 100!!!!!!!!! based on num games
      for (int i = 0; i < 10; i++)
      {
        population[i] = population[99 - i].copy();
      }
      
      if (population[99].getWins() >= 420)
      {
        population[99].printStrategy();
      }
      
      if (population[99].getWins() >= 500)
        System.exit(0);
      
      for (Organism org: population)
        org.resetWins();
    }
  }
  
  public void sortPopulationByWins(Organism[] a)
  {
    for (int i = 0; i < a.length; i++)
    {
      int min = indexOfMin(a, i);
      Organism save = a[i];
      a[i] = a[min];
      a[min] = save;
    }
  }
  
  public static int indexOfMin(Organism[] a, int startIndex)
  {
    int lowest = startIndex;
    Organism y = a[lowest];
    for (int i = startIndex; i < a.length; i++)
    {
      if (y.getWins() > a[i].getWins())
      {
        y = a[i];
        lowest = i;
      }
    }
    return lowest;
  }
}