import java.io.*;
import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int lastNBit, number;
			boolean flag = false;
			String answer = null;

			String[] temp = br.readLine().split(" ");

			lastNBit = Integer.parseInt(temp[0]);
			number = Integer.parseInt(temp[1]);

			int nod = (int) (Math.pow(2, lastNBit) - 1);

			if ((nod & number) == nod) {
				flag = true;
			}

			if (flag) {
				answer = "ON";
			}

			else {
				answer = "OFF";
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}
