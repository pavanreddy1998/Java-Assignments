import java.util.Scanner;

public class Check {
    public static void main(String arg[]){
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter string");
        String string=scanner.nextLine() ;
        int index=0,i=0;
        boolean[] alphabetArray = new boolean[26];
        for (char character:string.toCharArray())
        {
           //if in upper case
            if ('A' <= character && character <= 'Z')
                index = character - 'A';
            //if in lower case
            else if('a' <= character && character <= 'z')
                index = character - 'a';
            alphabetArray[index] = true;           // Mark current character
        }
        for (boolean isMarked:alphabetArray) {
            if (!isMarked) {
                System.out.println("The given string doesnot contain all the characters from a-z");
                return;
            }
        }
                System.out.println("The given string contain all the characters from a-z");
    }
}
//Time Complexity:O(n)
//Space Complexity: O(1)

/**
TestCases:

1.
  enter string
  abcdefghijklmnopqrstuvwxyz
  The given string contain all the characters from a-z

2.
  enter string
  afgd
  The given string doesnot contain all the characters from a-z

3.
  enter string
  ZZZZZZYCCABabcdefghijklmnopqrstuvwx
  The given string contain all the characters from a-z

4.
  enter string
  ABCDrg
  The given string doesnot contain all the characters from a-z

5.
 enter string
 1234567890098765432qwertyui
 The given string doesnot contain all the characters from a-z

*/
