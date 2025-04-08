import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int number = Integer.parseInt(br.readLine());
			int maxLength = 0;
			int[] dp = new int[number + 1];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < number; i++) { // LIS 구하기
				int n = Integer.parseInt(st.nextToken());

				dp[n] = dp[n - 1] + 1;

				maxLength = Math.max(maxLength, dp[n]);
			}

			sb.append("#").append(tc).append(" ").append(number - maxLength).append("\n");
		}

		System.out.print(sb);
	}
}
