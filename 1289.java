import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int count = 0;
			int flag = 0; // false = 0, true = 1

			String bit = br.readLine();

			for (int i = 0; i < bit.length(); i++) {
				if (Character.getNumericValue(bit.charAt(i)) != flag) {
					count += 1;

					if (flag == 0) {
						flag = 1;
					}

					else {
						flag = 0;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}

		System.out.println(sb);
	}
}
