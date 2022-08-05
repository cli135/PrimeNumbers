# import java.util.HashMap;
# import java.util.Map;
# import java.util.ArrayList;
# import java.util.List;
# import java.util.Arrays;
# import java.util.Collections;
import math

def solution(dimensions, your_position, trainer_position, distance):
    #Your code here

    # i am convinced it is runtime that is too slow# time limit exceeded# look at using constant factors to speed up the program


    # 8-2-2022 6:50pm
    # not passing tests 5 and 9 at the moment
    # potential reasons:
    # doesn't seem like there is roundoff error
    # even after transitioning to a new thing, gcd simplified
    # arrays, it still works the same
    # and anyways roundoff error should be deterministic and should
    # be the same each time
    # so this probably means the issue is something else
    # time limit exceeded
    # finish out the change from (1, 1) to (0, 0)
    # roundoff error in double equivalence class
    # could shift to using a custom fraction class
    # with integer numerator and denominator
    # you need to make changes to the program and run it
    # to gather information on what works and what doesn't
    # also you should reread the problem description
    # <= distance or < distance? I think <= but not sure
    # yep it's <= so we are good, test 7 checks this
    # since it fails on >= but succeeds on > (which is opposite of <=)
    # update: this poresolved
    # corner bounceback in same direction - should be taken
    # care of, via the reflecting thing




    # This problem is a lot like
    # pool table questions,
    # geometric optics / light reflection questions
    # integer lattice questions
    # and a good mix of algebra and geometry (analytic geometry)
    # using equations of lines and etc. in the Cartesian plane


    # law of reflection
    # entry angle == exit angle
    # but more importantly,
    # this is equivalent to reflecting the board
    # and letting the ball continue in a straight line

    # reflect left, right, up, down, and let the ball
    # go in straight lines outward
    # count the # of directions you can go in with the
    # distance limit as a radius limit

    # if two are in the same direction, only count them as one
    # because you'll run into it the first time before you
    # can get to the second one

    # (0, 0) is the True corner, not (1, 1)
    # just found that out

    # initializing variables
    xDim = dimensions[0];
    yDim = dimensions[1];
    xPlayer = your_position[0];
    yPlayer = your_position[1];
    xTrainer = trainer_position[0];
    yTrainer = trainer_position[1];

    # generate reflection list
    # right

    # list of bearings to reach trainer
    bearings = []

    # keep reflecting until the distance is too far away
    curTrainerPosition = [xTrainer, yTrainer, 1] # 1 indicates trainer is here, not player


    bearings.append(curTrainerPosition);


    # the wall here is at (0, 0) so we reflect off the wall like this
    xParityForTrainer = [2 * (xDim - xTrainer), 2 * (xTrainer)]
    xParityForPlayer = [2 * (xDim - xPlayer), 2 * (xPlayer)]
    yParityForTrainer = [2 * (yDim - yTrainer), 2 * (yTrainer)]
    yParityForPlayer = [2 * (yDim - yPlayer), 2 * (yPlayer)]

    # reflect board continuously to the right
    reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, curTrainerPosition, "right", False);

    # same thing, but going left now, reflecting to the left
    # t == 1 means that we are starting with a different reflection first
    #        reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, curTrainerPosition, "left", False);

    # for each of the horizontal reflection images, we will reflect *those*
    # up and down

    # I probably don't need to store these in memory, I could just iterate
    # over them, but it's fine and I'll figure it out later
    # I'll find the corners manually and make a double for loop that hop-skips
    # by 2 different jump sizes later

    traverseLength = len(bearings)
    i = 0
    while i < traverseLength:
        # for i in range(traverseLength):
        # for (i = 0; i < traverseLength; i++) {
        position = bearings[i]
        reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, position, "up", False);#            reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, position, "down", False);
        i += 1
    # now doing the same thing but for the player

    #=================================================
    curPlayerPosition = [xPlayer, yPlayer, 0]

    idxSaved = len(bearings);

    bearings.append(curPlayerPosition);

    # reflect board continuously to the right
    reflectBoardRecursively(distance, bearings, xParityForPlayer, yParityForPlayer, curPlayerPosition, "right", True);

    # same thing, but going left now, reflecting to the left
    # t == 1 means that we are starting with a different reflection first#        reflectBoardRecursively(distance, bearings, xParityForPlayer, yParityForPlayer, curPlayerPosition, "left", True);

    # for each of the horizontal reflection images, we will reflect *those*
    # up and down

    # TODOnomore: need a better way to count this - the player reflections
    # UPDATE: DONE
    traverseLength = len(bearings)
    i = idxSaved
    while i < traverseLength:
        # for i in range(idxSaved, traverseLength):
        # for (i = idxSaved; i < traverseLength; i++) {
        position = bearings[i]
        # lesson learned: make sure you get the parameter calls right
        reflectBoardRecursively(distance, bearings, xParityForPlayer, yParityForPlayer, position, "up", True);#            reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, position, "down", True);
        i += 1

    # do all 4 quadrants
    # -x, y
    # x, -y
    # -x, -y
    # oops save the size beforehand
    traverseLength = len(bearings)
    i = 0
    while i < traverseLength:
        # for i in range(traverseLength):
        # for (i = 0; i < traverseLength; i++) {
        position = bearings[i]
        # other three quadrants
        bearings.append([-position[0], position[1], position[2]]);
        bearings.append([position[0], -position[1], position[2]]);
        bearings.append([-position[0],-position[1], position[2]]);
        i += 1

    # now all bearings should be centered from the player's original position, not (0, 0)
    #     for (i = 0; i < len(bearings); i++)
    i = 0
    while i < len(bearings):
        # for i in range(len(bearings)):
        position = [bearings[i][0], bearings[i][1]]
        diff = direction(your_position, position)
        bearings[i][0] = diff[0]
        bearings[i][1] = diff[1]
        i += 1
    # leave bearings[i][2] the same as it was - 0 for player, 1 for trainer

    # remove any (0, 0, x) bearings

    # going in reverse when we remove from an ArrayList
    # so we don't mess up our traversal
    # (only rightward elements are shifted over toward the left,
    # leftward elements remain as they were at the inception of the loop)
    i = len(bearings) - 1
    while (i >= 0):
        position = bearings[i]
        if position[0] == 0 and position[1] == 0:
            bearings.pop(i)
        i-=1
    # for i in range(len(bearings)- 1, -1, -1):
    #     # for (i = len(bearings) - 1; i >= 0; i--) {
    #     position = bearings[i]
    #     if (position[0] == 0 and position[1] == 0):
    #         bearings.pop(i)

    # debugging output
    for coords in bearings:
        pass
        #System.out.println(Arrays.toString(coords));


    # put them into equivalence classes
    # Map<UnitVectorDirection, List<ActualPosition>> map
    #     Map<List<Integer>, int[]> map = new HashMap<>();
    map = {}
    for i in range(len(bearings)):
        # for (i = 0; i < len(bearings); i++) {
        position = bearings[i]
        # positions and directions are now pretty much the same thing
        # after centralizing everything to be from the
        # player's original position
        # we only care about relative directions from the player's
        # poof view
        unitVectorDirection = unitVector(position) # a list
        angle = math.atan2(position[1], position[0])
        # TODO: make the below line more efficient than O(n)
        if (angle in map.keys()):
        #                map.get(Arrays.asList(unitVectorDirection)).append(position);
            cur = map[angle]
            if (magnitude(position) < magnitude(cur)):
            # position is closer than cur
                map[angle] = position
        else:
            map[angle] = position

    #System.out.println(map);

    # i really hope this map worked out okay
    # TODO: be careful about <local4> is null errors that could occur here

    count = 0 # we will return this value,
    # the count of directions we could go in

    # also need to remember max distance value, don't forget that

    # traversing map and applying the filter
    # e.g. for each direction, look for the closest person
    # if it's a player, +0 (we would hit ourselves first)
    # if it's a trainer, +1 (and ignore any further collinear points beyond that)
    #     Map<List<Integer>, int[]> TrueMap = new HashMap<>();
    trueMap = {}
    for key, value in map.items():
        # for (Map.Entry<List<Integer>, int[]> entry : map.entrySet()) {

        angle = key;
        position = value;

