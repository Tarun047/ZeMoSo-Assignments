import java.util.regex.Pattern;
/*

Question:

Using the documentation for java.util.regex.Pattern as a resource,
write and test a regular expression that checks a sentence to see that it begins with a capital letter
and ends with a period.
*/

class Match
{
  static Pattern mPattern = Pattern.compile("^[A-Z].*\\.$");
  static String tests[] = new String[]{
    "A.",
    "ABacZ.",
    "a .",
    "A B AEEQad sdasdweqads.",
    "xacsd . sad. awe2113 ;.",
    ".",
    "",
    "GRDSDASae r.",
    "Ppl.",
    "123 sad asdq341 $%",
    "@12311__adas .",
    "Final very very large match Test with 0123456789    .asd ."
  };
  static boolean[] results = {
    true,
    true,
    false,
    true,
    false,
    false,
    false,
    true,
    true,
    false,
    false,
    true,
  };
  public static void main(String args[])
  {
    regexTest();
  }

  public static void regexTest()
  {
    int i=0;
    for(String test:tests)
    {
      assert results[i]==mPattern.matcher(test).matches():"Failed for case "+(i+1);
      i++;
    }
  }
}
