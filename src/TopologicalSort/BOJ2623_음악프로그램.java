/*
    BOJ2623_음악프로그램
    -------------------------------------------------------------------------------------------------------------------
    문제
    https://www.acmicpc.net/problem/2623
    -------------------------------------------------------------------------------------------------------------------
    풀이

    입력 형식만 다른 전형적인 위상 정렬 문제이다.
    이 문제는 모든 요소가 정렬이 가능하면 정렬한대로 출력, 만약 불가능하다면 0을 출력하면 된다.
    전체 요소가 N개이니 마지막에 정렬한 배열의 길이가 N인지 아닌지만 검사하는 과정을 추가하면 된다.
    별다른 어려운 점은 없었다.
    -------------------------------------------------------------------------------------------------------------------
 */
package TopologicalSort;

import java.io.*;
import java.util.*;

public class BOJ2623_음악프로그램 {
    static int N, M;
    static int[] inDegree;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) que.add(i);
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            sb.append(cur).append("\n");
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if (--inDegree[next] == 0) que.add(next);
            }
        }
        if (sb.length() < (2 * N)) System.out.println(0);
        else System.out.println(sb);
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
            int n = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            for (int j = 1; j < n; j++) {
                int next = Integer.parseInt(st.nextToken());
                graph.get(cur).add(next);
                inDegree[next]++;
                cur = next;
            }
        }
    }
}
