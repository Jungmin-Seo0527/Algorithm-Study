package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ16986_인싸들의_가위바위보 {

    static int N, K, ans;
    static int[] win;
    static boolean[] visited;
    static int[][] arr, game;

    static void solve() {
        dfs(new int[]{0, 0, 0}, new boolean[]{true, true, false});
        System.out.println(ans);
    }

    static void dfs(int[] idx, boolean[] gamer) {
        if (win[0] == K) {
            ans = 1;
            return;
        }
        if (ans == 1 || win[1] == K || win[2] == K) {
            return;
        }
        int g1 = -1;
        int g2 = -1;
        for (int i = 0; i < 3; i++) {
            if (gamer[i]) {
                if (g1 == -1) g1 = i;
                else g2 = i;
            }
        }
        if ((g1 != 0 && idx[g1] == 20) || (idx[g2] == 20)) {
            return;
        }
        if (g1 == 0) {
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    int result = arr[i][game[idx[g2]][g2]];
                    idx[g2]++;
                    visited[i] = true;
                    doGame(idx, g1, g2, result);
                    idx[g2]--;
                    visited[i] = false;
                }
            }
        } else {
            int result = arr[game[idx[g1]][g1]][game[idx[g2]][g2]];
            idx[g1]++;
            idx[g2]++;
            doGame(idx, g1, g2, result);
            idx[g1]--;
            idx[g2]--;
        }
    }

    private static void doGame(int[] idx, int g1, int g2, int result) {
        if (result == 2) {
            win[g1]++;
            boolean[] nexgtGamer = new boolean[3];
            Arrays.fill(nexgtGamer, true);
            nexgtGamer[g2] = false;
            dfs(idx, nexgtGamer);
            win[g1]--;
        } else {
            win[g2]++;
            boolean[] nextGamer = new boolean[3];
            Arrays.fill(nextGamer, true);
            nextGamer[g1] = false;
            dfs(idx, nextGamer);
            win[g2]--;
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
        K = fr.nextInt();
        arr = new int[N + 1][N + 1];
        game = new int[20][3];
        visited = new boolean[N + 1];
        win = new int[3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i + 1][j + 1] = fr.nextInt();
            }
        }
        for (int i = 0; i < 20; i++) {
            game[i][1] = fr.nextInt();
        }
        for (int i = 0; i < 20; i++) {
            game[i][2] = fr.nextInt();
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
