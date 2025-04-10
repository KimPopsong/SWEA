import java.io.*;
import java.util.*;

public class Solution {
	static int atomNumber, sumEnergy;
	static Atom[] atoms;
	static ArrayList<Collision> collisions;

	static int[] dx = { 0, 0, -1, 1 }, dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			atomNumber = Integer.parseInt(br.readLine());
			sumEnergy = 0;
			atoms = new Atom[atomNumber];
			collisions = new ArrayList<>();

			for (int i = 0; i < atomNumber; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());

				atoms[i] = new Atom(x, y, direction, energy);
			}

			simulate();

			Collections.sort(collisions, new Comparator<Collision>() { // 시간 오름차 순으로 정렬
				@Override
				public int compare(Collision c1, Collision c2) {
					return Double.compare(c1.time, c2.time);
				}
			});

			checkCollision();

			int sumE = 0;

			for (Collision c : collisions) {
				sumE += c.sumEnergy;
			}

			sb.append("#").append(tc).append(" ").append(sumE).append("\n");
		}

		System.out.print(sb);
	}

	// 유효하지 않은 충돌 제거
	static void checkCollision() {
		boolean[] collIndex = new boolean[atomNumber];

		for (int i = 0; i < collisions.size() - 1; i++) {
			Collision c1 = collisions.get(i);

			collIndex[c1.index1] = true;
			collIndex[c1.index2] = true;

			for (int j = i + 1; j < collisions.size(); j++) {
				Collision c2 = collisions.get(j);

				if (collIndex[c2.index1] && collIndex[c2.index2]) {
					collisions.remove(c2);
					j -= 1;
				}

				else if (collIndex[c2.index1] || collIndex[c2.index2]) {
					if (c1.time == c2.time) { // 같은 시간에 충돌하였다면
						int i1 = c1.index1, i2 = c1.index2, dupleIndex;

						if (c2.index1 == i1) {
							dupleIndex = i1;
						}

						else {
							dupleIndex = i2;
						}

						collIndex[c2.index1] = true;
						collIndex[c2.index2] = true;

						c1.sumEnergy = c1.sumEnergy + c2.sumEnergy - atoms[dupleIndex].energy;
						collisions.remove(c2);
						j -= 1;
					}

					else { // 충돌 시간이 다르거나, 중복된 충돌 제거
						collisions.remove(c2);
						j -= 1;
					}
				}
			}
		}
	}

	static void simulate() {
		// 두 원자의 충돌 여부를 확인
		for (int i = 0; i < atomNumber - 1; i++) {
			Atom atom1 = atoms[i];

			for (int j = i + 1; j < atomNumber; j++) {
				Atom atom2 = atoms[j];

				if (atom1.direction == atom2.direction) { // 방향이 같으면 생략
					continue;
				}

				double xGap = Math.abs(atom1.x - atom2.x);
				double yGap = Math.abs(atom1.y - atom2.y);

				if (xGap == 0) { // Y축에 평행할 때
					double y1 = 1001;
					double y2 = -1001;

					if (atom1.direction == 0) {
						y1 = atom1.y + (yGap / 2);
					}

					else if (atom1.direction == 1) {
						y1 = atom1.y - (yGap / 2);
					}

					if (atom2.direction == 0) {
						y2 = atom2.y + (yGap / 2);
					}

					else if (atom2.direction == 1) {
						y2 = atom2.y - (yGap / 2);
					}

					if (y1 == y2) {
						int sumEnergy = atom1.energy + atom2.energy;

						collisions.add(new Collision(atom1.x, y1, sumEnergy, (yGap / 2), i, j));
					}
				}

				else if (yGap == 0) { // X축에 평행할 때
					double x1 = 1001;
					double x2 = -1001;

					if (atom1.direction == 2) {
						x1 = atom1.x - (xGap / 2);
					}

					else if (atom1.direction == 3) {
						x1 = atom1.x + (xGap / 2);
					}

					if (atom2.direction == 2) {
						x2 = atom2.x - (xGap / 2);
					}

					else if (atom2.direction == 3) {
						x2 = atom2.x + (xGap / 2);
					}

					if (x1 == x2) {
						int sumEnergy = atom1.energy + atom2.energy;

						collisions.add(new Collision(x1, atom1.y, sumEnergy, (xGap / 2), i, j));
					}
				}

				else if (xGap == yGap) {
					double x1 = atom1.x + dx[atom1.direction] * xGap;
					double y1 = atom1.y + dy[atom1.direction] * yGap;
					double x2 = atom2.x + dx[atom2.direction] * xGap;
					double y2 = atom2.y + dy[atom2.direction] * yGap;

					if (x1 == x2 && y1 == y2) {
						int sumEnergy = atom1.energy + atom2.energy;

						collisions.add(new Collision(x1, y1, sumEnergy, xGap, i, j));
					}
				}
			}
		}
	}

	static class Collision {
		int index1, index2;
		int sumEnergy;
		double x, y, time;

		Collision(double x, double y, int sumEnergy, double time, int index1, int index2) {
			this.x = x;
			this.y = y;
			this.sumEnergy = sumEnergy;
			this.time = time;
			this.index1 = index1;
			this.index2 = index2;
		}
	}

	static class Atom {
		int x, y, direction, energy; // 방향 : { 상, 하, 좌, 우 }

		Atom(int x, int y, int direction, int energy) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.energy = energy;
		}
	}
}
