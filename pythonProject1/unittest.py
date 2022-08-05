import solution
import govanify
import math
from random import randint
# from main import solution


def main():
    pass
    # assert solution.solution([3, 2], [1, 1], [2, 1], 4) == govanify.solution([3, 2], [1, 1], [2, 1], 4)
    # assert solution.solution([300, 275], [150, 150], [185, 100], 500) == govanify.solution([300, 275], [150, 150], [185, 100], 500)
    # # assert solution.solution([10, 10], [4, 4], [3, 3], 5000) == govanify.solution([10, 10], [4, 4], [3, 3], 5000)
    # assert solution.solution([2, 5], [1, 2], [1, 4], 11) == govanify.solution([2, 5], [1, 2], [1, 4], 11)
    # assert solution.solution([23, 10], [6, 4], [3, 2], 23) == govanify.solution([23, 10], [6, 4], [3, 2], 23)
    # # assert solution.solution([1250, 1250], [1000, 1000], [500, 400], 10000) == govanify.solution([1250, 1250], [1000, 1000], [500, 400], 10000)
    # assert solution.solution([10, 10],[5, 8], [1, 7], 6) == govanify.solution([10, 10],[5, 8], [1, 7], 6)
    # # assert solution.solution([10, 10],[5, 8], [1, 7], 10000) == govanify.solution([10, 10],[5, 8], [1, 7], 10000)
    # assert solution.solution([15, 13], [7, 4], [11, 12], 28) == govanify.solution([15, 13], [7, 4], [11, 12], 28)
    # assert solution.solution([35, 36], [17, 24], [13, 32], 729) == govanify.solution([35, 36], [17, 24], [13, 32], 729)
    # # assert solution.solution([2, 3], [1, 1], [1, 2], 1000) == govanify.solution([2, 3], [1, 1], [1, 2], 1000)
    # assert solution.solution([2, 3], [1, 1], [1, 2], 2) == govanify.solution([2, 3], [1, 1], [1, 2], 2)
    # assert solution.solution([1250, 1250], [1000, 1000], [1000, 998], 2) == govanify.solution([1250, 1250], [1000, 1000], [1000, 998], 2)
    #
    # assert solution.solution([1000, 1000], [999, 999], [1, 1], 2000) == govanify.solution([1000, 1000], [999, 999], [1, 1], 2000)
    # assert solution.solution([10, 10], [9, 9], [1, 1], 2000) == govanify.solution([10, 10], [9, 9], [1, 1], 2000)
    # assert solution.solution([9, 3], [3, 2], [8, 2], 13) == govanify.solution([9, 3], [3, 2], [8, 2], 13)
    print(solution.solution([9, 3], [3, 2], [8, 2], 13))
    print(govanify.solution([9, 3], [3, 2], [8, 2], 13))
    # for i in range(1000):
    #     # randint() is inclusive both ends
    #     xDim = randint(2, 1250)
    #     yDim = randint(2, 1250)
    #
    #     if xDim == 2 and yDim == 2:
    #         continue
    #
    #     xPlayer = randint(1, xDim - 1)
    #     yPlayer = randint(1, yDim - 1)
    #
    #     xTrainer = randint(1, xDim - 1)
    #     yTrainer = randint(1, yDim - 1)
    #
    #     if xPlayer == xTrainer and yPlayer == yTrainer:
    #         continue
    #
    #     distance = randint(2, 30)
    #     print("xDim is " + str(xDim) + " yDim is " + str(yDim) + " xP is " + str(xPlayer) + " yP is " + str(yPlayer) + " xT is " + str(xTrainer) + " yT is " + str(yTrainer) + " dist is " + str(distance))
    #     assert solution.solution([xDim, yDim], [xPlayer, yPlayer], [xTrainer, yTrainer], distance) == govanify.solution([xDim, yDim], [xPlayer, yPlayer], [xTrainer, yTrainer], distance)
    #     print("Random test case " + str(i))

    print("got to the end")


main()