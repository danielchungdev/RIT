/*Name: Daniel Chung
 * Lab01: Python To Java
 * 1/23/2018
 * CSC1 - 142
 * */
package lab01.student;

import java.util.Scanner;



public class PrimalityTest {
    /*This function checks if the number is a prime number.
    * Will return a true or false statement.
    * @param: Number
    * @return: true or false
    * */
    public static boolean isPrime(int number){
        int counter = 0;
        for (int i = 1; i < number+1; i++){
            if (number % i == 0){
                counter++;
            }
        }
        if (counter == 2){
            return true;
        }
        else{
            return false;
        }
    }
    /*This is the main function that
    * also contains a loop for the
    * solo output of the code.
    * */
    public static void main(String[]args) {
        while ( 1 > 0){
            Scanner user = new Scanner(System.in);
            user.close();
            System.out.println("Enter a number (0 to quit):");
            int n = user.nextInt();
            if (n < 1){
                System.out.println("Goodbye!");
                System.exit(0);
            }
            else {
                if (isPrime(n) == true){
                    System.out.println(n + " is prime!");
                }
                else {
                    System.out.println(n + " is not prime.");
                }
            }
        }
    }
}
