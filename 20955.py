testCase = int(input())

for i in range(1, testCase + 1):
    start = input()
    target = input()

    flag = False

    while (True):
        if (target[-1] == "X"):  # 마지막이 X일 경우 X 제거
            target = target[0:len(target) - 1]

        else:  # 마지막이 Y일 경우 Y 제거 후 뒤집기
            target = target[0:len(target) - 1]
            target = target[::-1]

        if (len(target) == len(start)):
            if (target == start):
                flag = True

            else:
                flag = False

            break

    print("#%d " % i, end="")
    if (flag):
        print("Yes")

    else:
        print("No")
