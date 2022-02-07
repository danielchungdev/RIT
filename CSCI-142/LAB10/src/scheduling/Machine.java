package scheduling;

import scheduling.Job;

import java.util.Collection;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class Machine {
    private int ID;
    private int FinishTime;
    private List<Dictionary<String, Integer>> items;
    private Map<Job, Integer> jobs;

    /**
     * Constructor for the  machine
     * @param ID
     * @param finishTime
     */
    public Machine(int ID, int finishTime){
        this.ID = ID;
        this.FinishTime = finishTime;
    }

    /**
     * gets the ID of the machine
     * @return
     */
    public int getID(){
        return this.ID;
    }

    /**
     * Returns the finish time
     * @return
     */
    public int getFinishTime(){
        return this.getFinishTime();
    }

    /**
     * Gets the items list.
     * @return
     */
    public List<Dictionary<String, Integer>> getItems() {
        return this.items;
    }

    /**
     * Adds a job to the map.
     * @param job
     * @param jobsMap
     */
    public void addJob(Job job, Map<Job, Integer> jobsMap){
        jobsMap.put(job, job.getRank());
    }

    public void setup(List<Object> items, int number){

    }

    /**
     * Formats the printing
     * @return
     */
    @Override
    public String toString() {
        String toString = "Machine" + this.ID + ": ";
        for (Dictionary key : items){
            toString.concat("(" + key.toString() + ")");
        }
        return toString;
    }
}
