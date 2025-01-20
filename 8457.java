import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");

		int testCase = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= testCase; tc++) {
			int hourglassNumber, exactTime, errorTime; // 모래시계의 개수, 정확한 시간, 오차
			int canBuy = 0;
			int[] hourglasses;

			st = new StringTokenizer(br.readLine(), " ");
			hourglassNumber = Integer.parseInt(st.nextToken());
			exactTime = Integer.parseInt(st.nextToken());
			errorTime = Integer.parseInt(st.nextToken());

			hourglasses = new int[hourglassNumber];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < hourglassNumber; i++) {
				hourglasses[i] = Integer.parseInt(st.nextToken());
			}

			for (int hg : hourglasses) {
				int he = exactTime + errorTime;

				if (he % hg <= errorTime * 2) {
					canBuy += 1;
				}
			}

			sb.append("#").append(tc).append(" ").append(canBuy).append("\n");
		}

		System.out.println(sb);
	}
}
