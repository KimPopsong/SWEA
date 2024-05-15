import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 1; i <= testCase; i++)
        {
            int snailSize = Integer.parseInt(br.readLine());
            int[][] snail = new int[snailSize][snailSize];

            int r = 0, c = 0;
            int nod = 0;
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};

            for (int k = 1; k <= snailSize * snailSize; k++)
            {
                snail[r][c] = k;

                if (r + dx[nod] < 0 || r + dx[nod] >= snailSize || c + dy[nod] < 0 || c + dy[nod] >= snailSize || snail[r + dx[nod]][c + dy[nod]] != 0)  // 범위를 벗어나거나, 가려는 방향에 이미 숫자가 채워져있다면 방향전환
                {
                    nod += 1;

                    if (nod >= 4)
                    {
                        nod = 0;
                    }
                }

                r = r + dx[nod];
                c = c + dy[nod];
            }

            sb.append("#").append(i).append("\n");

            for (int j = 0; j < snailSize; j++)
            {
                for (int k = 0; k < snailSize; k++)
                {
                    sb.append(snail[j][k]).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
