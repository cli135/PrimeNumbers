import java.math.BigInteger;
import java.util.HashMap;

public class FuelInjectionHailstone {

    // this HashMap serves as a cache for memoization
    // in dynamic programming
    // so we don't have to re-fetch certain values
    // by re-computing them at runtime.
    // The first time we compute a value, we store it in
    // this HashMap for O(1) access at any later time
    public static HashMap<BigInteger, Integer> cache = new HashMap<>();
    public static int solution(String x) {
        BigInteger a = new BigInteger(x);
        return helper(a);
        // dynamic programming through recursion
        // map cache provides memoization
        // to avoid repeating recursive routes
        // going down the call stack
    }

    // reduce repetition in dynamic programming
    // with a memoization cache
    // so we only calculate each value at most once
    public static int helper(BigInteger x) {

        // BigInteger for the large numbers
        // (up to 309 digits)
        BigInteger a = x;

        // base case: 1 is done
        if (a.equals(BigInteger.valueOf(1))) {
            return 0;
        }

        // recursive case:

        // even numbers should always be divided by 2
        // this is the most effective thing to do with even numbers
        // kind of like Collatz Conjecture (n/2, 3n + 1) idea
        if (a.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0))) {
            int fromHalf;
            // meaning we hop 'from n/2' to get to n
            // in dynamic programming
            // as part of the 'last step'

            // check cache
            if (cache.containsKey(a.divide(BigInteger.valueOf(2)))) {
                fromHalf = cache.get(a.divide(BigInteger.valueOf(2)));
            }
            else {
                // compute and populate cache
                fromHalf = helper(a.divide(BigInteger.valueOf(2)));
                cache.put(a.divide(BigInteger.valueOf(2)), fromHalf);
            }
            // this is the length of taking this route
            return 1 + fromHalf;
        }

        // odd numbers: either -1 or +1 to get to an adjacent even number:
        // example of -1: 9 - 1 = 8
        // example of +1: 15 + 1 = 16
        // both are optimal in different situations, so we have to
        // try both and see which one is lesser

        int fromBelow; // meaning we arrive at n from n-1
        // in the dynamic programming route / path through recursive
        // call stack

        // check cache
        if (cache.containsKey(a.subtract(BigInteger.valueOf(1)))) {
            fromBelow = cache.get(a.subtract(BigInteger.valueOf(1)));
        }
        else {
            // if not in cache, compute value and populate cache
            fromBelow = helper(a.subtract(BigInteger.valueOf(1)));
            cache.put(a.subtract(BigInteger.valueOf(1)), fromBelow);
        }

        int fromAbove; // meaning we arrive at n from n + 1
        // in the dynamic programming route / path through recursive
        // call stack

        // check cache
        if (cache.containsKey(a.add(BigInteger.valueOf(1)))) {
            fromAbove = cache.get(a.add(BigInteger.valueOf(1)));
        }
        else {
            // if not in cache, compute value and populate cache
            fromAbove = helper(a.add(BigInteger.valueOf(1)));
            cache.put(a.add(BigInteger.valueOf(1)), fromAbove);
        }

        // whichever route was faster (lower path length through recursion)
        // we choose that one
        // and the 1 + beforehand on the left hand side
        // means we also include this number as contributing
        // to the path length
        // it helps 'extend the solution' in recursive terms
        return 1 + Math.min(fromBelow, fromAbove);
    }
    public static boolean isPowerOfTwo(int n) {
        while (n > 1) {
            if (n % 2 == 1) {
                return false;
            }
            n /= 2;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(FuelInjectionHailstone.solution("4"));
        System.out.println(FuelInjectionHailstone.solution("15"));
        System.out.println(FuelInjectionHailstone.solution("153453"));
        System.out.println(FuelInjectionHailstone.solution("81"));
        System.out.println(FuelInjectionHailstone.solution("127498484"));



    }

    public static int solution1(String x) {
        int a = Integer.parseInt(x);
        // loop until a == 1
        int count = 0;
        while (a > 1) { // run one more time
            if (a % 2 == 0) {
                a = a / 2;
                count++;
            }
            else {
                int lowerPowerOfTwo = (int) Math.pow(2, (int) (Math.log(a) / Math.log(2)));
                int higherPowerOfTwo = (int) Math.pow(2, (int) (Math.log(a) / Math.log(2)) + 1);
                if (Math.abs(a - lowerPowerOfTwo) <= Math.abs(a - higherPowerOfTwo)) {
                    // go to the lower one
                    int test = 4;
                }
            }
        }
        // TODO:
        return 0;
    }

}
