import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BananaPairingUp {
    public static int solution(int[] banana_list) {
        // can't possibly check all possible ways using brute force
        // would be something on the order of O(n!)
        // due to permutations of matching pairs of people up
        // look for an optimization: greedy, sorted array, two pointers
        // etc.

        Arrays.sort(banana_list);

        // how can you tell, ahead of time, (by looking at them in O(1))
        // that two numbers will cycle? << the big question




        return 0; // method stub
    }

    public static boolean cycle(int x, int y) {
        Set<Integer> differences = new HashSet<>();
        while (x != y) {
            if (differences.contains(x - y)) {
                return true; // cycle
            }
            differences.add(x - y);
            // x - y uniquely identifies a pair (x, y)
            // since the sum is already fixed: x + y = some constant amount
            // what we are really keeping track of here
            // is whether we reach a pair we've already seen before
            // thus indicating a never-ending cycle of banana-trading
            // that the trainers will go through
            int a = Math.min(x, y);
            int b = Math.max(x, y);
            x = a + a;
            y = b - a;
        }
        return false; // bananas equal, game over, trainers stop playing
        // (no cycle)
    }
}