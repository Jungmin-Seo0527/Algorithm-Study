package dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PGM_가장_먼_노드 {
    private int n;
    private final List<List<Integer>> adjList = new ArrayList<>();

    public int solution(int n, int[][] edge) {
        init(n, edge);
        return ans();
    }

    private int ans() {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        que.add(1);
        int queSZ = que.size();
        int ret = que.size();
        while (!que.isEmpty()) {
            ret = que.size();
            while (queSZ != 0) {
                int cur = que.poll();
                queSZ--;
                for (int i = 0; i < adjList.get(cur).size(); i++) {
                    int next = adjList.get(cur).get(i);
                    if (!visited[next]) {
                        visited[next] = true;
                        que.add(next);
                    }
                }
            }
            queSZ = que.size();
        }
        return ret;
    }

    private void init(int n, int[][] edge) {
        this.n = n;
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] e : edge) {
            adjList.get(e[0]).add(e[1]);
            adjList.get(e[1]).add(e[0]);
        }
    }
}