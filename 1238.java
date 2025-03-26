import java.io.*;
import java.util.*;

class Solution {
	static int startNumber, edgeNumber;
	static Map<Integer, Set<Integer>> edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());

			edgeNumber = Integer.parseInt(st.nextToken());
			startNumber = Integer.parseInt(st.nextToken());

			edges = new HashMap<>();

			for (int i = 1; i <= 100; i++) {
				edges.put(i, new HashSet<>());
			}

			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) { // 간선 입력
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				edges.get(from).add(to);
			}

			// 입력 종료

			int maxNumber = 0;
			ArrayDeque<Integer> bfs = new ArrayDeque<>();
			boolean[] isVisit = new boolean[101];

			bfs.add(startNumber);
			isVisit[startNumber] = true;

			while (!bfs.isEmpty()) {
				maxNumber = 0;
				ArrayDeque<Integer> tempBfs = new ArrayDeque<>(bfs);
				bfs.clear();

				while (!tempBfs.isEmpty()) {
					int node = tempBfs.removeFirst();
					maxNumber = Math.max(maxNumber, node);

					Set<Integer> to = edges.get(node);

					for (int i : to) {
						if (isVisit[i] == true) {
							continue;
						}

						bfs.add(i);
						isVisit[i] = true;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(maxNumber).append("\n");
		}

		System.out.print(sb);
	}
}
