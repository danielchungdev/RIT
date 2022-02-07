/* A few useful items are provided to you. You must write the rest. */

import java.util.TooManyListenersException;

import static java.util.Objects.hash;

public class TollRecord implements Comparable<TollRecord>{

    /**
     * For printing toll records in reports
     * using {@link String#format(String, Object...)}
     */
    private static final String TOLL_RECORD_FORMAT = "[%11s] on #%2d, time %5d";
    private static final String OFF_FORMAT = "; off #%2d, time %5d";
    private String tag;
    private int onTime;
    private int OnExit;
    private int exitTime;
    private int exitNumber;

    public static final int UNINITIALIZED = -1;

    /**
     * Constructor for the TollRecord
     * @param tag
     * @param onTime
     * @param onExit
     */

    public TollRecord(String tag, int onTime, int onExit){
        this.tag = tag;
        this.OnExit = onExit;
        this.onTime = onTime;
        this.exitTime = UNINITIALIZED;
    }

    /**
     * Everytime an event happens it calls this function
     * to set the exit number and the exit time.
     * @param exitNumber
     * @param exitTime
     */

    public void setOffExit(int exitNumber, int exitTime){
        this.exitNumber = exitNumber;
        this.exitTime = exitTime;
    }

    /**
     * Getters for the individual values.
     * @return
     */
    public String getTag(){
        return this.tag;
    }

    public int getOnExit(){
        return this.OnExit;
    }

    public int getOnTime(){
        return this.onTime;
    }

    public int getOffExit(){
        return this.exitNumber;
    }

    public int getOffTime(){
        return this.exitTime;
    }

    /**
     * Gets the fare for the Tollschedule.
     * @return
     */
    public double getFare(){
        Double unrounded = TollSchedule.getFare( getOnExit(), exitNumber);
        return unrounded;
    }
    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        else if (  o instanceof TollRecord){
            TollRecord Toll = (TollRecord) o;
            return Toll.tag.equals(this.tag)
                    && Toll.onTime == this.onTime
                    && Toll.OnExit == this.OnExit
                    && Toll.exitNumber == this.exitNumber
                    && Toll.exitTime == this.exitTime;
        }
        return false;
    }

    public String toString(){
        return "[" + this.tag + "]" + " on #" + getOnExit() + ", " + "time   " + getOnTime();
    }

    public String report(){
        return "[" + this.tag + "]" + " on #"+ getOnExit() + ", time   " + getOnTime() + "; " + "off #" + getOffExit() + ", time   " + getOffTime();
    }

    public int hashCode(){
        return hash(this.tag);
    }

    /**
     * CompareTo method for the sort of the list.
     * @param record
     * @return
     */
    public int compareTo(TollRecord record){
        return tag.compareTo( record.tag );
    }


}
