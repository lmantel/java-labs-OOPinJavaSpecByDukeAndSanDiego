import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap() {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source) {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "time", "verb", "fruit"};
        for (String category : categories) {
            ArrayList<String> words = readIt(source + "/" + category +".txt");
            myMap.put(category, words);
        }
        usedWords = new ArrayList<String>();
    }

    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String word) {
        int first = word.indexOf("<");
        int last = word.indexOf(">", first);
        if (first == -1 || last == -1) {
            return word;
        }
        String prefix = word.substring(0, first);
        String suffix = word.substring(last + 1);
        String sub = getSubstitute(word.substring(first + 1, last));
        int count = 0;
        while (usedWords.indexOf(sub) != -1) {
            sub = getSubstitute(word.substring(first + 1, last));
            count++;
            if (count > 100) {
                return "**" + word.substring(first + 1, last) + "**";
            }
        }
        usedWords.add(sub);
        return prefix + sub + suffix;
    }

    private void printOut(String story) {
        String[] storyArray = story.split("\\s+");
        for (String word : storyArray) {
            System.out.print(word + " ");
        }
        System.out.println();
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line.replace("\n", ""));
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        usedWords.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story);
        System.out.println("\nTotal words replaced: " + usedWords.size());
        System.out.println("Total possible words: " + totalWordsInMap());
        System.out.println("Total words considered: " + totalWordsConsidered());

    }

    private String randomFrom(ArrayList<String> list) {
        return list.get(myRandom.nextInt(list.size()));
    }

    private int totalWordsInMap() {
        int total = 0;
        for (ArrayList<String> list : myMap.values()) {
            total += list.size();
        }
        return total;
    }

    private int totalWordsConsidered() {
        int total = 0;
        HashSet<String> categoriesUsed = new HashSet<>();

        String[] storyArray = fromTemplate("data/madtemplate2.txt").split("\\s+");
        for(String word: storyArray){
            int first = word.indexOf("<");
            int last = word.indexOf(">",first);
            if(first != -1 && last != -1){
                String category = word.substring(first + 1, last);
                if(myMap.containsKey(category) && !categoriesUsed.contains(category)){
                    total+= myMap.get(category).size();
                    categoriesUsed.add(category);
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        GladLibMap gl = new GladLibMap();
        gl.makeStory();
    }
}