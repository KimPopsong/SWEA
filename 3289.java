import java.io.*;
import java.util.*;

class Solution {
	static int number, calcNumber;
	static int[] ancestors;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");

			st = new StringTokenizer(br.readLine());

			number = Integer.parseInt(st.nextToken());
			calcNumber = Integer.parseInt(st.nextToken());

			ancestors = new int[number + 1];

			for (int i = 1; i <= number; i++) {
				ancestors[i] = i;
			}

			for (int i = 0; i < calcNumber; i++) {
				st = new StringTokenizer(br.readLine());

				int command = Integer.parseInt(st.nextToken());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());

				if (command == 0) { // 합집합
					union(num1, num2);
				}

				else { // 두 원소가 같은 집합인지 확인
					if (findAncestor(num1) == findAncestor(num2)) {
						sb.append(1);
					}

					else {
						sb.append(0);
					}
				}
			}

			sb.append("\n");
		}

		System.out.print(sb);
	}

	static int findAncestor(int n) {
		if (ancestors[n] == n) {
			return n;
		}

		return ancestors[n] = findAncestor(ancestors[n]);
	}

	static void union(int n1, int n2) {
		if (n1 > n2) {
			int temp = n1;
			n1 = n2;
			n2 = temp;
		}

		int root1 = findAncestor(n1);
		int root2 = findAncestor(n2);

		ancestors[root2] = root1;
	}
}
