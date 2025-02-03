import java.io.*;
import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			int listNumber, commandNumber, printIndex; // 수열의 길이, 추가 횟수, 출력할 인덱스 번호
			LinkedList<Integer> numberList = new LinkedList<>(); // 수열

			st = new StringTokenizer(br.readLine(), " "); // 수열의 길이, 추가 횟수, 출력할 인덱스 번호 입력
			listNumber = Integer.parseInt(st.nextToken());
			commandNumber = Integer.parseInt(st.nextToken());
			printIndex = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine(), " "); // 수열 입력
			while (st.hasMoreTokens()) {
				numberList.add(Integer.parseInt(st.nextToken()));
			}

			for (int i = 0; i < commandNumber; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				String command = st.nextToken();

				if (command.equals("I")) { // 추가
					int index = Integer.parseInt(st.nextToken());
					int numberToAdd = Integer.parseInt(st.nextToken());

					numberList.add(index, numberToAdd);
				}

				else if (command.equals("D")) {
					int index = Integer.parseInt(st.nextToken());

					numberList.remove(index);
				}

				else {
					int index = Integer.parseInt(st.nextToken());
					int numberToChange = Integer.parseInt(st.nextToken());

					numberList.set(index, numberToChange);
				}
			}

			int answer = -1;

			if (numberList.size() >= printIndex) {
				answer = numberList.get(printIndex);
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}
