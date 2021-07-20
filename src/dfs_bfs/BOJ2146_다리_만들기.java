package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2146_다리_만들기 {

    static int N;
    static int[][] map;
    static int[][] group;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        int ans = Integer.MAX_VALUE;
        int groupNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (group[i][j] == 0 && map[i][j] == 1) {
                    int b = bfs(i, j, groupNum++);
                    // System.out.println(i + " " + j + " " + b);
                    ans = Math.min(ans, b);
                }
            }
        }
        System.out.println(ans);
    }

    static int bfs(int sr, int sc, int gn) {
        Queue<Point> que = new PriorityQueue<>();
        group[sr][sc] = gn;
        que.add(new Point(sr, sc, 0));
        boolean[][] visited = new boolean[N][N];
        int ret = 0;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                int nd = cur.d;
                if (checkBoundary(nr, nc)) {
                    if (map[cur.r][cur.c] == 1) {
                        if (map[nr][nc] == 0 && !visited[nr][nc]) {
                            que.add(new Point(nr, nc, nd + 1));
                            visited[nr][nc] = true;
                        } else if (map[nr][nc] == 1 && !visited[nr][nc]) {
                            que.add(new Point(nr, nc, nd));
                            visited[nr][nc] = true;
                            group[nr][nc] = gn;
                        }
                    } else if (map[cur.r][cur.c] == 0) {
                        if (map[nr][nc] == 0 && !visited[nr][nc]) {
                            que.add(new Point(nr, nc, nd + 1));
                            visited[nr][nc] = true;
                        } else if (map[nr][nc] == 1 && group[nr][nc] != gn) {
                            ret = nd;
                            return ret;
                        }
                    }
                }
            }
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Point implements Comparable<Point> {
        int r, c, d;

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override public int compareTo(Point o) {
            return Integer.compare(this.d, o.d);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        group = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }
}
