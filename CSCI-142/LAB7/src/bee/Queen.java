package bee;

import util.RandomBee;
import world.BeeHive;
import javax.management.Query;
import java.util.Random;

/**
 * The queen is the master of the bee hive and the only bee that is allowed
 * to mate with the drones.  The way the queen works is she will try to
 * mate with a drone if these two conditions are met:<br>
 * <br>
 * <ul>
 *     <li>The bee hive has 1 unit of nectar and 1 unit of pollen</li>
 *     <li>There is a drone available and ready to do the wild thing</li>
 * </ul>
 * <br>
 * After the stimulating mating session which takes one unit of time,
 * the queen produces between 1 and 4 new bees (if resources exist).
 * Finally, the queen takes a break and smokes a cigarette and puts on some
 * netflix before she chills with her next drone.
 *
 * @author Sean Strout @ RIT CS
 * @author Daniel Chung
 */
public class Queen extends Bee {
    /**
     * the amount of time the queen waits after performing a task, whether she mated
     * this specific time or not.
     */
    public final static int SLEEP_TIME_MS = 1000;
    /** the time it takes for the queen and the drone to mate */
    public final static int MATE_TIME_MS = 1000;
    /** the minimum number of new bees that will be created by one mating session */
    public final static int MIN_NEW_BEES = 1;
    /** the maximum number of new bees that will be created by one mating session */
    public final static int MAX_NEW_BEES = 4;

    /**
     * Create the queen.  She should get the queen's chamber from the bee hive.
     *
     * @param beeHive the bee hive
     */
    public Queen(BeeHive beeHive) {
        super(Role.QUEEN, beeHive);
    }

    /**
     * The queen will continue performing her task of mating until the bee hive
     * becomes inactive. Each time she tries to mate, whether successful or not,
     * she will sleep for the required time.
     * The queen will first check that both conditions are met (see the class
     * level description).  If so, the queen will summon the next drone,
     * and sleep to simulate the mating time.  Next,
     * the queen will roll the dice to see how many bees she should
     * try and create, between the min and max inclusive.  Each time there are
     * enough resources a new bee is created.  The bees are created based on
     * another random dice roll - a nectar worker bee has a 20% chance
     * of being created, a pollen bee has a 20% change of being created,
     * and a drone has a 60% change of being created.  After all the bees
     * are created for a single mating message, you should display:<br>
     * <br>
     * <tt>*Q* Queen birthed # children</tt><br>
     * <br>
     * <br>
     * When the simulation is over and before the queen can retire, she needs
     * to make sure that she individually dismisses each drone that is
     * still waiting in her chamber.
     */

    /**
     * This is my helper function that based on the int that it is given it will
     * determine what type of bee it makes.
     * @param number
     * @return
     */
    public Bee makeBee(int number){
        if (number <= 3){
            return Bee.createBee( Role.DRONE, Worker.Resource.NONE, this.beeHive );
        }else if (number == 4){
            return Bee.createBee( Role.WORKER, Worker.Resource.NECTAR, this.beeHive );
        }else {
            return Bee.createBee( Role.WORKER, Worker.Resource.POLLEN, this.beeHive );
        }
    }

    /**
     * This is the functionality of the queen, it is explained line by line
     * inside the code.
     */
    public void run() {
        while ( beeHive.isActive() ){
            //Checks if the beehive has the resources to make one and checks if there are any
            //drones in the queen's chamber.
            if (beeHive.hasResources() && beeHive.getQueensChamber().getSexySpot().size() > 0){
                try {
                    beeHive.getQueensChamber().summonDrone();
                    //The time that it takes to sleep.
                    this.sleep( MATE_TIME_MS );
                    //Using RandomBee determines how many bees there are going to be.
                    int amountBees = RandomBee.nextInt( MIN_NEW_BEES, MAX_NEW_BEES );
                    for (int i = 0; i <= amountBees; i++) {
                        //checks if the beehive has enough resources to make
                        //a bee.
                        if (beeHive.hasResources()) {
                            //Uses the functions created above to determine
                            //what the new bee role is going to be.
                            int naturalSelection = RandomBee.nextInt( MIN_NEW_BEES, MAX_NEW_BEES );
                            //deducts one from pollen and nectar for the reproduction.
                            beeHive.claimResources();
                            //Cause as soon as they are born the gotta work xD!!
                            Bee childAbuseVictim = makeBee( naturalSelection );
                            //The poor childAbuseVictim starts to work as soon as it's born.
                            childAbuseVictim.start();
                            //Adds the abused child bee into the bees.
                            this.beeHive.addBee( childAbuseVictim );
                        }
                    }
                    System.out.println( "*Q* Queen birthed " + amountBees + " children" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}