/*Name: Daniel Chung
* Lab01: Python To Java
* 1/23/2018
* CSC1 - 142
* */
package lab01.student;

import java.util.Scanner;

public class GoodHashFunc {
    /*This function implements the
    * good hashing function formula
    * @param: String
    * @return: Hashcode value.
    * */
    public static int computeHash(String a){
        int[] array = new int[a.length()];
        int counter = 1;
        for (int i = 0; i < a.length(); i++){
            int value = (int) (a.charAt(i) * Math.pow(31, a.length() - counter));
            counter++;
            array[i] = value;
        }
        int total = 0;
        int ArrayLength = 0;
        while (ArrayLength < array.length){
            total += array[ArrayLength];
            ArrayLength++;
        }
        return total;
    }
    /*This is the main function
    * that call sthe whole code.
    * @Param: User Input for a string.
    * */
    public static void main(String[]args){
        Scanner user = new Scanner(System.in);
        System.out.println("Enter a string");
        String n = user.nextLine();
        System.out.println(computeHash(n));
    }
}
