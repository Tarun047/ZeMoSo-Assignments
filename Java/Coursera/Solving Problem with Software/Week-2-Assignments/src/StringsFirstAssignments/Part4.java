package StringsFirstAssignments;

import edu.duke.*;
class Part4
{

  public static void main(String[] args)
  {
    URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    String query = "youtube.com";
    for(String word:url.words())
    {
      int idx = word.toLowerCase().indexOf(query);
      if(idx!=-1)
      {
        int startIdx = word.lastIndexOf("\"",idx);
        int stopIdx = word.indexOf("\"",idx+query.length());
        if(startIdx!=-1 && stopIdx!=-1)
          System.out.println(word.substring(startIdx+1,stopIdx));
      }
    }

  }

}
