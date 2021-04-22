/*
    BOJ9466_텀_프로젝트
    -------------------------------------------------------------------------------------------------------------------
    문제 링크

    https://www.acmicpc.net/problem/9466
    -------------------------------------------------------------------------------------------------------------------
    풀이

    처음에는 이 문제가 위상정렬로 쉽게 풀어낼수 있다는 것을 눈치 채지 못했다.
    문제 카테코리가 DFS 라 계속 DFS 로 국한하여 문제를 풀어내려고 하니깐 생각지도 못하게 시간을 많이 잡아 먹었다.
    애초에 내가 위상정렬을 DFS 로 풀어내는 방법을 알고 있었다면 쉬웠을지도 모르겠다.

    하여튼 이 문제는 위상정렬의 특성을 이용해야 한다.
    위상정렬은 사이클이 되지 않는 요소들만 정렬이 가능하다. 즉 위상정렬을 수행하면 남는 노드들이 바로 사이클이 돌아버리는 노드들이다.
    이 문제는 사이클이 도는 노드들이 팀을 이룰수 있으며, 팀을 이루지 못하는 인원수를 출력하는 문제이기 때문에
    위상정렬이 가능한, 즉 사이클이 돌지 않는 노드의 갯수를 세어주면 된다.

    DFS 로 풀어내는 코드는 확인을 해봐도 눈으로는 쉽게 이해가 가지 않았다.
    코드를 따라가면서 진행상황을 손으로 써가면서 코드를 분석해 보아야 겠다.
    -------------------------------------------------------------------------------------------------------------------
 */
package topologicalSort;

import java.io.*;
import java.util.*;

public class BOJ9466_텀_프로젝트 {
    static int N;
    static int[] inDegree;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve() {
        int cnt = 0;
        Queue<Integer> que = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) que.add(i);
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            cnt++;
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if (--inDegree[next] == 0) que.add(next);
            }
        }
        return cnt;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            N = Integer.parseInt(br.readLine());
            inDegree = new int[N + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int v = Integer.parseInt(st.nextToken());
                graph.get(i).add(v);
                inDegree[v]++;
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
