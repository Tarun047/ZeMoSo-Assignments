import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BabyBirth {

    public static void main(String[] args) {

        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }

    static void totalBirts(FileResource fr)
    {
        CSVParser parser = fr.getCSVParser();
        long boys = StreamSupport.stream(parser.spliterator(),false).filter(record->record.get(1).equals("F")).count();
        parser = fr.getCSVParser();
        long girls = StreamSupport.stream(parser.spliterator(),false).filter(record->record.get(1).equals("M")).count();
        System.out.println("The number of boys are: "+boys);
        System.out.println("The number of girls are: "+girls);
        System.out.println("The total births are: "+(boys+girls));
    }

    static int getRank(int year,String name,String gender)
    {
        File f = new File("us_babynames_by_year/yob"+year+".csv");
        return getRankFromFile(f,name,gender);
    }

    static int getRankFromFile(File f,String name,String gender)
    {
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        List<String> sortedNames = StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get(1).equals(gender)).
                sorted(Comparator.comparingInt(record->-Integer.parseInt(record.get(2)))).
                map(record->record.get(0)).collect(Collectors.toList());
        int idx = sortedNames.indexOf(name);
        return idx==-1?idx:idx+1;
    }

    static String getName(int year,int rank,String gender)
    {
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        CSVParser parser = fr.getCSVParser();
        List<String> sortedNames = StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get(1).equals(gender)).
                sorted(Comparator.comparingInt(record->-Integer.parseInt(record.get(2)))).
                map(record->record.get(0)).collect(Collectors.toList());
        return (sortedNames.size()<rank || rank<1)?"NO_NAME":sortedNames.get(rank-1);
    }

    static void whatIsNameInYear(String name,int year,int newYear,String gender)
    {
        int oldRank = getRank(year,name,gender);
        String newName = getName(newYear,oldRank,gender);
        System.out.println(String.format("%s born in %d would be %s if she was born in %d",name,year,newName,newYear));
    }

    static int getYearFromFile(File f)
    {
       int year=0;
       for(char ch:f.getName().toCharArray())
       {
           if(ch>='0' && ch<='9')
               year=year*10+(ch-'0');
       }
       return year;
    }


    static int yearOfHighestRank(String name,String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        return StreamSupport.stream(dr.selectedFiles().spliterator(),false).
                min(Comparator.comparing(file->getRankFromFile(file,name,gender))).
                 map(BabyBirth::getYearFromFile).orElse(-1);
    }

    static double getAverageRank(String name,String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int[] ranks = StreamSupport.stream(dr.selectedFiles().spliterator(),false).
                mapToInt(file->getRankFromFile(file,name,gender)).toArray();
        double sum = 0;
        for (int rank : ranks) {
            if (rank == -1)
                return -1;
            sum += rank;
        }
        return sum/ranks.length;
    }

    static int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        FileResource fr= new FileResource();
        CSVParser parser = fr.getCSVParser();
        return StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get(1).equals(gender)).
                sorted(Comparator.comparingInt(record->-Integer.parseInt(record.get(2)))).
                takeWhile(record->!record.get(0).equals(name)).mapToInt(record->Integer.parseInt(record.get(2))).
                sum();
    }
}