#            double minDistance = Double.MAX_VALUE;#            closestPosition = new int[3];# just a placeholder for now#            for (position : positions) {#                if (magnitude(position) < minDistance) {#                    minDistance = magnitude(position);#                    closestPosition = position;#                }#            }
        closestPosition = position;
        minDistance = magnitude(closestPosition);

    # check whether the closest position is within the distance
        if (minDistance > distance):
            continue; # we can't reach this one

    # otherwise we can reach the closest one
    # which is the only one we care about
    # since we can't get past it to see anything behind it

    # i have a feeling that (0, 0, x) could be causing issues

        if (closestPosition[2] == 0):
            # player is closest in this direction
            #System.out.println("we did get some cases like this here");
            continue # can't aim here
        elif (closestPosition[2] == 1):
        # trainer is closest in this direction
        # we can aim here, and there is only one
        # count added since we can't reach anything
        # behind it

#                if (closestPosition[0] == -1 && your_position[0] == 1) {#                    continue;# can't go straight left#                }#                if (closestPosition[1] == -1 && your_position[1] == 1) {#                    continue;# can't go straight down#                }
            trueMap[angle] = position
            count += 1
        # tell us what was added#                System.out.println(unitDirection);
        #System.out.print(Arrays.toString(closestPosition));
        #System.out.print("   " + Math.atan2(closestPosition[0], closestPosition[1]));
        #System.out.println();#                System.out.println("  magnitude is: " + magnitude(closestPosition));#                System.out.print(Arrays.toString(closestPosition));#                System.out.println((double)Math.round(magnitude(closestPosition) * 1000d) / 1000d);


    # still need to filter out cases where it hits the
    # trainer first
    # or the player first
    # e.g. collinear cases
    # still need to filter out cases where it is too far away too

    # debugging output
    # printing out each direction and its closest node in that direction
    #     for (Map.Entry<List<Integer>, int[]> entry : map.entrySet()) {
    #         List<Integer> unitDirection = entry.getKey();
    #         position = entry.getValue();#            System.out.println(unitDirection + " : angle is " + Math.atan2(unitDirection.get(0), unitDirection.get(1)));#            System.out.println(Math.atan2(unitDirection.get(0), unitDirection.get(1)));#            System.out.println("\t" + Arrays.toString(position));
    #     }

    #System.out.println("count is: " + count);
    return count


