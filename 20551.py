def printAnswer(case, answer):
    print("#" + str(case) + " " + str(answer))


testCase = int(input())

for case in range(1, testCase + 1):
    eatCandy = 0

    candyBox = list(map(int, input().split()))

    flag = True

    for i in range(len(candyBox) - 2, -1, -1):  # 뒤에서부터 감소
        if (candyBox[i] >= candyBox[i + 1]):  # 뒤에 있는 사탕 박스보다 크다면
            eatCandy += candyBox[i] - (candyBox[i + 1] - 1)  # 뒤에 있는 사탕 박스보다 1개 더 적게 남도록 먹기

            candyBox[i] = candyBox[i + 1] - 1

            if (candyBox[i] <= 0):
                flag = False

                break

    if (flag == False):
        eatCandy = -1

    printAnswer(case, eatCandy)
