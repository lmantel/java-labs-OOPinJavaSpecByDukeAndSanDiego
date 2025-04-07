
/**
 * Write a description of class codonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import edu.duke.*;

public class CodonCount {
    private HashMap<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        for (int i = start; i < dna.length() - 2; i += 3) {
            String codon = dna.substring(i, i + 3);
            if (codonMap.containsKey(codon)) {
                codonMap.put(codon, codonMap.get(codon) + 1);
            } else {
                codonMap.put(codon, 1);
            }
        }
    }

    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int maxCount = 0;
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if (count > maxCount) {
                maxCount = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if (count >= start && count <= end) {
                System.out.println(codon + "\t" + count);
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();

        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + codonMap.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("  and most common codon is " + mostCommonCodon + " with count " + codonMap.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(1, 8);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}
