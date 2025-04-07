
/**
 * Write a description of class AllGenesStoredRattio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.Character;
import edu.duke.*;
import java.io.*;

public class AllGenesStoredRattio {
    
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
            //System.out.println("------------DNA strand is " + dna);
            //System.out.println("------------startIndex is " + startIndex);
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
        
        //System.out.println("------------DNA strand is " + dna);
        //System.out.println("------------startIndex is " + startIndex);
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
    
    public double cgRatio(String dna) {
        
        int countC = 0;
        int countG = 0;

        // Recorre la cadena y cuenta las ocurrencias de C y G
        for (char ch : dna.toCharArray()) {
            if (ch == 'C' || ch == 'c') { // Cuenta mayúsculas y minúsculas
                countC++;
            } else if (ch == 'G' || ch == 'g') {
                countG++;
            }
        }
        
        int totalChars = dna.length();
        
        // System.out.println(countC);
        // System.out.println(countG);
        // System.out.println(countC + countG);
        // System.out.println(totalChars);
        
        return (double) (countC + countG) / totalChars;
        
        
    }
    
    public int countCTG(String dna) {
        
        int count = 0;
        int startIndex = 0;
        String stopCodon = "CTG";
       
        int currIndex = dna.indexOf(stopCodon,startIndex);
        while (currIndex != -1) {
            count++;
            currIndex = dna.indexOf(stopCodon,currIndex+stopCodon.length());
            
        }
        return count;
    }
    
    public void processGenes(StorageResource genes) {
                  
        //System.out.println("Las cadenas alojadas en StorageResource son");
        int count9 = 0;
        int countR = 0;
        int current = 0;
        
        for (String g : genes.data() ) {
            if (g.length() > 60 ) {
                System.out.println(g);
                System.out.println("Mayor a 60" + "\n");
                count9++;
            }
            
            if (cgRatio(g) > 0.35 ) {
                System.out.println(g);
                System.out.println("Mayor a 0.35" + "\n");
                countR++;
            }
            
            if (g.length() > current) {
                current = g.length();
            }
            
        }
        
        System.out.println("Procesando todas las cadenas encontramos: ");
        System.out.println("cantidad de genes mayor a 60: " + count9);
        System.out.println("cantidad de ratio mayor a 0.35: " + countR);
        System.out.println("mayor longitud de gene: " + current);
                
    }
    
    public void testProcessGenes(StorageResource genes) {
        
        processGenes(genes);
        
        
    }
         
    public void test() {
        
        System.out.println("salida 1");
        FileResource fr = new FileResource("../dna/GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(dna);
        testOn(dna);
        
        // System.out.println("salida 2");
        // testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
                
    }
    
    public void testOn(String dna) {
        //System.out.println("testing getAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        int count = 0;
        
        System.out.println("Las cadenas alojadas en StorageResource son: ");
        for (String g : genes.data() ) {
            System.out.println(g);
            count++;
        }
        
        System.out.println("\n" + "Cantidad de cadenas validas de genes son: "+count);
        System.out.println("\n" + "Validación de la cadena total sin desmenuzar de dna: ");
        double cgRatio = cgRatio(dna);
        System.out.println("Ratio de -c+g- es: " + cgRatio);
        double countCTG = countCTG(dna);
        System.out.println("Cantidad de CTG: " + countCTG);
        
        System.out.println("\n" + "Llamando a testProcessGenes: ");
        testProcessGenes(genes);
        
        
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


