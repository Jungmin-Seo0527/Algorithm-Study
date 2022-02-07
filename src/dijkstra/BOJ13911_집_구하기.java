package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ13911_집_구하기 {

    static int V, E, M, x, S, y;
    static List<List<Node>> adjList;
    static List<Integer> mac, star;

    static void solve() {
        long[] distMac = dijkstra(mac);
        long[] distStar = dijkstra(star);

        Long min = Long.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            if (distMac[i] > 0 && distMac[i] <= x && distStar[i] > 0 && distStar[i] <= y) {
                min = Math.min(min, distMac[i] + distStar[i]);
            }
        }
        if (min == Long.MAX_VALUE) min = (long)-1;
        System.out.println(min);
    }

    static long[] dijkstra(List<Integer> list) {
        PriorityQueue<Node> que = new PriorityQueue<>();
        long[] dist = new long[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        for (int i = 0; i < list.size(); i++) {
            dist[list.get(i)] = 0;
            que.add(new Node(list.get(i), 0));
        }

        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (int i = 0; i < adjList.get(cur.v).size(); i++) {
                Node next = adjList.get(cur.v).get(i);

                if (!visited[next.v] && dist[next.v] > dist[cur.v] + next.w) {
                    dist[next.v] = dist[cur.v] + next.w;
                    que.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int v;
        long w;

        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.w, o.w);
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = getBufferedReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjList = new ArrayList<>(V + 1);
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }
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
        mac = new ArrayList<>(M);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mac.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        star = new ArrayList<>(S);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            star.add(Integer.parseInt(st.nextToken()));
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
