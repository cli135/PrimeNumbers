import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Math.sqrt;

public class AccessCodes {


    public static int solution2(int[] l) {
        // need to find triplets that divide
        // ok
        // need to sort first for "sort and two pointers"
        // because two pointers relies on sorting invariant
        // to effectively filter out / rule out further extremes
        // kind of like comparison test for series
        Arrays.sort(l);

        for (int i = 0; i < l.length; i++) {
            int start = i + 1;
            int end = l.length - 1;
            while (start < end) {
                // if
                int test = 5;
            }
        }

        // TODO:
        return 0;
    }
    // potential solution
    public static int solution1(int[] l) {

        HashMap<Integer, Integer> freq = new HashMap<>();
        // frequency distribution
        for (int num : l) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // assuming the list is sorted
        //int i = l[0];
        //int max = l[l.length - 1];

        // start from i and go up
        //for (int j = ; j <= max / 2; j++) {

        //}
        int count = 0;
        for (int i = 0; i < l.length; i++) {
            for (int j = i + 1; j < l.length; j++) {
                int start = l[i];
                int end = l[j];
                if (divides(start, end)) { // function runtime: O(sqrt(n))
                    // add number of factors / 2 to count

                    // remember to remove out the start and end
                    // to avoid double using numbers

                    freq.put(start, freq.get(start) - 1);
                    freq.put(end, freq.get(end) - 1);
                    count += halfNumFactors(start, end, freq, i, j);

                    // add them back when done
                    freq.put(start, freq.get(start) + 1);
                    freq.put(end, freq.get(end) + 1);
                }
            }
        }
        return count;

    }

    // Runtime O(sqrt(n))
    public static int halfNumFactors(int start, int end, HashMap<Integer, Integer> freq, int startIdx, int endIdx) {
        int n = end / start;
        int count = 0;
        for (int i = 1; i <= sqrt(n); i++) {
            if (divides(i, n)) {
                int x = i;
                int y = n / i;
                if (freq.get(x) != 0 && endIdx - startIdx > 1) {
                    count++; // one for x
                    System.out.println(start + " " + x + " " + end);

                    count++; // one for y
                    System.out.println(start + " " + y + " " + end);
                }
            }
        }
        return count;
    }

    // hash table
    public static int solutionHash(int[] l) {

        HashMap<Integer, Integer> freq = new HashMap<>();
        // frequency distribution
        for (int num : l) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // TODO:
        return 0;
    }

    // O(N^3)
    public static int solution(int[] l) {
        int count = 0;
        for (int i = 0; i < l.length; i++) {
            for (int j = i + 1; j < l.length; j++) {
                for (int k = j + 1; k < l.length; k++) {
                    // don't forget indices are not same as elements in array
                    if (divides(l[i], l[j]) && divides(l[j], l[k])) {
                      count++;
                    }
                }
            }
        }
        return count;
    }
    public static boolean divides(int a, int b) {
        // returns whether a divides b
        // in other words, if b is a multiple of a
        // in other words, if b is 0 mod a
        return b % a == 0;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1};
        System.out.println(AccessCodes.solution(arr1));

        int[] arr2 = {1, 2, 3, 4, 5, 6};
        System.out.println(AccessCodes.solution(arr2));

    }
}
