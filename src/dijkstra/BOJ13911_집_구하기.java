package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ13911_집_구하기 {
    static int V, E, M, S, x, y;
    static int[] distM, distS;
    static List<List<Node>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = Integer.MAX_VALUE;
        dijkstra(distS);
        dijkstra(distM);
        for (int i = 1; i <= V; i++) {
            int dm = distM[i];
            int ds = distS[i];
            if (dm != 0 && ds != 0 && dm <= x && ds <= y) {
                ans = Math.min(ans, dm + ds);
            }
        }
        if (ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    static void dijkstra(int[] dist) {
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        for (int i = 1; i <= V; i++) {
            if (dist[i] == 0) {
                que.add(new Node(i, 0));
            }
        }
        while (!que.isEmpty()) {
            Node cur = que.poll();
            adjList.get(cur.vertex).forEach(next -> {
                if (dist[next.vertex] > dist[cur.vertex] + next.weight) {
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    que.add(new Node(next.vertex, dist[next.vertex]));
                }
            });
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }
        distM = new int[V + 1];
        distS = new int[V + 1];
        Arrays.fill(distM, Integer.MAX_VALUE);
        Arrays.fill(distS, Integer.MAX_VALUE);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(u).add(new Node(v, w));
            adjList.get(v).add(new Node(u, w));
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int m = Integer.parseInt(st.nextToken());
            distM[m] = 0;
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int s = Integer.parseInt(st.nextToken());
            distS[s] = 0;
        }
    }

    static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", weight=" + weight +
                    '}';
        }
    }

}
