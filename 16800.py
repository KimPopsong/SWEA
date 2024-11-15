import math


def printAnswer(case, answer):
    print("#" + str(case) + " " + str(answer))


testCase = int(input())

for case in range(1, testCase + 1):
    number = int(input())

    multPair = set()

    minDistance = 9999999999999999999

    for i in range(1, math.floor(math.sqrt(number)) + 1):
        if (number % i == 0):
            multPair.add((i, number // i))

    for pair in multPair:
        px, py = pair

        distance = px + py - 2

        if (distance < minDistance):
            minDistance = distance

    printAnswer(case, minDistance)
