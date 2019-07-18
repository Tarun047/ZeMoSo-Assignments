import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CaesarCipherTwo {
    private int key1,key2;
    CaesarCipherTwo(int key1,int key2)
    {
        this.key1=key1;
        this.key2=key2;
    }

    String encrypt(String message)
    {

        StringBuilder sb1=new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0;i<message.length();i++)
        {
            if(i%2==0)
                sb1.append(message.charAt(i));
            else
                sb2.append(message.charAt(i));
        }
        CaesarCipher cc = new CaesarCipher(key1);
        return joinStrings(sb1.toString(),sb2.toString(),cc::encrypt);
    }

    String joinStrings(String s1, String s2, Function<String,String> func)
    {
        Iterator<Character> it1= func.apply(s1).
                chars().
                mapToObj(ch->(char)ch).
                iterator();

        Iterator<Character> it2=func.apply(s2).
                chars().
                mapToObj(ch->(char)ch).
                iterator();

        return IntStream.range(0,s1.length()+s2.length()).
                map(x->x%2==0?it1.next():it2.next()).
                collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();
    }

    String decrypt(String s)
    {
        CaesarCipherTwo _internal = new CaesarCipherTwo(26-key1,26-key2);
        return _internal.encrypt(s);
    }

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

    String getKey(String encrypted)
    {
        CaesarCipherProcedural cipher = new CaesarCipherProcedural();
        int key = indexOfMax(countLetters(encrypted));
        if(key<4)
            key = 26-(4-key);
        else
            key=key-4;
        System.out.println("Key is :"+key);
        return cipher.encrypt(encrypted,26-key);
    }
    String halfString(String message,int seed)
    {
        return IntStream.
                iterate(seed,i->i<message.length(),i->i+=2).
                map(message::charAt).
                collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();
    }
    String breakCipher(String message)
    {
       return joinStrings(halfString(message,0),halfString(message,1),this::getKey);
    }

    public static void main(String[] args)
    {
        CaesarCipherTwo c2 = new CaesarCipherTwo(23,17);
        String encr = c2.encrypt("First Legion");
        System.out.println("Encrypted is: "+encr);
        System.out.println("Decrypted is: "+c2.decrypt(encr));
    }

}
