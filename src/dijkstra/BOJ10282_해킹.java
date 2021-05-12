package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ10282_해킹 {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        // solve();
    }

    static int[] dijkstra(int N, int start, List<List<Node>> list) {
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        int cnt = 0, max = 0;
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        que.add(new Node(start, 0));

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (visited[cur.vertex]) {
                continue;
            }
            visited[cur.vertex] = true;
            cnt++;
            max = Math.max(max, cur.weight);

            list.get(cur.vertex).forEach(next -> {
                if (!visited[next.vertex] && dist[next.vertex] > dist[cur.vertex] + next.weight) {
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    que.add(new Node(next.vertex, dist[next.vertex]));
                }
            });
        }
        return new int[]{cnt, max};
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<List<Node>> adjList = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                adjList.get(b).add(new Node(a, s));
            }
            int[] ans = dijkstra(N, c, adjList);
            sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }
        System.out.println(sb);
    }

    static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

}
