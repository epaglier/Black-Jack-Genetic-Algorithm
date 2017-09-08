public class Card
{
  private int rank;
  private String image;
  
  public Card(int n, String s)
  {
    rank = n;
    image = n + s;
  }
  
  public int getRank()
  {
    return rank;
  }
  
  public String getImage()
  {
    return image;
  }
}