public class Checksum {
    public static int solution(int start, int length) {
        // System.out.println("Result for start: " + start + " and length: " + length);

        // begin checksum with start
        int checksum = start;

        // System.out.print(start + " ");

        // example:
        // 0 1 2 /
        // 3 4 / 5
        // 6 / 7 8

        // Outer loop goes from i = 3, 2, 1 (how much horizontal
        // to go)
        for (int i = length; i >= 1; i--) { // 3 2 1
            // Inner loop finds where to start and goes i spots over
            // e.g. when i = 2, we start at 3 and go 2 spots over (3, then 4)
            for (int j = start + length * (length - i); j < start + length * (length - i) + i; j++) { //

                // checksum already began with start
                // so we skip it here
                if (j == start) {
                    continue;
                }

                // System.out.print(j + " ");

                // all other numbers traversed are xor'ed to checksum
                checksum ^= j;
            }
            // System.out.println();
        }

        // algorithm done,
        // and we skipped any numbers that were outside of the
        // upper left diagonal triangle
        return checksum;
    }

    public static void main(String[] args) {
        System.out.println(Checksum.solution(0, 3));
        System.out.println(Checksum.solution(17, 4));
    }
}
