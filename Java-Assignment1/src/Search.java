import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    public static void main(String[] args) {



        File[] files = new File("/home/").listFiles();    //storing the files in home directory in files
        Search search=new Search();
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter the regex to match files");
            String regularExpression = scanner.next();
            search.printFiles(files,regularExpression);             //calling printFiles with files and pattern as parameters
        }

    }

    void printFiles(File[] files,String regularExpression){
        for (File file : files) {
          //checking if it is a file
            if (file.isFile()) {
                Pattern pattern = Pattern.compile(regularExpression);        //converting the string into pattern
                Matcher m = pattern.matcher(file.getName());

                 //checking if file name matches with pattern
                if(m.find())
                System.out.println(file.getPath());

            }
             //if it is a directory, call the printFiles method with new directory
            else if(file.isDirectory()){
                File[] subfiles = new File(file.getPath()).listFiles();
                printFiles(subfiles,regularExpression);
            }
        }
    }
}

/**
TestCases
1.
    Enter the regex to match files
    3.jpg
    /home/zemoso/Documents/3.jpg
    /home/zemoso/pavanreddy21.github.io/new/images/3.jpg

2.
   Enter the regex to match files
   rest.txt
   /home/zemoso/IdeaProjects/assignments/Rest-assignment/rest.txt

3.
  Enter the regex to match files
  ^Sc.*
  /home/zemoso/Pictures/Screenshot from 2019-01-24 06-46-20.png
  /home/zemoso/Pictures/Screenshot from 2019-01-23 17-40-59.png

4.
    Enter the regex to match files
    ^[1,2]
   /home/zemoso/.local/share/Trash/files/101.jpg
   /home/zemoso/.local/share/Trash/files/201.png

5.
    Enter the regex to match files
    ^@

     //No matching files

*/
