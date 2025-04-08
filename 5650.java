import java.io.*;
import java.util.*;

class Solution {
	static int size, score;
	static int[] dr = { -1, 0, 0, 1 }, dc = { 0, 1, -1, 0 }; // 상우좌하
	static int[][] block = { { 3, 2, 0, 1 }, { 1, 2, 3, 0 }, { 2, 3, 1, 0 }, { 3, 0, 1, 2 }, { 3, 2, 1, 0 } };
	static int[][] map;
	static ArrayList<ArrayList<Point>> wormHoles;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			size = Integer.parseInt(br.readLine().trim());
			score = 0;

			map = new int[size][size];
			wormHoles = new ArrayList<>();

			for (int i = 0; i < 5; i++) {
				wormHoles.add(new ArrayList<>());
			}

			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());

					if (map[r][c] >= 6) { // 웜홀이라면
						wormHoles.get(map[r][c] - 6).add(new Point(r, c));
					}
				}
			}

			// 입력부 및 초기화 종료
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					if (map[r][c] == 0) {
						for (int direction = 0; direction < 4; direction++) { // 상하좌우 모든 경우를 테스트
							score = Math.max(score, simulate(r, c, direction));
						}
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(score).append("\n");
		}
		System.out.print(sb);
	}

	static int simulate(int startR, int startC, int direction) {
		int s = 0; // 점수

		int r = startR, c = startC;

		while (true) {
			r = r + dr[direction];
			c = c + dc[direction];

			if (0 <= r && r < size && 0 <= c && c < size) {
				if (r == startR && c == startC) { // 시작 지점으로 돌아왔다면
					break;
				}

				else if (map[r][c] == -1) { // 블랙홀이라면
					break;
				}

				else if (1 <= map[r][c] && map[r][c] <= 5) { // 블록이라면
					s += 1;

					direction = block[map[r][c] - 1][direction]; // 방향전환
				}

				else if (6 <= map[r][c] && map[r][c] <= 10) { // 웜홀이라면
					ArrayList<Point> wH = wormHoles.get(map[r][c] - 6);

					for (Point p : wH) {
						if (p.r != r || p.c != c) {
							r = p.r;
							c = p.c;

							break;
						}
					}
				}
			}

			else { // 벽에 부딪치면
				s += 1;

				direction = 3 - direction;
			}
		}

		return s;
	}

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
