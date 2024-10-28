testCase = int(input())

for test in range(1, testCase + 1):
    number = int(input())

    numbers = list(map(int, input().split()))

    dp = []
    dp.append(numbers[0])

    for i in range(1, number):
        if (numbers[i] > dp[i - 1] + numbers[i]):
            dp.append(numbers[i])

        else:
            dp.append(dp[i - 1] + numbers[i])

    print("#" + str(test) + " " + str(max(dp)))
