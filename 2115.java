import java.io.*;
import java.util.*;

/**
 * 1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 3초.
 * 
 * 2. 벌통들의 크기 N은 3 이상 10 이하의 정수이다. (3 ≤ N ≤ 10)
 * 
 * 3. 선택할 수 있는 벌통의 개수 M은 1 이상 5 이하의 정수이다. (1 ≤ M ≤ 5)
 * 
 * 4. 선택할 수 있는 벌통의 개수 M은 반드시 N 이하로만 주어진다.
 * 
 * 5. 꿀을 채취할 수 있는 최대 양 C는 10 이상 30 이하의 정수이다. (10 ≤ C ≤ 30)
 * 
 * 6. 하나의 벌통에서 채취할 수 있는 꿀의 양은 1 이상 9 이하의 정수이다.
 * 
 * 7. 하나의 벌통에서 일부분의 꿀만 채취할 수 없고, 벌통에 있는 모든 꿀을 한번에 채취해야 한다.
 */
class Solution {
	static int size, canPick, maxCollect;
	static int maxHoney;
	static int[][] honeyComb;
	static int[][] preSum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			size = Integer.parseInt(st.nextToken());
			canPick = Integer.parseInt(st.nextToken());
			maxCollect = Integer.parseInt(st.nextToken());
			maxHoney = 0;
			honeyComb = new int[size][size];
			preSum = new int[size][size];

			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < size; c++) {
					honeyComb[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 입력부 끝

			// 각 위치별 최대값 구하기
			for (int r = 0; r < size; r++) {
				for (int c = 0; c <= size - canPick; c++) {
					int sumH = calcHoney(r, c);

					preSum[r][c] = sumH;
				}
			}

			for (int r = 0; r < size; r++) {
				for (int c = 0; c <= size - canPick; c++) {
					int h1 = preSum[r][c];
					int h2 = 0;

					for (int cc = c + canPick; cc <= size - canPick; cc++) {
						h2 = preSum[r][cc];

						maxHoney = Math.max(maxHoney, h1 + h2);
					}

					for (int rr = r + 1; rr < size; rr++) {
						for (int cc = 0; cc <= size - canPick; cc++) {
							h2 = preSum[rr][cc];

							maxHoney = Math.max(maxHoney, h1 + h2);
						}
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(maxHoney).append("\n");
		}

		System.out.print(sb);
	}

	/**
	 * @param r 시작 점의 행
	 * @param c 시작 점의 열
	 * @return 채취할 수 있는 최대 꿀
	 */
	static int calcHoney(int r, int c) {
		int max = 0;
		int[] honey = new int[canPick];

		for (int i = 0; i < canPick; i++) {
			honey[i] = honeyComb[r][c + i];
		}

		Arrays.sort(honey);

		for (int p = 1; p <= canPick; p++) {
			max = Math.max(max, recursion(honey, new int[p], p, 0, 0, 0, 0));
		}

		return max;
	}

	static int recursion(int[] honey, int[] picks, int maxDepth, int depth, int index, int maxH, int sum) {
		if (sum > maxCollect) {
			return 0;
		}

		if (depth == maxDepth) {
			int s = 0;
			for (int i : picks) {
				s += i * i;
			}

			return Math.max(maxH, s);
		}

		for (int i = index; i < canPick; i++) {
			picks[depth] = honey[i];

			maxH = Math.max(maxH, recursion(honey, picks, maxDepth, depth + 1, i + 1, maxH, sum + honey[i]));
		}

		return maxH;
	}
}
