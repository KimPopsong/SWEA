import java.io.*;
import java.util.*;

public class Solution {
	static int size, constructionHeight, highestHeight, maxWay;
	static int[][] map;
	static ArrayList<Point> highest;

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			size = Integer.parseInt(st.nextToken());
			constructionHeight = Integer.parseInt(st.nextToken());
			highestHeight = 0;
			maxWay = 0;
			map = new int[size][size];
			highest = new ArrayList<>();

			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());

					if (map[r][c] > highestHeight) {
						highestHeight = map[r][c];
						highest = new ArrayList<>();
						highest.add(new Point(r, c));
					}

					else if (map[r][c] == highestHeight) {
						highest.add(new Point(r, c));
					}
				}
			}

			for (Point p : highest) {
				boolean[][] isVisit = new boolean[size][size];
				isVisit[p.r][p.c] = true;

				bfs(map, isVisit, 1, false, p.r, p.c);
			}

			sb.append("#").append(tc).append(" ").append(maxWay).append("\n");
		}

		System.out.print(sb);
	}

	static void bfs(int[][] newMap, boolean[][] isVisit, int depth, boolean isConstruct, int r, int c) {
		maxWay = Math.max(maxWay, depth);

		boolean[][] newIsVisit = new boolean[size][size];
		int[][] newNewMap = new int[size][size];

		for (int rr = 0; rr < size; rr++) {
			for (int cc = 0; cc < size; cc++) {
				newIsVisit[rr][cc] = isVisit[rr][cc];
			}
		}

		for (int rr = 0; rr < size; rr++) {
			for (int cc = 0; cc < size; cc++) {
				newNewMap[rr][cc] = newMap[rr][cc];
			}
		}

		for (int d = 0; d < 4; d++) { // 4방탐색
			int rr = r + dr[d];
			int cc = c + dc[d];

			if (0 <= rr && rr < size && 0 <= cc && cc < size) {
				if (newIsVisit[rr][cc] == false) {
					if (newNewMap[rr][cc] < newNewMap[r][c]) {
						newIsVisit[rr][cc] = true;
						bfs(newNewMap, newIsVisit, depth + 1, isConstruct, rr, cc);
						newIsVisit[rr][cc] = false;
					}

					else if (isConstruct == false && newNewMap[rr][cc] - constructionHeight < newNewMap[r][c]) {
						newNewMap[rr][cc] = newNewMap[r][c] - 1;
						newIsVisit[rr][cc] = true;

						bfs(newNewMap, newIsVisit, depth + 1, true, rr, cc);

						newNewMap[rr][cc] = newMap[rr][cc];
						newIsVisit[rr][cc] = false;
					}
				}
			}

		}
	}

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
