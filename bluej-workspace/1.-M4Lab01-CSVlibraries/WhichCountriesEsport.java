/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesEsport {
    
    public void listExporters(CSVParser parser, String exportOfInterest) {
        
        int count = 0;
        for (CSVRecord record : parser){
            
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.print(country);
                System.out.print("\n");
                               
            }
                                    
        }
        
    }
    
    public void whoExportsInterest() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser,"cocoa");
        
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
