/*
    BOJ2056_작업
    --------------------------------------------------------------------------------------------------------------------
    문제

    https://www.acmicpc.net/problem/2056
    --------------------------------------------------------------------------------------------------------------------
    풀이

    ACM Craft 문제 풀이 참고 (풀이가 동일한 문제)
    --------------------------------------------------------------------------------------------------------------------
 */
package TopologicalSort;

import java.io.*;
import java.util.*;

public class BOJ2056_작업 {
    static int N;
    static int[] inDegree, time;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        int[] ret = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) que.add(i);
            ret[i] = time[i];
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if (--inDegree[next] == 0) que.add(next);
                ret[next] = Math.max(ret[next], ret[cur] + time[next]);
            }
        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, ret[i]);
        }
        System.out.println(ans);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];
        time = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            inDegree[i] = k;
            for (int j = 0; j < k; j++) {
                int v = Integer.parseInt(st.nextToken());
                graph.get(v).add(i);
            }
        }
    }
}
