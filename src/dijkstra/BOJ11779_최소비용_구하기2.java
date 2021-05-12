package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ11779_최소비용_구하기2 {
    static int N, M, A, B;
    static int[] prevNode;
    static List<List<Node>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        StringBuilder ans = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        ans.append(dijkstra(A, B)).append("\n");
        while (B != 0) {
            stack.add(B);
            B = prevNode[B];
        }
        ans.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            ans.append(stack.pop()).append(" ");
        }
        System.out.println(ans);
    }

    static long dijkstra(int start, int end) {
        long ret = 0;
        boolean[] visited = new boolean[N + 1];
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Long.compare(n1.weight, n2.weight));
        que.add(new Node(start, 0));
        dist[start] = 0;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.vertex]) {
                continue;
            }
            visited[cur.vertex] = true;
            if (cur.vertex == end) {
                ret = cur.weight;
                break;
            }
            adjList.get(cur.vertex).forEach(next -> {
                if (!visited[next.vertex] && dist[next.vertex] > dist[cur.vertex] + next.weight) {
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    prevNode[next.vertex] = cur.vertex;
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
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        prevNode = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            adjList.get(from).add(new Node(to, weight));
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
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
