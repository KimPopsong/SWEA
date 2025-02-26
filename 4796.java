import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int testCase = scanner.nextInt();

		for (int tc = 1; tc <= testCase; tc++) {
			int mountainNumber = scanner.nextInt();
			int highestMountain = 0;
			int[] mountains = new int[mountainNumber];

			for (int i = 0; i < mountainNumber; i++) {
				mountains[i] = scanner.nextInt();
			}

			for (int i = 1; i < mountainNumber - 1; i++) { // 1번 인덱스부터 산의 개수 - 1번 인덱스까지
				if (mountains[i - 1] < mountains[i] && mountains[i] > mountains[i + 1]) { // 양 옆 산보다 높다면
					int left = 1, right = 1;

					for (int j = i - 1; j >= 1; j--) {
						if (mountains[j] > mountains[j - 1]) {
							left += 1;
						}

						else {
							break;
						}
					}

					for (int j = i + 1; j < mountainNumber - 1; j++) {
						if (mountains[j] > mountains[j + 1]) {
							right += 1;
						}

						else {
							break;
						}
					}

					highestMountain += (left * right);
				}
			}

			sb.append("#").append(tc).append(" ").append(highestMountain).append("\n");
		}

		System.out.print(sb);

		scanner.close();
	}
}
