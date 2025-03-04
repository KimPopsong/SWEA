import java.io.*;
import java.util.*;

public class Solution {
	static int size;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			size = Integer.parseInt(st.nextToken());
			String command = st.nextToken();

			map = new int[size][size];

			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			if (command.equals("up")) {
				moveUp();
			}

			else if (command.equals("down")) {
				moveDown();
			}

			else if (command.equals("left")) {
				moveLeft();
			}

			else {
				moveRight();
			}

			sb.append("#").append(tc).append("\n"); // 답안 출력
			for (

					int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					sb.append(map[r][c]).append(" ");
				}
				sb.append("\n");
			}
		}

		System.out.print(sb);
	}

	static void moveUp() {
		for (int c = 0; c < size; c++) {
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			boolean flag = true; // 더할 수 있는 상태

			for (int r = 0; r < size; r++) {
				if (map[r][c] == 0) {
					continue;
				}

				if (flag) {
					if (queue.isEmpty()) {
						queue.addLast(map[r][c]);
					}

					else if (map[r][c] == queue.peekLast()) {
						queue.addLast(map[r][c] + queue.removeLast());

						flag = false;
					}

					else {
						queue.addLast(map[r][c]);
					}
				}

				else {
					queue.addLast(map[r][c]);
					flag = true;
				}
			}

			for (int r = 0; r < size; r++) {
				map[r][c] = 0;
			}

			int r = 0;
			while (!queue.isEmpty()) {
				map[r][c] = queue.removeFirst();
				r++;
			}
		}
	}

	static void moveDown() {
		for (int c = 0; c < size; c++) {
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			boolean flag = true; // 더할 수 있는 상태

			for (int r = size - 1; r >= 0; r--) {
				if (map[r][c] == 0) {
					continue;
				}

				if (flag) {
					if (queue.isEmpty()) {
						queue.addLast(map[r][c]);
					}

					else if (map[r][c] == queue.peekLast()) {
						queue.addLast(map[r][c] + queue.removeLast());

						flag = false;
					}

					else {
						queue.addLast(map[r][c]);
					}
				}

				else {
					queue.addLast(map[r][c]);
					flag = true;
				}
			}

			for (int r = 0; r < size; r++) {
				map[r][c] = 0;
			}

			int r = size - 1;
			while (!queue.isEmpty()) {
				map[r][c] = queue.removeFirst();
				r--;
			}
		}
	}

	static void moveLeft() {
		for (int r = 0; r < size; r++) {
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			boolean flag = true; // 더할 수 있는 상태

			for (int c = 0; c < size; c++) {
				if (map[r][c] == 0) {
					continue;
				}

				if (flag) {
					if (queue.isEmpty()) {
						queue.addLast(map[r][c]);
					}

					else if (map[r][c] == queue.peekLast()) {
						queue.addLast(map[r][c] + queue.removeLast());

						flag = false;
					}

					else {
						queue.addLast(map[r][c]);
					}
				}

				else {
					queue.addLast(map[r][c]);
					flag = true;
				}
			}

			for (int c = 0; c < size; c++) {
				map[r][c] = 0;
			}

			int c = 0;
			while (!queue.isEmpty()) {
				map[r][c] = queue.removeFirst();
				c++;
			}
		}
	}

	static void moveRight() {
		for (int r = 0; r < size; r++) {
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			boolean flag = true; // 더할 수 있는 상태

			for (int c = size - 1; c >= 0; c--) {
				if (map[r][c] == 0) {
					continue;
				}

				if (flag) {
					if (queue.isEmpty()) {
						queue.addLast(map[r][c]);
					}

					else if (map[r][c] == queue.peekLast()) {
						queue.addLast(map[r][c] + queue.removeLast());

						flag = false;
					}

					else {
						queue.addLast(map[r][c]);
					}
				}

				else {
					queue.addLast(map[r][c]);
					flag = true;
				}
			}

			for (int c = 0; c < size; c++) {
				map[r][c] = 0;
			}

			int c = size - 1;
			while (!queue.isEmpty()) {
				map[r][c] = queue.removeFirst();
				c--;
			}
		}
	}
}
