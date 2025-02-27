import java.io.*;
import java.util.*;

class Solution {
	static int minPrice;
	static int[] ticket, month;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			minPrice = Integer.MAX_VALUE;
			ticket = new int[4]; // [ 1일, 한 달, 연속 세 달, 일년 ]
			month = new int[13]; // 월 별 사용 횟수

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}

			bruteForce(1, month, 0);
			minPrice = Math.min(minPrice, ticket[3]); // 1년 이용권과 가격 비교

			sb.append("#").append(tc).append(" ").append(minPrice).append("\n");
		}

		System.out.print(sb);
	}

	static void bruteForce(int m, int[] monthCopy, int sumPrice) {
		if (m == 13) { // 12월까지 모두 탐색하였다면
			minPrice = Math.min(minPrice, sumPrice);

			return;
		}

		if (month[m] == 0) {
			bruteForce(m + 1, monthCopy, sumPrice);
		}

		else {
			int[] monthTemp = monthCopy.clone(); // 1일 또는 1달 이용권 사용
			int p = 0;
			if (monthTemp[m] * ticket[0] < ticket[1]) { // 1일 이용권이 더 싸다면
				p = sumPrice + monthTemp[m] * ticket[0]; // 1일 이용권 사용
			}

			else { // 1달 이용권이 더 싸다면
				p = sumPrice + ticket[1]; // 1달 이용권 사용
			}
			monthTemp[m] = 0;
			bruteForce(m + 1, monthTemp, p);

			monthTemp = monthCopy.clone(); // 3달 이용권 사용
			p = sumPrice + ticket[2];
			for (int i = m; i < m + 3 && i <= 12; i++) {
				monthTemp[i] = 0;
			}
			bruteForce(m + 1, monthTemp, p);
		}
	}
}
