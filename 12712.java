import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 1; i <= testCase; i++)
        {
            int size, power;  // 배열의 크기, 스프레이의 세기
            int maxKill = 0;  // 최대로 죽일 수 있는 파리의 수
            int[][] map;

            String[] temp = br.readLine().split(" ");  // size, power 입력

            size = Integer.parseInt(temp[0]);
            power = Integer.parseInt(temp[1]);
            map = new int[size][size];

            for (int j = 0; j < size; j++)  // map 입력
            {
                String[] temp1 = br.readLine().split(" ");

                for (int k = 0; k < size; k++)
                {
                    map[j][k] = Integer.parseInt(temp1[k]);
                }
            }

            maxKill = getMaxKill(size, power, maxKill, map, new int[]{-1, 1, 0, 0}, new int[]{0, 0, -1, 1});  // 상하좌우
            maxKill = getMaxKill(size, power, maxKill, map, new int[]{-1, 1, 1, -1}, new int[]{1, 1, -1, -1});  // 1시부터 시계방향 대각선

            System.out.println("#" + i + " " + maxKill);
        }
    }

    private static int getMaxKill(int size, int power, int maxKill, int[][] map, int[] dr, int[] dc)
    {
        for (int r = 0; r < size; r++)  // + 탐색
        {
            for (int c = 0; c < size; c++)
            {
                int tempKill = map[r][c];

                for (int k = 0; k < 4; k++)  // 방향 고정
                {
                    int rr = r;
                    int cc = c;

                    for (int p = 1; p < power; p++)
                    {
                        rr += dr[k];
                        cc += dc[k];

                        if (0 <= rr && rr < size && 0 <= cc && cc < size)  // 범위 안에 있다면
                        {
                            tempKill += map[rr][cc];
                        }
                    }
                }

                maxKill = Math.max(maxKill, tempKill);
            }
        }

        return maxKill;
    }
}
