import java.util.Set;


public class WordPlay
{
    private final static Set vowels = Set.of('a','e','i','o','u');
    boolean isVowel(char ch)
    {

           return vowels.contains(ch);

    }
    String replaceVowels(String phrase,char ch)
    {
       return phrase.chars().mapToObj(x->(char)x).map(x->isVowel(x)?ch:x).
               collect(StringBuilder::new,StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    String emphasize(String phrase,char ch)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<phrase.length();i++) {
            if (phrase.charAt(i) == ch) {
                if (i % 2 == 0)
                    sb.append('*');
                else
                    sb.append('+');
            }
            else
                sb.append(phrase.charAt(i));
        }
        return sb.toString();

    }

    public static void runTests()
    {
        WordPlay tester = new WordPlay();
        assert tester.replaceVowels("Hello World",'*').equals("H*ll* W*rld");
        assert tester.emphasize("dna ctgaaactga",'a').equals("dn* ctg+*+ctg+");
    }

    public static void main(String[] args) {
        runTests();
    }
}
