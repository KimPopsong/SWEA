import java.io.*;
import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int commandNumer = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙

			sb.append("#").append(tc).append(" ");

			for (int command = 0; command < commandNumer; command++) {
				st = new StringTokenizer(br.readLine());

				if (Integer.parseInt(st.nextToken()) == 1) {
					int n = Integer.parseInt(st.nextToken());

					pq.add(n);
				}

				else {
					if (pq.isEmpty()) {
						sb.append(-1).append(" ");
					}

					else {
						sb.append(pq.remove()).append(" ");
					}
				}
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}
}
