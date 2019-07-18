import java.util.Arrays;
import java.util.Optional;

public class CaesarCipher {
    static private char[] alphabet = new char[26];
    private char[] shifted = new char[26];
    private int masterKey;
    CaesarCipher(int key)
    {
        masterKey=key;
        for(char i=0;i<26;i++)
            alphabet[i] = (char)(i+'a');
        for(int i=key,j=0;i<26;i++,j++)
            shifted[i]=alphabet[j];
        for(int j=0,i=26-key;j<key;j++,i++)
            shifted[j]=alphabet[i];

    }
    private  char getEncryptedChar(char ch)
    {
        if(ch>='a'&&ch<='z')
            return shifted[ch-'a'];
        else if(ch>='A' && ch<='Z')
            return Character.toUpperCase(shifted[ch-'A']);
        else
            return ch;
    }
    String encrypt(String s)
    {
        return s.chars().
                mapToObj(ch->(char)ch).
                map(this::getEncryptedChar).
                collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();
    }

    String decrypt(String s)
    {
        CaesarCipher _internal = new CaesarCipher(26-masterKey);
        return _internal.encrypt(s);
    }
}
