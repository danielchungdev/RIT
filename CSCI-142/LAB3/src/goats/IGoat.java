/**
 * Daniel Chung
 * LAB 3
 *2/6/18
 */
package goats;

/**
 * Interface for the goats.
 *
 * @author Bruce Herring
 */
public interface IGoat {

   /**
    * Goat conversation as it approaches the trolls bridge.
    *
    * @return String containing the goats conversation.
    */
   String approach ();

   /**
    * Method which will be called when the goat comes in
    * contact with a trolls.
    *
    * @return Goat impact on the trolls.
    */
    int impact ();

   /**
    * Method used to determine if the goat is still active.
    *
    * @return True if the goat is still active, false otherwise.
    */
   boolean isActive ();

   /**
    * Mutator for the goats active property.
    *
    * @param isActive true to make the goat active, false to set it inactive
    */
   void setActive (boolean isActive);
}