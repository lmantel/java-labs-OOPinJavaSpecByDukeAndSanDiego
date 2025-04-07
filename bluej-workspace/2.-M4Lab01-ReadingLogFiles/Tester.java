
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

class Tester {
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
    }
    
    public void testCountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");        
        System.out.println("IP unique: " + la.countUniqueIPs());
    }
    
     public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> uniqueIPsSep14 = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("Unique IPs on Sep 27: " + uniqueIPsSep14);
        System.out.println(uniqueIPsSep14.size());
        ArrayList<String> uniqueIPsSep30 = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Unique IPs on Sep 30: " + uniqueIPsSep30);
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int count200_299 = la.countUniqueIPsInRange(200, 299);
        System.out.println("Unique IPs in range 200-299: " + count200_299);
        int count300_399 = la.countUniqueIPsInRange(300, 399);
        System.out.println("Unique IPs in range 300-399: " + count300_399);
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("IP visits: " + counts);
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Max visits by IP: " + maxVisits);
    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> mostVisitedIPs = la.iPsMostVisits(counts);
        System.out.println("IPs with most visits: " + mostVisitedIPs);
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipsForDays = la.iPsForDays();
        System.out.println("IPs for days: " + ipsForDays);
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipsForDays = la.iPsForDays();
        String mostVisitedDay = la.dayWithMostIPVisits(ipsForDays);
        System.out.println("Day with most IP visits: " + mostVisitedDay);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipsForDays = la.iPsForDays();
        ArrayList<String> mostVisitedIPs = la.iPsWithMostVisitsOnDay(ipsForDays, "Sep 29");
        System.out.println("IPs with most visits on Sep 29: " + mostVisitedIPs);
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testLogAnalyzer();
        t.testCountVisitsPerIP();
        t.testMostNumberVisitsByIP();
        t.testIPsMostVisits();
        t.testIPsForDays();
        t.testDayWithMostIPVisits();
        t.testIPsWithMostVisitsOnDay();
    }
}
