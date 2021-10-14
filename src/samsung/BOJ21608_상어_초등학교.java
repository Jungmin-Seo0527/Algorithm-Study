package samsung;

import java.io.*;
import java.util.*;

public class BOJ21608_상어_초등학교 {

    static int N;
    static Point[][] map;
    static RelationShip[] relationShips;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        initBlankCnt();
        for (int i = 0; i < N * N; i++) {
            Point seat = findSeat(relationShips[i]);
            sitDown(relationShips[i], seat);
        }
        System.out.println(countScore());
    }

    static int countScore() {
        int ret = 0;

        for (int i = 0; i < N * N; i++) {
            int cnt = 0;
            RelationShip rel = relationShips[i];
            for (int j = 0; j < 4; j++) {
                if (map[rel.r][rel.c].adjFriend[rel.friends[j]]) {
                    cnt++;
                }
            }
            if (cnt == 1) ret += 1;
            else if (cnt == 2) ret += 10;
            else if (cnt == 3) ret += 100;
            else if (cnt == 4) ret += 1000;
        }

        return ret;
    }

    static void sitDown(RelationShip rel, Point point) {
        point.sit(rel.stdNum);
        rel.sit(point.r, point.c);

        for (int i = 0; i < 4; i++) {
            int nr = point.r + vr[i];
            int nc = point.c + vc[i];
            if (checkBoundary(nr, nc)) {
                map[nr][nc].adjFriend[rel.stdNum] = true;
                map[nr][nc].adjBlank--;
            }
        }
    }

    static Point findSeat(RelationShip rel) {
        PriorityQueue<Point> que = new PriorityQueue<>();
        int maxAdjFriendCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Point cur = map[i][j];
                if (cur.student != 0) continue;

                int adjFriendCnt = 0;
                for (int k = 0; k < 4; k++) {
                    if (cur.adjFriend[rel.friends[k]]) {
                        adjFriendCnt++;
                    }
                }
                if (maxAdjFriendCnt == adjFriendCnt) {
                    que.add(cur);
                } else if (maxAdjFriendCnt < adjFriendCnt) {
                    que.clear();
                    que.add(cur);
                    maxAdjFriendCnt = adjFriendCnt;
                }
            }
        }
        return que.poll();
    }

    static void initBlankCnt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i + vr[d];
                    int nc = j + vc[d];
                    if (checkBoundary(nr, nc)) cnt++;
                }
                map[i][j].adjBlank = cnt;
            }
        }
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Point implements Comparable<Point> {
        int r, c;
        boolean[] adjFriend = new boolean[401];
        int adjBlank;
        int student;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void sit(int student) {
            this.student = student;
        }

        @Override
        public int compareTo(Point o) {
            if (adjBlank != o.adjBlank) {
                return o.adjBlank - adjBlank;
            } else if (r != o.r) {
                return r - o.r;
            } else return c - o.c;
        }

        @Override public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static class RelationShip {
        int stdNum, r, c;
        int[] friends;

        public RelationShip(int stdNum, int[] friends) {
            this.stdNum = stdNum;
            this.friends = friends;
        }

        public void sit(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void showMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j].student).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
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
        relationShips = new RelationShip[N * N];
        map = new Point[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new Point(i, j);
            }
        }
        for (int i = 0; i < N * N; i++) {
            int std = fr.nextInt();
            int[] friends = new int[4];
            for (int j = 0; j < 4; j++) {
                friends[j] = fr.nextInt();
            }
            relationShips[i] = new RelationShip(std, friends);
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
