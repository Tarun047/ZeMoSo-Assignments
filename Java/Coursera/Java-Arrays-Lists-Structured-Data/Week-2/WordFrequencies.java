import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.*;
import java.util.stream.StreamSupport;

public class WordFrequencies {
    private List<String> myWords;
    private List<Integer> myFreqs;
    WordFrequencies()
    {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    void putWord(String s)
    {
        int idx = myWords.indexOf(s);
        if(idx==-1)
        {
            myWords.add(s);
            myFreqs.add(1);
        }
        else
            {
                int freq = myFreqs.get(idx);
                myFreqs.remove(idx);
                myFreqs.add(idx,freq+1);
        }
    }
    void findUnique()
    {
        myFreqs.clear();
        myWords.clear();

        FileResource fr = new FileResource();
        StreamSupport.stream(fr.words().spliterator(),false).
                map(String::toLowerCase).forEach(this::putWord);
        System.out.println("Total Unique words: "+myWords.size());
        for(int i=0;i<myWords.size();i++)
        {
            System.out.println(myFreqs.get(i)+" "+myWords.get(i));
        }

    }

    void getMax()
    {
        int mFreq = Collections.max(myFreqs);
        String word = myWords.get(myFreqs.indexOf(mFreq));
        System.out.println("The word that occurs most often and its count are: "+word+" "+mFreq);
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.findUnique();
        wf.getMax();
    }
}
