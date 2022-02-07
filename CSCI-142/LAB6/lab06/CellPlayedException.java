package battleship;

public class CellPlayedException extends Exception {
    public static final String ALREADY_HIT = " This cell has already been hit";

    public CellPlayedException(int row, int col){
        super("row= " + row + " column= " + col + ALREADY_HIT);
    }
}
