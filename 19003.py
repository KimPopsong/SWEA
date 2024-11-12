def printAnswer(case, answer):
    print("#" + str(case) + " " + str(answer))


def isPalindrome(word):
    for i in range(len(word) // 2):
        if (word[i] != word[len(word) - i - 1]):
            return False

    return True


testCase = int(input())

for case in range(1, testCase + 1):
    palindrome = 0

    stringNum, stringLength = map(int, input().split())

    strings = list()

    for _ in range(stringNum):
        strings.append(input())

    for i, s in enumerate(strings):
        reversedS = s[::-1]

        for j in range(i + 1, stringNum):
            if (strings[j] == reversedS):
                palindrome += 2

    for s in strings:
        if (isPalindrome(s)):
            palindrome += 1

            break

    printAnswer(case, palindrome * stringLength)
