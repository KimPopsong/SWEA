import java.io.*;
import java.util.*;

class Solution {
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());  // 테스트케이스 입력

        for (int tc = 1; tc <= testCase; tc++)
        {
            String[] temp = br.readLine().split(" ");
            int[] hobits = new int[6];
            int sumHeight = 0;

            for (int i = 0; i < 6; i++)
            {
                hobits[i] = Integer.parseInt(temp[i]);  // 정수로 변환
                sumHeight += hobits[i];  // 기존의 키 저장
            }

            Arrays.sort(hobits);  // 정렬

            int hobit = hobits[5] + 1;  // 마지막 사람보다 키가 커야함

            int rest = (sumHeight + hobit) % 7;
            
            if (rest != 0)
            {
                hobit += (7 - rest);
            }

            sb.append(hobit).append("\n");
        }

        System.out.println(sb);
    }
}
