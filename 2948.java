import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int answer = 0;
			int str1Length, str2Length;
			Set<String> str1 = new HashSet<>();
			Set<String> str2 = new HashSet<>();

			st = new StringTokenizer(br.readLine());

			str1Length = Integer.parseInt(st.nextToken());
			str2Length = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				str1.add(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				str2.add(st.nextToken());
			}

			for (String s : str1) {
				if (str2.contains(s)) {
					answer += 1;
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}
}
