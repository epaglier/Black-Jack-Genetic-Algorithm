import java.util.*;
import java.io.*;

public class Blackjack
{
  private ArrayList<Card> deck;
  private ArrayList<Card> playerHand;
  private ArrayList<Card> dealerHand;
  private boolean showDealerCard;
  private BufferedReader in;
  private String winOrLoss;
  
  public static void main(String[] args)
  {
    Blackjack b = new Blackjack();
    try{b.play();}
    catch (IOException e){}
  }
  
  public Blackjack()
  {
    winOrLoss = "";
    in = new BufferedReader(new InputStreamReader(System.in));
    deal();
    //updateDisplay();
  }
  
  public void play() throws IOException
  {
    while (true)
    {
      String temp = in.readLine();
      if (temp.equals("h"))
        hit();
      else if(temp.equals("s"))
        stand();
    }
  }
  
  public void deal()
  {
    deck = new ArrayList<Card>();
    playerHand = new ArrayList<Card>();
    dealerHand = new ArrayList<Card>();
    showDealerCard = false;
    //put all cards in deck in order
    for (int i = 1; i <= 13; i++)
    {
      deck.add(new Card(i,"h"));
      deck.add(new Card(i,"d"));
      deck.add(new Card(i,"c"));
      deck.add(new Card(i, "s"));
    }
    
    //randomizes the player card selected
    //makes sure that the cards dont repeat
    int x = 0;
    for (int i = 0; i <= 1; i++)
    {
      int n = (int)(Math.random()*deck.size());
      Card player = deck.remove(n);
      playerHand.add(player);
      x = getScore(playerHand);
    }
    for (int i = 0; i <= 1; i++)
    {
      int n = (int)(Math.random()*deck.size());
      Card dealer = deck.remove(n);
      dealerHand.add(dealer);
      int y = getScore(dealerHand);
      if (y == 21)
        stand();
    }
    if (x == 21)
    {
      //System.out.println("You Win!");
      deal();
    }
  }
  
  
  
  public int getScore(ArrayList<Card> hand)
  {
    int tot = 0;
    int numAces = 0;
    for (int i = 0; i <= hand.size() - 1; i++)
    {
      Card c = hand.get(i);
      int add = c.getRank();
      if (add > 10 && add <= 13)
        add = 10;
      if (add == 1)
      {
        add = 11;
        numAces++;
      }
      tot = tot + add;
      //if bust add 1 if ace
      while (tot > 21 && numAces > 0)
      {
        tot = tot - 10;
        numAces--;
      }
    }
    return tot;
  }
  
  
  public ArrayList<Card> getPlayerHand()
  {
    return playerHand;
  }
  
  public Card getShownDealerCard()
  {
    return dealerHand.get(1);
  }
  
  public void hit()
  {
    //System.out.println("hit");
    //System.out.println("");
    int n = (int)(Math.random()*deck.size());
    Card player = deck.remove(n);
    playerHand.add(player);
    //updateDisplay();
    if (getScore(playerHand) > 21)
    {
      winOrLoss = "loss";
      //System.out.println("HAHAH YOU BUSTED");
      //System.out.println("");
      deal();
      //updateDisplay();
    }
  }
  
  public void stand()
  {
    //System.out.println("stand");
    //System.out.println("");
    int playerScore = getScore(playerHand);
    int dealerScore = getScore(dealerHand);
    showDealerCard = true;
    while (dealerScore < 16)
    {
      int n = (int)(Math.random()*deck.size());
      Card dealer = deck.remove(n);
      dealerHand.add(dealer);
      dealerScore = getScore(dealerHand);
    }
    //updateDisplay();
    if (dealerScore > playerScore && dealerScore < 22)
    {
      winOrLoss = "loss";
      //System.out.println("HOUSE WINS");
      //System.out.println("");
    }
    if (dealerScore > 21 || playerScore > dealerScore)
    {
      winOrLoss = "win";
      //System.out.println("YOU WIN!");
      //System.out.println("");
    }
    if (playerScore == dealerScore)
    {
      winOrLoss = "tie";
      //System.out.println("TIE");
      //System.out.println("");
    }
    deal();
    //updateDisplay();
  }
  
  public void updateDisplay()
  {
    String s = "Dealer(" + getScore(dealerHand) +"): ";
    for (Card card: dealerHand)
      s += card.getImage() + " : ";
    //System.out.println(s);
    //System.out.println(" ");
    s = "You("+ getScore(playerHand) + "): ";
    for (Card card: playerHand)
      s += card.getImage() + " : ";
    //System.out.println(s);
    //System.out.println(" ");
  }
  
  public String result()
  {
    String temp = winOrLoss;
    winOrLoss = "";
    return temp;
  }
}