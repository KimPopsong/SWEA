import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int stuffNumber, bagVolume;
			int[][] stuffs, dp; // 부피, 가치 순으로 저장. dp 배열

			st = new StringTokenizer(br.readLine());

			stuffNumber = Integer.parseInt(st.nextToken());
			bagVolume = Integer.parseInt(st.nextToken());

			stuffs = new int[stuffNumber + 1][2];
			dp = new int[stuffNumber + 1][bagVolume + 1];

			for (int i = 1; i <= stuffNumber; i++) {
				st = new StringTokenizer(br.readLine());

				stuffs[i][0] = Integer.parseInt(st.nextToken());
				stuffs[i][1] = Integer.parseInt(st.nextToken());
			}

			for (int s = 1; s <= stuffNumber; s++) {
				for (int v = 1; v <= bagVolume; v++) {
					if (v < stuffs[s][0]) {
						dp[s][v] = dp[s - 1][v];
					}

					else {
						dp[s][v] = Math.max(dp[s - 1][v], dp[s - 1][v - stuffs[s][0]] + stuffs[s][1]);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(dp[stuffNumber][bagVolume]).append("\n");
		}

		System.out.print(sb);
	}
}
