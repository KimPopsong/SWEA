def printAnswer(case, answer):
    print("#" + str(case) + " %d" % answer)


testCase = int(input())

for case in range(1, testCase + 1):
    num1, num2 = map(int, input().split())

    answer = 0

    gap = num2 - num1

    if (gap == 0):
        answer = 0

    elif (gap <= 1):
        answer = -1

    else:
        answer = gap // 2

    printAnswer(case, answer)
