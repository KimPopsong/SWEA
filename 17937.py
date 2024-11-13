def printAnswer(case, answer):
    print("#" + str(case) + " %d" % answer)


testCase = int(input())

for case in range(1, testCase + 1):
    num1, num2 = map(int, input().split())

    answer = 1

    if (num1 == num2):
        answer = num1

    printAnswer(case, answer)
