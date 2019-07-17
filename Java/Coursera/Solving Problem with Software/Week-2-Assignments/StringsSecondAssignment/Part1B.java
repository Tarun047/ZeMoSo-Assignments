import java.util.List;
import java.util.Random;
import java.util.*;
class Part1B
{
  int findStopCodon(String dna,int startIdx,String stopCodon)
  {
    int idx = dna.indexOf(stopCodon,startIdx);
    return idx==-1||(idx-startIdx)%3!=0?dna.length():idx;
  }

  void testFindStopCodon()
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
    Random gen = new Random();

    for(String test:tests)
    {
      int idx = gen.nextInt(test.length());
      System.out.println(test+" stop codon index starting from the index "+idx+" is at "+findStopCodon(test,idx,"TAA"));
    }
  }


  String findGene(String dna)
  {
    int idx = dna.indexOf("ATG");
    //System.out.println(idx);
    if(idx==-1)
      return "";
    String ans = "";
    int ends[] = new int[3];
    ends[0]=findStopCodon(dna,idx+3,"TAA");
    ends[1]=findStopCodon(dna,idx+3,"TAG");
    ends[2]=findStopCodon(dna,idx+3,"TGA");
    //System.out.println(Arrays.toString(ends));
    int minDist = Integer.MAX_VALUE;
    for(int end:ends)
    {
      if(end!=dna.length() && minDist>(end-idx))
      {

          minDist=end-idx;
          ans = dna.substring(idx,end+3);

      }
    }
    return ans;
  }

  void testFindGene()
  {
    List<String> tests = Arrays.asList(
      "ATGTAA",
      "ATGGTATAG",
      "AAATGCCCTGACTAGATTAAGAAACC",
      "AGTCAA",
      "AGTCCGTAGATCGAC",
      "AATGCTAACTAGCTGACTAAT"
      );

    for(String test:tests)
    {
      System.out.println("Test "+test+" : "+findGene(test));
    }
  }

  void printGene(String dna,int startIdx,int stopIdx)
  {
    if(stopIdx!=dna.length())
      System.out.println("Gene found is: "+dna.substring(startIdx,stopIdx+3));
  }

  void printAllGenes(String dna)
  {
    int startIdx = dna.indexOf("ATG");

    while(startIdx!=-1)
    {
      printGene(dna, startIdx, findStopCodon(dna, startIdx, "TAA"));
      printGene(dna,startIdx,findStopCodon(dna, startIdx, "TGA"));
      printGene(dna,startIdx,findStopCodon(dna, startIdx, "TAG"));
      startIdx = dna.indexOf("ATG",startIdx+3);
    }
  }

  public static void main(String[] args)
  {
     Part1B myDriver = new Part1B();
     System.out.println("Test for find stop codon ... ");
     myDriver.testFindStopCodon();
     System.out.println("\nTest for find gene ... ");
     myDriver.testFindGene();
     System.out.println("\nTest for finding multiple genes: ");
     myDriver.printAllGenes("ATGTAATAGACGGAGTCGTTGCTAGAGTTGA");
  }

}
