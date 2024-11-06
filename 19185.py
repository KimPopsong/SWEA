def printAnswer(case, answer):
    print("#" + str(case) + " " + answer)


testCase = int(input())

for case in range(1, testCase + 1):
    n, m = map(int, input().split())

    nString = list(input().split())
    mString = list(input().split())

    answer = ""

    miniTestCase = int(input())

    for miniCase in range(1, miniTestCase + 1):
        year = int(input())

        answer += nString[(year - 1) % n] + mString[(year - 1) % m] + " "

    printAnswer(case, answer)
