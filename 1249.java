import java.io.*;
import java.util.*;

/**
 * [입력]
 * 
 * 가장 첫 줄은 전체 테스트케이스의 수이다.
 * 
 * 각 테스트 케이스마다 지도의 크기(N x N)가 주어진다. 지도의 크기는 최대 100 x 100이다.
 * 
 * 그 다음줄 부터 지도의 크기만큼 2차원 배열 형태의 지도 정보가 주어진다.
 * 
 * [출력]
 * 
 * 각 테스트 케이스의 답을 순서대로 출력하며, 각 케이스마다 줄의 시작에 “#C”를 출력하여야 한다.
 * 
 * 이때 C는 케이스의 번호이다.
 * 
 * 같은 줄에 빈 칸을 하나 두고, 주어진 입력에서 출발지에서 도착지까지 가는 경로 중에 복구 작업에 드는 시간이 가장 작은 경로의 복구
 * 시간을 출력하시오.
 */
class Solution {
	static int size;
	static int[][] map, isVisit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			size = Integer.parseInt(br.readLine());
			map = new int[size][size];
			isVisit = new int[size][size];

			// isVisit 초기화
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					isVisit[r][c] = Integer.MAX_VALUE;
				}
			}

			for (int r = 0; r < size; r++) {
				String line = br.readLine();

				for (int c = 0; c < size; c++) {
					map[r][c] = line.charAt(c) - '0';
				}
			}

			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.add(new Point(0, 0, 0));

			int[] dr = { -1, 1, 0, 0 };
			int[] dc = { 0, 0, -1, 1 };

			while (!pq.isEmpty()) {
				Point p = pq.remove();

				if (isVisit[p.r][p.c] <= p.time) {
					continue;
				}

				isVisit[p.r][p.c] = p.time;

				for (int d = 0; d < 4; d++) {
					int rr = p.r + dr[d];
					int cc = p.c + dc[d];

					if (0 <= rr && rr < size && 0 <= cc && cc < size) { // 범위 안에 있고
						if (map[rr][cc] + p.time < isVisit[rr][cc]) {
							pq.add(new Point(p.time + map[rr][cc], rr, cc));
						}
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(isVisit[size - 1][size - 1]).append("\n");
		}

		System.out.print(sb);
	}

	static class Point implements Comparable<Point> {
		int time; // 복구에 필요한 시간
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		Point(int time, int r, int c) {
			this.time = time;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point p) {
			return Integer.compare(this.time, p.time);
		}
	}
}
