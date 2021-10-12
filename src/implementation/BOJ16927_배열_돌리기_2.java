package implementation;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BOJ16927_배열_돌리기_2 {

    static int rowSZ, colSZ, R;
    static int[][] arr, ans;

    static void solve() {
        int r = 0;
        int c = 0;
        int rs = rowSZ - 1;
        int cs = colSZ - 1;
        while (rs > r && cs > c) {
            int RR = R % (2 * (rs - r + 1) + 2 * (cs - c + 1 - 2));
            initAns(r++, c++, RR, rs, cs);
            rs -= 1;
            cs -= 1;
        }
        showAns();
    }

    static void initAns(int r, int c, int RR, int rs, int cs) {
        Point p1 = new Point(r * colSZ + c, rowSZ, colSZ);
        Point p2 = new Point(r * colSZ + c, rowSZ, colSZ);
        p1.settingBoundary(r, c, rs, cs);
        p2.settingBoundary(r, c, rs, cs);
        p1.settingDirAndDist(0, colSZ);
        p2.settingDirAndDist(0, colSZ);

        for (int i = 0; i < RR; i++) {
            p1.move();
        }

        while (ans[p1.getR()][p1.getC()] == 0) {
            ans[p1.getR()][p1.getC()] = arr[p2.getR()][p2.getC()];
            p1.move();
            p2.move();
        }
    }

    static class Point {
        int r, c, n, dir, moveDist;
        int sr, sc, er, ec, rowSZ, colSZ;

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }

        public Point(int n, int rowSZ, int colSZ) {
            this.n = n;
            this.rowSZ = rowSZ;
            this.colSZ = colSZ;
            r = n / colSZ;
            c = n % colSZ;
        }

        public void settingBoundary(int sr, int sc, int er, int ec) {
            this.sr = sr;
            this.sc = sc;
            this.er = er;
            this.ec = ec;
        }

        public void settingDirAndDist(int dir, int moveDist) {
            this.dir = dir;
            this.moveDist = moveDist;
        }

        public boolean checkBoundary(int r, int c) {
            return r >= sr && r <= er && c >= sc && c <= ec;
        }

        public void move() {
            int next = n + moveDist;
            int nr = next / colSZ;
            int nc = next % colSZ;
            if (!checkBoundary(nr, nc)) {
                if (dir == 0) moveDist = 1;
                else if (dir == 1) moveDist = -colSZ;
                else if (dir == 2) moveDist = -1;
                else if (dir == 3) moveDist = colSZ;
                dir = (dir + 1) % 4;
                next = n + moveDist;
            }
            n = next;
        }

        public int getR() {
            return r = n / colSZ;
        }

        public int getC() {
            return c = n % colSZ;
        }
    }


    static boolean checkBoundary(int r, int c, int sr, int sc, int er, int ec) {
        return r >= sr && r <= er && c >= sc && c <= ec;
    }

    static void showAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
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

        rowSZ = fr.nextInt();
        colSZ = fr.nextInt();
        R = fr.nextInt();
        arr = new int[rowSZ][colSZ];
        ans = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                arr[i][j] = fr.nextInt();
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
