/*Name: Daniel Chung
 * Lab01: Python To Java
 * 1/23/2018
 * CSC1 - 142
 * */
package lab01.student;

import java.util.ArrayList;



public class SieveOfEratosthenes {
    /*Implementation of SieveOfEratosthenes.
     * This finds all the prime numbers and marks
     * them as 0 and 1.
     * @param: a bound of numbers.
     * @return: a primes list.
     * */
    public static int[] makeSieve(int upperbound){
        int[] prime = new int[upperbound];

        prime[0] = 1;

        for (int c = 1; c < upperbound; c++){
            prime[c] = 0;

        }
        for (int i = 2; i< upperbound; i++){
            if (prime[i - 1] == 0){
                for (int j = i*i; j< upperbound; j+= i){
                    prime[j] = 1;
                }
            }
        }
        if (prime[1] == 0){
            prime[1] = 1;
        }
        return prime;

    }

    /*
    * Main function that calls the
    * whole code.
    * */

    public static void main(String[]args){
        makeSieve(30);

    }
}
