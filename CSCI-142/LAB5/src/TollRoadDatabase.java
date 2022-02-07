/* A few useful items are provided to you. You must write the rest. */

import java.io.*;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Dictionary;

public class TollRoadDatabase {
    private HashMap<Integer, TollRecord> database = new HashMap<Integer, TollRecord>();
    private HashMap<Integer, TollRecord> completed_trips = new HashMap<>();
    private ArrayList<TollRecord> sorted_records = new ArrayList<>();

    /**
     * For printing floating point values in dollar/cents format. Example:
     * System.out.println( String.format( DOLLAR_FORMAT, 10.5 );  // $10.50
     */
    private static final String DOLLAR_FORMAT = "$%5.2f";
    private static final String SPEED_FORMAT = "%5.1f MpH";

    /**
     * Universal new line
     */
    private static final String NL = System.lineSeparator();

    /**
     * Conversion constant from minutes to hours
     */
    public static final double MINUTES_PER_HOUR = 60.0;

    /**
     * This toll road's speed limit, in miles per hour
     */
    public static final double SPEED_LIMIT = 65.0;

    /**
     * Create for the database.
     * @param eventFileName
     * @throws IOException
     */
    public TollRoadDatabase(String eventFileName) throws IOException {

        BufferedReader reader;
        String this_line;

        reader = new BufferedReader( new FileReader( eventFileName ) );
        while ((this_line = reader.readLine()) != null) {
            String[] record_info = this_line.split( "," );

            enterEvent( record_info[1], Integer.parseInt( record_info[0] ), Integer.parseInt( record_info[2] ) );
        }
        reader.close();
    }

    /**
     * Everytime a car goes through a toll,
     * it calls this to change its fields.
     * @param tag
     * @param time
     * @param exit
     */
    private void enterEvent(String tag, int time, int exit){
        TollRecord the_Record = new TollRecord( tag, time, exit );
        if (database.containsKey( the_Record.hashCode() )){
            TollRecord updated_record = new TollRecord( database.get( the_Record.hashCode() ).getTag(),
                                                        database.get( the_Record.hashCode() ).getOnTime(),
                                                        database.get( the_Record.hashCode() ).getOnExit() );
            updated_record.setOffExit( exit, time );
            database.remove( the_Record.hashCode() );
            int new_Hashed = updated_record.hashCode();
            completed_trips.put( new_Hashed, updated_record);
            sorted_records.add( updated_record );
            sorted_records.sort( TollRecord::compareTo);
        }
        else {
            database.put( the_Record.hashCode(), the_Record );
        }
    }

    /**
     * Gives the summary report
     */
    public void summaryReport(){
        System.out.println( database.size() + " incomplete trips" );
        System.out.println( completed_trips.size() + " completed trips");

    }

    /**
     * Gives the report of the cars that are
     * still on the road.
     */
    public void onRoadReport(){
        System.out.println( "On-road Report" );
        System.out.println( "==============" );
        for ( int i : database.keySet()) {
            System.out.println( database.get( i ) );
        }
    }

    /**
     * Gives the billing information
     * for all the completed trips.
     */
    public void printBills(){
        double total = 0;
        System.out.println( "BILLING INFORMATION" );
        System.out.println( "===================" );
        for ( TollRecord i : sorted_records){
            total += bill( i );
            System.out.println(i.report() + ": $" + String.format( DOLLAR_FORMAT, bill(i)));
        }
        System.out.println( "Total: $" + String.format( DOLLAR_FORMAT, total ));

    }

    /**
     * Gets the bill.
     * @param record
     * @return
     */
    private double bill(TollRecord record){
        double total = record.getFare();
        return total;
    }

    /**
     * Checks for the people who have
     * speed through.
     */
    public void speedReport(){
        double onExit;
        double offExit;
        double distance;
        double time1;
        double time2;
        double time;
        double speed;
        System.out.println( "SPEEDER REPORT" );
        System.out.println( "==============" );
        for ( int i : completed_trips.keySet()){
            String tag = completed_trips.get( i ).getTag();
            onExit = TollSchedule.getLocation( completed_trips.get( i ).getOnExit() );
            offExit = TollSchedule.getLocation( completed_trips.get( i ).getOffExit() );
            distance = Math.abs( onExit - offExit );
            time1 = completed_trips.get( i ).getOnTime();
            time2 = completed_trips.get( i ).getOffTime();
            time = Math.abs( time1 - time2 ) / 60;
            speed = distance/time;
            if (speed > SPEED_LIMIT){
                System.out.println( "Vehicle " + tag +", stating at time " + time1 + "\n" +
                                    "       " + " from " + TollSchedule.getInterchange( completed_trips.get( i ).getOnExit() ) + "\n" +
                                    "       " + " to " + TollSchedule.getInterchange( completed_trips.get( i ).getOffExit() ) + "\n" +
                                    "            " + String.format( SPEED_FORMAT, speed));
            }
        }

    }

    /**
     * Prinst the summary of individual tags.
     * @param tag
     */
    public void printCustSummary(String tag){
        System.out.println( tag );
        int hashed = tag.hashCode();
        if (completed_trips.containsKey( hashed )){
            System.out.println( completed_trips.get( hashed ).report() );
            System.out.println( "Vehicle total due: " + completed_trips.get( hashed ).getFare() );

            System.out.println( "Vehicle total due: $" + String.format( DOLLAR_FORMAT, completed_trips.get( hashed ).getFare() ) );
        }
        else {
            System.out.println( "Vehicle total due: $0.00" );
        }
    }

    /**
     * Prints the report at individual exits
     * @param exit
     */
    public void printExitActivity(int exit){
        System.out.println( "EXIT " + exit + " REPORT" );
        for (TollRecord i : sorted_records){
            if (i.getOnExit() == exit){
                System.out.println(i.report() );
            }
        }
        for (int i : database.keySet()){
            if (database.get( i ).getOnExit() == exit){
                System.out.println( database.get( i ) );
            }
        }


    }
}
