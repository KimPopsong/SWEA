import java.io.*;
import java.util.*;

public class Solution {
	static int size, maxDessert;
	static int[][] map;

	static int[] dr = { -1, 1, 1, -1 }, dc = { 1, 1, -1, -1 }; // 1시부터 시계방향으로 대각선

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			size = Integer.parseInt(br.readLine());
			maxDessert = -1;
			map = new int[size][size];

			for (int r = 0; r < size; r++) { // map 입력
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					if ((r == 0 && c == 0) || (r == 0 && c == size - 1) || (r == size - 1 && c == 0)
							|| (r == size - 1 && c == size - 1)) {
						continue;
					}

					else {
						for (int d = 0; d < 4; d++) {
							HashSet<Integer> desserts = new HashSet<>();
							desserts.add(map[r][c]);

							int rr = r + dr[d];
							int cc = c + dc[d];

							if (0 <= rr && rr < size && 0 <= cc && cc < size) { // 범위 안에 있고
								if (!desserts.contains(map[rr][cc])) { // 디저트를 포함하고 있지 않다면
									desserts.add(map[rr][cc]);

									dfs(d, 0, rr, cc, r, c, d, desserts);
								}
							}
						}
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(maxDessert).append("\n");
		}

		System.out.println(sb);
	}

	/**
	 * 최대한 크게 돌기
	 */
	// TODO 대각선 찍고 오는 경우 예외처리 해주기
	static boolean dfs(int direction, int change, int r, int c, int startR, int startC, int startDirection,
			Set<Integer> desserts) {
		// 모서리에 있다면
		if ((r == 0 && c == 0) || (r == 0 && c == size - 1) || (r == size - 1 && c == 0)
				|| (r == size - 1 && c == size - 1)) {
			return false;
		}

		else if (change >= 4) {
			return false;
		}

		int startD = direction;

		for (int d = 0; d < 3; d++) {
			int rr = r + dr[direction];
			int cc = c + dc[direction];

			if (0 <= rr && rr < size && 0 <= cc && cc < size) { // 범위 안에 있고
				if (!desserts.contains(map[rr][cc])) { // 디저트를 포함하고 있지 않다면
					desserts.add(map[rr][cc]);

					boolean result = false;

					if (startD == direction) {
						result = dfs(direction, change, rr, cc, startR, startC, startDirection, desserts);
					}

					else {
						result = dfs(direction, change + 1, rr, cc, startR, startC, startDirection, desserts);
					}

					if (result) {
						return true;
					}

					else {
						desserts.remove(map[rr][cc]);
					}
				}

				else if (rr == startR && cc == startC && (Math.abs(startDirection - direction) != 2)) {
					maxDessert = Math.max(maxDessert, desserts.size());
					return true;
				}
			}

			direction += 1;

			if (direction > 3) {
				direction = 0;
			}
		}

		return false;
	}
}
