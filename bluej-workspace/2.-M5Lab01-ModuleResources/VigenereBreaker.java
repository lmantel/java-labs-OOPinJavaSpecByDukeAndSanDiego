import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    
    public String sliceString(String message, int whichSlice, int totalSlices) {        
        StringBuilder sliced = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sliced.append(message.charAt(i));
        }
        return sliced.toString();
    }
    

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int realWordCount = 0;
        String[] words = message.split("\\W+");
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                realWordCount++;
            }
        }
        
        return realWordCount;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCounts = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    char lowerC = Character.toLowerCase(c);
                    if (!charCounts.containsKey(lowerC)) {
                        charCounts.put(lowerC, 1);
                    } else {
                        charCounts.put(lowerC, charCounts.get(lowerC) + 1);
                    }
                }
            }
        }
        char mostCommonChar = 'a';
        int maxCount = 0;
        for (char c : charCounts.keySet()) {
            if (charCounts.get(c) > maxCount) {
                maxCount = charCounts.get(c);
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }
    
    // public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        // int maxRealWords = 0;
        // String bestDecryption = "";
        // for (int keyLength = 1; keyLength <= 100; keyLength++) {
            // int[] key = tryKeyLength(encrypted, keyLength, 'e'); // Assuming 'e' is most common
            // VigenereCipher vc = new VigenereCipher(key);
            // String decrypted = vc.decrypt(encrypted);
            // int realWords = countWords(decrypted, dictionary);
            // if (realWords > maxRealWords) {
                // maxRealWords = realWords;
                // bestDecryption = decrypted;
                // System.out.println("\nKey found by tryKeyLength: " + Arrays.toString(key));
                // System.out.println("\nKeyLength: " + key.length);
                // System.out.println("\nValidWords: " + realWords);
            // }
        // }
        // return bestDecryption;
    // }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxRealWords = 0;
        String bestDecryption = "";
        char mostCommonChar = mostCommonCharIn(dictionary);
        for (int keyLength = 1; keyLength <= 100; keyLength++) {
            int[] key = tryKeyLength(encrypted, keyLength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int realWords = countWords(decrypted, dictionary);
            if (realWords > maxRealWords) {
                maxRealWords = realWords;
                bestDecryption = decrypted;
                System.out.println("\nKey found by tryKeyLength: " + Arrays.toString(key));
                System.out.println("\nKeyLength: " + key.length);
                System.out.println("\nValidWords: " + realWords);
            }
        }
        return bestDecryption;
    }

    // public void breakVigenere () {
        // // FileResource fr = new FileResource();
        // // String encrypted = fr.asString();
        // // int[] key = tryKeyLength(encrypted, 4, 'e'); // key length 5 for athens_keyflute.txt
        // // VigenereCipher vc = new VigenereCipher(key);
        // // String decrypted = vc.decrypt(encrypted);
        // // System.out.println("Decrypted message:\n" + decrypted);
        // // System.out.println("\nKey found by tryKeyLength: " + Arrays.toString(key));
        // FileResource fr = new FileResource();
        // String encrypted = fr.asString();
        // FileResource frDictionary = new FileResource();
        // HashSet<String> dictionary = readDictionary(frDictionary);
        // String decrypted = breakForLanguage(encrypted, dictionary);
        // System.out.println("Decrypted message:\n" + decrypted);
    
    // }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxWords = 0;
        String bestLang = "";
        String bestDecryption = "";

        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int realWords = countWords(decrypted, dictionary);

            if (realWords > maxWords) {
                maxWords = realWords;
                bestLang = lang;
                bestDecryption = decrypted;
            }
        }

        System.out.println("Language: " + bestLang);
        System.out.println("Decrypted message:\n" + bestDecryption);
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] languageNames = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};

        for (String lang : languageNames) {
            FileResource frDict = new FileResource("dictionaries/" + lang);
            languages.put(lang, readDictionary(frDict));
            System.out.println("Read dictionary for " + lang);
        }

        breakForAllLangs(encrypted, languages);
    }
    
}
