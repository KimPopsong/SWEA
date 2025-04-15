import java.io.*;
import java.util.*;

public class Solution {
	static int rowSize, colSize, time;
	static ArrayList<StemCell> stemCells;
	static HashSet<Point> coordinates;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());

			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());

			stemCells = new ArrayList<>();
			coordinates = new HashSet<>();

			for (int r = 0; r < rowSize; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < colSize; c++) {
					int stemCell = Integer.parseInt(st.nextToken());

					if (stemCell != 0) {
						StemCell sc = new StemCell(r, c, stemCell, stemCell);

						stemCells.add(sc);
						coordinates.add(new Point(r, c));
					}
				}
			}

			// 입력부 종료

			// 계산

			Collections.sort(stemCells, new Comparator<StemCell>() {
				@Override
				public int compare(StemCell sc1, StemCell sc2) {
					return Integer.compare(sc2.force, sc1.force);
				}
			});

			int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
			ArrayDeque<StemCell> bfs = new ArrayDeque<>(stemCells);

			for (int t = 0; t < time; t++) {
				ArrayDeque<StemCell> tempBfs = new ArrayDeque<>(bfs);
				bfs.clear();

				while (!tempBfs.isEmpty()) {
					StemCell sc = tempBfs.removeFirst();

					if (sc.activeTime == 0) {
						for (int d = 0; d < 4; d++) {
							int rr = sc.r + dr[d];
							int cc = sc.c + dc[d];

							if (!coordinates.contains(new Point(rr, cc))) {
								StemCell newStemCell = new StemCell(rr, cc, sc.force, sc.force);

								bfs.addLast(newStemCell);
								coordinates.add(new Point(rr, cc));
							}
						}
					}

					if (sc.activeTime > -sc.force + 1) {
						bfs.addLast(sc);
						sc.activeTime -= 1;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(bfs.size()).append("\n");
		}

		System.out.print(sb);
	}

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			return r * 10000 + c;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}

			Point other = (Point) obj;

			return this.r == other.r && this.c == other.c;
		}
	}

	static class StemCell {
		int r, c, force, activeTime;

		public StemCell(int r, int c, int force, int activeTime) {
			this.r = r;
			this.c = c;
			this.force = force;
			this.activeTime = activeTime;
		}
	}
}
