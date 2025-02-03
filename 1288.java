import java.io.*;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int number = Integer.parseInt(br.readLine());
			int count = 0;
			boolean[] isCount = new boolean[10]; // 0 ~ 9까지 카운트

			while (true) {
				if (isAllNumber(isCount)) {
					break;
				}

				count += 1;
				int tempNumber = number * count;

				while (tempNumber > 0) {
					isCount[tempNumber % 10] = true;

					tempNumber /= 10;
				}
			}

			sb.append("#").append(tc).append(" ").append(number * count).append("\n");
		}

		System.out.println(sb);
		br.close();
	}

	static boolean isAllNumber(boolean[] isCount) {
		for (boolean c : isCount) {
			if (!c) {
				return false;
			}
		}

		return true;
	}
}
