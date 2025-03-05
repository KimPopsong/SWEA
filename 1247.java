import java.io.*;
import java.util.*;

public class Solution {
	static int customerNumber, minDistance; // 손님의 수, 최단거리
	static boolean[] isVisit;
	static Point company, house; // 회사의 좌표, 집의 좌표
	static Point[] customers; // 손님의 좌표

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			customerNumber = Integer.parseInt(br.readLine());
			minDistance = Integer.MAX_VALUE;

			isVisit = new boolean[customerNumber];
			customers = new Point[customerNumber];

			st = new StringTokenizer(br.readLine());

			company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			for (int i = 0; i < customerNumber; i++) { // 손님의 좌표 입력
				customers[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			bruteForce(0, 0, company.r, company.c);

			sb.append("#").append(tc).append(" ").append(minDistance).append("\n");
		}

		System.out.print(sb);
	}

	static void bruteForce(int depth, int distance, int r, int c) { // 순열 계산
		if (depth == customerNumber) { // 모든 집을 방문했다면
			distance += Math.abs(r - house.r) + Math.abs(c - house.c); // 집까지 이동 거리 계산

			minDistance = Math.min(minDistance, distance);

			return;
		}

		for (int i = 0; i < customerNumber; i++) {
			if (isVisit[i]) {
				continue;
			}

			isVisit[i] = true;
			bruteForce(depth + 1, distance + Math.abs(r - customers[i].r) + Math.abs(c - customers[i].c),
					customers[i].r, customers[i].c);
			isVisit[i] = false;
		}
	}

	static class Point {
		int r, c;

		Point() {
		}

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
