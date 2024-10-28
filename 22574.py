testCase = int(input())

for _ in range(testCase):
    chance, bomb = map(int, input().split())

    elevator = 0

    flag = False
    for i in range(1, chance + 1):
        elevator += i

        if (elevator == bomb):
            flag = True

    if (flag):
        elevator -= 1

    print(elevator)
