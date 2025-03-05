import java.io.*;
import java.util.*;

public class Solution {
	static final int SIZE = 10;
	static int moveTime, bcNumber;
	static int sumPerson1, sumPerson2;
	static int[][][] map;
	static Point person1, person2;
	static ArrayList<BC> bc;
	static ArrayDeque<Integer> person1move, person2move;

	static int[] dr = { 0, 1, 0, -1 }, dc = { -1, 0, 1, 0 }; // 상우하좌 순

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			moveTime = Integer.parseInt(st.nextToken());
			bcNumber = Integer.parseInt(st.nextToken());

			sumPerson1 = 0;
			sumPerson2 = 0;

			map = new int[2][SIZE][SIZE];
			for (int p = 0; p < 2; p++) {
				for (int r = 0; r < SIZE; r++) {
					for (int c = 0; c < SIZE; c++) {
						map[p][r][c] = bcNumber;
					}
				}
			}

			person1 = new Point(0, 0);
			person2 = new Point(9, 9);

			person1move = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine()); // 사용자1 입력
			while (st.hasMoreTokens()) {
				person1move.addLast(Integer.parseInt(st.nextToken()));
			}

			person2move = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine()); // 사용자2 입력
			while (st.hasMoreTokens()) {
				person2move.addLast(Integer.parseInt(st.nextToken()));
			}

			bc = new ArrayList<>();
			for (int i = 0; i < bcNumber; i++) { // bc 입력
				st = new StringTokenizer(br.readLine());

				bc.add(new BC(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			bc.add(new BC(-1, -1, 0, 0));

			Collections.sort(bc, new Comparator<BC>() { // BC를 power순으로 내림차순 정렬
				@Override
				public int compare(BC b1, BC b2) {
					return b2.power - b1.power;
				}
			});

			// 입력 끝

			for (int i = 0; i < bcNumber; i++) { // power가 큰 순으로 map에 표시
				BC b = bc.get(i);

				ArrayDeque<Point> bfs = new ArrayDeque<>();
				boolean[][] isVisit = new boolean[SIZE][SIZE];

				bfs.add(new Point(b.r, b.c));
				isVisit[b.r][b.c] = true;

				if (map[0][b.r][b.c] == bcNumber) {
					map[0][b.r][b.c] = i;
				}

				else if (map[1][b.r][b.c] == bcNumber) {
					map[1][b.r][b.c] = i;
				}

				for (int time = 0; time < b.range; time++) {
					ArrayDeque<Point> tempBfs = new ArrayDeque<>(bfs);
					bfs.clear();

					while (!tempBfs.isEmpty()) {
						Point p = tempBfs.remove();

						for (int d = 0; d < 4; d++) {
							int rr = p.r + dr[d];
							int cc = p.c + dc[d];

							if (0 <= rr && rr < SIZE && 0 <= cc && cc < SIZE) {
								if (isVisit[rr][cc] == false) {
									bfs.add(new Point(rr, cc));
									isVisit[rr][cc] = true;

									if (map[0][rr][cc] == bcNumber) {
										map[0][rr][cc] = i;
									}

									else if (map[1][rr][cc] == bcNumber) {
										map[1][rr][cc] = i;
									}
								}
							}
						}
					}
				}
			}

			if (map[0][person1.r][person1.c] != bcNumber && map[0][person2.r][person2.c] != bcNumber
					&& map[0][person1.r][person1.c] == map[0][person2.r][person2.c]) {
				sumPerson1 += Math.max(
						bc.get(map[0][person1.r][person1.c]).power + bc.get(map[1][person2.r][person2.c]).power,
						bc.get(map[1][person1.r][person1.c]).power + bc.get(map[0][person2.r][person2.c]).power);
			}

			else {
				sumPerson1 += bc.get(map[0][person1.r][person1.c]).power;
				sumPerson2 += bc.get(map[0][person2.r][person2.c]).power;
			}

			while (!person1move.isEmpty()) { // 사람 움직이기
				int d1 = person1move.removeFirst();
				int d2 = person2move.removeFirst();

				if (d1 != 0) {
					person1.r += dr[d1 - 1];
					person1.c += dc[d1 - 1];
				}

				if (d2 != 0) {
					person2.r += dr[d2 - 1];
					person2.c += dc[d2 - 1];
				}

				if (map[0][person1.r][person1.c] != bcNumber && map[0][person2.r][person2.c] != bcNumber
						&& map[0][person1.r][person1.c] == map[0][person2.r][person2.c]) {
					sumPerson1 += Math.max(
							bc.get(map[0][person1.r][person1.c]).power + bc.get(map[1][person2.r][person2.c]).power,
							bc.get(map[1][person1.r][person1.c]).power + bc.get(map[0][person2.r][person2.c]).power);
				}

				else {
					sumPerson1 += bc.get(map[0][person1.r][person1.c]).power;
					sumPerson2 += bc.get(map[0][person2.r][person2.c]).power;
				}
			}

			sb.append("#").append(tc).append(" ").append(sumPerson1 + sumPerson2).append("\n");
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

	static class BC {
		int r, c; // 좌표
		int range, power; // 충전 범위, 성능

		BC(int r, int c, int range, int power) {
			this.r = r;
			this.c = c;
			this.range = range;
			this.power = power;
		}
	}
}
