package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ14497_주난의_난 {

    static final char EMPTY = '0';
    static final char WALL = '1';
    static final char START = '#';
    static final char END = '*';

    static int N, M;
    static char[][] map;
    static int[][] dist;
    static Point start, end;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        int ans = 0;
        Deque<Point> que = new LinkedList<>();
        que.add(start);
        dist[start.r][start.c] = 0;
        while (!que.isEmpty()) {
            Point cur = que.pollFirst();

            if (cur.equals(end)) {
                ans = cur.w + 1;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc)) {
                    int nw = cur.w;
                    boolean wall = false;
                    if (map[nr][nc] == WALL) {
                        nw++;
                        wall = true;
                    }
                    if (dist[nr][nc] > nw) {
                        dist[nr][nc] = nw;
                        if (wall) {
                            que.addLast(new Point(nr, nc, nw));
                        } else {
                            que.addFirst(new Point(nr, nc, nw));
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static class Point {
        int r, c, w;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
            this.w = 0;
        }

        public Point(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int er = Integer.parseInt(st.nextToken());
        int ec = Integer.parseInt(st.nextToken());
        start = new Point(sr - 1, sc - 1);
        end = new Point(er - 1, ec - 1);
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input3.txt";
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
