package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ22254_공정_컨설턴트_호석 {

    static int N, K;
    static int[] time;

    static void solve() {
        int start = 1;
        int end = N;

        while (start < end) {
            int mid = (start + end) >>> 1;
            if (func(mid) <= K) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(end);
    }

    static int func(int mid) {
        PriorityQueue<Integer> que = new PriorityQueue<>(mid);
        for (int i = 0; i < mid; i++) {
            que.add(time[i]);
        }
        for (int i = mid; i < N; i++) {
            Integer temp = que.poll();
            if (temp + time[i] > K) return K + 1;
            que.add(temp + time[i]);
        }
        int ret = 0;
        while (!que.isEmpty()) {
            ret = Math.max(ret, que.poll());
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        K = fr.nextInt();
        time = new int[N];
        for (int i = 0; i < N; i++) {
            time[i] = fr.nextInt();
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
