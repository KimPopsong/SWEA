import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			boolean flag = true;
			int length = Integer.parseInt(br.readLine());
			ArrayDeque<Character> stack = new ArrayDeque<>();

			char[] brackets = br.readLine().toCharArray();
			
			for (char c : brackets) {
				switch (c) {
				case '(': // 넣기
				case '{':
				case '[':
				case '<':
					stack.add(c);

					break;

				default: // 빼기
					if (stack.isEmpty()) {
						flag = false;

						break;
					}

					char compare = stack.removeLast();

					if (c == ')' && compare == '(') {

					}

					else if (c == '}' && compare == '{') {

					}

					else if (c == ']' && compare == '[') {

					}

					else if (c == '>' && compare == '<') {

					}

					else {
						flag = false;

						break;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ");
			if(flag) {
				sb.append(1);
			}
			
			else {
				sb.append(0);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
