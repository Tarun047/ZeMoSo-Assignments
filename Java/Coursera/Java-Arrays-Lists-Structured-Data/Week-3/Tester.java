
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import edu.duke.FileResource;

import java.util.*;


public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("Logs/weblog1_log");
        la.printAll();
        la.printAllHigherThanNum(400);
        System.out.println("*"+la.mostNumberVisitsByIP(la.countVisitsPerIP()));
        System.out.println("Unique ips are: "+la.countUniqueIPs());
        System.out.println(la.uniqueIPVisitsOnDay("Mar 17").size());
        System.out.println(la.countUniqueIPsInRange(200,299));
        Map<String,Integer> vMap = la.countVisitsPerIP();
        System.out.println("Ip with most visits: "+la.iPsMostVisits(vMap));
        Map<String,List<String>> iMap = la.iPsForDays();
        System.out.println("Day with most visits "+la.dayWithMostIPVisits(iMap));
        System.out.println("Ip with most visits on day are: "+la.iPsWithMostVisitsOnDay(iMap,"Mar 17"));
    }

    public static void main(String[] args) {
        Tester t= new Tester();
        t.testLogAnalyzer();

    }

}
