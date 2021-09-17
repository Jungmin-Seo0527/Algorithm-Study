package prefixSum;

import java.io.*;
import java.util.*;

public class BOJ16973_직사각형_탈출 {

    static int rowSZ, colSZ, H, W;
    static int[][] map, sum;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static Point start, finish;

    static int solve() {
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[rowSZ + 1][colSZ + 1];
        que.add(start);
        visited[start.r][start.c] = true;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && checkBoundary(nr + H - 1, nc + W - 1)
                        && !visited[nr][nc]
                        && prefixSum(nr, nc, nr + H - 1, nc + W - 1) == 0) {
                    if (nr == finish.r && nc == finish.c) return cur.cnt + 1;
                    que.add(new Point(nr, nc, cur.cnt + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        return -1;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 1 && r <= rowSZ && c >= 1 && c <= colSZ;
    }

    static int prefixSum(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1];
    }

    static class Point {
        int r, c, cnt;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", cnt=" + cnt +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new int[rowSZ + 1][colSZ + 1];
        sum = new int[rowSZ + 1][colSZ + 1];

        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i + 1][j + 1] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= rowSZ; i++) {
            for (int j = 1; j <= colSZ; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + map[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int fr = Integer.parseInt(st.nextToken());
        int fc = Integer.parseInt(st.nextToken());
        start = new Point(sr, sc);
        finish = new Point(fr, fc);
        System.out.println(solve());
    }
}
