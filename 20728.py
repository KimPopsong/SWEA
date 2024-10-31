testCase = int(input())

for case in range(1, testCase + 1):
    minGap = 10000000000000

    candyNum, childrenNum = map(int, input().split())

    candy = list(map(int, input().split()))

    candy.sort()

    for i in range(candyNum - childrenNum + 1):
        gap = candy[i + childrenNum - 1] - candy[i]

        if (gap < minGap):
            minGap = gap

    print("#" + str(case) + " " + str(minGap))
