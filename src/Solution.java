import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        String result = solution(3);
        System.out.println(result);
    }
    public static String solution(int i) {

        // Overall strategy:
        // Using Sieve of Erastosthenes
        // to find primes, forming a concatenated string
        // of primes, and returning the requested substring
        // of the primeString

        // filling array [0, 24999]
        int maxSize = 25000;

        // Goal:
        // By the end of the method, the nums array
        // will contain only prime numbers,
        // and the intervening indices for composite numbers
        // will be filled in with -1 after the Sieve of
        // Erastosthenes
        int[] nums = new int[maxSize];

        // initially, the array looks like
        // [0, 1, 2, 3, 4, ... , 24999]
        for (int k = 0; k < nums.length; k++) {
            nums[k] = k;
        }

        // Sieve of Erastosthenes algorithm

        // Strategy:
        // Starting with lowest primes i.e. 2, 3, 5, ...
        // We "mark off" the multiples of those primes as
        // composite by setting them to be -1 in the nums array,
        // leaving behind only the primes

        // for all numbers in [2, 24999]
        for (int k = 2; k < maxSize; k++) {
            // check if marked off yet
            if (nums[k] != -1) {
                // not marked off yet
                // means it's a prime (k is a prime)
                // mark off all multiples of the prime k
                // (but not k itself)
                // since those multiples are composite
                for (int j = k + k; j < maxSize; j += k) {
                    // multiple of k is composite
                    nums[j] = -1; // marked off
                }
            }
        }

        // Now, the array looks like
        // [0, 1, 2, 3, -1, 5, -1, 7, -1, ... ]
        //        ^ starting here, only primes remain

        // Sieve of Erastosthenes algorithm over

        // The string of the concatenated primes
        String primeString = "";

        // looping through nums array
        for (int k = 2; k < maxSize; k++) {
            if (nums[k] != -1) {
                // k is a prime,
                // so we concatenate it to primeString
                primeString += Integer.toString(k);
            }
        }

        // primeString is complete,
        // we just want the 5-digit substring
        // starting at index i
        // for the new minion's ID number
        return primeString.substring(i, i + 5);
    }


}
