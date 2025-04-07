/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class LabExerciseCountry {
    
    public void numberOfExporters(CSVParser parser, String exportItem) {
        
        int countCountry = 0;
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem)){
                ++countCountry;                       
                
            }                                    
        }
        System.out.println(countCountry); 
                
    }
    
    public void testnumberOfExporters() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        numberOfExporters(parser,"cocoa");
        
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
                       
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));            
                
            }
                                    
        }
        
                
    }
    
    public void testlistExportersTwoProducts() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        listExportersTwoProducts(parser,"cotton","flowers");
        
    }
    
    public String countryInfo(CSVParser parser, String country) {
        
        String result = "";
        
        for (CSVRecord record : parser){
            if (record.get("Country").equals(country)){
                result = country + ": ";
                result = result.concat(record.get("Exports") + ": ");
                result = result.concat(record.get("Value (dollars)"));
                
                return result;
            }
                                    
        }
        return "NOT FOUND";
                
    }
    
    public void testCountryInfo() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println(countryInfo(parser,"Nauru"));
        
    }
    
    public void listExporters(CSVParser parser, String exportOfInterest) {
        
        
        for (CSVRecord record : parser){
            
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.print(country);
                System.out.print("\n");
            }
                                    
        }
    }
    
    public void whoExportsCoffee() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser,"coffee");
        
    }
    
    public void readCSV() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.print(record.get("Country") + " ");
            System.out.print(record.get("Exports") + " ");
            System.out.println(record.get("Value (dollars)"));
        }
    }
}
