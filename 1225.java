import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");

			int tcNumber = Integer.parseInt(br.readLine());
			List<Integer> numbers = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}

			int node = 0;
			int minus = -1;

			while (true) {
				numbers.set(node, numbers.get(node) + minus);

				if (numbers.get(node) <= 0) {
					numbers.set(node, 0);

					break;
				}

				else {
					node += 1;

					if (node >= 8) {
						node = 0;
					}

					minus -= 1;

					if (minus < -5) {
						minus = -1;
					}
				}
			}

			for (int i = 0; i <= node; i++) {
				numbers.add(numbers.remove(0));
			}

			for (int n : numbers) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
