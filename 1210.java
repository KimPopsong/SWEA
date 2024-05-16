import java.io.*;

public class Solution {
    static int[][] ladder = new int[100][100];

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        for (int t = 1; t <= 10; t++)
        {
            int endR = 0, endC = 0;

            br.readLine();  // 테스트 케이스의 번호 입력

            for (int i = 0; i < 100; i++)
            {
                String[] temp = br.readLine().split(" ");

                for (int j = 0; j < 100; j++)
                {
                    ladder[i][j] = Integer.parseInt(temp[j]);

                    if (ladder[i][j] == 2)
                    {
                        endR = i;
                        endC = j;
                    }
                }
            }

            while (endR != 0)  // 좌우 먼저 찾고, 기존에 갔던 길은 되돌아가는 것을 방지하기위해 0으로 처리
            {
                if (endC - 1 >= 0 && ladder[endR][endC - 1] == 1)
                {
                    ladder[endR][endC] = 0;

                    endC -= 1;
                }

                else if (endC + 1 < 100 && ladder[endR][endC + 1] == 1)
                {
                    ladder[endR][endC] = 0;

                    endC += 1;
                }

                else
                {
                    ladder[endR][endC] = 0;

                    endR -= 1;
                }
            }

            sb.append("#").append(t).append(" ").append(endC).append("\n");
        }

        System.out.println(sb);
    }
}
