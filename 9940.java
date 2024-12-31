import java.io.*;

class Solution {
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++)
        {
            int number = Integer.parseInt(br.readLine());  // 숫자의 개수
            boolean[] numbers = new boolean[number + 1];
            String[] temp = br.readLine().split(" ");
            String answer = "Yes";

            for (String s : temp)
            {
                int intS = Integer.parseInt(s);

                if (intS > number)
                {
                    continue;
                }

                numbers[intS] = true;
            }

            for (int i = 1; i <= number; i++)
            {
                if (!numbers[i])
                {
                    answer = "No";

                    break;
                }
            }

            if (tc == testCase)  // 문제 오류
            {
                sb.append("#").append(tc).append(" ").append(answer);
            }

            else
            {
                sb.append("#").append(tc).append(" ").append(answer).append("\n");
            }
        }

        System.out.println(sb);
    }
}
