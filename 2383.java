import java.io.*;
import java.util.*;

/**
 * 1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 3초
 * 
 * 2. 방의 한 변의 길이 N은 4 이상 10 이하의 정수이다. (4 ≤ N ≤ 10)
 * 
 * 3. 사람의 수는 1 이상 10 이하의 정수이다. (1 ≤ 사람의 수 ≤ 10)
 * 
 * 4. 계단의 입구는 반드시 2개이며, 서로 위치가 겹치지 않는다.
 * 
 * 5. 계단의 길이는 2 이상 10 이하의 정수이다. (2 ≤ 계단의 길이 ≤ 10)
 * 
 * 6. 초기에 입력으로 주어지는 사람의 위치와 계단 입구의 위치는 서로 겹치지 않는다.
 */
class Solution {
	static int size, minTime;
	static ArrayList<People> people; // 사람들의 좌표
	static Stair[] stairs; // 계단의 좌표

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			minTime = Integer.MAX_VALUE;
			size = Integer.parseInt(br.readLine());

			people = new ArrayList<>();
			stairs = new Stair[2];

			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					int n = Integer.parseInt(st.nextToken());

					if (n == 1) {
						people.add(new People(r, c));
					}

					else if (n > 1) { // 계단이라면
						if (stairs[0] == null) {
							stairs[0] = new Stair(r, c, n);
						}

						else {
							stairs[1] = new Stair(r, c, n);
						}
					}
				}
			}

			// 입력부 종료

			chooseStair(0);

			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
		}

		System.out.print(sb);
	}

	static void simulate() {
		PriorityQueue<Integer> stair1 = new PriorityQueue<>();
		PriorityQueue<Integer> stair2 = new PriorityQueue<>();
		ArrayDeque<Integer> s1 = new ArrayDeque<>();
		ArrayDeque<Integer> s2 = new ArrayDeque<>();

		int stair1R = stairs[0].r;
		int stair1C = stairs[0].c;
		int stair2R = stairs[1].r;
		int stair2C = stairs[1].c;

		for (People p : people) { // PQ에 저장
			if (p.destination == 0) { // 1번 계단
				stair1.add(Math.abs(stair1R - p.r) + Math.abs(stair1C - p.c));
			}

			else { // 2번 계단
				stair2.add(Math.abs(stair2R - p.r) + Math.abs(stair2C - p.c));
			}
		}

		int time1 = 0;
		while (!stair1.isEmpty() || !s1.isEmpty()) {
			time1 += 1;

			while (!s1.isEmpty()) {
				if (s1.peekFirst() + stairs[0].depth + 1 <= time1) {
					s1.removeFirst();
				}

				else {
					break;
				}
			}

			while (s1.size() < 3) {
				if (!stair1.isEmpty()) {
					if (stair1.peek() < time1) {
						stair1.remove();

						s1.add(time1 - 1);
					}

					else if (stair1.peek() == time1) {
						stair1.remove();

						s1.add(time1);
					}

					else {
						break;
					}
				}

				else {
					break;
				}
			}
		}

		int time2 = 0;
		while (!stair2.isEmpty() || !s2.isEmpty()) {
			time2 += 1;

			while (!s2.isEmpty()) {
				if (s2.peekFirst() + stairs[1].depth + 1 <= time2) {
					s2.removeFirst();
				}

				else {
					break;
				}
			}

			while (s2.size() < 3) {
				if (!stair2.isEmpty()) {
					if (stair2.peek() < time2) {
						stair2.remove();

						s2.add(time2 - 1);
					}

					else if (stair2.peek() == time2) {
						stair2.remove();

						s2.add(time2);
					}

					else {
						break;
					}
				}

				else {
					break;
				}
			}
		}

		minTime = Math.min(minTime, Math.max(time1, time2));
	}

	static void chooseStair(int depth) { // 어느 계단으로 갈지 선택
		if (depth == people.size()) { // 모든 사람들이 자신의 계단을 선택했다면
			simulate(); // 시간 계산

			return;
		}

		for (int i = 0; i < 2; i++) {
			people.get(depth).destination = i;

			chooseStair(depth + 1);
		}
	}

	static class Stair {
		int r, c, depth; // 좌표와 계단의 길이

		Stair(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
	}

	static class People {
		int r, c;
		int destination; // 사용할 계단의 인덱스

		People(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
