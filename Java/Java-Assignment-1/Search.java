/*
Create a java program to search through the home directory and look for files that match a regular expression.
 The program should be able to take inputs repeatedly and should print out the full absolute path of the matching files found.
Provide appropriate documentation and comments on your code.
*/
import java.io.*;
import java.util.regex.*;
class Search
{
  static boolean recursive = true; // Variable that defines the state if recursive search is to be performed
  static Pattern pattern;
  public static void search(String path,String regex)
  {
    /*
    Method name: search
    Method parameters:-
      path: String - Describes the path in which search is to be performed
      regex: String - Describes the regular expression pattern on which matching is done
    Functionality:
        This method searches for the file names matching the regex in the given path
        recursively unless the recursion mode is turned off.
    */
    File folder = new File(path);
    pattern = Pattern.compile(regex);
    for(File file:folder.listFiles())
    {
      if(file.isHidden()) continue; // If a file is hidden it's probably not a user concern.
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
