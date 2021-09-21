package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ16952_체스판_여행_2 {

    static int N;
    static int[][] board;
    static boolean[][][][] visited;
    static Point[] points;

    static int[] vr = {-1, 0, 1, 0};
    static int[] vc = {0, 1, 0, -1};

    static void solve() {
        PriorityQueue<Point> que = new PriorityQueue<>();
        int ret = 0;
        for (int i = 0; i < 3; i++) {
            Point start = new Point(points[1].r, points[1].c, 1, i, 0, 0);
            que.add(start);
        }

        while (!que.isEmpty()) {
            Point cur = que.poll();
            // System.out.println("cur = " + cur);
            if (visited[cur.r][cur.c][cur.num][cur.piece]) continue;
            visited[cur.r][cur.c][cur.num][cur.piece] = true;
            if (cur.num == N * N) {
                System.out.println(cur.cnt + " " + cur.changeCnt);
                break;
            }

            List<Point> nextSamePiece = cur.move();
            for (int i = 0; i < nextSamePiece.size(); i++) {
                Point next = nextSamePiece.get(i);
                if (!visited[next.r][next.c][next.num][next.piece]) {
                    que.add(next);
                }
            }

            List<Point> changePiece = cur.changePiece();
            for (int i = 0; i < changePiece.size(); i++) {
                Point next = changePiece.get(i);
                if (!visited[next.r][next.c][next.num][next.piece]) {
                    que.add(next);
                }
            }
        }
    }

    static class Point implements Comparable<Point> {
        int r, c, num, piece, cnt, changeCnt;
        boolean change;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int num, int piece, int cnt, int changeCnt) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.piece = piece;
            this.cnt = cnt;
            this.changeCnt = changeCnt;
        }

        public List<Point> move() {
            if (piece == 0) return knightMove();
            else if (piece == 1) return bishopMove();
            else if (piece == 2) return rookMove();
            else return null;
        }

        public List<Point> knightMove() {
            // System.out.println("Point.knightMove");
            List<Point> ret = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = -1; j <= 1; j += 2) {
                    int nr = r + vr[i] * 2 + vr[(i + j + 4) % 4];
                    int nc = c + vc[i] * 2 + vc[(i + j + 4) % 4];
                    if (checkBoundary(nr, nc)) {
                        // System.out.println("nr=" + nr + " nc" + nc);
                        int nn = num;
                        if (board[nr][nc] == nn + 1) nn++;
                        ret.add(new Point(nr, nc, nn, this.piece, this.cnt + 1, this.changeCnt));
                    }
                }
            }
            return ret;
        }

        public List<Point> bishopMove() {
            List<Point> ret = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int t = 1;
                while (true) {
                    int nr = r + (vr[i] + vr[(i + 1) % 4]) * t;
                    int nc = c + (vc[i] + vc[(i + 1) % 4]) * t;
                    if (checkBoundary(nr, nc)) {
                        int nn = num;
                        if (board[nr][nc] == nn + 1) nn++;
                        ret.add(new Point(nr, nc, nn, this.piece, this.cnt + 1, this.changeCnt));
                        t++;
                    } else {
                        break;
                    }
                }

                t = -1;
                while (true) {
                    int nr = r + (vr[i] + vr[(i + 1) % 4]) * t;
                    int nc = c + (vc[i] + vc[(i + 1) % 4]) * t;
                    if (checkBoundary(nr, nc)) {
                        int nn = num;
                        if (board[nr][nc] == nn + 1) nn++;
                        ret.add(new Point(nr, nc, nn, this.piece, this.cnt + 1, this.changeCnt));
                        t--;
                    } else {
                        break;
                    }
                }
            }

            return ret;
        }

        public List<Point> rookMove() {
            List<Point> ret = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int t = 1;
                while (true) {
                    int nr = r + vr[i] * t;
                    int nc = c + vc[i] * t;
                    if (checkBoundary(nr, nc)) {
                        int nn = num;
                        if (board[nr][nc] == nn + 1) nn++;
                        ret.add(new Point(nr, nc, nn, this.piece, this.cnt + 1, this.changeCnt));
                        t++;
                    } else {
                        break;
                    }
                }

                t = -1;
                while (true) {
                    int nr = r + vr[i] * t;
                    int nc = c + vc[i] * t;
                    if (checkBoundary(nr, nc)) {
                        int nn = num;
                        if (board[nr][nc] == nn + 1) nn++;
                        ret.add(new Point(nr, nc, nn, this.piece, this.cnt + 1, this.changeCnt));
                        t--;
                    } else {
                        break;
                    }
                }
            }

            return ret;
        }

        public List<Point> changePiece() {
            List<Point> ret = new ArrayList<>();
            if (change) return ret;
            change = true;

            for (int i = 1; i <= 2; i++) {
                int np = (piece + i) % 3;
                ret.add(new Point(r, c, num, np, cnt + 1, this.changeCnt + 1));
            }
            return ret;
        }

        public boolean checkBoundary(int row, int col) {
            return row >= 0 && row < N && col >= 0 && col < N;
        }

        @Override public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", num=" + num +
                    ", piece=" + piece +
                    ", cnt=" + cnt +
                    ", change=" + change +
                    '}';
        }

        @Override
        public int compareTo(Point o) {
            if (cnt != o.cnt) {
                return Integer.compare(cnt, o.cnt);
            } else {
                return Integer.compare(changeCnt, o.changeCnt);
            }
        }
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
        FastReader fr = new FastReader();
        N = fr.nextInt();
        board = new int[N][N];
        points = new Point[N * N + 1];
        visited = new boolean[N][N][N * N + 1][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = fr.nextInt();
                points[board[i][j]] = new Point(i, j);
            }
        }
        solve();
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
