testCase = int(input())

for case in range(1, testCase + 1):
    personNum = int(input())

    print("#" + str(case), end=" ")

    for i in range(personNum):
        print(str(1) + "/" + str(personNum), end=" ")
    print()
