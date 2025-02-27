import java.io.*;
import java.util.*;

class Solution {
	static int size, maxMoveRoomNumber, maxMoveCount;
	static boolean[][] isVisit;
	static int[][] room;
	static Point[] coordinates; // 숫자별 좌표 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			size = Integer.parseInt(br.readLine());
			maxMoveRoomNumber = 0;
			maxMoveCount = 0;
			room = new int[size][size];
			isVisit = new boolean[size][size];
			coordinates = new Point[(size * size) + 1];

			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					room[r][c] = Integer.parseInt(st.nextToken());
					coordinates[room[r][c]] = new Point(r, c);
				}
			}

			// 초기화 및 입력 끝

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			for (int number = 1; number <= size * size; number++) { // 1부터 탐색
				Point num = coordinates[number];

				int r = num.r;
				int c = num.c;

				if (isVisit[r][c] == true) { // 이미 방문하였다면
					continue; // 방문하지 않음
				}

				int count = 0;

				while (true) { // 탐색
					boolean flag = false;

					isVisit[r][c] = true; // 방문 처리
					count += 1;

					for (int d = 0; d < 4; d++) {
						int rr = r + dr[d];
						int cc = c + dc[d];

						if (0 <= rr && rr < size && 0 <= cc && cc < size) {
							if (room[rr][cc] == room[r][c] + 1) {
								flag = true;

								r = rr;
								c = cc;

								break;
							}
						}
					}

					if (flag == false) {
						break;
					}
				}

				if (count > maxMoveCount) {
					maxMoveRoomNumber = number;
					maxMoveCount = count;
				}
			}

			sb.append("#").append(tc).append(" ").append(maxMoveRoomNumber).append(" ").append(maxMoveCount)
					.append("\n");
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
