package battleship;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class Battleship {
    public static void main(String[] args) throws IOException, OutOfBoundsException, CellPlayedException, OverlapException, ClassNotFoundException {

        /**
         * This part of the function, uses the buffered reader to read a
         * file into the program. This then turns it into a space separated
         * values into a integer string. We use indexing to create the board
         * with the first line of the file which are the
         */
        BufferedReader reader;
        String line;
        reader = new BufferedReader(new FileReader("data/4-4-0-0-2.txt"));

        String[] boardDimmensions = reader.readLine().split(" ");
        Board current_board = new Board(Integer.parseInt(boardDimmensions[0]), Integer.parseInt(boardDimmensions[1]));
        while ((line = reader.readLine()) != null) {
            String[] info = line.split(" ");
            if (info[2].equals("HORIZONTAL")) {
                Ship current_ship = new Ship(current_board, Integer.parseInt(info[0]),
                        Integer.parseInt(info[1]),
                        Ship.Orientation.HORIZONTAL,
                        Integer.parseInt(info[3]));
                current_board.shipCollection.add(current_ship);
            } else {
                Ship current_ship = new Ship(current_board, Integer.parseInt(info[0]),
                        Integer.parseInt(info[1]),
                        Ship.Orientation.VERTICAL,
                        Integer.parseInt(info[3]));
                current_board.shipCollection.add(current_ship);
            }
        }


        /**
         * This part of the function, takes the values from the array
         * previously taken, and adds them into the board to each
         * individual cell and updates the ship field in the cell.
         * The function creates 2 separates for the Horizontal and
         * vertical because they both take different col and row values.
         */
        for (Ship i : current_board.shipCollection) {
            if (i.getOrt().equals("HORIZONTAL")) {
                for (int j = 0; j < i.getLenght(); j++) {
                    if ((i.getCol() + j) > current_board.column) {
                        throw new OutOfBoundsException(i.getRow(), i.getCol() + j);
                    }
                    current_board.getCell(i.getRow(), i.getCol() + j).putShip(i);
                }
            } else {
                for (int k = 0; k < i.getLenght(); k++) {
                    if ((i.getRow() + k) > current_board.column) {
                        throw new OutOfBoundsException(i.getRow() + k, i.getCol());
                    } else {
                        current_board.getCell(i.getRow() + k, i.getCol()).putShip(i);
                    }
                }
            }
        }

        /**
         * This is just a test, prints out the 2D Array with a test hit
         * and the pristine waters in it. To run it must comment out the
         * part below this one.
         */
//        current_board.getCell(2, 3).hit();
//        current_board.getCell(3, 3).hit();
//        int i;
//        int j;
//        for (i = 0; i < Integer.parseInt(boardDimmensions[0]); i++) {
//            for (j = 0; j < Integer.parseInt(boardDimmensions[1]); j++) {
//                System.out.print(current_board.getCell(i, j).displayChar() + " ");
//            }
//            System.out.println();
//        }

        /**
         * This part of the function plays the game with the ships on the position that they are
         * put in by the previous function.
         */
        while (current_board.allSunk() == false) {
            BufferedReader userInput = new BufferedReader(( new InputStreamReader( System.in)));
            String[] userI = userInput.readLine().split(" ");
            if (userI[0].equals("q")){
                break;
            }
            else if (userI[0].equals("h")){
                current_board.getCell(Integer.parseInt(userI[1]), Integer.parseInt(userI[2])).hit();
            }
            else if (userI[0].equals("!")){
                current_board.getCell(Integer.parseInt(userI[1]), Integer.parseInt(userI[2])).displayHitStatus();
            }
            else if (userI[0].equals("s")){
                FileInputStream fis = new FileInputStream("t.tmp");
                ObjectInputStream ois = new ObjectInputStream(fis);

                int i = ois.readInt();
                String today = (String) ois.readObject();
                Date date = (Date) ois.readObject();

                ois.close();
            }

            int i;
            int j;
            for (i = 0; i < Integer.parseInt(boardDimmensions[0]); i++) {
                for (j = 0; j < Integer.parseInt(boardDimmensions[1]); j++) {
                    System.out.print(current_board.getCell(i, j).displayChar() + " ");
                }
                System.out.println();
            }
        }
    }
}


