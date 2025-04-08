import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int length = Integer.parseInt(br.readLine());
			int dpLength = 0;
			int[] numbers = new int[length];
			int[] dp = new int[length];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < length; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());

				boolean flag = false;

				for (int j = 0; j < dpLength; j++) {
					if (dp[j] > numbers[i]) {
						flag = true;
						dp[j] = numbers[i];

						break;
					}
				}

				if (!flag) {
					dp[dpLength++] = numbers[i];
				}
			}

			sb.append("#").append(tc).append(" ").append(dpLength).append("\n");
		}
		System.out.print(sb);
	}
}
