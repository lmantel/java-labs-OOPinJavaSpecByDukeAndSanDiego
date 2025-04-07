
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry record = WebLogParser.parseEntry(line);
            records.add(record);
        }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }
     
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            if (date.contains(someday)) {
                String ipAddr = le.getIpAddress();
                if (!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high) {
                String ipAddr = le.getIpAddress();
                if (!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String,Integer> countVisitsPerIP() {
        
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)) {
                counts.put(ip,1);
            }
            else {
                counts.put(ip,counts.get(ip) + 1);
            }
            
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxVisits = 0;
        for (int visits : counts.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }
        return maxVisits;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> mostVisitedIPs = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);
        for (String ip : counts.keySet()) {
            if (counts.get(ip) == maxVisits) {
                mostVisitedIPs.add(ip);
            }
        }
        return mostVisitedIPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsForDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String day = le.getAccessTime().toString().substring(4, 10);
            String ip = le.getIpAddress();
            if (!ipsForDays.containsKey(day)) {
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(ip);
                ipsForDays.put(day, ips);
            } else {
                ArrayList<String> ips = ipsForDays.get(day);
                ips.add(ip);
                ipsForDays.put(day, ips);
            }
        }
        return ipsForDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsForDays) {
        String mostVisitedDay = "";
        int maxVisits = 0;
        for (String day : ipsForDays.keySet()) {
            int visits = ipsForDays.get(day).size();
            if (visits > maxVisits) {
                maxVisits = visits;
                mostVisitedDay = day;
            }
        }
        return mostVisitedDay;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsForDays, String day) {
        if (!ipsForDays.containsKey(day)) {
            return new ArrayList<String>(); // Return empty list if day not found
        }
        ArrayList<String> ipsOnDay = ipsForDays.get(day);
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        for (String ip : ipsOnDay) {
            if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }
        return iPsMostVisits(ipCounts);
    }
    
}
