package battleship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 */
public class Cell implements Serializable {
    private int row;
    private int col;
    private boolean hit;
    private Ship ship;
    private char currentval;
    public static final char SUNK_SHIP_SECTION = '*';
    public static final char HIT_SHIP_SECTION = '☐';
    public static final char HIT_WATER = '.';
    public static final char PRISTINE_WATER = '_';
    public static final char HIDDEN_SHIP_SECTION = 'S';

    /**
     * Creates the cell
     * @param row
     * @param col
     */
    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.hit = false;
        this.ship = null;
        this.currentval = PRISTINE_WATER;
    }

    /**
     * Hits the cell and depending on the
     * situation displays a different char.
     * @throws CellPlayedException
     */
    public void hit() throws CellPlayedException{
        if (this.hit == true){
            throw new CellPlayedException(this.row, this.col);
        }
        if (this.ship != null){
            this.ship.hit();
            this.currentval = HIT_SHIP_SECTION;
            this.hit = true;
        }else if (this.ship != null && this.ship.isSunk()){
            this.ship.hit();
            this.currentval = SUNK_SHIP_SECTION;
            this.hit = true;
        }
        else {
            this.currentval = HIT_WATER;
            this.hit = true;
        }
    }

    /**
     * displays the whole places of the ship.
     * @return
     */
    public char displayHitStatus(){
        return currentval;
    }

    /**
     * Puts a ship into the cell.
     * @param ship
     * @throws OverlapException
     */
    public void putShip(Ship ship) throws OverlapException{
        if (this.ship != null) {
            throw new OverlapException(this.row, this.col);
        }
        this.ship = ship;
    }

    /**
     * Returns the char.
     * @return
     */
    public char displayChar(){
        return currentval;
    }
}
