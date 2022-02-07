package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ2211_네트워크_복구 {

    static int N, M;
    static List<List<Node>> adjList;

    static void solve() {
        List<Node> ans = new ArrayList<>();
        PriorityQueue<Node> que = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        // visited[1] = true;
        que.add(new Node(1, 0));

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.v]) continue;
            visited[cur.v] = true;
            if (cur.p != 0) {
                ans.add(new Node(cur.p, -1, cur.v));
            }

            for (int i = 0; i < adjList.get(cur.v).size(); i++) {
                Node next = adjList.get(cur.v).get(i);

                if (!visited[next.v] && (dist[next.v] == Integer.MAX_VALUE || dist[next.v] > dist[cur.v] + next.w)) {
                    dist[next.v] = dist[cur.v] + next.w;
                    que.add(new Node(next.v, dist[next.v], cur.v));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i).p).append(" ").append(ans.get(i).v).append("\n");
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {
        int v, w, p;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Node(int v, int w, int p) {
            this.v = v;
            this.w = w;
            this.p = p;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", w=" + w +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = getBufferedReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            adjList.get(A).add(new Node(B, C));
            adjList.get(B).add(new Node(A, C));
        }
        solve();
    }

    private static BufferedReader getBufferedReader() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
