import java.io.*;
import java.util.*;

class Solution {
	static int rowSize, colSize;
	static char[][] map;
	static Point end;
	static ArrayDeque<Point> suyeon, devil;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());

			map = new char[rowSize][colSize];

			suyeon = new ArrayDeque<>();
			devil = new ArrayDeque<>();

			for (int r = 0; r < rowSize; r++) { // map 입력
				char[] temp = br.readLine().toCharArray();

				for (int c = 0; c < colSize; c++) {
					map[r][c] = temp[c];

					if (map[r][c] == 'S') { // 시작점
						suyeon.add(new Point(r, c));
					}

					else if (map[r][c] == 'D') { // 도착점
						end = new Point(r, c);
					}

					else if (map[r][c] == '*') { // 악마의 위치
						devil.add(new Point(r, c));
					}
				}
			}

			// 입력 및 초기화 끝
			// BFS 시작

			boolean flag = false;
			int time = 0;

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			while (!suyeon.isEmpty() || !devil.isEmpty()) {
				time += 1;

				// 악마 먼저 이동
				ArrayDeque<Point> temp = new ArrayDeque<>(devil);
				devil.clear();

				while (!temp.isEmpty()) {
					Point p = temp.remove();

					for (int d = 0; d < 4; d++) {
						int rr = p.r + dr[d];
						int cc = p.c + dc[d];

						if (0 <= rr && rr < rowSize && 0 <= cc && cc < colSize) {
							if (map[rr][cc] == 'S' || map[rr][cc] == '.') {
								map[rr][cc] = '*';
								devil.add(new Point(rr, cc));
							}
						}
					}
				}

				// 수연 이동
				temp = new ArrayDeque<>(suyeon);
				suyeon.clear();

				while (!temp.isEmpty()) {
					Point p = temp.remove();

					for (int d = 0; d < 4; d++) {
						int rr = p.r + dr[d];
						int cc = p.c + dc[d];

						if (0 <= rr && rr < rowSize && 0 <= cc && cc < colSize) {
							if (map[rr][cc] == '.') {
								map[rr][cc] = 'S';
								suyeon.add(new Point(rr, cc));
							}

							else if (map[rr][cc] == 'D') { // 도착했다면
								flag = true;

								suyeon.clear();
								devil.clear();
								temp.clear();

								break;
							}
						}
					}
				}
			}

			// 답 출력

			sb.append("#").append(tc).append(" ");

			if (flag) {
				sb.append(time).append("\n");
			}

			else {
				sb.append("GAME OVER").append("\n");
			}
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
