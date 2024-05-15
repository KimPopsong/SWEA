import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 1; i <= testCase; i++)
        {
            int sum = 0;

            String[] temp = br.readLine().split(" ");

            for (int j = 0; j < 10; j++)
            {
                if (Integer.parseInt(temp[j]) % 2 == 1)
                {
                    sum += Integer.parseInt(temp[j]);
                }
            }

            sb.append("#").append(i).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);
    }
}
