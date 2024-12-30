import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int c = 1; c <= testCase; c++)
        {
            int size = Integer.parseInt(br.readLine());
            String[][] map = new String[size][size];
            String[][] answer = new String[size][3];

            for (int k = 0; k < size; k++)  // map 입력
            {
                String[] temp = br.readLine().split(" ");

                for (int l = 0; l < size; l++)
                {
                    map[k][l] = temp[l];
                }
            }

            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    answer[i][j] = "";
                }
            }

            for (int i = 0; i < size; i++)
            {
                for (int j = size - 1; j >= 0; j--)
                {
                    answer[i][0] += map[j][i];
                }

                for (int j = size - 1; j >= 0; j--)
                {
                    answer[i][1] += map[size - i - 1][j];
                }

                for (int j = 0; j < size; j++)
                {
                    answer[i][2] += map[j][size - i - 1];
                }
            }

            System.out.println("#" + c);
            for (String[] ans : answer)
            {
                System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
            }
        }
    }
}
