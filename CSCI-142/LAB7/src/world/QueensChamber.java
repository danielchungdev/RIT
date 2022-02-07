package world;

import bee.Bee;
import bee.Drone;
import bee.Queen;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The queen's chamber is where the mating ritual between the queen and her
 * drones is conducted.  The drones will enter the chamber in order.
 * If the queen is ready and a drone is in here, the first drone will
 * be summoned and mate with the queen.  Otherwise the drone has to wait.
 * After a drone mates they perish, which is why there is no routine
 * for exiting (like with the worker bees and the flower field).
 *
 * @author Sean Strout @ RIT CS
 * @author Daniel Chung
 */
public class QueensChamber {
    private ConcurrentLinkedQueue<Drone> sexySpot;
    private boolean queenReady;

    /**
     * Constructor for the QueensChambers.
     */
    public QueensChamber(){
        sexySpot = new ConcurrentLinkedQueue( );
        queenReady = false;
    }


    /**
     * Gets the concurrentlinkedqueue.
     * @return
     */
    public ConcurrentLinkedQueue<Drone> getSexySpot() {
        return sexySpot;
    }

    /**
     * This functions checks if the queue is
     * empty or not.
     * @return
     */
    public boolean hasDrone(){
        if (sexySpot.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * This functions adds the drone into the queue
     * and then checks the conditions and both must be
     * true in order for it to mate with the queen, since
     * at first they are always false. this thread goes to
     * wait().
     * @param drone
     * @throws InterruptedException
     */
    public synchronized void enterChamber (Drone drone) throws InterruptedException {
        System.out.print( "*QC* " + drone.toString() + " enters chamber\n" );
        sexySpot.add( drone );
        while (queenReady != false && !drone.equals( sexySpot.peek() ) && hasDrone()){
            this.wait();
            System.out.println( "*QC* " + drone.toString() + " leaves chamber" );
        }
    }

    /**
     * This function dismisses all the drones at the
     * end of the program.
     */
    public synchronized void dismissDrone(){
        for ( Drone i : sexySpot){
            System.out.println( "\n*QC* " + i.toString() + " leaves chamber" );
            i.interrupt();
        }
    }

    /**
     * This function is called by the queen to summon the drones
     * that she is going to mate with. It is the first on in the
     * queue.
     */
    public synchronized void summonDrone(){
        queenReady = true;
        notifyAll();
        Drone thisboi = sexySpot.peek();
        sexySpot.remove();
        System.out.println("*D* " + thisboi.toString() + " leaves chamber");
        thisboi.setMated();
        queenReady = false;
        System.out.println("*D* " + thisboi.toString() + " has perished!");
    }
}