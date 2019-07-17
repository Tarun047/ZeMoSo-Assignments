

import java.util.function.*;
class Part3A
{
  public boolean twoOccourences(String a,String b)
  {

    int idx = b.indexOf(a);
    if(idx==-1)
      return false;
    return b.substring(idx+a.length()).indexOf(a)!=-1;
  }

  public void printResult(String a[],String b[],BiFunction<String,String,Object> myFunc)
  {
    for(int i=0;i<Math.min(a.length,b.length);i++)
    {
      Object result = myFunc.apply(a[i], b[i]);
      System.out.println(String.format("a = %2s b = %2s result = %2s",
      a[i],b[i],result));
    }
  }

  public void testing()
  {
    String aTests[] = new String[]{
      "car",
      "java",
      "struts",
      "hibernate",
      "jsx",
      "rest",
      "data",
      "dark",
      "web",
      "browse",
    };

    String bTests[] = new String[]{
      "carridescar",
      "adxcjavaavaj",
      "pqrsstrutstruts",
      "asdwghibernateterhibernatea",
      "jsxxsjasdsxjsxloo",
      "pasdweqrPasdkrypton",
      "datadatbadat",
      "darkardarkar",
      "webnotwe",
      "browsesbeobrowse",
    };
    System.out.println("Testing two occourences ... ");
    printResult(aTests,bTests,this::twoOccourences);
    System.out.println("Testing last part ... ");
    printResult(aTests, bTests,this::lastPart);
  }


  public String lastPart(String a,String b)
  {
    return b.substring(b.indexOf(a)+1);
  }

  public static void main(String[] args) {
    new Part3A().testing();
  }

}
