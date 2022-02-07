package battleship;

public class OutOfBoundsException extends Exception {
    public static final String PAST_EDGE = " Coordinates are past board edge";

    public OutOfBoundsException(int row, int col){
        super("Row= " + row + " column= " + col + PAST_EDGE);
    }
}
