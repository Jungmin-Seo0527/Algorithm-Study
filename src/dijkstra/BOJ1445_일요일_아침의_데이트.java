package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ1445_일요일_아침의_데이트 {

    static int rowSZ, colSZ;
    static char[][] map;
    static Node start, end;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};


    static void solve() {
        boolean[][] visited = new boolean[rowSZ][colSZ];
        int[][][] dist = new int[rowSZ][colSZ][2];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(start);
        dist[start.r][start.c][0] = 0;
        dist[start.r][start.c][1] = 0;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.equals(end)) {
                System.out.println(cur.w1 + " " + cur.w2);
                return;
            }
            if (visited[cur.r][cur.c]) continue;
            visited[cur.r][cur.c] = true;
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc)) {
                    Node next = new Node(nr, nc, cur.w1, cur.w2);
                    if (map[nr][nc] == 'g') next.w1++;
                    else if (map[nr][nc] == '.') next.w2 += isAdjacentGarbage(nr, nc);
                    if (dist[nr][nc][0] > next.w1) {
                        dist[nr][nc][0] = next.w1;
                        dist[nr][nc][1] = next.w2;
                        que.add(next);
                    } else if (dist[nr][nc][0] == next.w1 && dist[nr][nc][1] > next.w2) {
                        dist[nr][nc][1] = next.w2;
                        que.add(next);
                    }
                }
            }
        }
    }

    static int isAdjacentGarbage(int r, int c) {
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + vr[i];
            int nc = c + vc[i];
            if (checkBoundary(nr, nc) && map[nr][nc] == 'g') {
                ret++;
                break;
            }
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    public static class Node implements Comparable<Node> {
        int r, c, w1, w2;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Node(int r, int c, int w1, int w2) {
            this.r = r;
            this.c = c;
            this.w1 = w1;
            this.w2 = w2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return r == node.r && c == node.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c, w1, w2);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", w1=" + w1 +
                    ", w2=" + w2 +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            if (this.w1 != o.w1) {
                return Integer.compare(this.w1, o.w1);
            } else {
                return Integer.compare(this.w2, o.w2);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            String s = br.readLine();
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') start = new Node(i, j);
                else if (map[i][j] == 'F') end = new Node(i, j);
            }
        }

        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input2.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
