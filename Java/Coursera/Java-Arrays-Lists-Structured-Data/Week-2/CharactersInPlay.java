import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class CharactersInPlay
{
    private List<String> names;
    private List<Integer> freqs;

    CharactersInPlay()
    {
        names=new ArrayList<>();
        freqs=new ArrayList<>();
    }

    private void update(String name)
    {
        int idx=names.indexOf(name);
        if(idx==-1)
        {
            names.add(name);
            freqs.add(1);
        }
        else
        {
            int currFreq = freqs.get(idx);
            freqs.remove(idx);
            freqs.add(idx,currFreq+1);
        }
    }

    private void resetLocals()
    {
        names.clear();
        freqs.clear();
    }

    void findAllCharacters()
    {
        resetLocals();
        FileResource fr = new FileResource();
        StreamSupport.stream(fr.lines().spliterator(),false).
                filter(s-> s.contains(".")).
                map(s->s.substring(0,s.indexOf('.'))).
                forEach(this::update);
    }

    void charactersWithNumParts(int num1,int num2)
    {
        IntStream.range(0,freqs.size()).
                filter(x->num1<=freqs.get(x)&&freqs.get(x)<=num2).
                mapToObj(x->names.get(x)+" - "+freqs.get(x)).forEach(System.out::println);
    }

    void getMax()
    {
        int mFreq = Collections.max(freqs);
        String word = names.get(freqs.indexOf(mFreq));
        System.out.println("The word that occurs most often and its count are: "+word+" "+mFreq);
    }

    void tester() {
        findAllCharacters();
        charactersWithNumParts(10,15);
    }


    public static void main(String[] args) {
        CharactersInPlay cp = new CharactersInPlay();
        cp.tester();
        cp.getMax();
    }

}
