package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2151_거울_설치 {
    static int N;
    static char[][] map;
    static List<Point> doors = new ArrayList<>(2);

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Deque<Point> que = new LinkedList<>();
        boolean[][][] visited = new boolean[N][N][4];
        int[] vr = {1, 0, -1, 0};
        int[] vc = {0, -1, 0, 1};
        Point start = doors.get(0);
        Point end = doors.get(1);
        for (int i = 0; i < 4; i++) {
            que.add(new Point(start.row, start.col, 0, i));
        }

        while (!que.isEmpty()) {
            Point cur = que.pollFirst();
            if (visited[cur.row][cur.col][cur.dir]) {
                continue;
            }
            visited[cur.row][cur.col][cur.dir] = true;
            if (cur.row == end.row && cur.col == end.col) {
                System.out.println(cur.mirror);
                return;
            }
            int nr = cur.row + vr[cur.dir];
            int nc = cur.col + vc[cur.dir];
            if (checkPoint(nr, nc)) {
                que.addFirst(new Point(nr, nc, cur.mirror, cur.dir));
                if (map[nr][nc] == '!') {
                    for (int d = 1; d <= 3; d += 2) {
                        int nd = (cur.dir + d) % 4;
                        que.addLast(new Point(nr, nc, cur.mirror + 1, nd));
                    }
                }
            }
        }
    }

    static boolean checkPoint(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N) return false;
        if (map[r][c] == '*') return false;
        return true;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '#') {
                    doors.add(new Point(i, j, 0));
                }
            }
        }
    }

    static class Point {
        int row, col, mirror, dir;

        public Point(int row, int col, int mirror, int dir) {
            this.row = row;
            this.col = col;
            this.mirror = mirror;
            this.dir = dir;
        }

        public Point(int row, int col, int mirror) {
            this.row = row;
            this.col = col;
            this.mirror = mirror;
        }
    }
}
