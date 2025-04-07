import edu.duke.*;

public class CaesarCipher {

    
    public void eyeballDecrypt(String encrypted) {
        
        CaesarCipher cipher = new CaesarCipher();
        for (int k = 0; k < 26; k++) {
        
            String s = cipher.encrypt(encrypted,k);
            System.out.println(k+"\t"+s);
        
        }
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetUpper1 = alphabetUpper.substring(key1) + alphabetUpper.substring(0, key1);
        String shiftedAlphabetUpper2 = alphabetUpper.substring(key2) + alphabetUpper.substring(0, key2);
        String shiftedAlphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0, key1);
        String shiftedAlphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0, key2);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isUpperCase = Character.isUpperCase(currChar);
            String alphabet = isUpperCase ? alphabetUpper : alphabetLower;
            String shiftedAlphabet = (i % 2 == 0) ? (isUpperCase ? shiftedAlphabetUpper1 : shiftedAlphabetLower1) : (isUpperCase ? shiftedAlphabetUpper2 : shiftedAlphabetLower2);

            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public String decryptTwoKeys(String input, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
    
        // Calcular los desplazamientos inversos
        int decryptKey1 = 26 - (key1 % 26); // Asegura que el desplazamiento esté en el rango 0-25
        int decryptKey2 = 26 - (key2 % 26);
    
        String shiftedAlphabetUpper1 = alphabetUpper.substring(decryptKey1) + alphabetUpper.substring(0, decryptKey1);
        String shiftedAlphabetUpper2 = alphabetUpper.substring(decryptKey2) + alphabetUpper.substring(0, decryptKey2);
        String shiftedAlphabetLower1 = alphabetLower.substring(decryptKey1) + alphabetLower.substring(0, decryptKey1);
        String shiftedAlphabetLower2 = alphabetLower.substring(decryptKey2) + alphabetLower.substring(0, decryptKey2);
    
        for (int i = 0; i < decrypted.length(); i++) {
            char currChar = decrypted.charAt(i);
            boolean isUpperCase = Character.isUpperCase(currChar);
            String alphabet = isUpperCase ? alphabetUpper : alphabetLower;
            String shiftedAlphabet = (i % 2 == 0) ? (isUpperCase ? shiftedAlphabetUpper1 : shiftedAlphabetLower1) : (isUpperCase ? shiftedAlphabetUpper2 : shiftedAlphabetLower2);
    
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                decrypted.setCharAt(i, newChar);
            }
        }
        return decrypted.toString();
    }
    
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
    
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int idxUpper = alphabetUpper.indexOf(currChar);
                if (idxUpper != -1) {
                    char newChar = shiftedAlphabetUpper.charAt(idxUpper);
                    encrypted.setCharAt(i, newChar);
                }
            } else if (Character.isLowerCase(currChar)) {
                int idxLower = alphabetLower.indexOf(currChar);
                if (idxLower != -1) {
                    char newChar = shiftedAlphabetLower.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
    return encrypted.toString();
    }   
    
    
    public String encryptUpper(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public void testCaesarTwoKeys() {
        // Testing encryptTwoKeys
        String phrase1 = "Just a test string with lots of eeeeeeeeeeeeeeeees ";
        int key1 = 23;
        int key2 = 2;
        System.out.println("encryptTwoKeys(\"" + phrase1 + "\", " + key1 + ", " + key2 + "): " + encryptTwoKeys(phrase1, key1, key2));

        String phrase2 = "Run like wild to beat the wind";
        int key3 = 2;
        int key4 = 20;
        System.out.println("encryptTwoKeys(\"" + phrase2 + "\", " + key3 + ", " + key4 + "): " + encryptTwoKeys(phrase2, key3, key4));

        String phrase3 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key5 = 21;
        int key6 = 8;
        System.out.println("encryptTwoKeys(\"" + phrase3 + "\", " + key5 + ", " + key6 + "): " + encryptTwoKeys(phrase3, key5, key6));
    }
    
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public void testCaesarDecrypt() {
        String encrypted = "Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!";
        eyeballDecrypt(encrypted);
    }
    
    public void testdecryptTwoKeys() {
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        int key1 = 14;
        int key2 = 24;
        System.out.println(decryptTwoKeys(encrypted,key1,key2));
    }
}

