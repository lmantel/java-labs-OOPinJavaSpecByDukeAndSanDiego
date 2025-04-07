import java.util.*;
import java.io.File;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordToFileMap;

    public WordsInFiles() {
        wordToFileMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (!wordToFileMap.containsKey(word)) {
                ArrayList<String> fileList = new ArrayList<>();
                fileList.add(f.getName());
                wordToFileMap.put(word, fileList);
            } else {
                ArrayList<String> fileList = wordToFileMap.get(word);
                if (!fileList.contains(f.getName())) {
                    fileList.add(f.getName());
                    wordToFileMap.put(word, fileList);
                }
            }
        }
    }

    public void buildWordFileMap() {
        wordToFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int max = 0;
        for (ArrayList<String> fileList : wordToFileMap.values()) {
            if (fileList.size() > max) {
                max = fileList.size();
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<>();
        for (String word : wordToFileMap.keySet()) {
            if (wordToFileMap.get(word).size() == number) {
                words.add(word);
            }
        }
        return words;
    }

    public void printFilesIn(String word) {
        if (wordToFileMap.containsKey(word)) {
            ArrayList<String> fileList = wordToFileMap.get(word);
            for (String fileName : fileList) {
                System.out.println(fileName);
            }
        } else {
            System.out.println("Word not found.");
        }
    }

    public void tester() {
        buildWordFileMap();
        ArrayList<String> wordsInFiveFiles = wordsInNumFiles(4);
        System.out.println("Words that occur in five files: " + wordsInFiveFiles.size());
        for (String word : wordsInFiveFiles) {
            System.out.println(word);
        }
    }

    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }
}