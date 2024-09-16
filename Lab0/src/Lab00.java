/* this part is as comment similar to comments in Python
CPE/CSC 203 Lab00
Name: Brian Kaplan

Section: 15

*/
public class Lab00 {
   public static void main(String[] arguments) {

      // declaring and initializing some variables

      byte x = 12;

      String y = "WELCOME to CSC/CPE203";

      double z = 3.1415;

      // printing the variables

      System.out.println("x:" + x + " y:" + y + " z:" + z);

      // a list (make an array in java)

      int[] nums = {5, 16, -8, 2, 11};

       for (int num : nums) {
           System.out.println(num);
       }

      // call a function

      int numFound = char_count(y, 'C');
      System.out.println("Number of C in " + y);
      System.out.println("Found:" + numFound);

      // a counting for loop

      for (int i = 1; i < 15; i++) {
         System.out.print(i + " ");
      }
   }

   // function counts the given character in the given string
   // str str -> int
   public static int char_count(String s, char c) {
      int count = 0;

         for (char ch: s.toCharArray()) {
            if (ch == c) {
               count++;
            }
         }
//      for (int i = 0; i < s.length(); i += 1) {
//         if (c == s.charAt(i)) {
//            count += 1;
//         }
//      }
      return count;
   }
}