def reflectBoardRecursively(distance, bearings, xParityForTrainer, yParityForTrainer, position, direction, isPlayer):

    curTrainerPosition = position;
    toggle = 0;

    # increasing total distance out
    totalDistanceOut = 0
    while totalDistanceOut <= distance:

        # for totalDistanceOut in range(0, distance + 1, xParityForTrainer[toggle]):
        # for (totalDistanceOut = 0; totalDistanceOut <= distance; totalDistanceOut += xParityForTrainer[toggle]) {
        # reflect the cur position
        # just a template that will change based on if-elseif-else statements below
        reflected = [curTrainerPosition[0] - xParityForTrainer[toggle], curTrainerPosition[1], 1]
        if isPlayer:
            reflected[2] = 0 # player
        else:
            reflected[2] = 1 # trainer
        # reflected[2] = isPlayer ? 0 : 1;
        if ("right" == direction):
            reflected[0] = curTrainerPosition[0] + xParityForTrainer[toggle]
        elif ("up" == direction):
            reflected[0] = curTrainerPosition[0]
            reflected[1] = curTrainerPosition[1] + yParityForTrainer[toggle]

        # add it to the bearings list
        bearings.append(reflected)

        # toggle jump
        if toggle == 0:
            toggle = 1
        else:
            toggle = 0

        # update cur trainer position to the recently reflected point
        curTrainerPosition = reflected

        # bring the distance out more
        totalDistanceOut += xParityForTrainer[toggle]

        # apparently for loops in python don't work so well
        # when translating from java
        # better to just use while loops


# vector subtraction in 2D Cartesian plane
def direction(start, end):
    # vector that connects two vectors (the difference)
    difference = [end[0] - start[0], end[1] - start[1]]
    return difference

# magnitude of direction between two points
def distance (start, end):
    return magnitude(direction(start, end))

# magnitude of a vector in 2D Cartesian plane
def magnitude(v):
    # Pythagorean theorem for distance
    # between a vector's tail and tip
    return math.sqrt(v[0] * v[0] + v[1] * v[1])

# please i hope double works right and no roundoff error or at least
# not that much
def unitVector(v):
    # Fraction[] ret = new Fraction[2];#        double norm = magnitude(v);
    return tuple(simplify(v))
    # ret[0] = new Fraction(simplified[0], simplified[1], (simplified[0] * simplified[1]) > 0 ? True : False);
    # ret[1] = v[1] / (double) norm;
    # actually we don't want to copy any add'l info over
    # just the direction
    # nothing else, like whether it was a player or trainer
    # that will all get combined together#        for (i = 2; i < v.length; i++) {#            ret[i] = (Double) (double) v[i];# copy over the rest#           # of the elements#        }
    # return ret;

# instead of using the unit vector as a representation
# use the, what do you call it
# use the smallest integer as a representation
# and do simplifying like you did before

def simplify(frac):
    greatestCommonDenominator = gcd(int(abs(frac[0])), int(abs(frac[1])))
    return [frac[0] / greatestCommonDenominator, frac[1] / greatestCommonDenominator]

def gcd(x, y):
    a = min(x, y)
    b = max(x, y)
    if (a == 0):
        return b
    # Euclidean algorithm
    return gcd(a, b%a)
