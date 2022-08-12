import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;


public class BananaPairingUp {

    public static int solution(int[] banana_list) {
        // make an adjacency matrix in O(N^2) time
        // of who can be paired up with who
        int n = banana_list.length;
        int[][] adjacency_matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    // can't pair up someone with themselves
                    adjacency_matrix[i][j] = 0;
                }
                // spot at (i, j) tells us whether i and j form a cycle
                // and whether they can be paired or not
                adjacency_matrix[i][j] = cycle(banana_list[i], banana_list[j]) ? 1 : 0;
            }
        }
        // maximum matching problem
        // pair the two most 'picky' people off first
        // leave the most flexible people for last
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += adjacency_matrix[i][j];
            }
            freq.put(i, sum);
        }

        int[][] sorted = new int[n][3];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            Integer key = entry.getKey(); // sum
            Integer value = entry.getValue(); // i
            sorted[key][0] = key;
            sorted[key][1] = value;
            sorted[key][2] = banana_list[key];
        }

        Arrays.sort(sorted, (n1, n2) -> Integer.compare(n1[1], n2[1]));
//        for (int x : banana_list) {
////            System.out.println(x);
//        }

        int count = n;
        int i = 0;
        while (i < n - 1) {
            // check i and i + 1
            if (sorted[i][1] > 0 && sorted[i + 1][1] > 0) {
                count -= 2;
            }
            else {
                // one or both is zero
                break;
            }
            i += 2;
        }
        return count;

    }

    // global variables carry across function calls
    // including across different unit tests
    // so you just want to parameter pass instead
    // so it goes out of scope and is recreated
    // when necessary
    public static int solution1(int[] banana_list) {
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
        ArrayList<Integer> odds = new ArrayList<>();
        ArrayList<Integer> evens = new ArrayList<>();

        for (Integer i : banana_list) {
            origBananas.add(i);
            bananas.add(i);
            if (i % 2 == 0) {
                evens.add(i);
            }
            else {
                odds.add(i);
            }
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
        int a = x / gcd(x, y);
        int b = y / gcd(x, y);
        int sum = a + b;
        return ((sum) & (sum - 1)) != 0; // when sum is not a power of two
//        if ((int) Math.abs(y - x) % 2 == 1) {
//            return true;
//        }
//        else if ((int) Math.abs(y / gcd(x, y) - x / gcd(x, y)) == 2) {
//            return false;
//        }
//        Set<Integer> differences = new HashSet<>();
//        while (x != y) {
//            if (differences.contains(x - y)) {
//                return true; // cycle
//            }
//            differences.add(x - y);
//            // x - y uniquely identifies a pair (x, y)
//            // since the sum is already fixed: x + y = some constant amount
//            // what we are really keeping track of here
//            // is whether we reach a pair we've already seen before
//            // thus indicating a never-ending cycle of banana-trading
//            // that the trainers will go through
//            int a = Math.min(x, y);
//            int b = Math.max(x, y);
//            x = a + a;
//            y = b - a;
//        }
//        return false; // bananas equal, game over, trainers stop playing
//        // (no cycle)
    }

    public static int gcd(int x, int y) {
        int a = Math.min(x, y);
        int b = Math.max(x, y);
        if (a == 0) {
            return b;
        }
        // Euclidean algorithm
        return gcd(a, b%a);
    }
}