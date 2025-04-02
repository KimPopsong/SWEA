import java.io.*;
import java.util.*;

class Solution {
	static int villageSize, canPay, maxHouseNumber;
	static int[][] village;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			villageSize = Integer.parseInt(st.nextToken());
			canPay = Integer.parseInt(st.nextToken());
			maxHouseNumber = 0;

			village = new int[villageSize][villageSize];

			for (int r = 0; r < villageSize; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < villageSize; c++) {
					village[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 입력부 끝

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			for (int range = 1; range <= villageSize + 2; range++) { // 감시 범위의 크기
				// 중심의 좌표를 기준으로 완전 탐색

				int operatingCost = 2 * range * range - 2 * range + 1; // 운영 비용
				int maxCover = 0; // 최대로 가능한 집의 개수

				for (int r = 0; r < villageSize; r++) {
					for (int c = 0; c < villageSize; c++) {
						int cover = 0;

						ArrayDeque<Point> bfs = new ArrayDeque<>();
						boolean[][] isVisit = new boolean[villageSize][villageSize];

						bfs.add(new Point(r, c));
						isVisit[r][c] = true;

						for (int t = 0; t < range; t++) { // range 횟수만큼 반복
							ArrayDeque<Point> tempBfs = new ArrayDeque<>(bfs);
							bfs.clear();

							while (!tempBfs.isEmpty()) {
								Point p = tempBfs.removeFirst();

								if (village[p.r][p.c] == 1) {
									cover += 1;
								}

								for (int d = 0; d < 4; d++) {
									int rr = p.r + dr[d];
									int cc = p.c + dc[d];

									if (0 <= rr && rr < villageSize && 0 <= cc && cc < villageSize) {
										if (isVisit[rr][cc] == false) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}
								}
							}
						}

						maxCover = Math.max(maxCover, cover);
					}
				}

				if (operatingCost <= maxCover * canPay) {
					maxHouseNumber = Math.max(maxHouseNumber, maxCover);
				}
			}

			sb.append("#").append(tc).append(" ").append(maxHouseNumber).append("\n");
		}

		System.out.print(sb);
	}

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
