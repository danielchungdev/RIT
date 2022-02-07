package trolls;

import goats.IGoat;

public class CuteTroll implements ITroll{
    public final int maxHappy;
    public int currenthappy = 0;

    /**
     * This function creates the cute troll
     * @param maxHappy
     */
    public CuteTroll(int maxHappy){
        this.maxHappy = maxHappy;
    }

    /**
     * This function prints the interaction.
     * @param goat
     */
    public void interact(IGoat goat){
        System.out.println("The troll pets" + goat.toString());
    }

    /**
     * This function adjusts the happiness
     * @param power
     */
    public void adjustPower(int power){
        this.currenthappy += power;
    }

    /**
     * This function prints the finished.
     * @param goat
     */
    public void finished(IGoat goat){
        System.out.println("The troll falls asleep after petting" + goat.toString());
    }

    /**
     * This function shows if the troll is alive or not.
     * @return
     */
    public boolean isActive(){
        boolean condition = true;
        if (currenthappy == maxHappy){
            condition = false;
        }
        return condition;
    }

}
