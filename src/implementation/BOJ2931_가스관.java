package implementation;

import java.io.*;
import java.util.*;

public class BOJ2931_가스관 {

    static int rowSZ, colSZ;
    static char[][] map;
    static boolean[][][] visited;
    static char[] pipe = {'-', '|', '+', '1', '2', '3', '4'};
    static Point M, Z;

    static int[] vr = {0, 1, 0, -1};
    static int[] vc = {1, 0, -1, 0};

    static void solve() {
        Point start = new Point(M.r, M.c, M.dir);
        for (int i = 0; i < 4; i++) {
            int nr = start.r + vr[i];
            int nc = start.c + vc[i];
            if (checkBoundary(nr, nc) && map[nr][nc] != '.') {
                if (i == 0 &&
                        (map[nr][nc] == '4' || map[nr][nc] == '3' || map[nr][nc] == '-' || map[nr][nc] == '+')) {
                    start.dir = i;
                } else if (i == 1 &&
                        (map[nr][nc] == '2' || map[nr][nc] == '3' || map[nr][nc] == '+' || map[nr][nc] == '|')) {
                    start.dir = i;
                } else if (i == 2 &&
                        (map[nr][nc] == '1' || map[nr][nc] == '2' || map[nr][nc] == '+' || map[nr][nc] == '-')) {
                    start.dir = i;
                } else if (i == 3 &&
                        (map[nr][nc] == '1' || map[nr][nc] == '4' || map[nr][nc] == '+' || map[nr][nc] == '|')) {
                    start.dir = i;
                }
            }
        }
        Point cur = new Point(start.r + vr[start.dir], start.c + vc[start.dir], start.dir);

        while (moveWater(cur, visited) == 1) {
        }
        Point flag = new Point(cur.r, cur.c, cur.dir);

        for (int i = 0; i < 7; i++) {
            map[cur.r][cur.c] = pipe[i];
            boolean[][][] copyVisited = copyVisited();
            int temp = 0;
            // System.out.println(pipe[i]);
            while ((temp = moveWater(cur, copyVisited)) == 1) {
            }
            // System.out.println();
            if (temp == 2) {
                System.out.println((flag.r + 1) + " " + (flag.c + 1) + " " + pipe[i]);
                break;
            }
            cur.r = flag.r;
            cur.c = flag.c;
            cur.dir = flag.dir;
        }

    }

    private static int moveWater(Point cur, boolean[][][] visited) {
        if (!checkBoundary(cur.r, cur.c)) {
            return -1;
        }

        if (visited[cur.r][cur.c][cur.dir]) {
            return -1;
        }

        if (map[cur.r][cur.c] == '.') {
            return 0;
        }

        if (map[cur.r][cur.c] == 'Z') {
            return 2;
        }

        visited[cur.r][cur.c][cur.dir] = true;

        if (map[cur.r][cur.c] == '|') {
            if (cur.dir == 1 || cur.dir == 3) {
                movePoint(cur);
                return 1;
            }
        }

        if (map[cur.r][cur.c] == '-') {
            if (cur.dir == 0 || cur.dir == 2) {
                movePoint(cur);
                return 1;
            }
        }

        if (map[cur.r][cur.c] == '+') {
            movePoint(cur);
            return 1;
        }

        if (map[cur.r][cur.c] == '1') {
            if (cur.dir == 2) {
                cur.dir = 1;
                movePoint(cur);
                return 1;
            }
            if (cur.dir == 3) {
                cur.dir = 0;
                movePoint(cur);
                return 1;
            }
        }

        if (map[cur.r][cur.c] == '2') {
            if (cur.dir == 1) {
                cur.dir = 0;
                movePoint(cur);
                return 1;
            }
            if (cur.dir == 2) {
                cur.dir = 3;
                movePoint(cur);
                return 1;
            }
        }

        if (map[cur.r][cur.c] == '3') {
            if (cur.dir == 0) {
                cur.dir = 3;
                movePoint(cur);
                return 1;
            }
            if (cur.dir == 1) {
                cur.dir = 2;
                movePoint(cur);
                return 1;
            }
        }

        if (map[cur.r][cur.c] == '4') {
            if (cur.dir == 0) {
                cur.dir = 1;
                movePoint(cur);
                return 1;
            }
            if (cur.dir == 3) {
                cur.dir = 2;
                movePoint(cur);
                return 1;
            }
        }

        return -1;
    }

    static boolean[][][] copyVisited() {
        boolean[][][] ret = new boolean[rowSZ][colSZ][4];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                for (int k = 0; k < 4; k++) {
                    ret[i][j][k] = visited[i][j][k];
                }
            }
        }
        return ret;
    }

    static void movePoint(Point point) {
        point.r += vr[point.dir];
        point.c += vc[point.dir];
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Point {
        int r, c, dir;

        public Point(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        @Override public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        visited = new boolean[rowSZ][colSZ][4];

        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == 'M') {
                    M = new Point(i, j, 0);
                } else if (map[i][j] == 'Z') {
                    Z = new Point(i, j, 0);
                }
            }
        }
        solve();
    }
}
