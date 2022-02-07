/*Name: Daniel Chung
 * Lab01: Python To Java
 * 1/23/2018
 * CSC1 - 142
 * */
package lab01.student;
import java.util.Scanner;

import static turtle.Turtle.* ;


public class HTree {
    public static double MAX_SEGMENT_LENGTH = 200;
    /*This is the implementation of
    * the HTree in Java.
    * @Params: length and depth
    * */
    public static void init(double length, int depth){

        Turtle.setWorldCoordinates((int) (-length * 2), (int) (-length * 2), (int) (length * 2), (int) (length * 2));
        Turtle.title("H-Tree, depth" + Integer.toString(depth));
    }

    public static void DrawHTree(double length, int depth){
        if (depth > 0){

            Turtle.forward(length/2);
            Turtle.left(90);
            Turtle.forward(length/2);
            Turtle.right(90);

            DrawHTree(length/2, depth - 1);

            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);

            DrawHTree(length/2, depth - 1);

            Turtle.left(90);
            Turtle.forward(length/2);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            Turtle.forward(length/2);
            Turtle.right(90);

            DrawHTree(length/2, depth - 1);

            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);

            DrawHTree(length/2, depth - 1);

            Turtle.left(90);
            Turtle.forward(length/2);
            Turtle.right(90);
            Turtle.forward(length/2);
        }
    }
    /*This is the main function
    * that calls the whole code.
    * @Param: user input for depth.
    * */
    public static void main(String[]args){
        Scanner a = new Scanner(System.in);
        System.out.println("Select a depth");
        int depth = a.nextInt();
        if (depth > 0){
            init(MAX_SEGMENT_LENGTH, depth);
            DrawHTree(200, 2);
            System.out.println("Close the window");
        }
    }
}
