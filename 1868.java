import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

public class Solution {
    static int click = 0;
    static int size;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++)
        {
            size = Integer.parseInt(br.readLine());  // 게임의 크기
            String[][] game = new String[size][size];  // 게임판
            click = 0;

            for (int i = 0; i < size; i++)
            {
                String[] temp = br.readLine().split("");

                for (int j = 0; j < size; j++)
                {
                    game[i][j] = temp[j];
                }
            }

            int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};  // 12시부터 시계방향
            int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

            for (int r = 0; r < size; r++)  // 지뢰 기준 8방향을 -로 변환
            {
                for (int c = 0; c < size; c++)
                {
                    if (Objects.equals(game[r][c], "*"))
                    {
                        for (int k = 0; k < 8; k++)
                        {
                            int rr = r + dr[k];
                            int cc = c + dc[k];

                            if (0 <= rr && rr < size && 0 <= cc && cc < size)  // 범위 안에 있고
                            {
                                if (Objects.equals(game[rr][cc], "."))  // 빈 칸이라면
                                {
                                    game[rr][cc] = "-";
                                }
                            }
                        }
                    }
                }
            }

            for (int r = 0; r < size; r++)
            {
                for (int c = 0; c < size; c++)
                {
                    if (game[r][c].equals("."))  // 빈 칸이라면
                    {
                        click(game, r, c);  // 클릭
                        click += 1;
                    }
                }
            }

            for (int r = 0; r < size; r++)  // 나머지 1개씩 오픈
            {
                for (int c = 0; c < size; c++)
                {
                    if (game[r][c].equals("-"))
                    {
                        click += 1;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(click).append("\n");
        }
        System.out.println(sb);
    }

    private static void click(String[][] game, int r, int c)  // 클릭
    {
        Deque<Point> bfs = new ArrayDeque<>();  // BFS
        bfs.add(new Point(r, c));
        game[r][c] = "*";

        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};  // 12시부터 시계방향
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

        while (!bfs.isEmpty())
        {
            Point point = bfs.removeFirst();

            for (int d = 0; d < 8; d++)  // 8방향 탐색
            {
                int rr = point.r + dr[d];
                int cc = point.c + dc[d];

                if (0 <= rr && rr < size && 0 <= cc && cc < size)  // 범위 안에 있고
                {
                    if (game[rr][cc].equals("."))  // .이라면
                    {
                        game[rr][cc] = "*";  // 변환
                        bfs.addLast(new Point(rr, cc));  // 추가 탐색
                    }

                    else if (game[rr][cc].equals("-"))  // -이라면
                    {
                        game[rr][cc] = "*";  // 변환
                    }
                }
            }
        }
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
