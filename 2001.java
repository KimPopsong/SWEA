import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int mapSize, flapperSize, maxKill = 0;
			int[][] map, dp;

			st = new StringTokenizer(br.readLine());

			mapSize = Integer.parseInt(st.nextToken());
			flapperSize = Integer.parseInt(st.nextToken());

			map = new int[mapSize][mapSize];
			dp = new int[mapSize][mapSize + 1];

			for (int r = 0; r < mapSize; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < mapSize; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < mapSize; r++) {
				for (int c = 1; c <= mapSize; c++) {
					dp[r][c] = map[r][c - 1] + dp[r][c - 1];
				}
			}

			for (int r = 0; r < mapSize - flapperSize + 1; r++) { // 누적합 활용해 최대 구하기
				for (int c = 1; c <= mapSize - flapperSize + 1; c++) {
					int kill = 0;

					for (int rr = r; rr < r + flapperSize; rr++) {
						kill += dp[rr][c + flapperSize - 1] - dp[rr][c - 1];
					}

					maxKill = Math.max(maxKill, kill);
				}
			}

			sb.append("#").append(tc).append(" ").append(maxKill).append("\n");
		}

		System.out.println(sb);
	}
}
