import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Solution {
	static int friendNumber, edgeNumber;
	static boolean[][] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			friendNumber = Integer.parseInt(st.nextToken());
			edgeNumber = Integer.parseInt(st.nextToken());

			edges = new boolean[friendNumber + 1][friendNumber + 1];

			for (int i = 0; i < edgeNumber; i++) {
				st = new StringTokenizer(br.readLine());

				int f1 = Integer.parseInt(st.nextToken());
				int f2 = Integer.parseInt(st.nextToken());

				edges[f1][f2] = true;
				edges[f2][f1] = true;
			}

			ArrayDeque<Integer> friends = new ArrayDeque<>();
			boolean[] isVisit = new boolean[friendNumber + 1];

			isVisit[1] = true;

			for (int i = 1; i <= friendNumber; i++) {
				if (edges[1][i]) {
					friends.add(i);
					isVisit[i] = true;
				}
			}

			while (!friends.isEmpty()) {
				int f = friends.remove();

				for (int i = 1; i <= friendNumber; i++) {
					if (edges[f][i]) {
						isVisit[i] = true;
					}
				}
			}

			int count = -1;

			for (boolean v : isVisit) {
				if (v) {
					count += 1;
				}
			}

			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}

		System.out.print(sb);
	}
}
