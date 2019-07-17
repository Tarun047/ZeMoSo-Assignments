import edu.duke.*;

import java.util.function.Predicate;
import java.util.stream.*;
import java.util.*;
import java.util.regex.*;
class Part1C
{

  static int findStopCodon(String dna,int startIdx,String stopCodon)
  {
    int currIdx = dna.indexOf(stopCodon,startIdx+3);
    while(currIdx!=-1)
    {
        int diff = currIdx-startIdx;
        if(diff%3==0)
            return currIdx;
        else
            currIdx = dna.indexOf(stopCodon,currIdx+3);
    }
    return -1;

  }

  static void saveGene(String gene,StorageResource sr)
  {
        sr.add(gene);
  }

  static String findGene(String dna,int from)
  {

      int startIdx = dna.indexOf("ATG",from);
      if(startIdx==-1)
          return "";
      int stop1 = findStopCodon(dna,startIdx,"TAA");
      int stop2 = findStopCodon(dna,startIdx,"TAG");
      int stop3 = findStopCodon(dna,startIdx,"TGA");
      int res = 0;
      if(stop1==-1 || (stop3!=-1 && stop3<stop1))
          res = stop3;
      else
          res = stop1;
      if(res==-1 || (stop2!=-1 && stop2<res))
          res = stop2;
      if(res==-1)
          return "";
      return dna.substring(startIdx,res+3);

  }

  static StorageResource getAllGenes(String dna)
  {
    dna = dna.toUpperCase();
    StorageResource sr = new StorageResource();
    int startIdx = 0;
    while(true)
    {
        String currGene = findGene(dna,startIdx);
        if(currGene.isEmpty())
            break;
        saveGene(currGene,sr);
        startIdx = dna.indexOf(currGene,startIdx)+currGene.length();
    }
    return sr;
  }

  static void testGetGene()
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
    for(String test:tests) {
      StorageResource sr = getAllGenes(test);

      System.out.println("Saved Storage Resource consists of: ");
      StreamSupport.stream(sr.data().spliterator(), false).forEach(System.out::println);
    }
  }

  static double cgRatio(String dna)
  {
    dna = dna.toLowerCase();
   long countOfC = dna.chars().filter(x -> x=='c').count();
   long countOfG = dna.chars().filter(x -> x=='g').count();
   return (double)countOfC/countOfG;
  }

  static int countCTG(String dna)
  {
    Pattern p = Pattern.compile("[^CTG]*CTG");
    Matcher m = p.matcher(dna.toUpperCase());
    int cnt = 0;
    while(m.find())
     cnt++;
    return cnt;
  }

  public static void main(String[] args) {
    //testGetGene();
    assert countCTG("ctgagctgcttcgtacgt") == 2;
    assert cgRatio("ctgagctgcttcgtacgt") == 1.0;

    FileResource fr = new FileResource("dna/brca1line.fa");

    StorageResource sr = getAllGenes(fr.asString());
    processGenes(sr);
  }

  public static void getResult(StorageResource sr,Predicate<String> stringPredicate)
  {
    List<String> result= StreamSupport.stream(sr.data().spliterator(),false).
            filter(stringPredicate).
            collect(Collectors.toList());
    System.out.println("Result: "+result);
    System.out.println("Count: "+result.size());
  }


  public static void processGenes(StorageResource sr)
  {


    System.out.println("The number of Strings whose length is greater than 60 are: ");
    getResult(sr,s->s.length()>60);
    System.out.println("The number of Strings whose length is cg ratio than 0.35 are: ");
    getResult(sr,s->cgRatio(s)>0.35);
    System.out.println("Maxiumum length is: "+StreamSupport.stream(sr.data().spliterator(),false).
            max(Comparator.comparingInt(String::length)).get().length());
  }

}
