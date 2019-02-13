import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class KycRangeFinder {
    /**
     * finds the the allowable date range to complete kyc.
     * @param signUpDateString
     *
     * @param currentDateString
     */
    private static void findRange(String  signUpDateString,String currentDateString){

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate anniversaryDate = LocalDate.parse(signUpDateString, dateFormat);
        LocalDate currentDate = LocalDate.parse(currentDateString, dateFormat);

        if (currentDate.getYear() <= anniversaryDate.getYear()) {
            noRange();
            return;
        }

        LocalDate anniversaryInCurrentyear = LocalDate.of(currentDate.getYear(), anniversaryDate.getMonth(), anniversaryDate.getDayOfMonth());
        LocalDate startRange = anniversaryInCurrentyear.minusDays(30);
        LocalDate endRange = anniversaryInCurrentyear.plusDays(30);

        if (startRange.isBefore(currentDate)){
            //current date is in between the startRange and endRange
            //the range of dates  will be from startRange to currentDate
            if(endRange.isAfter(currentDate))
                printRange(startRange.format(dateFormat),currentDate.format(dateFormat));
            //currentDate is after the endRange then
            //the range of dates will be from startRange to endRange
            else printRange(startRange.format(dateFormat),endRange.format(dateFormat));
        }
        //if startRange is after the currentDate in the same year
        else {
            //going a year back
            anniversaryInCurrentyear=anniversaryInCurrentyear.minusYears(1);
           //if no anniversary possible
            if(anniversaryInCurrentyear.equals(anniversaryDate)) {
                noRange();
                return;
            }
           //calculating start and end range in previous year
            startRange = anniversaryInCurrentyear.minusDays(30);
            endRange = anniversaryInCurrentyear.plusDays(30);

            if(endRange.isAfter(currentDate))
                printRange(startRange.format(dateFormat),currentDate.format(dateFormat));

            else printRange(startRange.format(dateFormat),endRange.format(dateFormat));
        }

    }

    /**
     * This method is called when there is no range possible.
     * It prints No Range.
     */

    private static void noRange () {
        System.out.println("No Range");
    }


    /**
     * Prints the range when the Date Strings are passed.
     * @param startRangeString
     * @param currentDateString
     */
    private static void printRange (String startRangeString,String currentDateString) {
        System.out.println(startRangeString + " " + currentDateString);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noOfTestCases = sc.nextInt();
        for (int i = 0; i < noOfTestCases; i++) {
            String signUpDateString = sc.next();
            String currentDateString = sc.next();

             try {
            findRange(signUpDateString,currentDateString);
            }
            catch (Exception e) {
              System.out.println("There may be some error in entered dates");
            }

        }
    }

}

/**
TestCases
5
16-07-1998 27-06-2017
04-02-2016 04-04-2017
04-05-2017 04-04-2017
04-04-2015 04-04-2016
04-04-2015 15-03-2016

Output:

16-06-2017 27-06-2017
05-01-2017 06-03-2017
No range
05-03-2016 04-04-2016
05-03-2016 15-03-2016

*/
