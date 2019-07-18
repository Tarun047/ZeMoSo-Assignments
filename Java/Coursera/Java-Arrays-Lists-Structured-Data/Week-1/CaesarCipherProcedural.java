import java.util.concurrent.atomic.AtomicInteger;

public class CaesarCipherProcedural {

     char getShifted(char ch,int key)
    {
        if((ch>='a'&& ch<='z')||(ch>='A' && ch<='Z'))
        {
            char start = 'A';
            if(ch>='a')
                start='a';
            return (char)(start+(ch-start+key)%26);
        }
        return ch;
    }

     String encrypt(String input, int key)
    {
        return input.chars().
                mapToObj(x->(char)x).
                map(x->getShifted(x,key)).
                collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();
    }

     String encryptTwoKeys(String input,int key1,int key2)
    {
        AtomicInteger index = new AtomicInteger();
        return input.chars().
                mapToObj(ch->(char)ch).
                map(ch->index.getAndIncrement()%2==0?getShifted(ch,key1):getShifted(ch,key2)).
                collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();

    }

    static void runTests()
    {
        CaesarCipherProcedural tester = new CaesarCipherProcedural();
        assert tester.encrypt("FIRST LEGION ATTACK EAST FLANK!",23).equals("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!");
        assert tester.encryptTwoKeys("First Legion",23,17).equals("Czojq Ivdzle");
    }
    public static void main(String[] args) {
        runTests();
        System.out.println(new CaesarCipherProcedural().encryptTwoKeys("At noon be in the conference " +
                "room with your hat on for a surprise party. YELL LOUD!",8,21));
    }
}
