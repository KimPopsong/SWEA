testCase = int(input())

for case in range(1, testCase + 1):
    length = int(input())  # 행렬의 한 변의 길이. 행렬은 정사각형

    matrix = []  # 행렬 저장
    for i in range(length):
        temp = list(map(int, input().split()))

        matrix.append(temp)

    reverse = 0  # 뒤집는 횟수
    isSorted = [False for i in range(length)]  # 첫 줄만 계산

    for i in range(length):
        if (matrix[0][i] == (i + 1)):
            isSorted[i] = True

    for i in range(length - 1, -1, -1):  # 뒤에서부터 계산
        if (isSorted[i] == False):  # 정렬되어있지 않으면
            flag = True
            reverse += 1

            for j in range(1, i + 1):  # 뒤집기 실행
                if (isSorted[j] == True):
                    isSorted[j] = False

                else:
                    isSorted[j] = True

    print(reverse)
