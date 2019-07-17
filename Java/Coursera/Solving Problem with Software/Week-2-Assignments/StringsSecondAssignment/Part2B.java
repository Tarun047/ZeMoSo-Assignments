import java.util.regex.*;
class Part2B
{
  static int howMany(String a,String b)
  {
    Pattern p = Pattern.compile(String.format("[^%s]*%s",a,a));
    Matcher m = p.matcher(b);
    int cnt = 0;
    while(m.find())
      cnt++;
    return cnt;
  }
  static void testHowMany()
  {
    assert 3==howMany("GAA", "ATGAACGAATTGAATC");
    assert 2==howMany("AA", "ATAAAA");
    assert 3==howMany("TAGT", "AGTAGTAGTAGTTAGT");
    assert 0==howMany("AGTTAGT", "AB");
    assert 1==howMany("TA", "TAT");
  }
  public static void main(String[] args) {
    testHowMany();
  }
}
