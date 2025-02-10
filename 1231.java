import java.util.*;
import java.io.*;

class Solution {
    static Map<Integer, ArrayDeque<Integer>> nodes;  // { 루트 노드 : [왼쪽 자식, 오른쪽 자식] }
    static Map<Integer, Character> words;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for (int tc = 1; tc <= 10; tc++)
        {
            nodes = new HashMap<>();
            words = new HashMap<>();

            int nodeNumber = Integer.parseInt(br.readLine());

            for (int i = 1; i <= nodeNumber; i++)
            {
                nodes.put(i, new ArrayDeque<>());
            }

            for (int i = 1; i <= nodeNumber; i++)
            {
                st = new StringTokenizer(br.readLine());

                int root = Integer.parseInt(st.nextToken());
                char c = st.nextToken().charAt(0);

                ArrayDeque<Integer> node = nodes.get(root);

                while (st.hasMoreTokens())
                {
                    node.add(Integer.parseInt(st.nextToken()));
                }

                nodes.put(root, node);
                words.put(root, c);
            }

            sb.append("#").append(tc).append(" ");
            inOrder(1);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void inOrder(int rootNode)
    {
        int leftNode = 0;
        int rightNode = 0;

        ArrayDeque<Integer> node = nodes.get(rootNode);

        if (!node.isEmpty())
        {
            leftNode = node.removeFirst();
        }

        if (!node.isEmpty())
        {
            rightNode = node.removeFirst();
        }

        if (leftNode != 0)
        {
            inOrder(leftNode);
        }

        sb.append(words.get(rootNode));

        if (rightNode != 0)
        {
            inOrder(rightNode);
        }
    }
}
