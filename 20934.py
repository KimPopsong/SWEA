testCase = int(input())

for i in range(1, testCase + 1):
    game = input().split()

    place = game[0]
    sound = int(game[1])

    answer = 0
    if (place[0] == "o"):  # 가장 왼쪽에 있을 때
        if (sound % 2 == 0):  # 짝수번 움직였다면
            answer = 0

        else:
            answer = 1

    elif (place[1] == "o"):  # 가운데 있을 때
        if (sound % 2 == 0):  # 짝수번 움직였다면
            answer = 1

        else:
            answer = 0

    else:  # 가장 오른쪽에 있을 때
        if (sound == 0):  # 움직이지 않았다면
            answer = 2

        elif (sound % 2 == 0):
            answer = 0

        else:
            answer = 1

    print("#%d " % i, end="")
    print(answer)
