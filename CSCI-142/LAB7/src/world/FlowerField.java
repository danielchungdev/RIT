package world;

import bee.Worker;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * The field of flowers that are ripe for the worker bees to gather the nectar
 * and pollen resources.  The bees can arrive in any order and they are
 * immediately allowed to start gathering, as long as there is a free flower.
 * Otherwise the bee must wait until a flower becomes free.
 *
 * @author Sean Strout @ RIT CS
 * @author YOUR NAME HERE
 */
public class FlowerField {
    /** the maximum number of workers allowed in the field at the same time */
    public final static int MAX_WORKERS = 10;
    /** the current number of workers in the field */
    private int numWorkers;

    /**
     * Constructor for the flower field.
     */
    public FlowerField() {
        this.numWorkers = 0;
    }

    /**
     * This function takes a worker and increases the num of workers.
     * If the field is already full it will do .wait into the worker thread.
     * @param worker
     */
    public synchronized void enterField(Worker worker) {
        if ( numWorkers >= MAX_WORKERS){
            try{
                worker.wait();
            } catch (InterruptedException e) {
                //throw
            }
        } else if (numWorkers <= MAX_WORKERS){
            System.out.println("*FF* " + worker + " enters field");
            this.numWorkers++;
        }
    }


    /**
     * Shows when a worker exits the field, it makes sure
     * to decrease the num of workers there is. And notifies
     * all the threads that there is an empty spot if there are
     * any waiting workers.
     * @param worker
     */
    public synchronized void exitField(Worker worker) {
        if (this.numWorkers == 0){
            System.out.print( "There are no bees in the field" );
        }
        else {
            this.numWorkers--;
            notifyAll();
            System.out.println( "*FF* " + worker + " leaves field" );
        }
    }
}
