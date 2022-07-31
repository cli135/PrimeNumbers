import java.util.ArrayList;
import java.util.List;

public class FuelInjectionHailstone {
    public static int solution(String x) {
        // don't go above the next highest power of two
        int a = Integer.parseInt(x);
        int nextHighestPowerOfTwo = Math.pow(2, Math.floor(Math.log(a) / Math.log(2)) + )
    }
    public static int helper(String x) {
        int a = Integer.parseInt(x);
        // base case
        if (a == 1) {
            return 0;
        }
        if (isPowerOfTwo(a)) {
            return (int) Math.log(a);
        }
        // recursive case: compare all 3 possible routes
        // choose the minimum
        List<Integer> possibleNext = new ArrayList<>();
        if (a % 2 == 0) {
            possibleNext.add(solution("" + (a / 2)));
        }
        possibleNext.add(solution("" + (a - 1)));
        possibleNext.add(solution("" + (a + 1)));

        int min = Integer.MAX_VALUE;
        for (int i : possibleNext) {
            if (i < min) {
                min = i;
            }
        }
        return 1 + min;
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
