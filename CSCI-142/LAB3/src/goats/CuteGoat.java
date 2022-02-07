/**
 * Daniel Chung
 * LAB 3
 *2/6/18
 */
package goats;

/**
 * The cute goat class, where everything is happy and rainbows.
 *
 * @author Bruce Herring
 */
public class CuteGoat implements IGoat {

   /**
    * The goats number (name).
    */
   private final int name;

   /**
    * The amount of happiness a goat will impart when it
    * interacts with something.
    */
   private final int happiness;

   /**
    * Flag used to denote if the goat is still active.
    */
   private boolean active = true;

   /**
    * Constructor
    *
    * @param name The goats name
    * @param happiness The goats happiness power
    */
   public CuteGoat (int name, int happiness) {
      this.name = name;
      this.happiness = happiness;
   }

   /**
    * The conversation the goat displays when approaching the
    * trolls bridge.
    *
    * @return The goats conversation string
    */
   public String approach () {
      return this + " hops along the bridge";
   }

   /**
    * Action which should be used when the goat interacts with
    * the trolls.
    *
    * @return The happiness the goat will impart on the other entity
    */
   public int impact () {
      return happiness;
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
      return name + " the goat";
   }
}