class Part3B
{
  static int findStopCodon(String dna,int startIdx,String stopCodon)
  {
    int idx = dna.indexOf(stopCodon,startIdx);
    return idx==-1||(idx-startIdx)%3!=0?dna.length():idx;
  }

  static int countAllGenes(String dna)
  {
    int startIdx = dna.indexOf("ATG");
    int ans = 0;
    while(startIdx!=-1)
    {
      if(findStopCodon(dna, startIdx, "TAA")!=dna.length())
        ans++;
      if(findStopCodon(dna, startIdx, "TGA")!=dna.length())
        ans++;
      if(findStopCodon(dna, startIdx, "TAG")!=dna.length())
        ans++;
      startIdx = dna.indexOf("ATG",startIdx+3);
    }
    return ans;
  }
  static void testCountAllGenes()
  {
    assert 2 == countAllGenes("ATGTAAGATGCCCTAGT");
    assert 1 == countAllGenes("GACTGATGGCTTAAGTAGTAG");
    assert 0 == countAllGenes("AATGCTAA");
  }

  public static void main(String[] args) {
     testCountAllGenes();
  }

}
