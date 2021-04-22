/*
    BOJ1516_게임_개발
    --------------------------------------------------------------------------------------------------------------------
    문제

    https://www.acmicpc.net/problem/1516
    --------------------------------------------------------------------------------------------------------------------
    풀이

    위상 정렬 문제중 BOJ1005_ACM_Craft 문제와 거의 동일한 문제
    일을 진행하는 시간이 주어지며, 선행해야 하는 일들이 모두 진행되었으면 다음 일을 진행 가능하고, 진행 가능한 일이 여러개가 존재할
    경우 동시에 일을 진행할 수 있는 문제

    진행 가능한 일이 있을때, 처리할 수 있는 최대값을 구하면 된다.
    --------------------------------------------------------------------------------------------------------------------
 */
package topologicalSort;

import java.io.*;
import java.util.*;

public class BOJ1516_게임_개발 {
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
            if (inDegree[i] == 0) {
                que.add(i);
            }
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

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ret[i]).append("\n");
        }
        System.out.println(sb);
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
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) break;
                graph.get(v).add(i);
                inDegree[i]++;
            }
        }
    }
}
