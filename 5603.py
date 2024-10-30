testCase = int(input())

for case in range(1, testCase + 1):
    hayNum = int(input())

    hayStack = []
    hayAve = 0
    hayMove = 0

    for _ in range(hayNum):
        hayStack.append(int(input()))

    hayAve = sum(hayStack) // hayNum

    for hay in hayStack:
        if (hayAve > hay):
            hayMove += (hayAve - hay)

    print("#" + str(case) + " " + str(hayMove))
