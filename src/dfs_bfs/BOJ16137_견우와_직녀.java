package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ16137_견우와_직녀 {
    static int N, M;
    static int[][] map;
    static int[] vr = {1, 0, -1, 0};
    static int[] vc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        System.out.println(doBFS());
    }

    static int doBFS() {
        Queue<State> que = new LinkedList<>();
        State start = new State(new Point(0, 0), 0);
        boolean[][] visited = new boolean[N * N][2];
        visited[0][0] = true;
        que.add(start);

        while (!que.isEmpty()) {
            State cur = que.poll();

            if (cur.point.row == N - 1 && cur.point.col == N - 1) {
                return cur.time;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.point.row + vr[i];
                int nc = cur.point.col + vc[i];
                int nt = cur.time + 1;
                if (checkBoundary(nr, nc)) {
                    if (map[nr][nc] != 0 && nt % map[nr][nc] == 0 && !visited[nr * N + nc][cur.crow]) {
                        if (map[cur.point.row][cur.point.col] > 1 && map[nr][nc] > 1) continue;
                        State next = new State(new Point(nr, nc), nt);
                        next.crow = cur.crow;
                        visited[nr * N + nc][next.crow] = true;
                        que.add(next);
                    } else if (map[nr][nc] == 0 && map[cur.point.row][cur.point.col] == 1 && cur.crow == 0 && nt % M == 0 && !checkCross(nr, nc)) {
                        State next = new State(new Point(nr, nc), nt);
                        next.crow = 1;
                        que.add(next);
                    }
                }
            }
            State next = new State(cur.point, cur.time + 1);
            next.crow = cur.crow;
            que.add(next);
        }
        return 0;
    }

    static boolean checkCross(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr1 = r + vr[i];
            int nc1 = c + vc[i];
            int nr2 = r + vr[(i + 1) % 4];
            int nc2 = c + vc[(i + 1) % 4];
            if (checkBoundary(nr1, nc1) && checkBoundary(nr2, nc2) && map[nr1][nc1] == 0 && map[nr2][nc2] == 0) {
                return true;
            }
        }
        return false;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class State {
        Point point;
        int time;
        int crow;

        public State(Point point, int time) {
            this.point = point;
            this.time = time;
        }

        @Override
        public String toString() {
            return "State{" +
                    "point=" + point +
                    ", time=" + time +
                    ", crow=" + crow +
                    '}';
        }
    }

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
