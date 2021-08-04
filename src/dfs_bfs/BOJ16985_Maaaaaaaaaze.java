package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16985_Maaaaaaaaaze {

    static int rowSZ, colSZ, heiSZ;
    static int ans = Integer.MAX_VALUE;
    static int[][][] map, inputMap;
    static boolean[] visitedMap;

    static int[] vr = {1, -1, 0, 0, 0, 0};
    static int[] vc = {0, 0, 1, -1, 0, 0};
    static int[] vh = {0, 0, 0, 0, 1, -1};

    static void solve() {
        dfs0(0);
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
    }

    static void dfs0(int idx) {
        if (idx == 5) {
            dfs(0);
            return;
        }
        for (int i = 0; i < heiSZ; i++) {
            if (!visitedMap[i]) {
                visitedMap[i] = true;
                copy2Map(inputMap[i], map[idx]);
                dfs0(idx + 1);
                visitedMap[i] = false;
            }
        }
    }

    static void dfs(int curHeight) {
        if (curHeight == heiSZ) {
            int d = bfs();
            if (d != -1) {
                ans = Math.min(ans, d);
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            rotateMap(map[curHeight]);
            dfs(curHeight + 1);
        }
    }

    static int bfs() {
        int ret = -1;
        if (map[0][0][0] == 0) {
            return ret;
        }
        boolean[][][] visited = new boolean[heiSZ][rowSZ][colSZ];
        Queue<Point> que = new LinkedList<>();
        visited[0][0][0] = true;
        que.add(new Point(0, 0, 0, 0));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.h == heiSZ - 1 && cur.r == rowSZ - 1 && cur.c == colSZ - 1) {
                ret = cur.d;
                break;

            }
            for (int i = 0; i < 6; i++) {
                int nh = cur.h + vh[i];
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nh, nr, nc) && map[nh][nr][nc] == 1 && !visited[nh][nr][nc]) {
                    visited[nh][nr][nc] = true;
                    que.add(new Point(nh, nr, nc, cur.d + 1));
                }
            }
        }
        return ret;
    }

    static boolean checkBoundary(int h, int r, int c) {
        return h >= 0 && h < heiSZ && r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static void rotateMap(int[][] inputMap) {
        int[][] rotatedMap = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                rotatedMap[i][j] = inputMap[rowSZ - 1 - j][i];
            }
        }
        copy2Map(rotatedMap, inputMap);
    }

    static void copy2Map(int[][] src, int[][] dest) {
        for (int i = 0; i < rowSZ; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, colSZ);
        }
    }

    static class Point {
        int h, r, c, d;

        public Point(int h, int r, int c, int d) {
            this.h = h;
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        rowSZ = 5;
        colSZ = 5;
        heiSZ = 5;
        map = new int[heiSZ][rowSZ][colSZ];
        inputMap = new int[heiSZ][rowSZ][colSZ];
        visitedMap = new boolean[heiSZ];
        for (int h = 0; h < heiSZ; h++) {
            for (int r = 0; r < rowSZ; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < colSZ; c++) {
                    inputMap[h][r][c] = Integer.parseInt(st.nextToken());
                }
            }
        }
        solve();
    }
}
