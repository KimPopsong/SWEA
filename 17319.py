def printAnswer(case, answer):
    print("#" + str(case) + " " + answer)


testCase = int(input())

for case in range(1, testCase + 1):
    length = int(input())
    string = input()

    str1 = string[0:length // 2]
    str2 = string[length // 2:]

    answer = ""

    if (str1 == str2):
        answer = "Yes"

    else:
        answer = "No"

    printAnswer(case, answer)
