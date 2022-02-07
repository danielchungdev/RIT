package trolls;

import goats.IGoat;

public class BattleTroll implements ITroll{
    public int hp;

    /**
     * This function creates the battle troll.
     * @param hp
     */
    public BattleTroll(int hp){
        this.hp = hp;
    }

    /**
     * This function prints the
     * interaction of the troll and goat.
     * @param goat
     */
    public void interact(IGoat goat){
        System.out.println("The troll eats" + goat.toString());
    }

    /**
     * This function adjusts the HP and lowers it.
     * @param power
     */
    public void adjustPower(int power){
        this.hp -= power;
    }

    /**
     * This function checks if the
     * troll is still alive.
     * @return
     */
    public boolean isActive(){
        boolean condition = true;
        if (this.hp == 0){
            condition = false;
        }
        return condition;
    }

    /**
     * This function prints the finished.
     * @param goat
     */
    public void finished(IGoat goat){
        System.out.println("The troll is vanquished by" + goat.toString());
    }

}
