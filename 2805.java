import java.io.*;
import java.util.*;

class Solution {
	static int farmSize;
	static int[][] farm;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			farmSize = Integer.parseInt(br.readLine());
			farm = new int[farmSize][farmSize];
			int sumValue = 0;
			// 초기화

			for (int r = 0; r < farmSize; r++) {
				char[] temp = br.readLine().toCharArray();

				for (int c = 0; c < farmSize; c++) {
					farm[r][c] = Character.getNumericValue(temp[c]);
				}
			}

			int midR = farmSize / 2, midC = farmSize / 2;
			ArrayDeque<Point> bfs = new ArrayDeque<>();
			boolean[][] isVisit = new boolean[farmSize][farmSize];

			bfs.add(new Point(midR, midC));
			isVisit[midR][midC] = true;
			sumValue += farm[midR][midC];

			for (int i = 0; i < (farmSize / 2); i++) { // farmSize / 2번 만큼 bfs 돌리기
				ArrayDeque<Point> tempBfs = new ArrayDeque<>(bfs);
				bfs.clear();

				while (!tempBfs.isEmpty()) {
					Point p = tempBfs.removeFirst();

					for (int d = 0; d < 4; d++) {
						int rr = p.r + dr[d];
						int cc = p.c + dc[d];

						if (isVisit[rr][cc] == false) {
							isVisit[rr][cc] = true;
							bfs.add(new Point(rr, cc));

							sumValue += farm[rr][cc];
						}
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(sumValue).append("\n");
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
