import java.io.*;
import java.util.*;

public class Solution {
	static int marbleNumber, colSize, rowSize;
	static int answer;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			marbleNumber = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			rowSize = Integer.parseInt(st.nextToken());
			answer = Integer.MAX_VALUE;
			map = new int[rowSize][colSize];

			for (int r = 0; r < rowSize; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < colSize; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			recursion(0, map); // 0번 열부터 마지막 열까지 선택

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(sb);
	}

	static void simulate(int[][] newMap, int col) {
		int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

		for (int r = 0; r < rowSize; r++) { // 벽돌 폭발시키기
			if (newMap[r][col] != 0) {
				ArrayDeque<Brick> bfs = new ArrayDeque<>();
				bfs.add(new Brick(r, col, newMap[r][col]));
				newMap[r][col] = 0;

				while (!bfs.isEmpty()) {
					Brick brick = bfs.remove();

					for (int i = 1; i < brick.range; i++) {
						for (int d = 0; d < 4; d++) {
							int rr = brick.r + dr[d] * i;
							int cc = brick.c + dc[d] * i;

							if (0 <= rr && rr < rowSize && 0 <= cc && cc < colSize) {
								if (newMap[rr][cc] != 0) {
									bfs.add(new Brick(rr, cc, newMap[rr][cc]));
									newMap[rr][cc] = 0;
								}
							}
						}
					}
				}

				break;
			}
		}

		// 벽돌 내리기
		for (int c = 0; c < colSize; c++) {
			ArrayDeque<Integer> queue = new ArrayDeque<>();

			for (int r = rowSize - 1; r >= 0; r--) {
				if (newMap[r][c] != 0) {
					queue.addLast(newMap[r][c]);
					newMap[r][c] = 0;
				}
			}

			int r = rowSize - 1;
			while (!queue.isEmpty()) {
				newMap[r][c] = queue.removeFirst();
				r -= 1;
			}
		}
	}

	static void recursion(int depth, int[][] newMap) {
		if (depth == marbleNumber) {
			int score = 0;

			for (int r = 0; r < rowSize; r++) {
				for (int c = 0; c < colSize; c++) {
					if (newMap[r][c] != 0) {
						score += 1;
					}
				}
			}

			answer = Math.min(answer, score);

			return;
		}

		for (int c = 0; c < colSize; c++) { // 구슬을 떨어뜨릴 열 선택
			int[][] newNewMap = new int[rowSize][colSize];

			for (int rr = 0; rr < rowSize; rr++) {
				for (int cc = 0; cc < colSize; cc++) {
					newNewMap[rr][cc] = newMap[rr][cc];
				}
			}

			simulate(newNewMap, c);

			recursion(depth + 1, newNewMap);
		}
	}

	static class Brick {
		int r, c, range;

		Brick(int r, int c, int range) {
			this.r = r;
			this.c = c;
			this.range = range;
		}
	}
}
