/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVWeather1 {
    
    public CSVRecord hottestInManyDays() {
        
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles() ) {
            
            FileResource fr = new FileResource(f);         
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            
            if (largestSoFar == null){
                largestSoFar = currentRow;                       
                
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp){
                    largestSoFar = currentRow;
                    
                }
            }
            
            
        }
        
        return largestSoFar;        
    }
    
    
    public CSVRecord hottestHourInFile(CSVParser parser) {
        
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (largestSoFar == null){
                largestSoFar = currentRow;                       
                
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp){
                    largestSoFar = currentRow;
                    
                }
            }
        }
        return largestSoFar; 
                
    }
    
    public void hottestInManyDay() {
                      
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temp so far was: " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
        
    }
    
    public void hottestInDay() {
        
        FileResource fr = new FileResource();         
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temp so far was: " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
        
    }
    
    
}
