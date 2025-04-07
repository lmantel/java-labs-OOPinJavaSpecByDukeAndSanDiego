
/**
 * Write a description of class CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {

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

    public String decrypt(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public void testDecrypt() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);
    }

    public String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }

    public void testHalfOfString() {
        String testString = "Qbkm Zgis";
        System.out.println("halfOfString(\"" + testString + "\", 0): " + halfOfString(testString, 0));
        System.out.println("halfOfString(\"" + testString + "\", 1): " + halfOfString(testString, 1));
    }

    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        System.out.println("maxDex: " + maxDex);
        int key = maxDex - 4;
        if (maxDex < 4) {
            key = 26 - (4 - maxDex);
        }
        return key;
    }

    public String decryptTwoKeys(String encrypted) {
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("Key 1: " + key1);
        System.out.println("Key 2: " + key2);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }

    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("Decrypted message: " + decrypted);
    }
    
    public void testGetKey() {
        
        System.out.println("Get Key: " + getKey("Just a test string with lots of eeeeeeeeeeeeeeeees"));
        System.out.println("Get Key: " + getKey("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        
    }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testHalfOfString();
        cb.testDecrypt();
        cb.testDecryptTwoKeys();
        cb.testGetKey();
    }
}
