
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.Character;
import edu.duke.*;
import java.io.*;

public class Part4 {
    
    public void realTesting() {
                
		URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
		//int count = 0;
		for (String line : ur.lines()) {
			//System.out.println("Line " + count++ + " is: " + line);
			String url = "";
			int startIndex = line.toLowerCase().indexOf("youtube");
			//System.out.println("startindex es: " + startIndex);
			//System.out.println("line is: " + line);
			
			if (startIndex != -1) {
			    int stopIndex = line.indexOf("\"",startIndex+7);
                	    //System.out.println("stopIndex es: " + stopIndex);
			    url = line.substring(startIndex,stopIndex+1);
			    System.out.println("La URL es: https://www." + url);
                		
        		}
                }
    }
    
    public void realTesting2() {
                
		URLResource file = new  URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
           	for (String item : file.words()) {
           	    String itemLower = item.toLowerCase();
           	    int pos = itemLower.indexOf("youtube.com");
       	            if (pos != -1) {
       	                int beg = item.lastIndexOf("\"",pos);
                        int end = item.indexOf("\"", pos+1);
                        System.out.println(item.substring(beg+1,end));
                    }
           	}
    }  
}
