/*
    BOJ1005_ACM_Craft
    --------------------------------------------------------------------------------------------------------------------
    문제링크
    https://www.acmicpc.net/problem/1005
    --------------------------------------------------------------------------------------------------------------------
    풀이

    위상정렬 문제에서 어떤 일을 하기까지 걸리는 최소 시간을 묻는 문제, 단 만약 어떤 일을 하기위해 선행해야 하는 일이 두가지가 있으면
    그 두가지의 일이 한번에 진행될 수 있다.
    결국에는 어떤 일이 진행되려면 선행되는 일중 가장 시간이 오래 걸리는 일이 끝나고 진행되는 것이 최소 시간이다. (결국에는 선행되는
    일들을 모두 해야 하기 때문에)
    메모제이션을 이용해서 위상정렬을 진행하면서 어떠한 경우가 가장 시간이 오래 결리는지 확인한다. 즉 cur를 선행한 후에 temp 를 진행하
    는 경우가 더 오래걸리는지 기존에 이미 메모제이션한 시간이 더 오래 걸리는지
    ret[next] = Math.max(ret[next], ret[cur]+time[next]) 를 진행하면 된다.
    cur를 진행 후에 next를 진행할 수 있다. cur 는 que에서 튀어 나온 일이므로 진행을 할수 있는 것이고 next는 cur 다음에 진행할 수
    있는 일이다. 따라서 ret[next]가 될수 있는 아이들 (ret[cur] + time[next] 즉 cur를 진행하는 최대 시간(선행되는 일들까지 모두
    진행 후, 그래고 그 cur도 진행하는 데 최대 시간들의 일들을 모두 선행한 시간) + next 일을 진행하는데 걸리는 단독 시간 이다)의
    최대값을 반영하면 된다.

    위상정렬과 다이나믹 프로그래밍이 혼합된 문제라고 할 수 있다.
    --------------------------------------------------------------------------------------------------------------------
 */
package TopologicalSort;

import java.io.*;
import java.util.*;

public class BOJ1005_ACM_Craft {
    static int N, K, W;
    static int[] indegree, time;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve() {
        Queue<Integer> que = new LinkedList<>();
        int[] ret = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) que.add(i);
            ret[i] = time[i];
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == W) break;
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int temp = graph.get(cur).get(i);
                ret[temp] = Math.max(ret[temp], ret[cur] + time[temp]);
                if (--indegree[temp] == 0) que.add(temp);
            }
        }
        return ret[W];
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            indegree = new int[N + 1];
            time = new int[N + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph.get(x).add(y);
                indegree[y]++;
            }
            W = Integer.parseInt(br.readLine());
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
