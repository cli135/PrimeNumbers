from itertools import product
from math import atan2


def solution(dimensions, your_position, trainer_position, distance):
    x0, y0 = your_position
    hits = dict()
    for position in your_position, trainer_position:
        for reflect in product(*[range(-(distance // -d) + 1) for d in dimensions]):
            for quadrant in [(1, 1), (-1, 1), (-1, -1), (1, -1)]:
                x, y = [
                    (d * r + (d - p if r % 2 else p)) * q
                    for d, p, r, q in zip(dimensions, position, reflect, quadrant)
                ]
                travel = (abs(x - x0) ** 2 + abs(y - y0) ** 2) ** 0.5
                bearing = atan2(x0 - x, y0 - y)
                if travel > distance or bearing in hits and travel > abs(hits[bearing]):
                    continue
                # mark self-hits with a negative travel so we can filter later
                hits[bearing] = travel * (-1 if position == your_position else 1)
    for key, value in hits.items():
        if value > 0:
            # print("k=", key, "v=", value)
            # print(round(value, 3))
            pass

    return len([1 for travel in hits.values() if travel > 0])