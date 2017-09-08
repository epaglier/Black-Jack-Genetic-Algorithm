public class Organism
{
  public String[][] allele;
  public int wins;
  
  public Organism()
  {
    wins = 0;
    allele = new String[21][11];
    
    for (int r = 0; r < allele.length; r++)
    {
      for (int c = 0; c < allele[0].length; c++)
      {
        int temp = (int)(Math.random()*2) + 1;
        if (temp == 1)
        {
          allele[r][c] = "h";
        }
        else
        {
          allele[r][c] = "s";
        }
      }
    }
  }
  
  public Organism(String[][] array)
  {
    allele = array;
  }
  
  public void play(Blackjack game, int rounds)
  {
    while(rounds > 0)
    {
      String loop = game.result();
      while (loop.equals(""))
      {
        int playerScore = game.getScore(game.getPlayerHand());
        int shownDealerScore = game.getShownDealerCard().getRank();
        if (shownDealerScore > 11)
          shownDealerScore = 10;
        String temp = allele[playerScore - 1][shownDealerScore - 1];
        if (temp.equals("h"))
        {
          game.hit();
        }
        else
        {
          game.stand();
        }
        loop = game.result();
      }
      if (loop.equals("win"))
      {
        wins++;
      }
      rounds--;
    }
  }
  
  public int getWins()
  {
    return wins;
  }
  
  public void resetWins()
  {
    wins = 0;
  }
  
  public Organism copy()
  {
    String[][] array = new String[allele.length][allele[0].length];
    for (int i = 0; i < array.length; i++)
    {
      for (int j = 0; j < array[0].length; j++)
      {
        //chance of mutation
        if (Math.random() < 0.06)
          array[i][j] = Math.random() < 0.5 ? "h" : "s";
        else
          array[i][j] = allele[i][j];
      }
    }
    return new Organism(array);
  }
  
  public void printStrategy()
  {
    for (int r = 0; r < allele.length; r++)
    {
      for (int c = 0; c < allele[0].length; c++)
      {
        System.out.print(allele[r][c] + ",");
      }
      System.out.println("");
    }
  }
}