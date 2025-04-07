import edu.duke.*;

public class WordPlay {
        
    public boolean isVowel(char ch) {
        // Convert the character to lowercase for easier comparison
        char lowerCh = Character.toLowerCase(ch);
        return lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u';
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++) {
            char currChar = result.charAt(i);
            if (isVowel(currChar)) {
                result.setCharAt(i, ch);
            }
        }
        return result.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++) {
            char currChar = result.charAt(i);
            if (Character.toLowerCase(currChar) == Character.toLowerCase(ch)) {
                if ((i + 1) % 2 == 1) { // Odd position (i+1)
                    result.setCharAt(i, '*');
                } else { // Even position (i+1)
                    result.setCharAt(i, '+');
                }
            }
        }
        return result.toString();
    }

    // Tester method (main method)
    public void tester() {
        // Test cases
        System.out.println("isVowel('F'): " + isVowel('F')); // Expected: false
        System.out.println("isVowel('a'): " + isVowel('a')); // Expected: true
        System.out.println("isVowel('E'): " + isVowel('E')); // Expected: true
        System.out.println("isVowel('z'): " + isVowel('z')); // Expected: false
        System.out.println("isVowel('I'): " + isVowel('I')); // Expected: true
        
        String phrase1 = "Hello World";
        char replaceChar1 = '*';
        System.out.println("replaceVowels(\"" + phrase1 + "\", '" + replaceChar1 + "'): " + replaceVowels(phrase1, replaceChar1));

        String phrase2 = "Programming is fun";
        char replaceChar2 = '-';
        System.out.println("replaceVowels(\"" + phrase2 + "\", '" + replaceChar2 + "'): " + replaceVowels(phrase2, replaceChar2));

        String phrase3 = "AEIOUaeiou";
        char replaceChar3 = '#';
        System.out.println("replaceVowels(\"" + phrase3 + "\", '" + replaceChar3 + "'): " + replaceVowels(phrase3, replaceChar3));

        // Testing emphasize
        String phrase4 = "dna ctgaaactga";
        char emphasizeChar1 = 'a';
        System.out.println("emphasize(\"" + phrase4 + "\", '" + emphasizeChar1 + "'): " + emphasize(phrase4, emphasizeChar1));

        String phrase5 = "Mary Bella Abracadabra";
        char emphasizeChar2 = 'a';
        System.out.println("emphasize(\"" + phrase5 + "\", '" + emphasizeChar2 + "'): " + emphasize(phrase5, emphasizeChar2));

        String phrase6 = "Banana";
        char emphasizeChar3 = 'a';
        System.out.println("emphasize(\"" + phrase6 + "\", '" + emphasizeChar3 + "'): " + emphasize(phrase6, emphasizeChar3));

        String phrase7 = "Cheerios";
        char emphasizeChar4 = 'e';
        System.out.println("emphasize(\"" + phrase7 + "\", '" + emphasizeChar4 + "'): " + emphasize(phrase7, emphasizeChar4));

    }
        
    
}

