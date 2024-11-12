import copy


def printAnswer(case, answer):
    print("#" + str(case) + " ", end='')
    for ans in answer:
        print(ans, end=' ')
    print()


testCase = int(input())

for case in range(1, testCase + 1):
    stuffNum = int(input())  # 물건의 개수
    stuff = list(map(int, input().split()))  # 물건의 가격

    discountStuff = []

    while stuff:
        originPrice = stuff.pop()
        discountPrice = originPrice // 4 * 3

        stuff.remove(discountPrice)

        discountStuff.append(discountPrice)

    discountStuff.sort()

    printAnswer(case, discountStuff)
