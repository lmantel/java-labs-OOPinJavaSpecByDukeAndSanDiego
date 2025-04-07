
/**
 * Write a description of class WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {

    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int wordLength = 0;
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if (Character.isLetter(currChar)) {
                    wordLength++;
                } else if (wordLength == 0 && i == 0) {
                    // ignore punctuation at the start
                } else if (i == word.length() - 1 && wordLength > 0) {
                    // ignore punctuation at the end
                } else{
                    wordLength++; // count punctuation in the middle of word
                }
            }
            if (wordLength > 0) {
                if (wordLength >= counts.length) {
                    counts[counts.length - 1]++;
                } else {
                    counts[wordLength]++;
                }
            }
        }
    }

    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int[] counts = new int[31]; // Array to hold counts for lengths 0 to 30+
        countWordLengths(resource, counts);

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println(counts[i] + " words of length " + i);
            }
        }

        int maxIndex = indexOfMax(counts);
        System.out.println("The most common word length in this file is " + maxIndex + ".");

    }

    public int indexOfMax(int[] values) {
        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}
