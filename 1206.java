import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 10; i++)  // 10개의 테스트케이스
        {
            int goodView = 0;  // 조망권이 확보된 세대
            int buildingNumber = Integer.parseInt(br.readLine());  // 빌딩의 개수
            int[] buildings = new int[buildingNumber];  // 빌딩의 높이

            String[] temp = br.readLine().split(" ");

            for (int j = 0; j < buildingNumber; j++)
            {
                buildings[j] = Integer.parseInt(temp[j]);
            }

            for (int j = 2; j < buildingNumber - 2; j++)
            {
                int isGoodView = Math.min(Math.min(buildings[j] - buildings[j - 2], buildings[j] - buildings[j - 1]), Math.min(buildings[j] - buildings[j + 1], buildings[j] - buildings[j + 2]));

                if (isGoodView <= 0)
                {
                    continue;
                }

                else
                {
                    goodView += isGoodView;
                }
            }

            sb.append("#").append(i).append(' ').append(goodView).append('\n');
        }

        System.out.println(sb);
    }
}
