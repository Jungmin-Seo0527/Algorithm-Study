package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ1504_특정한_최단_경로 {
    static int N, E, V1, V2;
    static List<List<Node>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        long dist1 = dijkstra(1, V1);
        long dist2 = dijkstra(V1, V2);
        long dist3 = dijkstra(V2, N);
        long ans = -1;
        if (dist1 != -1 && dist2 != -1 && dist3 != -1) {
            ans = dist1 + dist2 + dist3;
        }
        // System.out.println(ans);
        dist1 = dijkstra(1, V2);
        dist2 = dijkstra(V2, V1);
        dist3 = dijkstra(V1, N);
        if (dist1 != -1 && dist2 != -1 && dist3 != -1) {
            long dist = dist1 + dist2 + dist3;
            if (ans == -1) {
                ans = dist;
            } else {
                ans = Math.min(ans, dist);
            }
        }
        System.out.println(ans);
    }

    static long dijkstra(int start, int end) {
        long ret = -1;
        boolean[] visited = new boolean[N + 1];
        long[] dist = new long[N + 1];
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Long.compare(n1.weight, n2.weight));

        Arrays.fill(dist, Long.MAX_VALUE);
        que.add(new Node(start, 0));
        dist[start] = 0;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.vertex]) {
                continue;
            }
            visited[cur.vertex] = true;
            if (cur.vertex == end) {
                ret = dist[end];
                break;
            }
            adjList.get(cur.vertex).forEach(next -> {
                if (!visited[next.vertex] && dist[next.vertex] > dist[cur.vertex] + next.weight) {
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    que.add(new Node(next.vertex, dist[next.vertex]));
                }
            });
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            adjList.get(from).add(new Node(to, weight));
            adjList.get(to).add(new Node(from, weight));
        }
        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());
    }

    static class Node {
        int vertex;
        long weight;

        public Node(int vertex, long weight) {
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
