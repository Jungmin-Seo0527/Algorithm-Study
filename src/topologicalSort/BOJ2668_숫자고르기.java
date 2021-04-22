/*
    BOJ2668_숫자고르기
    --------------------------------------------------------------------------------------------------------------------
    문제 링크

    https://www.acmicpc.net/problem/2668
    --------------------------------------------------------------------------------------------------------------------
    풀이

    BOJ9466_텀_프로젝트 문제와 거의 동일한 문제이다.
    문제를 잘 읽어보면 최대한 길게 사이클을 만들어야 한다. 이말은 즉슨 위상정렬을 수행하고 남은 모든 노드들을 출력하면 된다.
    위상정렬을 하지 못한 노드들은 들어오는 간선이 존재하기 때문에 들어오는 간선의 갯수가 0이 아닌 노드들을 출력하면 된다.
    --------------------------------------------------------------------------------------------------------------------
 */
package topologicalSort;

import java.io.*;
import java.util.*;

public class BOJ2668_숫자고르기 {
    static int N;
    static int[] inDegree;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) que.add(i);
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if (--inDegree[next] == 0) que.add(next);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] != 0) {
                sb.append(i).append("\n");
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inDegree = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) {
            int v = Integer.parseInt(br.readLine());
            graph.get(i).add(v);
            inDegree[v]++;
        }
    }
}
