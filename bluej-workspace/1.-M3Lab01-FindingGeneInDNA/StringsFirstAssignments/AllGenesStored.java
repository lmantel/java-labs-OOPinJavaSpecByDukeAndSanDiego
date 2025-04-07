
/**
 * Write a description of class AllGenesStored here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.Character;
import edu.duke.*;
import java.io.*;

public class AllGenesStored {
    
    public int findStopCodon(String dna, int startIndex,String stopCodon) {
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        while (currIndex != -1) {
            if ( (currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon,currIndex+stopCodon.length());
            }
        }
        return -1;
    }
    
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG",where);
        if (startIndex == -1) {
            System.out.println("------------DNA strand is " + dna);
            System.out.println("------------startIndex is " + startIndex);
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
       
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
                    minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
                    minIndex = tagIndex;
        }
        
        if (minIndex == -1) {
            return "";
        }
        
        System.out.println("------------DNA strand is " + dna);
        System.out.println("------------startIndex is " + startIndex);
        return dna.substring(startIndex,minIndex+3);
    }
    
    
    public void printAllGenes(String dna) {
        
        int startIndex = 0;
        
        while (true) {
            
            String currentGene = findGene(dna, startIndex);
            
            System.out.println("La cadena es " + currentGene);
            
            if (currentGene.isEmpty()) {
                break;
            }
                       
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            
            System.out.println("Printout último despues del break. Cadena es " + currentGene);
        }
        System.out.println("Printout fuera del while. Chau!");
           
    }
    
    public void testOn(String dna) {
        System.out.println("testing getAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        
        System.out.println("Las cadenas alojadas en StorageResource son");
        for (String g : genes.data() ) {
            System.out.println(g);
        }
        
    }
    
    public void test() {
        System.out.println("salida 1");
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        System.out.println("\n" + "salida 2");
        testOn("");
        System.out.println("\n"+ "salida 3");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        System.out.println("\n"+ "salida 4");
        testOn("AATGCTAACTAGCTGACTAAT");
    }
    
    public StorageResource getAllGenes(String dna) {
        
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        
        while (true) {
            
            String currentGene = findGene(dna, startIndex);
                                  
            if (currentGene.isEmpty()) {
                break;
            }
            
            geneList.add(currentGene);
            
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            
        }
        return geneList;        
    }
}


