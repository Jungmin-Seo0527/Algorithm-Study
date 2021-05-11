package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ1238_파티 {
    static int N, M, X;
    static List<List<Node>> adjNodeList = new ArrayList<>();
    static List<List<Node>> revAdjNodeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();

    }

    static void solve() {
        int max = 0;
        int[] goDist = dijkstra(X, 0);
        int[] backDist = dijkstra(X, 1);
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, goDist[i] + backDist[i]);
        }
        System.out.println(max);
    }

    static int[] dijkstra(int start, int mode) {
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<List<Node>> list;
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        if (mode == 0) {
            list = revAdjNodeList;
        } else {
            list = adjNodeList;
        }
        visited[start] = true;
        for (int i = 0; i < list.get(start).size(); i++) {
            Node node = list.get(start).get(i);
            dist[node.vertex] = node.weight;
            que.add(new Node(node.vertex, node.weight));
        }
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;
            for (int i = 0; i < list.get(cur.vertex).size(); i++) {
                Node next = list.get(cur.vertex).get(i);
                if (!visited[next.vertex] && dist[next.vertex] > dist[cur.vertex] + next.weight) {
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    que.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
        return dist;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            adjNodeList.add(new ArrayList<>());
            revAdjNodeList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjNodeList.get(from).add(new Node(to, weight));
            revAdjNodeList.get(to).add(new Node(from, weight));
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
