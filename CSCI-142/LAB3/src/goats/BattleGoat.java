/**
 * Daniel Chung
 * LAB 3
 *2/6/18
 */

package goats;

/**
 * Class that represents the hardened battle goats.
 * 
 * @author Bruce Herring
 */
public class BattleGoat implements IGoat
{
   /**
    * Name of the goat.
    */
   private final String name;

   /**
    * Damage the goat inflicts on other entities.
    */
   private final int damage;

    /**
    * Flag used to denote if the goat is still active.
    */
    private boolean active = true;

   /**
    * Constructor
    *
    * @param name The goats name
    * @param damage The damage amount the goat inflicts 
    */
   public BattleGoat (String name, int damage) {
      this.name = name;
      this.damage = damage;
   }

   /**
    * The conversation the goat displays when approaching the
    * trolls bridge.
    *
    * @return The goats conversation string
    */
   public String approach () {
      return name + " cautiously approaches the bridge";
   }

    /**
    * Action which should be used when the goat interacts with
    * another entity.
    *
    * @return The damage the goat will impart on the other entity
    */
   public int impact () {
      return damage;
   }

      /**
    * Accessor for the goat's active property.
    *
    * @return true if the goat is active, false otherwise
    */
    public boolean isActive () {
      return active;
    }

   /**
    * Mutator for the goat's active property.
    *
    * @param isActive true to make the goat active, false to set it inactive
    */
   public void setActive (boolean isActive) {
      active = isActive;
   }

   /**
    * String representation of the goat.
    *
    * @return The goats name.
    */
   public String toString () {
      return name;
   }
}