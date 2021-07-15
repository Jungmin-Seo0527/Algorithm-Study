package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1916_최소비용_구하기 {

    static int solve(int n, List<List<Pair>> adj, int s, int e) {
        PriorityQueue<Pair> que = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.w, p2.w));
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        que.add(new Pair(s, 0));

        while (!que.isEmpty()) {
            Pair cur = que.poll();
            if (cur.v == e) {
                break;
            }
            if (visited[cur.v]) {
                continue;
            }
            visited[cur.v] = true;
            for (int i = 0; i < adj.get(cur.v).size(); i++) {
                Pair next = adj.get(cur.v).get(i);
                if (!visited[next.v] && dist[next.v] > dist[cur.v] + next.w) {
                    dist[next.v] = dist[cur.v] + next.w;
                    que.add(new Pair(next.v, dist[next.v]));
                }
            }
        }
        return dist[e];
    }

    static class Pair {
        int v, w;

        public Pair(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(s).add(new Pair(e, w));
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, adj, s, e));
    }
}
