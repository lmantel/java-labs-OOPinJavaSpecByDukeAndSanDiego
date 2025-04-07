
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.Character;

public class Part3 {
    
    public boolean twoOccurrences(String stringa, String stringb) {
        int count = 0;
        int index = stringb.indexOf(stringa);
        while (index != -1) {
            count++;
            index = stringb.indexOf(stringa, index + stringa.length());
        }
        return count >= 2;
    }
    
    public String lastPart(String stringa, String stringb) {
       
       int startIndex = stringb.indexOf(stringa); 
       if (startIndex != -1) { 
           return stringb.substring(startIndex + stringa.length());
       } else {
           return stringb;
           
       }
              
    }
    
    public void testing() {
        
        String stringa1 = "by";
        String stringb1 = "A story by Abby Long";
        System.out.println(twoOccurrences(stringa1, stringb1)); // Output: true

        String stringa2 = "a";
        String stringb2 = "banana";
        System.out.println(twoOccurrences(stringa2, stringb2)); // Output: true

        String stringa3 = "atg";
        String stringb3 = "ctgtatgta";
        System.out.println(twoOccurrences(stringa3, stringb3)); // Output: false
        
    }
    
    public void testingLast() {
        
        String stringa1 = "by";
        String stringb1 = "A story by Abby Long";
        System.out.println(lastPart(stringa1, stringb1)); // Output: true

        String stringa2 = "an";
        String stringb2 = "banana";
        System.out.println(lastPart(stringa2, stringb2)); // Output: true

        String stringa3 = "zoo";
        String stringb3 = "forest";
        System.out.println(lastPart(stringa3, stringb3)); // Output: false
        
    }
}
