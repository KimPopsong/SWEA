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
            int lengthA, lengthB;  // 배열의 길이
            int[] arrayA, arrayB;  // 숫자 배열
            int maxSum = Integer.MIN_VALUE;

            String[] temp = br.readLine().split(" ");

            lengthA = Integer.parseInt(temp[0]);
            lengthB = Integer.parseInt(temp[1]);

            if (lengthA < lengthB)  // 항상 lengthA가 더 길거나 같도록
            {
                arrayA = new int[lengthB];
                arrayB = new int[lengthA];

                temp = br.readLine().split(" ");

                for (int i = 0; i < lengthA; i++)
                {
                    arrayB[i] = Integer.parseInt(temp[i]);
                }

                temp = br.readLine().split(" ");

                for (int i = 0; i < lengthB; i++)
                {
                    arrayA[i] = Integer.parseInt(temp[i]);
                }

                int t = lengthA;
                lengthA = lengthB;
                lengthB = t;
            }

            else
            {
                arrayA = new int[lengthA];
                arrayB = new int[lengthB];

                temp = br.readLine().split(" ");

                for (int i = 0; i < lengthA; i++)
                {
                    arrayA[i] = Integer.parseInt(temp[i]);
                }

                temp = br.readLine().split(" ");

                for (int i = 0; i < lengthB; i++)
                {
                    arrayB[i] = Integer.parseInt(temp[i]);
                }
            }

            for (int i = 0; i <= lengthA - lengthB; i++)  // 계산
            {
                int tempSum = 0;

                for (int j = 0; j < lengthB; j++)
                {
                    tempSum += (arrayA[i + j] * arrayB[j]);
                }

                maxSum = Math.max(maxSum, tempSum);
            }

            System.out.println("#" + c + " " + maxSum);
        }
    }
}
