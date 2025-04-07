
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterCounts;

    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        characterCounts = new ArrayList<Integer>();
    }

    public void update(String person) {
        int index = characterNames.indexOf(person);
        if (index == -1) {
            characterNames.add(person);
            characterCounts.add(1);
        } else {
            int value = characterCounts.get(index);
            characterCounts.set(index, value + 1);
        }
    }

    public void findAllCharacters() {
        characterNames.clear();
        characterCounts.clear();
        FileResource resource = new FileResource();
        for (String line : resource.lines()) {
            int periodIndex = line.indexOf('.');
            if (periodIndex != -1) {
                String possibleName = line.substring(0, periodIndex).trim();
                update(possibleName);
            }
        }
    }

    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characterNames.size(); i++) {
            if (characterCounts.get(i) > 2) { // Adjust this threshold as needed
                System.out.println(characterNames.get(i) + "\t" + characterCounts.get(i));
            }
        }
        System.out.println("\nCharacters with specific part counts:");
        charactersWithNumParts(9, 16); // Test with specific ranges
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < characterNames.size(); i++) {
            int count = characterCounts.get(i);
            if (count >= num1 && count <= num2) {
                System.out.println(characterNames.get(i) + "\t" + count);
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay characters = new CharactersInPlay();
        characters.tester();
    }
}
