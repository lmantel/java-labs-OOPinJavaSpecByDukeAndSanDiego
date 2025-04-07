
/**
 * Write a description of class CaesarTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Arrays;
import edu.duke.*;

class testCaesarCipher {
        
    public void testCaesarCipher() {
            CaesarCipher cc = new CaesarCipher(18);
            String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
            String encrypted = cc.encrypt(message);
            String decrypted = cc.decrypt(encrypted);
            System.out.println("Original message: " + message);
            System.out.println("Encrypted message: " + encrypted);
            System.out.println("Decrypted message: " + decrypted);
    
            char charToEncrypt = 'A';
            char encryptedChar = cc.encryptLetter(charToEncrypt);
            char decryptedChar = cc.decryptLetter(encryptedChar);
    
            System.out.println("\nOriginal character: " + charToEncrypt);
            System.out.println("Encrypted character: " + encryptedChar);
            System.out.println("Decrypted character: " + decryptedChar);
    
            FileResource fr = new FileResource("titus-small.txt");
            String fileInput = fr.asString();
            String fileEncrypted = cc.encrypt(fileInput);
            String fileDecrypted = cc.decrypt(fileEncrypted);
    
            System.out.println("\nFile Encryption and Decryption");
            System.out.println("Encrypted File (First 100 characters): " + fileEncrypted.substring(0, Math.min(100, fileEncrypted.length())));
            System.out.println("Decrypted File (First 100 characters): " + fileDecrypted.substring(0, Math.min(100,fileDecrypted.length())));
    
    }
    
    public void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource("titus-small_key5.txt");
        String encrypted = fr.asString();
        String decrypted = cc.decrypt(encrypted);

        System.out.println("Decrypted titus-small_key5.txt (First 100 characters): " + decrypted.substring(0, Math.min(100, decrypted.length())));
        System.out.println("Key: " + cc.getKey(encrypted));

        CaesarCracker ccPortuguese = new CaesarCracker('a');
        FileResource frPortuguese = new FileResource("oslusiadas_key17.txt");
        String encryptedPortuguese = frPortuguese.asString();
        String decryptedPortuguese = ccPortuguese.decrypt(encryptedPortuguese);

        System.out.println("\nDecrypted oslusiadas_key17.txt (First 100 characters): " + decryptedPortuguese.substring(0, Math.min(100, decryptedPortuguese.length())));
        System.out.println("Key: " + ccPortuguese.getKey(encryptedPortuguese));

    }
    
    public void testVigenereCipher() {
        int[] key = {17, 14, 12, 4}; // "rome"
        VigenereCipher vc = new VigenereCipher(key);

        FileResource fr = new FileResource("titus-small.txt");
        String input = fr.asString();

        String encrypted = vc.encrypt(input);
        String decrypted = vc.decrypt(encrypted);

        System.out.println("Key: " + vc.toString());
        System.out.println("Encrypted (First line): " + encrypted.split("\n")[0]);
        System.out.println("Decrypted (First line): " + decrypted.split("\n")[0]);

        //Verify first line matches given encrypted line
        String expectedEncrypted = "Tcmp-pxety mj nikhqv htee mrfhtii tyv";

        System.out.println("Does encrypted first line match expected? : " + encrypted.split("\n")[0].equals(expectedEncrypted));
    }
    
    public void testSliceString() {
        
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3)); // "adgjm"
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3)); // "behk"
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3)); // "cfil"
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4)); // "aeim"
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4)); // "bfj"
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4)); // "cgk"
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4)); // "dhl"
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5)); // "afk"
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5)); // "bgl"
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5)); // "chm"
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5)); // "di"
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5)); // "ej"
        
        FileResource frKey = new FileResource("athens_keyflute.txt");
        String encryptedKey = frKey.asString();
        int[] key = vb.tryKeyLength(encryptedKey, 5, 'e');
        System.out.println("\nKey found by tryKeyLength: " + Arrays.toString(key));

        // Testing breakVigenere method
        System.out.println("\nTesting breakVigenere:");
        vb.breakVigenere();
        
    }
    
        
}
