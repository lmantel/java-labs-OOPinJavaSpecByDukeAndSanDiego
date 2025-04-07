
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene(String dna) {
        // start codon is ATG
        // end codon is TAA
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "No hay ATG";
        }
        int stopIndex = dna.indexOf("TAA",startIndex+3);
        if (stopIndex == -1) {
            return "No hay TAA";
        }
        if ( (startIndex - stopIndex) % 3 == 0) {
            return dna.substring(startIndex,stopIndex+3);
        } else {
            return "No valid gene secuence";
        }
              
    }
    
    public void testSimpleGene() {
        
        String dna = "ATTTTGAATTTTATTGGATAA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGTTGAATTTTATAGGATAG";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATTTTGAATTTTATAGGATTG";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGTTGAATTTTATAGGATAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGTTGAATTTTATAGGAGTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
    }
}
