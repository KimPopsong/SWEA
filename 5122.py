test_case = int(input())
result = []

for tc in range(1, test_case + 1):
    list_number, command_number, print_index = map(int, input().split())

    number_list = list(map(int, input().split()))

    for _ in range(command_number):
        command = input().split()

        if command[0] == "I":  # Insert
            idx, number_to_add = int(command[1]), int(command[2])
            number_list.insert(idx, number_to_add)

        elif command[0] == "D":  # Delete
            idx = int(command[1])
            if 0 <= idx < len(number_list):
                number_list.pop(idx)

        else:  # Change
            idx, number_to_change = int(command[1]), int(command[2])

            if 0 <= idx < len(number_list):
                number_list[idx] = number_to_change

    answer = number_list[print_index] if print_index < len(number_list) else -1
    result.append(f"#{tc} {answer}")

print("\n".join(result))
