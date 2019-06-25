import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
class Checker
{
  public static boolean solve(char sequence[])
  {
    Set<Character> uniques = new HashSet<>();
    for(char ch:sequence)
      uniques.add(ch);
    for(char ch='a';ch!='z';ch++)
      if(!uniques.contains(ch))
        return false;
    return true;
  }
  public static void main(String []args)throws IOException
  {

    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
    {
      System.out.println("Enter the input string: ");
      char sequence[] = br.readLine().toLowerCase().toCharArray();
      System.out.println(solve(sequence));
    }
  }
}
