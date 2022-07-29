import java.util.Arrays;

public class RowOfGears {
    public static int[] solution(int[] pegs) {

        // note to self:
        // failing some middle cases at the moment
        // consider what might happen if you
        // bump into pegs in the middle of the array
        // e.g. if there isn't enough space in the middle
        // just do an o(n) linear search check to make sure
        // everybody is cool with that, shall wel?

        // now failing only case 10

        // alternating sum of pegs array
        // when a first radius is picked,
        // the whole sequence (including the last
        // gear radius) is all pre-determined
        // in other words, it is deterministic
        // instead of computing this algorithmically
        // using a for loop and potentially ending
        // up with an outer and inner for loop (O(n^2)),
        // we optimize beforehand using some math
        // to see that the sequence is in fact
        // an alternating sum
        boolean positive = true;
        int alternatingSum = 0;
        for (int i = pegs.length - 1; i >= 0; i--) {
            int val = pegs[i];
            if (!positive) {
                val *= -1;
            }
            if (i < pegs.length - 1 && i > 0) {
                // in the middle, telescoping terms
                // actually don't telescope, they double
                // in the direction / sign they are already in
                val *= 2;
            }
            alternatingSum += val;
            positive = !positive; // alternate
        }
        // this algorithm runs in O(N) time
        int[] ret = new int[2];
        // depending on parity, the closed form
        // algebra expression for the radius changes
        if (pegs.length % 2 == 0) {
            // even length array and alt sum
            ret[0] = 2;
            ret[1] = 3;
            // coming from algebra,
            // we multiply by 2/3
        }
        else {
            // odd length array and alt sum
            ret[0] = 2;
            ret[1] = 1;
            // coming from algebra,
            // we multiply by 2 (note: *not* 1/2)
        }
        // force feeding the multiplier in
        // ret[0] = 2;
        // ret[1] = 1;



        //==============================

        // or it's like test 1?
        // in that it has sthg
        // to do with [-1, -1]?

        // test 10 is an even length array
        // that should be getting the odd
        // length array treatment

        // it chooses the even length branch
        // and fails
        // but passes when forced to take
        // the odd length branch

        // seems related to the a/b >= 1 req
        // like [1, 2]
        // ends up with a/b = 2/3
        // but maybe it likes -2 better??
        // or 2?? should be??

        //==============================

        // not sure if i need this line here below
        if (alternatingSum < 0) {
            alternatingSum *= -1;
        }


        ret[0] *= alternatingSum;

        // invalid check - intersecting with next peg
        //if ((double) ret[0] / ret[1] > pegs[1] - pegs[0]) {
        //    ret[0] = -1;
        //    ret[1] = -1;
        //    return ret;
        //}
        // why don't we just run through the whole thing
        // and check if we are good, shall we?

        // comment below out
        // to see that test case 10
        // ought to take the [2, 1] route
        // not the [2, 3] route
        // and should result in [-1, -1]

        // test case 10 has to do with invalids

        double curRadius = (double) ret[0] / ret[1];
        for (int i = 0; i < pegs.length - 1; i++) {
            double here = pegs[i];
            double next = pegs[i + 1];
            // make sure *each and every* radius
            // is greater than 1, not just the first
            // this results from the fact that
            // Commander LAMBDA only has gears
            // in stock that are greater than radius
            // 1, so any sub-1 radii gears are not
            // available. It's important to check
            // all gears for this condition, not just
            // the first one, because while the first
            // gear might be >= 1 thankfully, because
            // the first gear determines the radii
            // of all following gears it could be the
            // case that a following gear later down
            // the line was actually caused to have
            // radius < 1, which is a no-no
            // basically, the radius < 1 check needs
            // to be checked on all gears in the line
            // not just the first gear
            // lesson learned: read the directions
            // and see if you should check conditions
            // for all inputs / all the way, rather
            // than just a starting input

            // basically there was a solution in there
            // somewhere where the starting gear was
            // not less than 1, which I thought was fine
            // but it actually was not fine because
            // a middle gear was less than 1
            // and I wasn't checking for that

            // lots of valuable skills learned in this problem:
            // black box testing, implication logic,
            // force feed testing, letting it choose a branch testing,
            // proof by contradiction logic to determine
            // conditions,
            // reading directions carefully,
            // thinking about edge cases and [-1, -1] conditions,
            // noting it must be an even length array that needs the odd treatment
            // acknowledging the complexity of code and realizing
            // that the [2, 1] choice is not so much important, as
            // is the fact that it pursues a different course in
            // the curRadius checking portion
            // considering a/b < 1 in the || portion of the curRadius
            // checking portion and finally implementing it later
            // reading directions and understanding them carefully
            // noting that curRadius >= 1 ***for all radii***
            // is a must from the instructions
            if (curRadius > next - here || curRadius < 1) {
                ret[0] = -1;
                ret[1] = -1;
                return ret;
            }
            // propagate the radius's effects
            // through the linear chain of gears
            curRadius = next - here - curRadius; // recurrence relation
            // but implemented iteratively / imperatively
            // it is just another state change of the program
            // and no big deal, as with normal assignment statements
            // x = x + 1 actually means
            // x_(t) = x_(t-1) + 1
            // an updating through time or through a numerical sequence
            // of computer states
        }
        // otherwise we made it through safely

        // one last check
        // seems to have no effect
        // if ((double) ret[0] / (double) ret[1] < 1) {
        //     ret[0] *= 276;
        //     ret[1] = 1;
        //     return ret;
        // }

        // otherwise, valid, and we simplify
        return simplify(ret[0], ret[1]);

    }

    public static int[] simplify(int a, int b) {
        int gcd = gcd(a, b);
        int[] ret = {a / gcd, b / gcd};
        return ret;
    }

    public static int gcd(int x, int y) {
        int a = Math.min(x, y);
        int b = Math.max(x, y);
        if (a == 0) {
            return b;
        }
        // Euclidean algorithm
        return gcd(a, b-a);
    }

    public static void main(String[] args) {

        int[] pegs = {0, 0, 0};
        int[] result;

        pegs[0] = 4;
        pegs[1] = 17;
        pegs[2] = 50;
        result = solution(pegs);
        System.out.println(Arrays.toString(result));

        pegs[0] = 4;
        pegs[1] = 30;
        pegs[2] = 50;
        result = solution(pegs);
        System.out.println(Arrays.toString(result));

        pegs = new int[4];
        pegs[0] = 4;
        pegs[1] = 30;
        pegs[2] = 50;
        pegs[3] = 70;
        result = solution(pegs);
        System.out.println(Arrays.toString(result));

    }
}
