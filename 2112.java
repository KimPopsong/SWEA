import java.io.*;
import java.util.*;

public class Solution {
	static int rowSize, colSize, acptCri, minChemical; // 사이즈, 합격기준, 사용한 약품 개수
	static int[][] cells;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			acptCri = Integer.parseInt(st.nextToken());
			minChemical = Integer.MAX_VALUE;

			cells = new int[rowSize][colSize];

			for (int r = 0; r < rowSize; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < colSize; c++) {
					cells[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			getSubSet(0, 0, cells);

			sb.append("#").append(tc).append(" ").append(minChemical).append("\n");
		}

		System.out.print(sb);
	}

	static void getSubSet(int index, int useChemical, int[][] newCells) { // 약품 사용에 대한 부분집합 구하기
		if (useChemical >= minChemical) { // 현재 최소 사용한 약품 개수보다 같거나 많다면
			return;
		}

		else if (index == rowSize) { // 마지막까지 왔다면
			Check(newCells, useChemical);

			return;
		}

		int[][] newNewCells = new int[rowSize][colSize]; // 셀 복사

		for (int r = 0; r < rowSize; r++) {
			for (int c = 0; c < colSize; c++) {
				newNewCells[r][c] = newCells[r][c];
			}
		}

		getSubSet(index + 1, useChemical, newCells); // 약품 사용 안하기

		for (int c = 0; c < colSize; c++) { // A약품 사용
			newNewCells[index][c] = 0;
		}
		getSubSet(index + 1, useChemical + 1, newNewCells); // A약품 사용

		for (int c = 0; c < colSize; c++) { // B약품 사용
			newNewCells[index][c] = 1;
		}
		getSubSet(index + 1, useChemical + 1, newNewCells); // B약품 사용
	}

	// 검증
	static void Check(int[][] newCells, int useChemical) {
		boolean flag = true;

		for (int c = 0; c < colSize; c++) {
			if (flag == false) {
				break;
			}

			int before = newCells[0][c];
			int streak = 1;

			for (int r = 1; r < rowSize; r++) {
				if (streak >= acptCri) {
					break;
				}

				else if (newCells[r][c] == before) {
					streak += 1;
				}

				else {
					before = newCells[r][c];
					streak = 1;
				}
			}

			if (streak >= acptCri) {
				flag = true;
			}

			else {
				flag = false;
			}
		}

		if (flag) {
			minChemical = useChemical;
		}
	}
}
