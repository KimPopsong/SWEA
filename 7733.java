import java.io.*;
import java.util.*;

class Solution {
	static int size, partNumberMax; // 치즈의 크기, 덩어리의 최대 개수
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			size = Integer.parseInt(br.readLine());
			partNumberMax = 0;
			map = new int[size][size];

			for (int i = 0; i < size; i++) {
				String[] temp = br.readLine().split(" ");

				for (int j = 0; j < size; j++) {
					map[i][j] = Integer.parseInt(temp[j]);
				}
			}

			for (int day = 0; day <= 100; day++) { // 치즈 먹기
				for (int r = 0; r < size; r++) {
					for (int c = 0; c < size; c++) {
						if (map[r][c] == day) {
							map[r][c] = 0;
						}
					}
				}

				calcPart(); // 덩어리 개수 구하기
			}

			sb.append("#").append(tc).append(" ").append(partNumberMax).append("\n");
		}

		System.out.println(sb);
	}

	public static void calcPart() {
		ArrayDeque<Point> bfs = new ArrayDeque<>();
		int countPart = 0; // 덩어리의 개수
		boolean[][] isVisit = new boolean[size][size];

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (map[r][c] != 0) { // 치즈가 있다면 덩어리 구하기
					if (!isVisit[r][c]) {
						countPart += 1;

						bfs.add(new Point(r, c));
						isVisit[r][c] = true;

						while (!bfs.isEmpty()) {
							Point p = bfs.remove();

							for (int d = 0; d < 4; d++) {
								int rr = p.r + dr[d];
								int cc = p.c + dc[d];

								if (0 <= rr && rr < size && 0 <= cc && cc < size) { // 범위 안에 있고
									if (map[rr][cc] != 0) { // 치즈가 있고
										if (!isVisit[rr][cc]) // 방문하지 않았다면
										{
											bfs.add(new Point(rr, cc)); // 방문
											isVisit[rr][cc] = true;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		partNumberMax = Math.max(partNumberMax, countPart);
	}

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
