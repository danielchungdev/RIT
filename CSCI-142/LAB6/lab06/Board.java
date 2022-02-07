package battleship;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class to represent the grid of cells (squares).
 * A collection of ships is also kept so the Board
 * can be asked if the game is over.
 * The class is Serializable so that its instance can
 * be saved to a file in binary form using an
 * {@link java.io.ObjectOutputStream} and restored
 * with an {@link java.io.ObjectInputStream}.
 * Because the object holds references to all other
 * objects in the system, no other objects need to
 * be separately saved.
 */
public class Board implements Serializable {

    int row;
    int column;
    List<Ship> shipCollection = new ArrayList<>();
    Cell[][] cells;

    /**
     * Constructor for the board
     * @param row
     * @param column
     */
    public Board(int row, int column){
        this.row = row;
        this.column = column;
        this.cells = new Cell[row][column];
        add_to_array(row, column);
    }

    /**
     * Adds to the array
     * @param row
     * @param column
     */
    public void add_to_array(int row, int column){
        int i;
        int j;
        for (i = 0; i < row; i++){
            for (j = 0; j < column; j++){
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Adds a ship to the collection
     * @param ship
     */
    public void addShip(Ship ship){
        this.shipCollection.add(ship);
    }

    /**
     * A string
     * @return
     */
    @Override
    public String toString(){
        return "This board contains " + this.shipCollection.size() +" ships and " +
                "the it is " + row +" rows and " + column +" columns";
    }

    /**
     * Gets the cell and throws OutOfBoundsException
     * @param row
     * @param column
     * @return
     * @throws OutOfBoundsException
     */
    public Cell getCell(int row, int column) throws OutOfBoundsException{
        if (row > this.row || column > this.column){
            throw new OutOfBoundsException(row, column);
        }
        return cells[row][column];
    }

    /**
     * Sees if everything is sunk
     * @return
     */
    public boolean allSunk(){
        int lenght = shipCollection.size();
        int count = 0;
        for(Ship i : shipCollection){
            if (i.isSunk()){
               count++;
            }
        }
        if (count == lenght){
            return true;
        }
        else {
            return false;
        }
    }
}