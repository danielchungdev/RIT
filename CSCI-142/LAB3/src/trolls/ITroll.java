package trolls;

import goats.IGoat;

/**
 * This is the interface for the troll.
 */

public interface ITroll {

    void interact(IGoat goat);

    void adjustPower(int power);

    boolean isActive();

    void finished(IGoat goat);


}
