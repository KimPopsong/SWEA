import java.util.*;
import java.io.*;

class Solution {
    static int nodeNumber, edgeNumber;
    static int node1, node2;
    static Map<Integer, Set<Integer>> pToChild;  // { 부모 : {자식} }
    static Map<Integer, Integer> cToParent;  // { 자식 : 부모 }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++)
        {
            pToChild = new HashMap<>();
            cToParent = new HashMap<>();

            st = new StringTokenizer(br.readLine());

            nodeNumber = Integer.parseInt(st.nextToken());
            edgeNumber = Integer.parseInt(st.nextToken());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= nodeNumber; i++)
            {
                pToChild.put(i, new HashSet<>());
            }

            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens())
            {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                Set<Integer> set = pToChild.get(parent);
                set.add(child);
                cToParent.put(child, parent);
            }

            // 공통 조상 찾기

            Set<Integer> findCommonEncestor = new HashSet<>();
            int commonEncestor = 0;

            int n = node1;
            while (true)  // node1을 기준으로 탐색
            {
                int encestor = cToParent.get(n);
                findCommonEncestor.add(encestor);

                if (encestor == 1)
                {
                    break;
                }

                else
                {
                    n = encestor;
                }
            }

            n = node2;
            while (true)
            {
                int encestor = cToParent.get(n);

                if (findCommonEncestor.contains(encestor))
                {
                    commonEncestor = encestor;

                    break;
                }

                else
                {
                    n = encestor;
                }
            }

            // 공통 조상 찾기 끝

            // 서브 트리의 크기 구하기

            int subTreeSize = 0;
            ArrayDeque<Integer> findSubTree = new ArrayDeque<>();
            findSubTree.add(commonEncestor);

            while (!findSubTree.isEmpty())
            {
                int node = findSubTree.pop();
                subTreeSize += 1;

                Set<Integer> children = pToChild.get(node);

                findSubTree.addAll(children);
            }

            // 서브 트리 크기 구하기 끝

            sb.append("#").append(tc).append(" ").append(commonEncestor).append(" ").append(subTreeSize).append("\n");
        }

        System.out.println(sb);
    }
}
