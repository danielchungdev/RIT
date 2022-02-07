import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class TollReport extends TollRoadDatabase {
    public TollReport(String eventFileName) throws IOException {
        super( eventFileName );
    }

    public static void main(String[]args) throws IOException {
        TollRoadDatabase this_toll = new TollRoadDatabase("data/small.txt");
        //recursion_method( this_toll );
        this_toll.summaryReport();
        System.out.println( "" );
        this_toll.onRoadReport();
        System.out.println( "" );
        this_toll.speedReport();
        System.out.println( "" );
        this_toll.printBills();

        String letter = "a";

        /**
         * Tried the loop but didn't really work,
         * single functions work. Commented it out
         * to see how the single program works.
         */
//        while (letter != "q") {
//            Scanner input = new Scanner( System.in );
//            letter = input.toString();
//            System.out.println( " 'b <string> ' to see the bill for license" + "\n"
//                                + "'e <number.' to see activity at exit" + "\n"
//                                + " 'q' to quit");
//            if (letter == "b"){
//                Scanner inputb = new Scanner( System.in );
//                String a_string = inputb.toString();
//                System.out.println( "" );
//                this_toll.printCustSummary(a_string);
//            }else if ( letter == "e"){
//                Scanner idk = new Scanner( System.in );
//                int inputc = idk.nextInt();
//                System.out.println( "" );
//                this_toll.printExitActivity( inputc );
//            }else if (letter == "q"){
//                break;
//            }
//        }
    }

}

