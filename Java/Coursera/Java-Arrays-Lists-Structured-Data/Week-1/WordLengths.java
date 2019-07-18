import edu.duke.FileResource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class WordLengths
{
    void countWordLengths(FileResource fr,int[] count)
    {
        StreamSupport.stream(fr.words().spliterator(),false).
                map(s->s.replaceAll("^[^A-Za-z0-9]*","")).
                map(s->s.replaceAll("[^A-Za-z0-9]*$","")).
                mapToInt(String::length).forEach(x->count[x]++);
    }

    void tester()
    {
        FileResource fr = new FileResource();
        int[] cnt = new int[31];
        countWordLengths(fr,cnt);
        System.out.println(Arrays.toString(cnt));
        System.out.println(indexOfMax(cnt));

    }

    int indexOfMax(int[] values)
    {
        List<Integer> li = Arrays.stream(values).boxed().collect(Collectors.toList());
        return li.indexOf(li.stream().max(Integer::compareTo).orElse(li.get(0)));
    }

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.tester();

    }
}
