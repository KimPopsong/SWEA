import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int stringNumber = Integer.parseInt(br.readLine());

			Set<String> names = new TreeSet<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if (o1.length() == o2.length()) {
						for (int i = 0; i < o1.length(); i++) {
							if (o1.charAt(i) != o2.charAt(i)) {
								return o1.charAt(i) - o2.charAt(i);
							}
						}

						return 0;
					}

					else {
						return o1.length() - o2.length();
					}
				}
			});

			for (int i = 0; i < stringNumber; i++) {
				names.add(br.readLine());
			}

			sb.append("#").append(tc).append("\n");
			for (String s : names) {
				sb.append(s).append("\n");
			}
		}

		System.out.print(sb);
	}
}
