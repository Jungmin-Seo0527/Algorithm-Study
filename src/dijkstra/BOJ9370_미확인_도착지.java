package dijkstra;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ9370_미확인_도착지 {

    static int crossCnt, roadCnt, destCnt, start, g, h;
    static boolean[] candidateDest;
    static List<List<Node>> adjList;

    static String solve() {
        StringBuilder ret = new StringBuilder();
        int[] dist = dijkstra();
        for (int i = 1; i <= crossCnt; i++) {
            if (candidateDest[i] && dist[i] != Integer.MAX_VALUE && dist[i] % 2 == 1) {
                ret.append(i).append(" ");
            }
        }
        return ret.toString();
    }

    static int[] dijkstra() {
        int[] dist = new int[crossCnt + 1];
        boolean[] visited = new boolean[crossCnt + 1];
        PriorityQueue<Node> que = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        que.add(new Node(start, 0));

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (int i = 0; i < adjList.get(cur.v).size(); i++) {
                Node next = adjList.get(cur.v).get(i);
                if (!visited[next.v] && dist[next.v] > dist[cur.v] + next.w) {
                    dist[next.v] = dist[cur.v] + next.w;
                    que.add(new Node(next.v, dist[next.v]));
                }
            }
        }
        return dist;
    }

    static void showDist(int[][] dist) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < crossCnt + 1; i++) {
            sb.append(dist[i][0]).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < crossCnt + 1; i++) {
            sb.append(dist[i][1]).append(" ");
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(w, o.w);
        }
    }


    public static void main(String[] args) throws IOException {
        // FastReader fr = getFastReader();
        FastReader fr = new FastReader();
        StringBuilder answer = new StringBuilder();
        int T = fr.nextInt();
        while (T-- > 0) {
            crossCnt = fr.nextInt();
            roadCnt = fr.nextInt();
            destCnt = fr.nextInt();
            start = fr.nextInt();
            g = fr.nextInt();
            h = fr.nextInt();
            adjList = new ArrayList<>(crossCnt + 1);
            candidateDest = new boolean[crossCnt + 1];
            for (int i = 0; i < crossCnt + 1; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int i = 0; i < roadCnt; i++) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                int d = fr.nextInt();
                int temp = 0;
                if ((a == g && b == h) || (a == h && b == g)) temp = -1;

                adjList.get(a).add(new Node(b, d * 2 + temp));
                adjList.get(b).add(new Node(a, d * 2 + temp));
            }
            for (int i = 0; i < destCnt; i++) {
                int x = fr.nextInt();
                candidateDest[x] = true;
            }
            answer.append(solve()).append("\n");
        }
        System.out.println(answer);
    }

    private static FastReader getFastReader() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        FastReader fr = new FastReader("input/input1.txt");
        return fr;
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
