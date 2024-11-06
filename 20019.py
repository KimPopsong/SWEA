def printAnswer(case, answer):
    print("#" + str(case) + " " + answer)


testCase = int(input())

for case in range(1, testCase + 1):
    flag = True

    string = input()  # 문자열
    reversedString = string[::-1]  # 뒤집은 문자열
    length = len(string)  # 문자열의 길이

    if (string != reversedString):  # 회문 판별
        flag = False

    if (string[0:(length // 2)] != reversedString[length // 2 + 1:length]):  # 처음 (N - 1) / 2 글자가 회문인지 판별
        flag = False

    if (string[length // 2 + 1:length] != reversedString[0:(length // 2)]):  # 마지막 (N - 1) / 2 글자가 회문인지 판별
        flag = False

    if (flag):  # 답 출력
        printAnswer(case, "YES")

    else:
        printAnswer(case, "NO")
