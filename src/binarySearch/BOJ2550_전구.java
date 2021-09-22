package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ2550_전구 {

    static int N;
    static int[] switchs, bulbs, bulbs2;

    static void solve() {
        int[] ret = new int[N];
        int[] arr = new int[N];
        int[] idx = new int[N];
        int len = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = bulbs[switchs[i]];
        }
        for (int i = 0; i < N; i++) {
            if (len == 0 || ret[len - 1] < arr[i]) {
                idx[i] = len;
                ret[len++] = arr[i];
            } else {
                int retIdx = lower_bound(ret, len, arr[i]);
                ret[retIdx] = arr[i];
                idx[i] = retIdx;
            }
        }

        int[] ans = new int[len];
        int cur = len - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (idx[i] == cur) {
                ans[cur--] = bulbs2[arr[i]];
            }
        }
        Arrays.sort(ans);
        System.out.println(len);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    static int lower_bound(int[] arr, int len, int target) {
        int start = 0;
        int end = len - 1;

        while (start < end) {
            int mid = (start + end) >>> 1;
            if (arr[mid] >= target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        switchs = new int[N];
        bulbs = new int[N];
        bulbs2 = new int[N];
        for (int i = 0; i < N; i++) {
            switchs[i] = fr.nextInt() - 1;
        }
        for (int i = 0; i < N; i++) {
            int b = fr.nextInt();
            bulbs[b - 1] = i;
            bulbs2[i] = b;

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
