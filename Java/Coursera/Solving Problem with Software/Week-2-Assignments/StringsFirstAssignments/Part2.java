

import java.util.Arrays;
import java.util.List;
class Part2
{
  public String findSimpleGene(String dna,String startCodon,String stopCodon)
  {
    if(dna.charAt(0)>='a' && dna.charAt(0)<='z'){
      startCodon = startCodon.toLowerCase();
      stopCodon = stopCodon.toLowerCase();
    }
    else
    {
      startCodon=startCodon.toUpperCase();
      stopCodon=stopCodon.toUpperCase();
    }
    int startIdx = dna.indexOf(startCodon);
    int endIdx = dna.indexOf(stopCodon,startIdx+3);
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
       "AgtCAA",
       "ATGAGCCGTAATCGAC",
       "atggctccataa",
       "ATGCCCCTACGTAATCTA",
       "atgtaagattcggctctgtaa",
       "AG",
       "TAA",
       "AGT"
     );
     for(String test:tests)
     {
       System.out.println(test+" dna: "+findSimpleGene(test,"ATG","TAA"));
     }

  }
  public static void main(String[] args) {
    new Part2().testSimpleGene();
  }

}
