package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ1726_로봇 {

    static final int EMPTY = 0;
    static final int WALL = 1;

    static int N, M;
    static int[][] map;
    static int[] vr = {0, 1, 0, -1};
    static int[] vc = {1, 0, -1, 0};
    static Point start, end;


    static void solve() {
        int answer = 0;
        // boolean[][][] visited = new boolean[N][M][4];
        int[][] visited = new int[N][M];
        Queue<Point> que = new LinkedList<>();
        que.add(start);
        visited[start.r][start.c] |= 1 << start.d;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = -1; i <= 1; i += 2) {
                int nd = (cur.d + i + 4) % 4;

                if ((visited[cur.r][cur.c] & 1 << nd) == 0) {
                    visited[cur.r][cur.c] |= 1 << nd;
                    Point next = new Point(cur.r, cur.c, nd, cur.cnt + 1);
                    if (next.equals(end)) {
                        answer = next.cnt;
                        que.clear();
                        break;
                    }
                    que.add(next);
                }
            }

            for (int k = 1; k <= 3; k++) {
                int nr = cur.r + vr[cur.d] * k;
                int nc = cur.c + vc[cur.d] * k;

                if (checkBoundary(nr, nc) && map[nr][nc] == EMPTY) {
                    if ((visited[nr][nc] & 1 << cur.d) != 0) continue;
                    visited[nr][nc] |= 1 << cur.d;
                    Point next = new Point(nr, nc, cur.d, cur.cnt + 1);
                    if (next.equals(end)) {
                        answer = next.cnt;
                        que.clear();
                        break;
                    }
                    que.add(next);
                } else {
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static int convertInputDir(int inputDir) {
        int ret = -1;
        switch (inputDir) {
            case 1:
                ret = 0;
                break;
            case 2:
                ret = 2;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                ret = 3;
                break;
        }
        return ret;
    }

    static class Point {
        int r, c, d;
        int cnt = 0;

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public Point(int r, int c, int d, int cnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", d=" + d +
                    ", cnt=" + cnt +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c && d == point.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c, d);
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                convertInputDir(Integer.parseInt(st.nextToken())));
        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                convertInputDir(Integer.parseInt(st.nextToken())));
        solve();
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
}
