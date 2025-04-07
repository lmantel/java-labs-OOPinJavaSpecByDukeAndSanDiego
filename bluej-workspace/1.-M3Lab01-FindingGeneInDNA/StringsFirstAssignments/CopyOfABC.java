
/**
 * Write a description of class CopyOfABC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CopyOfABC {
    
    public void findAbc(String input){
       int index = input.indexOf("abc");
       System.out.println("index " + index);
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           //index = input.indexOf("abc",index+4);
           index = input.indexOf("abc",index+3);
           System.out.println("index after updating " + index);
       }
   }

   public void test(){
       //findAbc("abcd");
       //findAbc("abcdabc");
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       System.out.println("\n");
       //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       findAbc("abcabcabcabca");
       
   }

}
