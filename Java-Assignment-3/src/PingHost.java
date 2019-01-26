import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PingHost {
    String url;
    Process process;
    PingHost(String string){
        url="ping -c 9 "+string;
    }
    void pingUrl(){
        try{
            String string;
            process=Runtime.getRuntime().exec(url);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));   //reading the output of the command into buffer reader
            int count = 0;
            List<Float> times = new ArrayList<>();
            while ((string = stdInput.readLine()) != null) {
                if(count>=1 && count<10){
                    times.add(Float.valueOf(string.substring(string.lastIndexOf("=")+1,string.length()-3)));    //extracting the time from the output
                    }
                count++;
            }
           Collections.sort(times);
            System.out.println("Median of the ping time is "+times.get(5));                //printing the median
    }
        catch (Exception e){
            e.printStackTrace();
        }
    }

        public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter website to ping");
        String input=scanner.next();
            System.out.println("please wait, checking for ping time");
        PingHost pinghost=new PingHost(input);
        pinghost.pingUrl();

    }
}


/**

TestCases
1.
  enter website to ping
  www.google.com
  please wait, checking for ping time
  Median of the ping time is 319.0

2.
  enter website to ping
  www.amazon.com
  please wait, checking for ping time
  Median of the ping time is 226.0
3.
  enter website to ping
  www.googleping.com
  please wait, checking for ping time
  Median of the ping time is 340.0

4.
  enter website to ping
  www.google.com
  please wait, checking for ping time
  Median of the ping time is 296.0

5.
  enter website to ping
  www.gmail.com
  please wait, checking for ping time
  Median of the ping time is 394.0


*/
