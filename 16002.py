import math


def printAnswer(case, num1, num2):
    print("#" + str(case) + " " + str(num2) + " " + str(num1))


def isCompo(number):
    for i in range(2, math.ceil(math.sqrt(number)) + 1):
        if (number % i == 0):  # 나누어 떨어진다면
            return True  # 합성수

    return False


testCase = int(input())

for case in range(1, testCase + 1):
    n = int(input())

    num1 = 4
    num2 = 0

    while (True):
        if (isCompo(num1)):  # 합성수라면
            num2 = num1 + n

            if (isCompo(num2)):
                break

        num1 += 1

    printAnswer(case, num1, num2)
