package mst;

import org.w3c.dom.Node;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class BOJ1944_복제_로봇 {

    static int N, M;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static int[] parent;
    static char[][] map;

    static Map<Point, Integer> keyPoint = new HashMap<>();
    static List<Point> keys = new ArrayList<>();
    static List<List<Node>> adjList = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>();

    static int solve() {
        int ret = 0;
        int cnt = 0;
        makeSet();
        getEdge();
        edges.sort(Comparator.comparingInt(o -> o.w));
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (find(edge.v1) != find(edge.v2)) {
                union(edge.v1, edge.v2);
                ret += edge.w;
                cnt++;
            }
        }

        if (cnt != M) ret = -1;
        return ret;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    static void makeSet() {
        for (int i = 0; i < M + 1; i++) {
            parent[i] = i;
        }
    }

    static void getEdge() {
        for (int i = 0; i <= M; i++) {
            bfs(i);
        }
    }

    static void bfs(int idx) {
        Point start = keys.get(idx);
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        que.add(start);
        visited[start.r][start.c] = true;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] != '1') {
                    Point next = new Point(nr, nc, cur.cnt + 1);
                    que.add(next);
                    visited[nr][nc] = true;
                    if (map[nr][nc] != '0') {
                        int keyNum = keyPoint.get(next);
                        if (idx < keyNum) {
                            edges.add(new Edge(idx, keyNum, next.cnt));
                        }
                    }
                }
            }
        }
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Point {
        int r, c, cnt;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }


    static class Edge {
        int v1, v2, w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v1=" + v1 +
                    ", v2=" + v2 +
                    ", w=" + w +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input4.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }
        parent = new int[M + 1];

        int keyNum = 0;
        for (int i = 0; i < N; i++) {
            map[i] = fr.readLine().replaceAll(" ", "").toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] != '0' && map[i][j] != '1') {
                    Point p = new Point(i, j);
                    keyPoint.put(p, keyNum++);
                    keys.add(p);
                }
            }
        }

        System.out.println(solve());
    }

    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
