import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++)
        {
            int size = Integer.parseInt(br.readLine());  // 수영장의 크기
            int[][] pool = new int[size][size];  // 수영장
            int startR, startC, endR, endC;  // 시작점과 도착점의 좌표
            int[][] isVisit = new int[size][size];  // 방문 여부 확인
            Deque<Point> bfs = new ArrayDeque<>();  // BFS
            ArrayList<Point> tornado = new ArrayList<>();  // 소용돌이 위치 저장
            int time = -1;

            for (int i = 0; i < size; i++)  // 수영장 입력
            {
                String[] temp = br.readLine().split(" ");

                for (int j = 0; j < size; j++)
                {
                    pool[i][j] = Integer.parseInt(temp[j]);

                    if (pool[i][j] == 2)  // 소용돌이라면
                    {
                        tornado.add(new Point(i, j));  // 좌표 저장
                    }
                }
            }

            String[] temp = br.readLine().split(" ");  // 시작점 입력
            startR = Integer.parseInt(temp[0]);
            startC = Integer.parseInt(temp[1]);

            temp = br.readLine().split(" ");  // 도착점 입력
            endR = Integer.parseInt(temp[0]);
            endC = Integer.parseInt(temp[1]);

            for (int r = 0; r < size; r++)  // isVisit 초기화
            {
                for (int c = 0; c < size; c++)  // 계산을 위해
                {
                    isVisit[r][c] = Integer.MAX_VALUE;
                }
            }
            isVisit[startR][startC] = 0;
            bfs.addFirst(new Point(startR, startC));

            int[] dr = {-1, 1, 0, 0};  // 상하좌우 순
            int[] dc = {0, 0, -1, 1};

            while (!bfs.isEmpty())
            {
                time += 1;  // 시간 증가

                if ((time + 1) % 3 == 0)  // 소용돌이 제거
                {
                    for (Point p : tornado)
                    {
                        pool[p.r][p.c] = 0;
                    }
                }

                else  // 소용돌이 생성
                {
                    for (Point p : tornado)
                    {
                        pool[p.r][p.c] = 2;
                    }
                }

                Deque<Point> tempQueue = new ArrayDeque<>(bfs);
                bfs.clear();

                while (!tempQueue.isEmpty())
                {
                    Point point = tempQueue.removeFirst();

                    for (int d = 0; d < 4; d++)
                    {
                        int rr = point.r + dr[d];
                        int cc = point.c + dc[d];

                        if (0 <= rr && rr < size && 0 <= cc && cc < size)  // 범위 안에 있고
                        {
                            if (isVisit[rr][cc] == Integer.MAX_VALUE)  // 가본 적이 없고
                            {
                                if (pool[rr][cc] == 0)  // 갈 수 있는 길이라면
                                {
                                    bfs.addLast(new Point(rr, cc));  // 방문
                                    isVisit[rr][cc] = time + 1;
                                }

                                else if (pool[rr][cc] == 2)  // 토네이도라면
                                {
                                    bfs.addLast(point);  // 기다리기
                                }
                            }
                        }
                    }

                }
            }

            // 정답 출력
            if (isVisit[endR][endC] == Integer.MAX_VALUE)  // 도착할 수 없다면 -1 출력
            {
                sb.append("#").append(tc).append(" ").append(-1).append("\n");
            }

            else
            {
                sb.append("#").append(tc).append(" ").append(isVisit[endR][endC]).append("\n");
            }
        }

        System.out.println(sb);
    }

    static class Point {
        int r, c;

        Point(int r, int c)
        {
            this.r = r;
            this.c = c;
        }
    }
}
