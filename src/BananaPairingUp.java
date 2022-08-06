import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BananaPairingUp {

    // global variables carry across function calls
    // including across different unit tests
    // so you just want to parameter pass instead
    // so it goes out of scope and is recreated
    // when necessary
    public static int solution(int[] banana_list) {
        // can't possibly check all possible ways using brute force
        // would be something on the order of O(n!)
        // due to permutations of matching pairs of people up
        // look for an optimization: greedy, sorted array, two pointers
        // etc.
        Integer[] bananas_arr = new Integer[banana_list.length];
        for (int i = 0; i < banana_list.length; i++) {
            bananas_arr[i] = banana_list[i];
        }
        ArrayList<Integer> bananas = new ArrayList<>();
        List<Integer> origBananas = new ArrayList<>();
        for (Integer i : banana_list) {
            origBananas.add(i);
            bananas.add(i);
        }
        //Arrays.sort(banana_list);

        // how can you tell, ahead of time, (by looking at them in O(1))
        // that two numbers will cycle? << the big question

        // maybe you can't
        // maybe it's algorithmic in nature
        // well i don't know for sure
        // but maybe part of the solution is to cache it
        // well for the other part,
        // even if you can do cycle(x, y) in O(1)
        // how are you going to get around checking all O(n!) possibilites?

        // alright let's just write the O(n!) algorithm
        return pairOffNums(bananas, 0, origBananas.size());

        //return 0; // method stub
    }

    public static int pairOffNums(ArrayList<Integer> bananas, int start, int origSize) {
        if (start >= bananas.size() - 1) {
            return origSize;
        }
        ArrayList<Integer> results = new ArrayList<>();
//        results.add(pairOffNums(bananas, start + 1));
        for (int i = start + 1; i < bananas.size(); i++) {
            // please avoid mixing up indices with elements
            if (cycle(bananas.get(start), bananas.get(i))) {
                Integer saved1 = bananas.remove(i);
                Integer saved2 = bananas.remove(start);
                results.add(-2 + pairOffNums(bananas, start, origSize));
                bananas.add(start, saved2);
                bananas.add(i, saved1);

            }
            results.add(0 + pairOffNums(bananas, start + 1, origSize));
        }

        // minimum of results
        Integer min = Integer.MAX_VALUE;
        for (Integer i : results) {
            if (i < min) {
                min = i;
            }
        }
        return min;
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