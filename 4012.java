import java.io.*;
import java.util.*;

public class Solution {
	static int ingNumber, minGap; // 재료들의 개수, 최소 차이
	static int[][] ingSynergy; // 재료들의 시너지
	static Set<Set<Integer>> combs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			ingNumber = Integer.parseInt(br.readLine());
			minGap = Integer.MAX_VALUE;
			ingSynergy = new int[ingNumber][ingNumber];
			combs = new HashSet<>();

			for (int r = 0; r < ingNumber; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < ingNumber; c++) {
					ingSynergy[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			getCombination(0, new HashSet<>(), 0);

			sb.append("#").append(tc).append(" ").append(minGap).append("\n");
		}

		System.out.println(sb);
	}

	static void calcSynergy(Set<Integer> comb1) {
		Set<Integer> comb2 = new HashSet<>();

		for (int i = 0; i < ingNumber; i++) {
			if (comb1.contains(i)) {
				continue;
			}

			else {
				comb2.add(i);
			}
		}

		int sum1 = 0;
		int sum2 = 0;

		for (int first : comb1) {
			for (int second : comb1) {
				if (first == second) {
					continue;
				}

				sum1 += ingSynergy[first][second];
				sum1 += ingSynergy[second][first];
			}
		}

		for (int first : comb2) {
			for (int second : comb2) {
				if (first == second) {
					continue;
				}

				sum2 += ingSynergy[first][second];
				sum2 += ingSynergy[second][first];
			}
		}

		minGap = Math.min(minGap, Math.abs(sum1 - sum2) / 2);
	}

	static void getCombination(int depth, Set<Integer> comb, int index) {
		if (depth == (ingNumber / 2)) {
			calcSynergy(comb);
		}

		else {
			for (int i = index; i < ingNumber; i++) {
				comb.add(i);
				getCombination(depth + 1, comb, i + 1);
				comb.remove(i);
			}
		}
	}
}
