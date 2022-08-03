import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

//class Fraction {
//    boolean positive;
//    int numerator;
//    int denominator;
//    public Fraction(int n, int d, boolean sign) {
//        numerator = n;
//        denominator = d;
//        positive = sign;
//    }
//}

public class BeamPoolTables {

    // 8-2-2022 6:50pm
    // not passing tests 5 and 9 at the moment
    // potential reasons:
    // doesn't seem like there is roundoff error
        // even after transitioning to a new thing, int[] gcd simplified
        // arrays, it still works the same
        // and anyways roundoff error should be deterministic and should
        // be the same each time
        // so this probably means the issue is something else
    // time limit exceeded
    // finish out the change from (1, 1) to (0, 0)
    // roundoff error in double equivalence class
        // could shift to using a custom fraction class
        // with integer numerator and denominator
    // you need to make changes to the program and run it
    // to gather information on what works and what doesn't
    // also you should reread the problem description
    // <= distance or < distance? I think <= but not sure
        // yep it's <= so we are good, test 7 checks this
        // since it fails on >= but succeeds on > (which is opposite of <=)
        // update: this point resolved
    // corner bounceback in same direction - should be taken
        // care of, via the reflecting thing




    // This problem is a lot like
    // pool table questions,
    // geometric optics / light reflection questions
    // integer lattice questions
    // and a good mix of algebra and geometry (analytic geometry)
    // using equations of lines and etc. in the Cartesian plane


    // law of reflection
    // entry angle == exit angle
    // but more importantly,
    // this is equivalent to reflecting the board
    // and letting the ball continue in a straight line

    // reflect left, right, up, down, and let the ball
    // go in straight lines outward
    // count the # of directions you can go in with the
    // distance limit as a radius limit

    // if two are in the same direction, only count them as one
    // because you'll run into it the first time before you
    // can get to the second one

    public static int solution(int[] dimensions, int[] your_position, int[] trainer_position, int distance) {

        // (0, 0) is the true corner, not (1, 1)
        // just found that out

        // initializing variables
        int xDim = dimensions[0];
        int yDim = dimensions[1];
        int xPlayer = your_position[0];
        int yPlayer = your_position[1];
        int xTrainer = trainer_position[0];
        int yTrainer = trainer_position[1];

        // generate reflection list
        // right

        // list of bearings to reach trainer
        List<int[]> bearings = new ArrayList<>();

        // keep reflecting until the distance is too far away
        int[] curTrainerPosition = {xTrainer, yTrainer, 1}; // 1 indicates trainer is here, not player


        bearings.add(curTrainerPosition);


        // the wall here is at (0, 0) so we reflect off the wall like this
        int[] xParityForTrainer = {2 * (xDim - xTrainer), 2 * (xTrainer)};
        int[] xParityForPlayer = {2 * (xDim - xPlayer), 2 * (xPlayer)};
        int[] yParityForTrainer = {2 * (yDim - yTrainer), 2 * (yTrainer)};
        int[] yParityForPlayer = {2 * (yDim - yPlayer), 2 * (yPlayer)};

        // reflect board continuously to the right
        reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, curTrainerPosition, "right", false);

