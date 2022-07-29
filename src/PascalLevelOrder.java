public class PascalLevelOrder {

    public static String solution(long x, long y) {

        // calculate number from coordinates
        // using triangular number functions
        // and convert to String
        return "" + (triangle(x) + triangle(x + y - 2) - triangle(x - 1));

        // simplified algebra
        // return "" + ((x * x + 2 * x * y + y * y - x - 3 * y + 2) / 2);

        // ==============================
        // Explanation below
        // ==============================

        // Investigating the triangle,
        // there is a pattern involving triangle numbers.

        /*
        11
        7  12
        4  8  13
        2  5  9  14
        1  3  6  10 15
        */

        // (triangle numbers are formed by taking
        // any of the first few terms of 1 + 2 + 3 + 4 + ...
        // and summing them together)

        // In this bunny worker locations triangle,
        // Triangle numbers are the numbers along
        // the 'x-axis', going 1, 3, 6, 10, 15, 21, ...
        // meaning solution(x, 0) returns a String
        // representation of triangle(x)
        // == 1 + 2 + 3 + ... + x
        // == x * (x + 1) / 2
        // This accounts for the first term, triangle(x)

        // To go from solution(x, 0) to solution(x, y) --
        // in other words, going upwards along the y-axis
        // after going rightwards along the x-axis --
        // we add
        // x + (x + 1) + (x + 2) + ... + (x + y - 2)
        // to the existing sum, solution(x, 0),
        // to get our final answer solution(x, y).

        // This is because to get from the x-axis
        // back to the spot above it, (going up one
        // layer), we need to go
        // x times, then the next layer up requires
        // x + 1 times, etc.
        // it's like:
        // solution(5, 10) == solution(5, 0) + 5 + 6 + 7 + ... + 13
        // why the 'minus 2' in solution(x + y - 2)?
        // minus 1 for 1-indexed coordinate counting
        // minus 1 for fencepost counting (or rather, counting
        // 'fences' in between the posts, not the posts themselves)

        // And the - triangle(x - 1) is just a way of
        // subtracting off the prefix of triangle(x + y - 2),
        // so we only sum from x + ... + (x + y - 2)

        // In short, this pattern involves adding triangle
        // numbers, which are basically adding sequences
        // that look like
        // (1 + 2 + 3 + 4 + 5) + (5 + 6 + 7 + ... + 12 + 13) == 96

        // It's a cool pattern!

    }
    public static long triangle(long n) {
        return n * (n + 1) / 2;
    }

    public static void printIDTriangle() {

    }
    public static void main(String[] args) {
        String result;
        PascalLevelOrder p = new PascalLevelOrder();
        result = p.solution(3, 2);
        System.out.println(result);
        result = p.solution(5, 10);
        System.out.println(result);

    }
}
