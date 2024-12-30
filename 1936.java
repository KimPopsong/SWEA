import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] rsp = br.readLine().split(" ");

        int personA = Integer.parseInt(rsp[0]);
        int personB = Integer.parseInt(rsp[1]);
        String winner;

        if (personA == 1)  // A가 가위를 낸 경우
        {
            if (personB == 2)  // B가 바위를 낸 경우
            {
                winner = "B";
            }

            else
            {
                winner = "A";
            }
        }

        else if (personA == 2)  // A가 바위를 낸 경우
        {
            if (personB == 3)
            {
                winner = "B";
            }

            else
            {
                winner = "A";
            }
        }

        else  // A가 보를 낸 경우
        {
            if (personB == 1)
            {
                winner = "B";
            }

            else
            {
                winner = "A";
            }
        }

        System.out.println(winner);
    }
}
