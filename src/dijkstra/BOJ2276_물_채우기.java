package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ2276_물_채우기 {

    static int rowSZ, colSZ;
    static int[][] pool, dist;
    static boolean[][] visited;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static int solve() {
        dist = new int[rowSZ + 2][colSZ + 2];
        visited = new boolean[rowSZ + 2][colSZ + 2];
        for (int i = 0; i < rowSZ + 2; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Point> que = new PriorityQueue<>();
        que.add(new Point(0, 0));
        dist[0][0] = 0;
        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (visited[cur.r][cur.c]) continue;
            visited[cur.r][cur.c] = true;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc)) {
                    int nd = Math.max(dist[cur.r][cur.c], pool[nr][nc]);
                    if (dist[nr][nc] > nd) {
                        dist[nr][nc] = nd;
                        que.add(new Point(nr, nc, nd));
                    }
                }
            }
        }
        return getAns();
    }

    private static int getAns() {
        int ret = 0;
        for (int i = 0; i < rowSZ + 2; i++) {
            for (int j = 0; j < colSZ + 2; j++) {
                ret += dist[i][j] - pool[i][j];
            }
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ + 2 && c >= 0 && c < colSZ + 2;
    }

    static class Point implements Comparable<Point> {
        int r, c, d;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", w=" + d +
                    '}';
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(d, o.d);
        }
    }

    static void showArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        // FastReader fr = new FastReader("input/input1.txt");
        FastReader fr = new FastReader();
        colSZ = fr.nextInt();
        rowSZ = fr.nextInt();
        pool = new int[rowSZ + 2][colSZ + 2];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                pool[i + 1][j + 1] = fr.nextInt();
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
