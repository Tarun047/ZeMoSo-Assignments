import java.io.*;

/*
 Question:
 Error Handling

Create three new types of exceptions. Write a class with a method that throws all three. In main( ),
call the method but only use a single catch clause that will catch all three types of exceptions.
Add a finally clause and verify that your finally clause is executed.

My code tries to compile a source code to it's language executable format,
sample usage can be like:
java ExceptionHandling code.c c
java ExceptionHandling MyClass.java c - this line throws LanguageMismatchException
*/

class ExceptionHandling
{
  public static void main(String args[])
  {
    try{
        executeProgram(args);
        System.out.println("Execution Success");
      }
     catch(Exception e)
     {
       e.printStackTrace();
     }
     finally
     {
       System.out.println("Exiting Program!");
     }
  }
  public static void executeProgram(String args[])throws LanguageMismatchException,EmptySourceFileException,CompilationException,IOException
  {
    if(args.length!=2)
      throw new EmptySourceFileException();
    String source = args[0].toLowerCase(),dest = args[1].toLowerCase();
    if(!source.substring(source.indexOf(".")+1).equals(dest))
      throw new LanguageMismatchException(args[0],args[1]);
    String cmd;
    if(dest.equals("c"))
      cmd = "gcc "+args[0]+" -o code";
    else if(dest.equals("cpp"))
      cmd = "g++ "+args[0]+" -o code";
    else if(dest.equals("java"))
      cmd = "javac "+args[0];
    else if(dest.equals("py"))
      cmd = "python3 "+args[0];
    else
      throw new EmptySourceFileException(args[0]);
    Process proc = Runtime.getRuntime().exec(cmd);
    try{
    if(proc.exitValue()!=0)
      throw new CompilationException(proc.getInputStream(),args[0]);
    }
    catch(IllegalThreadStateException e)
    {}
  }
}

class LanguageMismatchException extends Exception
{
  private String srcLang;
  private String destLang;

  public LanguageMismatchException(String source,String dest)
  {
    super();
    srcLang = source;
    destLang = dest;
  }
  public String toString()
  {
    return "Language Mismatch Exception: Cannot convert a "+srcLang+" source code to a "+destLang+" executable";
  }
}

class EmptySourceFileException extends Exception
{
  private String filePath;
  EmptySourceFileException()
  {
    filePath = " null ";
  }
  EmptySourceFileException(String path)
  {
    super();
    filePath = path;
  }
  public String toString()
  {
    return "EmptySourceFile Exception: The source file at "+filePath+" is either unaccessable or empty";
  }
}

class CompilationException extends Exception
{
   private String filePath;
   private String errorReason="";
   CompilationException(InputStream stream,String filePath){
     super();
     filePath = filePath;
     try(BufferedReader br = new BufferedReader(new InputStreamReader(stream)))
     {
       String line;
       while((line = br.readLine())!=null)
        errorReason+=line;
     }
     catch(Exception e)
     {
       e.printStackTrace();
     }
   }
   public String toString()
   {
     return "Compilation Exception:\nInput file: "+filePath+" contains one or more compilation issues."+"\n"+errorReason;
   }
}
