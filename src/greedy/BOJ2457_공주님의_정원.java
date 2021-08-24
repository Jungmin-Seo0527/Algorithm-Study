package greedy;

import java.io.*;
import java.util.*;

public class BOJ2457_공주님의_정원 {

    static int N;
    static List<Period> periods;

    static void solve() {
        int cnt = 0;
        int lastEnd = 301;
        int maxEnd = 0;

        Collections.sort(periods);
        for (int i = 0; i < N; i++) {
            Period cur = periods.get(i);
            if (lastEnd >= cur.start) {
                maxEnd = Math.max(maxEnd, cur.end);
            } else {
                if (maxEnd == 0) {
                    cnt = 0;
                    break;
                }
                lastEnd = maxEnd;
                maxEnd = 0;
                cnt++;
                if (lastEnd > 1130) {
                    break;
                }
                i--;
            }
        }
        System.out.println(maxEnd != 0 ? maxEnd > 1130 ? cnt + 1 : 0 : cnt);
    }

    static class Period implements Comparable<Period> {
        int start, end;

        public Period(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override public String toString() {
            return "Period{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        @Override
        public int compareTo(Period o) {
            return this.start - o.start;
        }
    }


    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        periods = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            int sm = fr.nextInt();
            int sd = fr.nextInt();
            int em = fr.nextInt();
            int ed = fr.nextInt();
            periods.add(new Period(sm * 100 + sd, em * 100 + ed));
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
