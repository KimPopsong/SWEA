import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			final int NODE = 20171109;
			int line; // 경근이가 추가로 쓸 숫자 세트
			int answer = 0; // 출력할 답안

			PriorityQueue<Integer> minH = new PriorityQueue<>(); // 최소 힙
			PriorityQueue<Integer> maxH = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙

			st = new StringTokenizer(br.readLine());

			line = Integer.parseInt(st.nextToken());
			int midNumber = Integer.parseInt(st.nextToken()); // 가운데 숫자 저장

			maxH.add(midNumber);

			for (int i = 0; i < line; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < 2; j++) { // 숫자 두 개 입력
					int num = Integer.parseInt(st.nextToken());

					if (num > midNumber) {
						minH.add(num);
					}

					else {
						maxH.add(num);
					}
				}

				while (maxH.size() - 1 != minH.size()) { // maxH이 minH보다 항상 원소가 한 개 더 많도록 조정
					if (minH.size() >= maxH.size()) {
						maxH.add(minH.remove());
					}

					else {
						minH.add(maxH.remove());
					}
				}

				midNumber = maxH.peek();

				answer = (answer + midNumber) % NODE;
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(sb);
	}
}
