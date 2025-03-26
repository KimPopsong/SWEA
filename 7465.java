import java.io.*;
import java.util.*;

class Solution {
	static int peopleNumber, edgeNumber;
	static int[] group;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			peopleNumber = Integer.parseInt(st.nextToken());
			edgeNumber = Integer.parseInt(st.nextToken());

			group = new int[peopleNumber + 1];

			for (int i = 1; i <= peopleNumber; i++) {
				group[i] = i;
			}

			for (int i = 0; i < edgeNumber; i++) {
				st = new StringTokenizer(br.readLine());

				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());

				makeGroup(p1, p2);
			}

			Set<Integer> groupNumber = new HashSet<>();
			for (int i = 1; i <= peopleNumber; i++) {
				int ans = findAncestor(i);

				groupNumber.add(ans);
			}

			sb.append("#").append(tc).append(" ").append(groupNumber.size()).append("\n");
		}

		System.out.print(sb);
	}

	static int findAncestor(int p) {
		if (group[p] == p) {
			return p;
		}

		return group[p] = findAncestor(group[p]);
	}

	static void makeGroup(int p1, int p2) {
		if (p1 > p2) {
			int temp = p1;
			p1 = p2;
			p2 = temp;
		}

		int rootP1 = findAncestor(p1);
		int rootP2 = findAncestor(p2);

		group[rootP2] = group[rootP1];
	}
}
