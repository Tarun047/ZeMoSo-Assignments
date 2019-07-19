
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

import java.io.FileReader;
import java.nio.file.FileVisitResult;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records=new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         StreamSupport.stream(fr.lines().spliterator(),false).
                 map(WebLogParser::parseEntry).
                 forEach(records::add);
     }


    public int countUniqueIPs()
    {
        return (int)records.stream().map(LogEntry::getIpAddress).distinct().count();
    }

    public int countUniqueIPsInRange(int start,int end)
    {
        return (int)records.stream().filter(x->x.getStatusCode()>=start&&x.getStatusCode()<=end).
                map(LogEntry::getIpAddress).distinct().count();
    }

    public void printAllHigherThanNum(int num)
    {
        records.stream().filter(r->r.getStatusCode()>num).forEach(System.out::println);
    }

    boolean isSameDay(String day,Date d)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM d");
        try {

            String[] dates = d.toString().split(" ");
            return sdf.parse(dates[1]+" "+dates[2]).compareTo(sdf.parse(day))==0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    List<String> uniqueIPVisitsOnDay(String day)
    {
        return records.stream().
                filter(r->isSameDay(day,r.getAccessTime())).
                map(LogEntry::getIpAddress).
                distinct().
                collect(Collectors.toList());
    }

     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

    Map<String,Integer> countVisitsPerIP()
    {
        Map<String,Integer> hm = new HashMap<>();
        records.stream().map(LogEntry::getIpAddress).forEach(ip->hm.put(ip,hm.getOrDefault(ip,0)+1));
        return hm;
    }

    int mostNumberVisitsByIP(Map<String,Integer> vMap)
    {
        return Collections.max(vMap.values());
    }

    List<String> iPsMostVisits(Map<String,Integer> vMap)
    {
        int maxVisits = Collections.max(vMap.values());
        List<String> al = new ArrayList<>();
        vMap.entrySet().stream().filter(r->r.getValue()==maxVisits).map(Map.Entry::getKey).forEach(al::add);
        return al;
    }

    Map<String,List<String>> iPsForDays()
    {
        HashMap<String,List<String>> hm = new HashMap<>();
        for(LogEntry e:records)
        {
            String[] date = e.getAccessTime().toString().split(" ");
            String key = date[1]+" "+date[2];
            List<String> al = hm.getOrDefault(key, new ArrayList<>());
            al.add(e.getIpAddress());
            hm.put(key,al);
        }
        return hm;
    }

    String dayWithMostIPVisits(Map<String,List<String>> hm)
    {
        return hm.entrySet().stream().
                max(Comparator.comparingInt(r->r.getValue().size())).
                map(Map.Entry::getKey).
                orElse("Empty Map");
    }

    List<String> iPsWithMostVisitsOnDay(Map<String, List<String>> hm,String day)
    {
        HashMap<String,Integer> am = new HashMap<>();
        for(String s:hm.get(day))
        {
            am.put(s,am.getOrDefault(s,0)+1);
        }
        return iPsMostVisits(am);
    }
}
