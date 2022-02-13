package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ10217_KCM_Travel {

    static int N, M, K;
    static int[][] dp;
    static boolean[][] visited;
    static List<List<Node>> adjList;

    static class Node implements Comparable<Node> {
        int v, c, t;

        public Node(int v, int c, int t) {
            this.v = v;
            this.c = c;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            if (this.t != o.t) {
                return this.t - o.t;
            } else {
                return this.c - o.c;
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", c=" + c +
                    ", t=" + t +
                    '}';
        }
    }


    static String solve() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        que.add(new Node(1, 0, 0));
        dp[1][0] = 0;
        // visited[1][0] = true;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.v][cur.c]) continue;
            visited[cur.v][cur.c] = true;

            for (int i = 0; i < adjList.get(cur.v).size(); i++) {
                Node next = adjList.get(cur.v).get(i);
                int nextCost = cur.c + next.c;
                int nextTime = cur.t + next.t;
                if (nextCost > M) continue;

                if (!visited[next.v][nextCost] && dp[next.v][nextCost] > nextTime) {
                    que.add(new Node(next.v, nextCost, nextTime));
                    dp[next.v][nextCost] = nextTime;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= M; i++) {
            ans = Math.min(ans, dp[N][i]);
        }
        if (ans == Integer.MAX_VALUE) {
            return "Poor KCM";
        } else {
            return String.valueOf(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = getBufferedReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            adjList = new ArrayList<>(N + 1);
            for (int i = 0; i <= N; i++) {
                adjList.add(new ArrayList<>());
            }
            dp = new int[N + 1][M + 1];
            visited = new boolean[N + 1][M + 1];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                adjList.get(u).add(new Node(v, c, d));
            }
            ans.append(solve()).append("\n");
        }
        System.out.println(ans);
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
