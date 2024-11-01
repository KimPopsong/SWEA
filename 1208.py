def printAnswer(case, answer):
    print("#" + str(case) + " " + str(answer))


for case in range(1, 11):
    dumpNumber = int(input())

    boxes = sorted(list(map(int, input().split())))

    for i in range(dumpNumber):
        if (boxes[0] == boxes[99]):  # 평탄화가 완료되었으면
            break

        boxes[0] += 1
        boxes[99] -= 1

        boxes.sort()

    printAnswer(case, boxes[99] - boxes[0])
