import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Solution {
	static int peopleNumber;
	static int[][] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			peopleNumber = Integer.parseInt(st.nextToken());
			edges = new int[peopleNumber][peopleNumber];

			for (int r = 0; r < peopleNumber; r++) {
				for (int c = 0; c < peopleNumber; c++) {
					edges[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 입력부 종료

			for (int start = 0; start < peopleNumber; start++) {
				int count = 0;
				PriorityQueue<Point> pq = new PriorityQueue<>();
				boolean[] isVisit = new boolean[peopleNumber + 1];

				pq.add(new Point(start, 0));

				while (!pq.isEmpty()) {
					if (count == peopleNumber) {
						break;
					}

					Point point = pq.remove();

					int node = point.destination;
					int distance = point.distance;

					if (isVisit[node]) {
						continue;
					}

					count += 1;
					isVisit[node] = true;
					edges[start][node] = distance;

					for (int c = 0; c < peopleNumber; c++) {
						if (edges[node][c] != 0) {
							if (isVisit[c]) {
								continue;
							}

							pq.add(new Point(c, distance + edges[node][c]));
						}
					}
				}
			}

			int minSum = Integer.MAX_VALUE;

			for (int r = 0; r < peopleNumber; r++) {
				int s = 0;

				for (int i : edges[r]) {
					s += i;
				}

				minSum = Math.min(minSum, s);
			}

			sb.append("#").append(tc).append(" ").append(minSum).append("\n");
		}

		System.out.print(sb);
	}

	static class Point implements Comparable<Point> {
		int destination;
		int distance;

		Point(int destination, int distance) {
			this.destination = destination;
			this.distance = distance;
		}

		@Override
		public int compareTo(Point p) {
			return Integer.compare(this.distance, p.distance);
		}
	}
}
