package battleship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A single ship in a Battleship game
 */
public class Ship implements Serializable {
    private Board board;
    private int row;
    private int col;
    private int lenght;
    private int hit_count = 0;
    private boolean isSunk = false;
    private Ship.Orientation ort;
    public static final String SUNK_MESSAGE = "A battleship has been sunk!";


    /**
     * Orientation is a property of a ship.
     * The names of the enum values were chosen because they
     * are descriptive and match the words put in the configuration
     * files.
     *
     */
    public enum Orientation {
        HORIZONTAL( 0, 1 ), VERTICAL( 1, 0 );

        /**
         * Use it to loop through all of the cell locations a ship
         * is on, based on the upper left end of the ship.
         */
        public int rDelta, cDelta;

        /**
         * Associate a direction vector with the orientation.
         * @param rDelta how much the row number changes in this direction
         * @param cDelta how much the column number changes
         *               in this direction
         */
        Orientation( int rDelta, int cDelta ) {
            this.rDelta = rDelta;
            this.cDelta = cDelta;
            }
        }

    /**
     * Ship constructor
     * @param board
     * @param uRow
     * @param lCol
     * @param ort
     * @param lenght
     * @throws OverlapException
     * @throws OutOfBoundsException
     */
    public Ship(Board board, int uRow, int lCol, Ship.Orientation ort, int lenght) throws OverlapException, OutOfBoundsException{
        this.board = board;
        this.row = uRow;
        this.col = lCol;
        this.lenght = lenght;
        this.ort = ort;

    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public Ship.Orientation getOrt(){
        return this.ort;
    }

    public int getLenght(){
        return this.lenght;
    }

    public int getHit_count(){
        return hit_count;
    }

    /**
     * Hits the ship
     */
    public void hit(){
            hit_count++;
            if (hit_count >= this.lenght){
                System.out.println(SUNK_MESSAGE);
                isSunk = true;
            }
        }

    /**
     * Checks if the ship is sunk
     * @return
     */
    public boolean isSunk(){
            return isSunk;
        }

    /**
     * Goes to string.
     * @return
     */
    @Override
        public String toString() {
            return "Row= " + this.row + " column= " + this.col;
    }
}
