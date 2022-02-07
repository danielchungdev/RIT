package battleship;

public class OverlapException extends Exception {
    public static final String OVERLAP = " Ships placed in overlapping positions";

    public OverlapException(int row, int col){
        super("Row= " + row + " column= " + col + OVERLAP);
    }
}
