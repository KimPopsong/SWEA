import java.io.*;
import java.util.*;

class Solution {
	static int ingredientNumber, calMax, tMax; // 재료의 개수, 칼로리 제한
	static int[][] ingredients; // [맛에 대한 점수, 칼로리]

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			String[] temp = br.readLine().split(" ");

			ingredientNumber = Integer.parseInt(temp[0]);
			calMax = Integer.parseInt(temp[1]);
			tMax = 0;

			ingredients = new int[ingredientNumber][2];

			for (int i = 0; i < ingredientNumber; i++) {
				temp = br.readLine().split(" ");

				ingredients[i][0] = Integer.parseInt(temp[0]);
				ingredients[i][1] = Integer.parseInt(temp[1]);
			}

			for (int combNumber = 1; combNumber <= ingredientNumber; combNumber++) {
				for (int index = 0; index <= ingredientNumber - combNumber; index++) {
					recursion(index, 0, combNumber, 0, 0);
				}
			}

			sb.append("#").append(tc).append(" ").append(tMax).append("\n");
		}

		System.out.println(sb);
	}

	public static void recursion(int index, int depth, int maxComb, int sumT, int sumC) {
		if (depth == maxComb) {
			if (sumC <= calMax) {
				tMax = Math.max(tMax, sumT);
			}

			return;
		}

		else if (sumC > calMax) { // 최적화
			return;
		}

		for (int i = index; i < ingredientNumber; i++) {
			recursion(i + 1, depth + 1, maxComb, sumT + ingredients[i][0], sumC + ingredients[i][1]);
		}
	}
}