        // same thing, but going left now, reflecting to the left
        // t == 1 means that we are starting with a different reflection first
        reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, curTrainerPosition, "left", false);

        // for each of the horizontal reflection images, we will reflect *those*
        // up and down

        // I probably don't need to store these in memory, I could just iterate
        // over them, but it's fine and I'll figure it out later
        // I'll find the corners manually and make a double for loop that hop-skips
        // by 2 different jump sizes later

        int traverseLength = bearings.size();
        for (int i = 0; i < traverseLength; i++) {
            int[] position = bearings.get(i);
            reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, position, "up", false);
            reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, position, "down", false);
        }

        // now doing the same thing but for the player

        //=================================================
        int[] curPlayerPosition = {xPlayer, yPlayer, 0};

        int idxSaved = bearings.size();

        bearings.add(curPlayerPosition);

        // reflect board continuously to the right
        reflectBoardRecursively(distance, bearings, xParityForPlayer, yParityForPlayer, curPlayerPosition, "right", true);

        // same thing, but going left now, reflecting to the left
        // t == 1 means that we are starting with a different reflection first
        reflectBoardRecursively(distance, bearings, xParityForPlayer, yParityForPlayer, curPlayerPosition, "left", true);

        // for each of the horizontal reflection images, we will reflect *those*
        // up and down

        // TODOnomore: need a better way to count this - the player reflections
        // UPDATE: DONE
        traverseLength = bearings.size();
        for (int i = idxSaved; i < traverseLength; i++) {
            int[] position = bearings.get(i);
            reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, position, "up", true);
            reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, position, "down", true);
        }

        // now all bearings should be centered from the player's original position, not (0, 0)
        for (int i = 0; i < bearings.size(); i++) {
            int[] position = {bearings.get(i)[0], bearings.get(i)[1]};
            int[] diff = direction(your_position, position);
            bearings.get(i)[0] = diff[0];
            bearings.get(i)[1] = diff[1];
            // leave bearings.get(i)[2] the same as it was - 0 for player, 1 for trainer
        }

        // remove any (0, 0, x) bearings

        // going in reverse when we remove from an ArrayList
        // so we don't mess up our traversal
        // (only rightward elements are shifted over toward the left,
        // leftward elements remain as they were at the inception of the loop)
        for (int i = bearings.size() - 1; i >= 0; i--) {
            int[] position = bearings.get(i);
            if (position[0] == 0 && position[1] == 0) {
                bearings.remove(i);
            }
        }

        // debugging output
        for (int[] coords : bearings) {
            //System.out.println(Arrays.toString(coords));
        }

        // put them into equivalence classes
        // Map<UnitVectorDirection, List<ActualPosition>> map
        Map<List<Integer>, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < bearings.size(); i++) {
            int[] position = bearings.get(i);
            // positions and directions are now pretty much the same thing
            // after centralizing everything to be from the
            // player's original position
            // we only care about relative directions from the player's
            // point of view
            Integer[] unitVectorDirection = unitVector(position);
            if (map.containsKey(Arrays.asList(unitVectorDirection))) {
                map.get(Arrays.asList(unitVectorDirection)).add(position);
            }
            else {
                // for the direction we are looking in at the moment
                List<int[]> equivalenceClass = new ArrayList<>();
                equivalenceClass.add(position);
                List<Integer> list = Arrays.asList(unitVectorDirection);
                map.put(
                        Collections.unmodifiableList(list),
                        equivalenceClass
                );
            }
        }

        //System.out.println(map);

        // i really hope this map worked out okay
        // TODO: be careful about <local4> is null errors that could occur here

        int count = 0; // we will return this value,
        // the count of directions we could go in

        // also need to remember max distance value, don't forget that

        // traversing map and applying the filter
        // e.g. for each direction, look for the closest person
        // if it's a player, +0 (we would hit ourselves first)
        // if it's a trainer, +1 (and ignore any further collinear points beyond that)
        for (Map.Entry<List<Integer>, List<int[]>> entry : map.entrySet()) {
            List<Integer> unitDirection = entry.getKey();
            List<int[]> positions = entry.getValue();


            double minDistance = Double.MAX_VALUE;
            int[] closestPosition = new int[3]; // just a placeholder for now
            for (int[] position : positions) {
                if (magnitude(position) < minDistance) {
                    minDistance = magnitude(position);
                    closestPosition = position;
                }
            }

            // check whether the closest position is within the distance
            if (minDistance > distance) {
                continue; // we can't reach this one
            }

            // otherwise we can reach the closest one
            // which is the only one we care about
            // since we can't get past it to see anything behind it

            // i have a feeling that (0, 0, x) could be causing issues

            if (closestPosition[2] == 0) {
                // player is closest in this direction
                //System.out.println("we did get some cases like this here");
                continue; // can't aim here
            }
            else if (closestPosition[2] == 1) {
                // trainer is closest in this direction
                // we can aim here, and there is only one
                // count added since we can't reach anything
                // behind it


//                if (closestPosition[0] == -1 && your_position[0] == 1) {
//                    continue; // can't go straight left
//                }
//                if (closestPosition[1] == -1 && your_position[1] == 1) {
//                    continue; // can't go straight down
//                }

                count++;
                // tell us what was added
//                //System.out.println(unitDirection);
                //System.out.println(Arrays.toString(closestPosition));
                //System.out.println("magnitude is: " + magnitude(closestPosition));
            }

        }


        // still need to filter out cases where it hits the
        // trainer first
        // or the player first
        // e.g. collinear cases
        // still need to filter out cases where it is too far away too
        //System.out.println("count is: " + count);
        return count;
    }

    private static void reflectBoardRecursively(int distance, List<int[]> bearings, int[] xParityForTrainer, int[] yParityForTrainer, int[] position, String direction, boolean isPlayer) {
        int totalDistanceOut = 0;
        int[] curTrainerPosition = position;

        int toggle;
        if ("right".equals(direction) || "up".equals(direction)) {
            toggle = 0;
        }
        else if ("left".equals(direction) || "down".equals(direction)) {
            toggle = 1;
        }
        else {
            // shouldn't happen, but will throw an
            // IndexOutOfBoundsException in case, just to let us know
            //System.out.println("error: shouldn't have gotten here");
            toggle = curTrainerPosition.length; // IndexOutOfBoundsException
        }
        while (totalDistanceOut <= distance) {
            // reflect the cur position
            // just a template that will change based on if-elseif-else statements below
            int[] reflected = {curTrainerPosition[0] - xParityForTrainer[toggle], curTrainerPosition[1], 1};
            if (isPlayer) {
                reflected[2] = 0; // player representation
            }
            else {
                reflected[2] = 1; // trainer
            }
            if ("left".equals(direction)) {
                reflected[0] = curTrainerPosition[0] - xParityForTrainer[toggle];
            }
            else if ("right".equals(direction)) {
                reflected[0] = curTrainerPosition[0] + xParityForTrainer[toggle];
            }
            else if ("up".equals(direction)) {
                reflected[0] = curTrainerPosition[0];
                reflected[1] = curTrainerPosition[1] + yParityForTrainer[toggle];
            }
            else if ("down".equals(direction)) {
                reflected[0] = curTrainerPosition[0];
                reflected[1] = curTrainerPosition[1] - yParityForTrainer[toggle];
            }
            else {
                // just a stub here, shouldn't reach this case
                //System.out.println("error: shouldn't reach this case");
                reflected[0] = curTrainerPosition[0] + xParityForTrainer[toggle];
            }

            // add it to the bearings list
            bearings.add(reflected);

            // increase total distance out
            totalDistanceOut += xParityForTrainer[toggle];

            // toggle jump
            toggle = toggle == 0 ? 1 : 0;

            // update cur trainer position to the recently reflected point
            curTrainerPosition = reflected;
        }
    }



    // vector subtraction in 2D Cartesian plane
    public static int[] direction(int[] start, int[] end) {
        // vector that connects two vectors (the difference)
        int[] difference = {end[0] - start[0], end[1] - start[1]};
        return difference;
    }

    // magnitude of direction between two points
    public static double distance (int[] start, int[] end) {
        return magnitude(direction(start, end));
    }

    // magnitude of a vector in 2D Cartesian plane
    public static double magnitude(int[] v) {
        // Pythagorean theorem for distance
        // between a vector's tail and tip
        return Math.sqrt(v[0] * v[0] + v[1] * v[1]);
    }

    // please i hope double works right and no roundoff error or at least
    // not that much
    public static Integer[] unitVector(int[] v) {
        // Fraction[] ret = new Fraction[2];
//        double norm = magnitude(v);
        return simplify(v);

        // ret[0] = new Fraction(simplified[0], simplified[1], (simplified[0] * simplified[1]) > 0 ? true : false);
        // ret[1] = v[1] / (double) norm;
        // actually we don't want to copy any add'l info over
        // just the direction
        // nothing else, like whether it was a player or trainer
        // that will all get combined together
//        for (int i = 2; i < v.length; i++) {
//            ret[i] = (Double) (double) v[i]; // copy over the rest
//            // of the elements
//        }
        // return ret;
    }

    public static void main(String[] args) {
        int[] dimensions = {3, 2};
        int[] your_position = {1, 1};
        int[] trainer_position = {2, 1};
        int distance = 4;
        int test = BeamPoolTables.solution(dimensions, your_position, trainer_position, distance);
        //System.out.println(test);

        int test2 = BeamPoolTables.solution(new int[]{300, 275},
                                            new int[]{150, 150},
                                            new int[]{185, 100},
                                    500);
        //System.out.println(test2);

    }

    // instead of using the unit vector as a representation
    // use the, what do you call it
    // use the smallest integer as a representation
    // and do simplifying like you did before

    public static Integer[] simplify(int[] frac) {
        int gcd = gcd((int) Math.abs(frac[0]), (int) Math.abs(frac[1]));
        return new Integer[]{frac[0] / gcd, frac[1] / gcd};
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

}
