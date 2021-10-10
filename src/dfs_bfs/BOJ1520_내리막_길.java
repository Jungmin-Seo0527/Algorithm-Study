package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ1520_내리막_길 {

    static int rowSZ, colSZ, ans;
    static int[][] map, dp;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        dfs(new Point(0, 0));
        System.out.println(dp[0][0]);
    }

    static int dfs(Point cur) {
        if (cur.r == rowSZ - 1 && cur.c == colSZ - 1) {
            return dp[cur.r][cur.c] = 1;
        }

        if (dp[cur.r][cur.c] != -1) {
            return dp[cur.r][cur.c];
        }

        int ret = 0;
        for (int i = 0; i < 4; i++) {
            int nr = cur.r + vr[i];
            int nc = cur.c + vc[i];
            if (checkBoundary(nr, nc) && map[cur.r][cur.c] > map[nr][nc]) {
                Point next = new Point(nr, nc);
                ret += dfs(next);
            }
        }
        return dp[cur.r][cur.c] = ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];
        dp = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }
}
