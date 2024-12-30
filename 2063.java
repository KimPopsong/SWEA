import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int number = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split(" ");
        int[] numbersInt = new int[number];

        for (int i = 0; i < number; i++)
        {
            numbersInt[i] = Integer.parseInt(numbers[i]);
        }

        Arrays.sort(numbersInt);
        System.out.println(numbersInt[(number - 1) / 2]);
    }
}
