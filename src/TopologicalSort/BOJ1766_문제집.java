/*
    BOJ1766_문제집
    https://www.acmicpc.net/problem/1766
    --------------------------------------------------------------------------------------------------------------------
    풀이

    전형적인 위상정렬이지만 조금 다른점은 현재 시점에서 풀이가 가능한 문제에 대한 조건이 따로 주어졌다는 점이다.
    que를 보면 선행되어야 하는 문제들을 모두 풀어낸 문제들만 que에 담았다. 그 안에 있는 문제들의 순서는 que에 들어간 순서대로
    정렬을 하였다. 사실 어떠한 방식으로 정렬을 해도 위상정렬을 위반하지는 않는다. 선행되어야 할 문제들을 모두 수행했으므로 언제 정렬을
    해도 무방한 것이다. 그래서 줄 세우기 문제는 답이 여러개가 뒬수 있는것이다.

    이 문제는 현 시점에서 풀어낼 수 있는 문제들을 뽑다 정렬을 진행할때에도 더 쉬운 문제를 우선시 하는 조건이 존재한다.
    따라서 일반 큐 대신 우선순위 큐를 이용하면 문제는 간단하게 풀린다.

    주의할점은 같은 깊이의 노드에 대한 조건이 아닌, 현 시점에서 풀어낼 수 있는, 즉 현 시점에서 큐에 저장되어 있는 문제들에 대한 조건
    이라는 점을 주의하자.
    예를 들어
    5 5
    1 3
    1 4
    3 2
    4 2
    1 5

    1 -> 3 , 1 -> 4 , 3 -> 2 , 4 -> 2 , 1 -> 5의 선행 순서가 있다고 가정하자자
    정답은 1 3 4 2 5 가 된다. 3과 4와 같은 깊이에 존재하는 5 이지만 3과 4를 모두 해결했을때 해결할 수 있는 2번이 5번보다 더 쉬운
    문제이기 때문에 5번보다 더 먼제 풀어야 하는 것이다.
    명심하자. 큐에 저장되어 있는 문제는 어떠한 것이 먼저 나와 정렬을 이룬다고 해도 위상정렬을 해치지 않는다.


   -------------------------------------------------------------------------------------------------------------------
 */
package TopologicalSort;

import java.io.*;
import java.util.*;

public class BOJ1766_문제집 {
    static int N, M;
    static int[] inDegree;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) que.add(i);
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            sb.append(cur).append(" ");
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if (--inDegree[next] == 0) que.add(next);
            }
        }
        System.out.println(sb);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            inDegree[B]++;
        }
    }
}
