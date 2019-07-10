import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;

class Counter
{
 public static void main(String args[])throws IOException
 {

   try(FileReader fr = new FileReader(args[0]);BufferedReader br  = new BufferedReader(fr);FileWriter fw = new FileWriter("Result.txt");)
   {

       Map<Character,Integer> charMap = new TreeMap<>();
       int ch;
       while((ch = br.read())!=-1)
          charMap.put((char)ch,charMap.getOrDefault((char)ch,0)+1);
      for(char key:charMap.keySet())
      {
        if(isPrintableChar(key))
          fw.write((key!=' '?key:"space")+" "+charMap.get(key)+"\n");
      }
   }
   catch(Exception e)
  {
   e.printStackTrace();
  }
 }
 public static boolean isPrintableChar( char c )
 {
    Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
    return (!Character.isISOControl(c)) &&
            block != null &&
            block != Character.UnicodeBlock.SPECIALS;
  }
}
