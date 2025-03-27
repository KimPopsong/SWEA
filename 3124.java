import java.io.*;
import java.util.*;

class Solution {
	static int nodeNumber, edgeNumber;
	static int[] parent;
	static PriorityQueue<Edge> edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			nodeNumber = Integer.parseInt(st.nextToken());
			edgeNumber = Integer.parseInt(st.nextToken());

			parent = new int[nodeNumber + 1];
			edges = new PriorityQueue<>();

			for (int i = 0; i < edgeNumber; i++) {
				st = new StringTokenizer(br.readLine());

				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				edges.add(new Edge(node1, node2, weight));
			}

			for (int i = 1; i <= nodeNumber; i++) {
				parent[i] = i;
			}

			int count = 0;
			long sumWeight = 0;

			while (!edges.isEmpty()) {
				if (count + 1 == nodeNumber) {
					break;
				}

				Edge e = edges.remove();

				if (union(e.node1, e.node2)) {
					count += 1;

					sumWeight += e.weight;
				}
			}

			sb.append("#").append(tc).append(" ").append(sumWeight).append("\n");
		}

		System.out.print(sb);
	}

	static int findParent(int n) {
		if (parent[n] == n) {
			return n;
		}

		return parent[n] = findParent(parent[n]);
	}

	static boolean union(int node1, int node2) {
		int root1 = findParent(node1);
		int root2 = findParent(node2);

		if (root1 == root2) {
			return false;
		}

		else {
			parent[root2] = root1;

			return true;
		}
	}

	static class Edge implements Comparable<Edge> {
		int node1, node2;
		int weight;

		public Edge(int node1, int node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}

		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
}
