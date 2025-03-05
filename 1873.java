import java.io.*;
import java.util.*;

public class Solution {
	static int rowSize, colSize, commandNumber;
	static int tankRow, tankCol, tankDirection;
	static char[] commands;
	static char[][] map;

	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());

			map = new char[rowSize][colSize];

			for (int r = 0; r < rowSize; r++) { // map 입력
				char[] temp = br.readLine().toCharArray();

				for (int c = 0; c < colSize; c++) {
					map[r][c] = temp[c];
				}
			}

			commandNumber = Integer.parseInt(br.readLine());
			commands = br.readLine().toCharArray();

			for (int r = 0; r < rowSize; r++) { // 탱크의 상태 입력
				for (int c = 0; c < colSize; c++) {
					if (map[r][c] == '^') {
						tankRow = r;
						tankCol = c;
						tankDirection = 0;
						map[r][c] = '.';

						break;
					}

					else if (map[r][c] == 'v') {
						tankRow = r;
						tankCol = c;
						tankDirection = 1;
						map[r][c] = '.';

						break;
					}

					else if (map[r][c] == '<') {
						tankRow = r;
						tankCol = c;
						tankDirection = 2;
						map[r][c] = '.';

						break;
					}

					else if (map[r][c] == '>') {
						tankRow = r;
						tankCol = c;
						tankDirection = 3;
						map[r][c] = '.';

						break;
					}
				}
			}

			// 입력 끝

			// 시뮬레이션 시작

			for (char command : commands) {
				if (command == 'U') {
					tankDirection = 0;
				}

				else if (command == 'D') {
					tankDirection = 1;
				}

				else if (command == 'L') {
					tankDirection = 2;
				}

				else if (command == 'R') {
					tankDirection = 3;
				}

				else { // 포탄 발사
					int rr = tankRow + dr[tankDirection];
					int cc = tankCol + dc[tankDirection];

					while (true) {
						if (0 <= rr && rr < rowSize && 0 <= cc && cc < colSize) {
							if (map[rr][cc] == '*') { // 벽돌로 만든 벽이라면
								map[rr][cc] = '.'; // 평지로 만들기

								break;
							}

							else if (map[rr][cc] == '#') { // 강철로 만든 벽이라면
								break; // 아무 일도 일어나지 않음
							}

							else { // 포탄 1칸 전진
								rr = rr + dr[tankDirection];
								cc = cc + dc[tankDirection];
							}
						}

						else { // 범위를 벗어날시
							break; // 종료
						}
					}

					continue;
				}

				int rr = tankRow + dr[tankDirection];
				int cc = tankCol + dc[tankDirection];

				if (0 <= rr && rr < rowSize && 0 <= cc && cc < colSize) {
					if (map[rr][cc] == '.') {
						tankRow = rr;
						tankCol = cc;
					}
				}
			}

			// 시뮬레이션 종료

			// 탱크를 지도에 표시
			if (tankDirection == 0) {
				map[tankRow][tankCol] = '^';
			}

			else if (tankDirection == 1) {
				map[tankRow][tankCol] = 'v';
			}

			else if (tankDirection == 2) {
				map[tankRow][tankCol] = '<';
			}

			else {
				map[tankRow][tankCol] = '>';
			}

			sb.append("#").append(tc).append(" ");
			for (int r = 0; r < rowSize; r++) {
				for (int c = 0; c < colSize; c++) {
					sb.append(map[r][c]);
				}
				sb.append("\n");
			}
		}

		System.out.print(sb);
	}
}
