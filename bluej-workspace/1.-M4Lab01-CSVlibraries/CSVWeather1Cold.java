/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class CSVWeather1Cold {
    
    public double averageTemperatureInFile(CSVParser parser) {
        
        double averageTemp,suma = 0;
        int contador = 0;
        for (CSVRecord currentRow : parser){
            suma += Double.parseDouble(currentRow.get("TemperatureF"));
            contador++;            
        }
        averageTemp = suma / contador;
                
        return averageTemp;
    }
    
    public void testAverageTemperatureInFile() {
        
        FileResource fr = new FileResource();
        double averageTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temp so far was: " + averageTemp);
        
    }
    
    public void testLowestHumidityInManyFiles() {
                      
        CSVRecord coldest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity so far was: " + coldest.get("Humidity") + " at " + coldest.get("DateUTC"));
        
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        
        CSVRecord minSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles() ) {
            
            FileResource fr = new FileResource(f);         
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            minSoFar = getMinOfTwoH(currentRow, minSoFar);            
            
        }
        
        return minSoFar;
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        
        CSVRecord lowestH = null;
        for (CSVRecord currentRow : parser){
            lowestH = getMinOfTwoH(currentRow, lowestH);
        }
                
        return lowestH;
    }
    
    public void testLowestHumidityInFile() {
        
        FileResource fr = new FileResource();
        CSVRecord csv = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity so far was: " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        
    }
    
    public void coldestInManyDay() {
                      
        CSVRecord coldest = fileWithColdestTemperature();
        System.out.println("Coldest temp so far was: " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
        
    }
    
    public CSVRecord fileWithColdestTemperature() {
        
        CSVRecord minSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles() ) {
            
            FileResource fr = new FileResource(f);         
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            minSoFar = getMinOfTwo(currentRow, minSoFar);            
            
        }
        
        return minSoFar;
        
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        
        CSVRecord minSoFar = null;
        for (CSVRecord currentRow : parser){
            minSoFar = getMinOfTwo(currentRow, minSoFar);
        }
        return minSoFar; 
                
    }
    
    public void testColdestHourInFile() {
        
        FileResource fr = new FileResource();
        CSVRecord largest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temp so far was: " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
        
    }
    
    public CSVRecord getMinOfTwoH(CSVRecord currentRow, CSVRecord minSoFar) {
        
        if (minSoFar == null){
                minSoFar = currentRow;                       
                
        } else if (currentRow.get("Humidity").equalsIgnoreCase("N/A")) {
            return minSoFar;
        } else {           
                double currentH = Double.parseDouble(currentRow.get("Humidity"));
                double minH = Double.parseDouble(minSoFar.get("Humidity"));
                if (currentH < minH){
                    minSoFar = currentRow;
                    
                }
        }
        
        return minSoFar;        
        
    }
    
    public CSVRecord getMinOfTwo(CSVRecord currentRow, CSVRecord minSoFar) {
        
        if (minSoFar == null){
                minSoFar = currentRow;                       
                
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double minTemp = Double.parseDouble(minSoFar.get("TemperatureF"));
            if (currentTemp < minTemp){
                minSoFar = currentRow;
                    
            }
        }
        
        return minSoFar;        
        
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        
        if (largestSoFar == null){
                largestSoFar = currentRow;                       
                
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp){
                largestSoFar = currentRow;
                    
            }
        }
        
        return largestSoFar;        
        
    }
    
    public CSVRecord hottestInManyDays() {
        
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles() ) {
            
            FileResource fr = new FileResource(f);         
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);            
            
        }
        
        return largestSoFar;        
    }
    
    
    public CSVRecord hottestHourInFile(CSVParser parser) {
        
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser){
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
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
