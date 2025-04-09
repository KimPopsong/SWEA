import java.io.*;
import java.util.*;

public class Solution {
	static int size, slopeLength;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			size = Integer.parseInt(st.nextToken());
			slopeLength = Integer.parseInt(st.nextToken());

			map = new int[size][size];

			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 입력부 종료

			int available = 0;

			// 가로 확인
			for (int r = 0; r < size; r++) {
				boolean flag = true;
				boolean[] isVisit = new boolean[size];
				int[] dp = new int[size];

				for (int c = 1; c < size; c++) {
					dp[c] = map[r][c] - map[r][c - 1];

					if (dp[c] < -1 || 1 < dp[c]) {
						flag = false;

						break;
					}
				}

				for (int c = 0; c < size; c++) {
					if (flag == false) {
						break;
					}

					if (dp[c] > 0) {
						if (c - slopeLength < 0) {
							flag = false;

							break;
						}

						else {
							for (int cc = c - 1; cc >= c - slopeLength; cc--) {
								if (dp[cc] == -1) {
									flag = false;

									break;
								}

								else if (isVisit[cc]) {
									flag = false;

									break;
								}

								isVisit[cc] = true;
							}
						}
					}

					else if (dp[c] < 0) {
						if (c + slopeLength - 1 >= size) {
							flag = false;

							break;
						}

						else {
							for (int cc = c + 1; cc <= c + slopeLength - 1; cc++) {
								if (dp[cc] != 0) {
									flag = false;

									break;
								}

								else if (isVisit[cc]) {
									flag = false;

									break;
								}

								isVisit[cc] = true;
							}
						}
					}
				}

				if (flag) {
					available += 1;
				}
			}

			// 세로 확인
			for (int c = 0; c < size; c++) {
				boolean flag = true;
				boolean[] isVisit = new boolean[size];
				int[] dp = new int[size];

				for (int r = 1; r < size; r++) {
					dp[r] = map[r][c] - map[r - 1][c];

					if (dp[r] < -1 || 1 < dp[r]) {
						flag = false;

						break;
					}
				}

				for (int r = 0; r < size; r++) {
					if (flag == false) {
						break;
					}

					if (dp[r] > 0) {
						if (r - slopeLength < 0) {
							flag = false;

							break;
						}

						else {
							for (int rr = r - 1; rr >= r - slopeLength; rr--) {
								if (dp[rr] == -1) {
									flag = false;

									break;
								}

								else if (isVisit[rr]) {
									flag = false;

									break;
								}

								isVisit[rr] = true;
							}
						}
					}

					else if (dp[r] < 0) {
						if (r + slopeLength - 1 >= size) {
							flag = false;

							break;
						}

						else {
							for (int rr = r + 1; rr <= r + slopeLength - 1; rr++) {
								if (dp[rr] != 0) {
									flag = false;

									break;
								}

								else if (isVisit[rr]) {
									flag = false;

									break;
								}

								isVisit[rr] = true;
							}
						}
					}
				}

				if (flag) {
					available += 1;
				}
			}

			sb.append("#").append(tc).append(" ").append(available).append("\n");
		}

		System.out.print(sb);
	}
}
