import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CodonCount {
    Map<String,Integer> cMap;
    CodonCount()
    {
        cMap = new HashMap<>();
    }

    private void resetMap()
    {
        cMap.clear();
    }

    void buildCodonMap(int start,String dna)
    {
        resetMap();
        IntStream.iterate(start,x->x+3<dna.length(),x->x+3).
                mapToObj(x->dna.substring(x,x+3)).
                forEach(s->cMap.put(s,cMap.getOrDefault(s,0)+1));
        System.out.println("Reading frame starting with "+start+" resulted in "+cMap.size()+" unique codons");
    }

    String getMostCommonCodon()
    {
        return cMap.entrySet().stream().
                max(Comparator.comparingInt(Map.Entry::getValue)).
                map(Map.Entry::getKey).
                orElse("Map empty");
    }
    void printCodonCounts(int start,int end)
    {
        cMap.entrySet().stream().
                filter(e->e.getValue()>=start&&e.getValue()<=end).
                forEach(System.out::println);
    }


    void tester()
    {
        FileResource fr = new FileResource();
        for(int i=0;i<3;i++) {
            buildCodonMap(i, fr.asString());
            String s = getMostCommonCodon();
            System.out.println("Most common codon is: " + s + " with count " + cMap.get(s));
            printCodonCounts(6, 6);
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
