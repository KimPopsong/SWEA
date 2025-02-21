import java.io.*;
import java.util.*;

public class Solution {
	static int ingredientNumber, edgeNumber; // 재료의 수, 간선의 수
	static int combNumber; // 조합의 수
	static ArrayList<Integer> cantTogether; // 같이 사용할 수 없는 재료. 2진수로 표현

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			ingredientNumber = Integer.parseInt(st.nextToken());
			edgeNumber = Integer.parseInt(st.nextToken());
			combNumber = 0;
			cantTogether = new ArrayList<>();

			for (int i = 0; i <= ingredientNumber; i++) {
				cantTogether.add(0);
			}

			for (int i = 0; i < edgeNumber; i++) {
				st = new StringTokenizer(br.readLine());

				int ing1 = Integer.parseInt(st.nextToken());
				int ing2 = Integer.parseInt(st.nextToken());

				cantTogether.set(ing1, cantTogether.get(ing1) | 0b1 << ing2);
				cantTogether.set(ing2, cantTogether.get(ing2) | 0b1 << ing1);
			}

			// 초기화 & 입력 종료

			// 사용하는 재료의 수. 0개부터 ingredientNumber개 까지
			for (int ingredientUseNumber = 0; ingredientUseNumber <= ingredientNumber; ingredientUseNumber++) {
				getComb(ingredientUseNumber, 0, 1, 0);
			}

			sb.append("#").append(tc).append(" ").append(combNumber).append("\n");
		}

		System.out.print(sb);
	}

	static void getComb(int maxPick, int pickNumber, int index, int pick) {
		if (maxPick == pickNumber) { // 원하는 개수만큼 뽑았다면
			combNumber += 1;

			return;
		}

		for (int p = index; p <= ingredientNumber; p++) {
			int n = 0b1 << p;

			if ((pick & n) != 0) {
				continue;
			}

			getComb(maxPick, pickNumber + 1, p + 1, pick | cantTogether.get(p));
		}
	}
}
