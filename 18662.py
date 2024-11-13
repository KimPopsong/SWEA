def printAnswer(case, answer):
    print("#" + str(case) + " %0.1f" % answer)


testCase = int(input())

for case in range(1, testCase + 1):
    numbers = list(map(int, input().split()))

    midNum = (numbers[0] + numbers[2]) / 2

    need = abs(midNum - numbers[1])
    
    printAnswer(case, need)
