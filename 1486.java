import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static int assistanceNumber, shelfHeight, minGap;
    static int[] assistants;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++)
        {
            String[] temp = br.readLine().split(" ");
            assistanceNumber = Integer.parseInt(temp[0]);
            shelfHeight = Integer.parseInt(temp[1]);

            assistants = new int[assistanceNumber];
            minGap = Integer.MAX_VALUE;

            temp = br.readLine().split(" ");

            for (int i = 0; i < assistanceNumber; i++)
            {
                assistants[i] = Integer.parseInt(temp[i]);
            }

            for (int combNumber = 1; combNumber <= assistanceNumber; combNumber++)  // 조합하는 사람의 수. 1명, 2명...
            {
                for (int i = 0; i <= assistanceNumber - combNumber; i++)
                {
                    getComb(i, assistants[i], 0, combNumber);
                }
            }

            sb.append("#").append(tc).append(" ").append(minGap).append("\n");
        }

        System.out.println(sb);
    }

    private static void getComb(int index, int sumHeigt, int depth, int combNumber)
    {
        if (depth == combNumber)  // 조합하는 사람의 수를 충족했다면
        {
            if (shelfHeight <= sumHeigt)
            {
                minGap = Math.min(minGap, sumHeigt - shelfHeight);
            }

            return;
        }

        for (int i = index + 1; i < assistanceNumber; i++)
        {
            getComb(i, sumHeigt + assistants[i], depth + 1, combNumber);
        }
    }
}
