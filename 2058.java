import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] numbers = br.readLine().split("");
        int sumNum = 0;

        for (String number : numbers)
        {
            sumNum += Integer.parseInt(number);
        }

        sb.append(sumNum);

        System.out.println(sb);
    }
}
