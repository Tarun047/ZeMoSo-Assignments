import java.io.*;
import java.util.regex.*;
class Search
{
  static boolean recursive = true;
  static Pattern pattern;
  public static void search(String path,String regex)
  {
    File folder = new File(path);
    pattern = Pattern.compile(regex);
    for(File file:folder.listFiles())
    {
      if(file.isHidden()) continue;
      if(file.isFile() && pattern.matcher(file.getName()).matches())
        System.out.println(file.getAbsolutePath());
      else if(file.isDirectory() && recursive)
        search(file.getAbsolutePath(),regex);
    }
  }
  public static void main(String []args)throws Exception
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Do you wan't recursive search(y/n)(default = yes):");
    String choice = br.readLine();
    recursive = (choice.length()==0?true:choice.toLowerCase().charAt(0) =='y');
    while(true)
    {
       System.out.println("Enter the regex to search (CTRL+C to quit): ");
       String regex = br.readLine();
       search(System.getProperty("user.home"),regex);
    }
  }
}
