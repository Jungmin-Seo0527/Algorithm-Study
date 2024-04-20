package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ2866_문자열_잘라내기 {

    static int R, C;
    static char[][] arr;

    static void solve() {
        Set<String> set = new HashSet<>();
        String[] strArr = new String[C];
        Arrays.fill(strArr, "");
        int ans = 0;
        for (int i = R - 1; i >= 0; i--) {
            boolean flag = false;
            for (int j = 0; j < C; j++) {
                strArr[j] = strArr[j] + arr[i][j];
                if (set.contains(strArr[j])) {
                    flag = true;
                } else {
                    set.add(strArr[j]);
                }
            }
            if (!flag) {
                ans = i;
                break;
            }
            set.clear();
        }
        System.out.println(ans);
    }

    static void solve2() {
        int left = 0;
        int right = R - 1;
        int ans = -1;
        char[][] temp = new char[C][R];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temp[j][i] = arr[i][j];
            }
        }
        while (left <= right) {
            int mid = (left + right) >>> 1;
            boolean flag = false;
            Set<String> set = new HashSet<>();
            for (int i = 0; i < C; i++) {
                String cur = String.valueOf(temp[i], mid, R - mid);
                if (set.contains(cur)) {
                    flag = true;
                    break;
                } else {
                    set.add(cur);
                }
            }
            if (flag) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans == -1 ? R - 1 : ans - 1);
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        FastReader fr = new FastReader();
        R = fr.nextInt();
        C = fr.nextInt();
        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = fr.readLine().toCharArray();
        }
        // solve();
        solve2();
    }

    private static BufferedReader readInputFile() throws IOException {
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
            byte[] buf = new byte[1000]; // line length
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
