import java.util.*;
import java.util.stream.IntStream;

import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        return IntStream.
                iterate(whichSlice,x->x+totalSlices<message.length(),x->x+=totalSlices).mapToObj(message::charAt).
                collect(StringBuilder::new,StringBuilder::appendCodePoint,StringBuilder::append).toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0;i<klength;i++)
        {
            String current = sliceString(encrypted,i,klength);
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            key[i]=cracker.getKey(current);
        }
        return key;
    }



    public void breakVigenere () {
        FileResource fr = new FileResource();
        CaesarCracker cr = new CaesarCracker();
        int key[] = tryKeyLength(fr.asString(),4,'e');
        System.out.println("Key found is: "+Arrays.toString(key));
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(fr.asString()));
    }


    public static void main(String[] args) {
        VigenereBreaker vigenereBreaker=new VigenereBreaker();
        vigenereBreaker.breakVigenere();
    }
    
}
