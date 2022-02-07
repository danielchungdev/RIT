package game;

/**
 * Represents a single dot in the game of Dots and Boxes.  A dot is uniquely
 * defined by a row and column coordinate.
 *
 * @author Daniel Chung
 */
public class Dot {
    /**
     * Create the dot.
     *
     * @param row the row
     * @param column the column
     * @rit.pre row and column are greater than or equal to 0
     */
    public Dot(int row, int column) {
    }

    /**
     * Get the row.
     *
     * @return the row
     */
    public int getRow() { return 0; }

    /**
     * Get the column.
     *
     * @return the column
     */
    public int getColumn() {return 0; }

    /**
     * Return the string representation of a dot.  Don't get too excited,
     * it's just a dot. :P
     *
     * @return a dot
     */
    @Override
    public String toString() { return null; }

    /**
     * Two dots are equal if they have the same row and column.
     *
     * @param other the dot to compare with
     * @return whether they are equal or not
     */
    @Override
    public boolean equals(Object other) {
        return false;
    }
}
