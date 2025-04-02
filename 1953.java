import java.io.*;
import java.util.*;

class Solution {
	static int rowSize, colSize, startR, startC, time;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			startR = Integer.parseInt(st.nextToken());
			startC = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());

			map = new int[rowSize][colSize];

			for (int r = 0; r < rowSize; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < colSize; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int count = 0;
			ArrayDeque<Point> bfs = new ArrayDeque<>();
			boolean[][] isVisit = new boolean[rowSize][colSize];

			bfs.add(new Point(startR, startC));
			isVisit[startR][startC] = true;

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			while (!bfs.isEmpty()) {
				ArrayDeque<Point> tempBfs = new ArrayDeque<>(bfs);
				bfs.clear();

				while (!tempBfs.isEmpty()) {
					count += 1;

					Point p = tempBfs.removeFirst();

					for (int d = 0; d < 4; d++) {
						int rr = p.r + dr[d];
						int cc = p.c + dc[d];

						if (0 <= rr && rr < rowSize && 0 <= cc && cc < colSize) {
							if (isVisit[rr][cc] == false) {
								// 파이프 모양을 비교하여 갈 수 있는지 확인
								if (map[p.r][p.c] == 1) {
									if (d == 0) {
										if (map[rr][cc] == 1 || map[rr][cc] == 2 || map[rr][cc] == 5 || map[rr][cc] == 6) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else if (d == 1) {
										if (map[rr][cc] == 1 || map[rr][cc] == 2 || map[rr][cc] == 4 || map[rr][cc] == 7) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else if (d == 2) {
										if (map[rr][cc] == 1 || map[rr][cc] == 3 || map[rr][cc] == 4 || map[rr][cc] == 5) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else {
										if (map[rr][cc] == 1 || map[rr][cc] == 3 || map[rr][cc] == 6 || map[rr][cc] == 7) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}
								}

								else if (map[p.r][p.c] == 2) {
									if (d == 0) {
										if (map[rr][cc] == 1 || map[rr][cc] == 2 || map[rr][cc] == 5 || map[rr][cc] == 6) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else if (d == 1) {
										if (map[rr][cc] == 1 || map[rr][cc] == 2 || map[rr][cc] == 4 || map[rr][cc] == 7) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}
								}

								else if (map[p.r][p.c] == 3) {
									if (d == 2) {
										if (map[rr][cc] == 1 || map[rr][cc] == 3 || map[rr][cc] == 4 || map[rr][cc] == 5) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else if (d == 3) {
										if (map[rr][cc] == 1 || map[rr][cc] == 3 || map[rr][cc] == 6 || map[rr][cc] == 7) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}
								}

								else if (map[p.r][p.c] == 4) {
									if (d == 0) {
										if (map[rr][cc] == 1 || map[rr][cc] == 2 || map[rr][cc] == 5 || map[rr][cc] == 6) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else if (d == 3) {
										if (map[rr][cc] == 1 || map[rr][cc] == 3 || map[rr][cc] == 6 || map[rr][cc] == 7) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}
								}

								else if (map[p.r][p.c] == 5) {
									if (d == 1) {
										if (map[rr][cc] == 1 || map[rr][cc] == 2 || map[rr][cc] == 4 || map[rr][cc] == 7) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else if (d == 3) {
										if (map[rr][cc] == 1 || map[rr][cc] == 3 || map[rr][cc] == 6 || map[rr][cc] == 7) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}
								}

								else if (map[p.r][p.c] == 6) {
									if (d == 1) {
										if (map[rr][cc] == 1 || map[rr][cc] == 2 || map[rr][cc] == 4 || map[rr][cc] == 7) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else if (d == 2) {
										if (map[rr][cc] == 1 || map[rr][cc] == 3 || map[rr][cc] == 4 || map[rr][cc] == 5) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}
								}

								else if (map[p.r][p.c] == 7) {
									if (d == 0) {
										if (map[rr][cc] == 1 || map[rr][cc] == 2 || map[rr][cc] == 5 || map[rr][cc] == 6) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}

									else if (d == 2) {
										if (map[rr][cc] == 1 || map[rr][cc] == 3 || map[rr][cc] == 4 || map[rr][cc] == 5) {
											bfs.add(new Point(rr, cc));
											isVisit[rr][cc] = true;
										}
									}
								}
							}
						}
					}
				}

				time -= 1;

				if (time <= 0) {
					break;
				}
			}

			sb.append("#").append(tc).append(" ").append(count).append("\n");
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
