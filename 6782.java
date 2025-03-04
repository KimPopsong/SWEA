import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		ArrayList<Long> squareNumbers = new ArrayList<>(); // 제곱수 저장
		for (long i = 1; i <= 1000000; i++) {
			squareNumbers.add(i * i);
		}

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			long number = Long.parseLong(br.readLine());

			int time = 0;

			if (number == 1) {
				time = 1;
			}

			else if (number != 2) {
				while (number != 2) {
					int left = 0;
					int right = squareNumbers.size();
					int mid = 0;

					while (left < right) {
						mid = ((left + right) / 2);

						if (squareNumbers.get(mid) > number) {
							right = mid;
						}

						else if (squareNumbers.get(mid) < number) {
							left = mid + 1;
						}

						else {
							break;
						}
					}

					mid = ((left + right) / 2);

					time += (squareNumbers.get(mid) - number + 1);
					number = (long) Math.sqrt(squareNumbers.get(mid));
				}
			}

			sb.append("#").append(tc).append(" ").append(time).append("\n");
		}

		System.out.println(sb);
	}
}
