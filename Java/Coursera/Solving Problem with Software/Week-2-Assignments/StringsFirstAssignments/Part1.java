import java.util.List;
import java.util.Arrays;
class Part1
{

  public String findSimpleGene(String dna)
  {
    int startIdx = dna.indexOf("ATG");
    int endIdx = dna.indexOf("TAA",startIdx+3);
    if(startIdx!=-1 && endIdx!=-1)
    {
       String match = dna.substring(startIdx,endIdx+3);
       //System.out.println(match+" "+match.length());
       return match.length()%3==0?match:"";
    }
    return "";
  }

  public void testSimpleGene()
  {
     List<String> tests = Arrays.asList(
       "ATGTAA",
       "ATGGTATAA",
       "AAATGCCCTAACTAGATTAAGAAACC",
       "AGTCAA",
       "ATGAGCCGTAATCGAC",
       "ATGGCTCCATAA",
       "ATGCCCCTACGTAATCTA",
       "AGTGATTCGGCTCTGTAA",
       "AG",
       "TAA",
       "AGT"
     );
     for(String test:tests)
     {
       System.out.println(test+" dna: "+findSimpleGene(test));
     }

  }

  public static void main(String[] args)
  {
    new Part1().testSimpleGene();
  }

}
