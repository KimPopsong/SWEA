import java.io.*;
import java.util.*;

class Solution {
	static int islandNumber; // 섬의 개수
	static double tariff; // 세율
	static int[] parent;
	static Point[] islands;
	static PriorityQueue<Tunnel> edges;
	static ArrayList<Tunnel> tunnels;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			islandNumber = Integer.parseInt(br.readLine());
			parent = new int[islandNumber + 1];
			islands = new Point[islandNumber + 1]; // 1번부터 시작
			edges = new PriorityQueue<>();
			tunnels = new ArrayList<>();

			st = new StringTokenizer(br.readLine()); // X좌표 입력

			for (int i = 1; i <= islandNumber; i++) {
				islands[i] = new Point();

				islands[i].r = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= islandNumber; i++) {
				islands[i].c = Integer.parseInt(st.nextToken());
			}

			tariff = Double.parseDouble(br.readLine());

			for (int i = 1; i <= islandNumber; i++) {
				parent[i] = i;
			}

			// 입력부 종료

			for (int i = 1; i < islandNumber; i++) {
				for (int j = i + 1; j <= islandNumber; j++) {
					edges.add(new Tunnel(i, j));
				}
			}

			while (!edges.isEmpty()) {
				if (tunnels.size() == islandNumber - 1) {
					break;
				}

				Tunnel tunnel = edges.remove();

				int is1 = tunnel.island1;
				int is2 = tunnel.island2;

				if (union(is1, is2)) {
					tunnels.add(tunnel);
				}
			}

			double sum = 0;

			for (Tunnel t : tunnels) {
				sum += tariff * t.distance;
			}

			sb.append("#").append(tc).append(" ").append(Math.round(sum)).append("\n");
		}

		System.out.print(sb);
	}

	static int findParent(int n) {
		if (parent[n] == n) {
			return n;
		}

		return parent[n] = findParent(parent[n]);
	}

	static boolean union(int is1, int is2) {
		int root1 = findParent(is1);
		int root2 = findParent(is2);

		if (root1 == root2) {
			return false; // 같은 부모임
		}

		else {
			parent[root2] = root1;

			return true;
		}
	}

	static class Tunnel implements Comparable<Tunnel> {
		long distance; // 거리
		int island1, island2; // 섬의 인덱스

		Tunnel(int i, int j) {
			Point is1 = islands[i];
			Point is2 = islands[j];

			distance = (long) Math.pow(is1.r - is2.r, 2) + (long) Math.pow(is1.c - is2.c, 2); // 거리

			island1 = i;
			island2 = j;
		}

		@Override
		public int compareTo(Tunnel t) {
			if (this.distance < t.distance) {
				return -1;
			}

			else if (this.distance > t.distance) {
				return 1;
			}

			else {
				return 0;
			}
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
