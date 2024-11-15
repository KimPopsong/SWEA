import math


def printAnswer(case, answer):
    print("#" + str(case) + " " + str(answer))


testCase = int(input())

for case in range(1, testCase + 1):
    r = int(input())

    point = 0

    rr = r * r  # 반지름의 제곱
    for x in range(-r, r + 1):  # x의 좌표 설정
        yMax = math.sqrt(rr - x ** 2)  # x^2 + y^2 == r^2 응용

        point += math.floor(yMax)

    point *= 2
    point += r * 2 + 1

    printAnswer(case, point)
