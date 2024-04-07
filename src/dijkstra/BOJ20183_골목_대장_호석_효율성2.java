package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ20183_골목_대장_호석_효율성2 {

    static int N, M, A, B;
    static long C;
    static long MAX = Long.MIN_VALUE;
    static long MIN = Long.MAX_VALUE;

    static ArrayList<Vertex>[] adjList;

    static void solve() {
        long left = MIN;
        long right = MAX;

        long ans = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (dijkstra(mid)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }


    static boolean dijkstra(long maxW) {
        PriorityQueue<Vertex> que = new PriorityQueue<>();
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        que.add(new Vertex(A, 0));
        dist[A] = 0;

        while (!que.isEmpty()) {
            Vertex cur = que.poll();

            if (cur.v == B && cur.w <= C) {
                return true;
            }

            if (cur.w >= C) {
                continue;
            }

            if (dist[cur.v] < cur.w) {
                continue;
            }

            for (int i = 0; i < adjList[cur.v].size(); i++) {
                Vertex next = adjList[cur.v].get(i);
                if (next.w <= maxW && dist[next.v] > cur.w + next.w) {
                    dist[next.v] = cur.w + next.w;
                    que.add(new Vertex(next.v, dist[next.v]));
                }
            }
        }
        return false;
    }

    static class Vertex implements Comparable<Vertex> {
        int v;
        long w;

        public Vertex(int v, long w) {
            this.v = v;
            this.w = w;
        }

        public Vertex copy() {
            return new Vertex(this.v, this.w);
        }

        @Override
        public int compareTo(Vertex o) {
            return Long.compare(this.w, o.w);
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken()) - 1;
        B = Integer.parseInt(st.nextToken()) - 1;
        C = Long.parseLong(st.nextToken());
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<Vertex>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());
            adjList[v1].add(new Vertex(v2, w));
            adjList[v2].add(new Vertex(v1, w));
            MAX = Math.max(MAX, w);
            MIN = Math.min(MIN, w);
        }
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input4.txt";
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
