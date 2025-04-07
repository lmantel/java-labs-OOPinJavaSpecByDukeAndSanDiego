
/**
 * Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }

    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The most frequent word is \"" + myWords.get(maxIndex) + "\" and occurs " + myFreqs.get(maxIndex) + " times.");
    }

    public int findIndexOfMax() {
        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > maxValue) {
                maxValue = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
