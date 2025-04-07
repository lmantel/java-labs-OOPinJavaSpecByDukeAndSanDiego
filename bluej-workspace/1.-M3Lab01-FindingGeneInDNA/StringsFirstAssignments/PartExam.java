
/**
 * Write a description of class PartExam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PartExam {
    
    public void mystery() {
        
          String dna = "AAATBBBTCCCTDDDTEEETFFF";  
          int pos = dna.indexOf("T");
          int count = 0;
          int startPos = 0;
          String newDna = "";
          if (pos == -1) {
            System.out.println(newDna);;
          }
          while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
              break;
            }
          }
          newDna = newDna + dna.substring(startPos);
          System.out.println(newDna);
    }
}
