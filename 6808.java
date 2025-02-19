import java.io.*;
import java.util.*;

class Solution {
	static int meWin, enemyWin;
	static boolean[] isVisit;
	static ArrayList<Integer> enemy; // 적이 가질 수 있는 카드 리스트
	static ArrayList<Integer> my; // 내 카드 리스트

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			Set<Integer> cardSet = new HashSet<>(); // 1 ~ 18까지의 카드 셋
			enemy = new ArrayList<>();
			my = new ArrayList<>();
			isVisit = new boolean[9];
			meWin = 0;
			enemyWin = 0;

			for (int i = 1; i <= 18; i++) {
				cardSet.add(i);
			}

			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) { // 인영이가 가능한 card set 구하기
				int c = Integer.parseInt(st.nextToken());

				cardSet.remove(c);
				my.add(c);
			}

			enemy = new ArrayList<>(); // 계산 편의를 위해 list로 변환

			for (int c : cardSet) {
				enemy.add(c);
			}

			// 입력 및 초기화 끝

			bruteForce(0, new ArrayList<Integer>());

			sb.append("#").append(tc).append(" ").append(meWin).append(" ").append(enemyWin).append("\n");
		}

		System.out.println(sb);
	}

	static void bruteForce(int depth, ArrayList<Integer> enemyCardSet) {
		if (depth == 9) { // 계산. 동점인 경우 제외
			int me = 0, you = 0;

			for (int i = 0; i < 9; i++) {
				if (my.get(i) > enemyCardSet.get(i)) { // 내가 이김
					me += my.get(i) + enemyCardSet.get(i);
				}

				else if (my.get(i) < enemyCardSet.get(i)) { // 상대 이김
					you += my.get(i) + enemyCardSet.get(i);
				}
			}

			if (me > you) {
				meWin += 1;
			}

			else if (me < you) {
				enemyWin += 1;
			}

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (isVisit[i]) { // 이미 방문한 인덱스라면
				continue; // 무시
			}

			enemyCardSet.add(enemy.get(i));
			isVisit[i] = true;
			
			bruteForce(depth + 1, enemyCardSet);
			
			enemyCardSet.remove(enemyCardSet.size() - 1);
			isVisit[i] = false;
		}
	}
}
