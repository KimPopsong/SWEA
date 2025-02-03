import java.io.*;
import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int encryptNumber = Integer.parseInt(br.readLine()); // 원본 암호문의 개수
			int commandNumber; // 명령어의 개수
			LinkedList<String> encryptedString = new LinkedList<>(); // 원본 암호문

			st = new StringTokenizer(br.readLine(), " "); // 원본 암호문 입력
			while (st.hasMoreTokens()) {
				encryptedString.add(st.nextToken());
			}

			commandNumber = Integer.parseInt(br.readLine()); // 명령어의 개수 입력

			st = new StringTokenizer(br.readLine(), " "); // 명령어 입력
			while (st.hasMoreTokens()) {
				String c = st.nextToken();

				if (c.equals("I")) { // 삽입
					int nod = Integer.parseInt(st.nextToken());
					int tokenNumber = Integer.parseInt(st.nextToken());

					LinkedList<String> addPart = new LinkedList<>();

					for (int i = 0; i < tokenNumber; i++) {
						addPart.add(st.nextToken());
					}

					encryptedString.addAll(nod, addPart);
				}

				else if (c.equals("D")) { // 삭제
					int nod = Integer.parseInt(st.nextToken());
					int tokenNumber = Integer.parseInt(st.nextToken());

					for (int i = 0; i < tokenNumber; i++) {
						encryptedString.remove(nod);
					}
				}

				else { // 추가
					int tokenNumber = Integer.parseInt(st.nextToken());

					for (int i = 0; i < tokenNumber; i++) {
						encryptedString.add(st.nextToken());
					}
				}
			}

			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(encryptedString.removeFirst()).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}
