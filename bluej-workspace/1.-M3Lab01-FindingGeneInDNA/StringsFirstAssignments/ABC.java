
/**
 * Write a description of class ABC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ABC {
    
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || (index > input.length() - 4)) {
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    
    }
    
   public void test() {
       //findAbc("abcd");
       findAbc("abcdabc");
    //no code yet
}

}
