
/**
 * Write a description of class CaesarCipher2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher2 {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher2(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipher2 cc = new CaesarCipher2(26 - mainKey);
        return cc.encrypt(input);
    }




    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex]++;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values) {
        int maxDex = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] > values[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher2 cc = new CaesarCipher2(18);
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        String autoDecrypted = breakCaesarCipher(encrypted);
        System.out.println("Auto Decrypted: " + autoDecrypted);
    }

    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int key = maxDex - 4;
        if (maxDex < 4) {
            key = 26 - (4 - maxDex);
        }
        CaesarCipher2 cc = new CaesarCipher2(key);
        return cc.decrypt(input);
    }

    
}
