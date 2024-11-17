def printAnswer(case, answer):
    print("#" + str(case) + " " + str(answer))


testCase = int(input())

for case in range(1, testCase + 1):
    n = int(input())

    printAnswer(case, n * n)
