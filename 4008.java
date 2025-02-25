import java.io.*;
import java.util.*;

class Solution {
	static int number, maxSum, minSum;
	static int[] operator, numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			number = Integer.parseInt(br.readLine());
			maxSum = Integer.MIN_VALUE;
			minSum = Integer.MAX_VALUE;
			operator = new int[4];
			numbers = new int[number];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < number; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}

			bruteForce(1, numbers[0]);

			sb.append("#").append(tc).append(" ").append(Math.abs(maxSum - minSum)).append("\n");
		}

		System.out.println(sb);
	}

	static void bruteForce(int depth, int sum) {
		if (depth == number) {
			maxSum = Math.max(maxSum, sum);
			minSum = Math.min(minSum, sum);

			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) { // 연산자가 있다면
				operator[i] -= 1;

				switch (i) {
				case 0: // 더하기
					bruteForce(depth + 1, sum + numbers[depth]);
					break;

				case 1: // 빼기
					bruteForce(depth + 1, sum - numbers[depth]);
					break;

				case 2: // 곱하기
					bruteForce(depth + 1, sum * numbers[depth]);
					break;

				case 3: // 나누기
					bruteForce(depth + 1, sum / numbers[depth]);
					break;
				}

				operator[i] += 1;
			}
		}
	}
}
