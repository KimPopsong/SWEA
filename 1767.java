import java.io.*;
import java.util.*;

public class Solution {
	static int size;
	static int maxActiveCore, minWire;
	static int[][] map; // 전체 맵
	static ArrayList<Point> cores; // 코어의 위치 정보 저장

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) { // 테스트케이스
			// 초기화
			size = Integer.parseInt(br.readLine());
			maxActiveCore = 0;
			minWire = Integer.MAX_VALUE;
			map = new int[size][size];
			cores = new ArrayList<>();

			// 입력
			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());

					if (map[r][c] == 1) {
						cores.add(new Point(r, c));
					}
				}
			}

			bruteforce(map.clone(), 0, 0, 0);

			sb.append("#").append(tc).append(" ").append(minWire).append("\n");
		}

		System.out.println(sb);
	}

	static void bruteforce(int[][] newMap, int depth, int activeCore, int wireLength) {
		if (depth == cores.size()) { // 모든 코어를 순회했다면
			if (activeCore == maxActiveCore) {
				maxActiveCore = activeCore;

				minWire = Math.min(minWire, wireLength);
			}

			else if (activeCore > maxActiveCore) {
				maxActiveCore = activeCore;

				minWire = wireLength;
			}

			return;
		}

		Point core = cores.get(depth);

		if (core.r == 0 || core.r == size - 1 || core.c == 0 || core.c == size - 1) { // 가장자리에 있어서 활성화 되었다면
			bruteforce(newMap, depth + 1, activeCore + 1, wireLength);
		}

		else { // 전선 보내기 및 비활성화 하기
			for (int d = 0; d < 4; d++) { // 4방향으로 전선 보내기
				int r = core.r;
				int c = core.c;

				boolean flag = true;

				while (flag) {
					r = r + dr[d];
					c = c + dc[d];

					if (0 <= r && r < size && 0 <= c && c < size) { // 범위 안에 있고
						if (newMap[r][c] != 0) { // 가는 길에 코어나 전선이 있다면
							flag = false;

							break;
						}
					}

					else { // 가는 길에 코어나 전선을 만나지 않고 가장자리까지 갔다면
						break;
					}
				}

				if (flag) { // 갈 수 있다면
					int[][] newNewMap = new int[size][size];
					for (int rr = 0; rr < size; rr++) {
						for (int cc = 0; cc < size; cc++) {
							newNewMap[rr][cc] = newMap[rr][cc];
						}
					}

					int wire = 0;

					r = core.r + dr[d];
					c = core.c + dc[d];

					while (0 <= r && r < size && 0 <= c && c < size) { // 전선 색칠하기
						newNewMap[r][c] = 2;
						wire += 1;

						r = r + dr[d];
						c = c + dc[d];
					}

					bruteforce(newNewMap, depth + 1, activeCore + 1, wireLength + wire);
				}
			}

			// 비활성화하고 넘어가기
			bruteforce(newMap, depth + 1, activeCore, wireLength);
		}
	}

	static void initialize() {

	}

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
