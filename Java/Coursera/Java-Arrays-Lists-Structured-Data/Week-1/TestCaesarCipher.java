import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestCaesarCipher
{
    int[] countLetters(String s)
    {
        int[] count=new int[26];
        s=s.toUpperCase();
        char start = 'A';
        s.chars().
                mapToObj(ch->(char)ch).
                filter(ch->ch>='A'&&ch<='Z').
                forEach(ch->count[ch-start]++);
        return count;
    }


    int indexOfMax(int[] values)
    {
        List<Integer> li = Arrays.stream(values).boxed().collect(Collectors.toList());
        return li.indexOf(li.stream().max(Integer::compareTo).orElse(li.get(0)));
    }

    void simpleTests()
    {
        String[] tests = {
                "Hello World",
                "JSL",
                "Streams are the best API in java",
                "Concurrent programming in the new era of programming"
        };
        CaesarCipher cc = new CaesarCipher(18);
        for(String test:tests)
        {
            String encrypted = cc.encrypt(test);
            System.out.println("Encrypted is: "+encrypted);
            System.out.println("Decrypted is: "+cc.decrypt(encrypted));
            breakCipher(encrypted);
        }
    }

    void breakCipher(String input)
    {
        CaesarCipherProcedural cipher = new CaesarCipherProcedural();
        int key = indexOfMax(countLetters(input));
        if(key<4)
            key = 26-(4-key);
        else
            key=key-4;
        System.out.println("Key is :"+key);
        System.out.println("Message is: "+cipher.encrypt(input,26-key));
    }


    public static void main(String[] args) {
        TestCaesarCipher tester = new TestCaesarCipher();
        tester.simpleTests();
    }



}
