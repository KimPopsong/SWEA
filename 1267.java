import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int nodeNumber, edgeNumber; // 노드의 개수, 간선의 개수
			int[] degree; // 노드의 차수
			ArrayList<Integer>[] edges; // 노드의 부모 노드

			st = new StringTokenizer(br.readLine());

			nodeNumber = Integer.parseInt(st.nextToken());
			edgeNumber = Integer.parseInt(st.nextToken());

			degree = new int[nodeNumber + 1];
			edges = new ArrayList[nodeNumber + 1];

			for (int i = 1; i <= nodeNumber; i++) {
				edges[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < edgeNumber; i++) {
				int nodeStart = Integer.parseInt(st.nextToken());
				int nodeEnd = Integer.parseInt(st.nextToken());

				degree[nodeStart] += 1;
				edges[nodeEnd].add(nodeStart);
			}

			ArrayDeque<Integer> topology = new ArrayDeque<>();

			for (int node = 1; node <= nodeNumber; node++) {
				if (degree[node] == 0) {
					topology.add(node);
				}
			}

			ArrayDeque<Integer> answer = new ArrayDeque<>();

			while (!topology.isEmpty()) {
				int number = topology.removeFirst();

				for (int i : edges[number]) {
					degree[i] -= 1;

					if (degree[i] == 0) {
						topology.addLast(i);
					}
				}

				answer.addFirst(number);
			}

			sb.append("#").append(tc).append(" ");
			while (!answer.isEmpty()) {
				sb.append(answer.removeFirst()).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
