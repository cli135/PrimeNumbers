import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FuelInjectionHailstone {
    public static int solution(String x) {
        BigInteger a = new BigInteger(x);
        return helper(a);
        // dynamic programming through recursion

    }
    public static int helper(BigInteger x) {
        BigInteger a = x;
        // base case
        if (a.equals(BigInteger.valueOf(1))) {
            return 0;
        }
        // recursive case
        if (a.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0))) {
            return 1 + helper(a.divide(BigInteger.valueOf(2)));
        }
        return 1 + Math.min(helper(a.subtract(BigInteger.valueOf(1))), helper(a.add(BigInteger.valueOf(1))));
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
