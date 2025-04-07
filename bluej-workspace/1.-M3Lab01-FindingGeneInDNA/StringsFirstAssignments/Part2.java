
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.Character;
import edu.duke.*;
import java.io.*;

public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        // start codon is ATG
        // end codon is TAA
        boolean esMayus = true;
        if (Character.isUpperCase(dna.charAt(0))) {
            esMayus = true;
        } else {
            esMayus = false;
            dna = dna.toUpperCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            System.out.println("------------DNA strand is " + dna);
            System.out.println("------------startIndex is " + startIndex);
            return "No hay ATG\n";
        }
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        if (stopIndex == -1) {
            System.out.println("------------DNA strand is " + dna);
            System.out.println("------------startIndex is " + stopIndex);
            return "No hay TAA\n";
        }
        if ( (startIndex - stopIndex) % 3 == 0) {
            
            if (esMayus) {
                System.out.println("------------DNA strand is " + dna);
                System.out.println("------------startIndex is " + startIndex);
                System.out.println("------------startIndex is " + stopIndex);
                return dna.substring(startIndex,stopIndex+3) + "\n";
            } else {
                System.out.println("------------DNA strand is " + dna);
                System.out.println("------------startIndex is " + startIndex);
                System.out.println("------------startIndex is " + stopIndex);
                return dna.substring(startIndex,stopIndex+3).toLowerCase() + "\n";
            }
            
        } else {
            System.out.println("------------DNA strand is " + dna);
            System.out.println("------------startIndex is " + startIndex);
            System.out.println("------------startIndex is " + stopIndex);
            return "No valid gene secuence\n";
        }
              
    }
    
    public void testSimpleGene() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        String dna = "ATTTTGAATTTTATTGGATAA";
        System.out.println("1.-DNA strand is " + dna);
        String gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
               
        dna = "ATGTTGAATTTTATAGGATAG";
        System.out.println("2.-DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "ATTTTGAATTTTATAGGATTG";
        System.out.println("3.-DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "ATGTTGAATTTTATAGGATAA";
        System.out.println("4.-DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "ATGTTGAATTTTATAGGAGTAA";
        System.out.println("5.-DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "atgttgaattttataggataa";
        System.out.println("6.-DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("7.-DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene);
        
    }
    
    public void realTesting() {
                String startCodon = "ATG";
                String stopCodon = "TAA";
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			String s = fr.asString();
			System.out.println("read " + s.length() + " characters");
			String result = findSimpleGene(s,startCodon,stopCodon);
			System.out.println("found " + result);
		}
    }
}
