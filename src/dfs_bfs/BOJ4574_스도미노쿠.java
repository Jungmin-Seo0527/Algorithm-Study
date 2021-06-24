package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ4574_스도미노쿠 {
    static int N, SZ = 9, pn;
    static int[][] map;
    static boolean[] visitedDomino;


    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve() {
        doDFS(0);
    }

    static boolean doDFS(int cur) {
        int r = cur / 9;
        int c = cur % 9;

        if (cur == 81) {
            showMap(map, ++pn);
            return true;
        }

        if (map[r][c] != 0) {
            return doDFS(cur + 1);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (check(r, c, i)) {
                    for (int j = 1; j <= 9; j++) {
                        int dn = dominoNo(i, j);
                        if (visitedDomino[dn]) continue;

                        if (checkBoundary(r + 1, c) && map[r + 1][c] == 0 && check(r + 1, c, j)) {
                            visitedDomino[dn] = true;
                            map[r][c] = i;
                            map[r + 1][c] = j;
                            if (doDFS(cur + 1)) {
                                return true;
                            }
                            visitedDomino[dn] = false;
                            map[r][c] = 0;
                            map[r + 1][c] = 0;
                        }
                        if (checkBoundary(r, c + 1) && map[r][c + 1] == 0 && check(r, c + 1, j)) {
                            visitedDomino[dn] = true;
                            map[r][c] = i;
                            map[r][c + 1] = j;
                            if (doDFS(cur + 1)) {
                                return true;
                            }
                            visitedDomino[dn] = false;
                            map[r][c] = 0;
                            map[r][c + 1] = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < 9 && c >= 0 && c < 9;
    }

    static boolean check(int r, int c, int n) {
        for (int i = 0; i < 9; i++) {
            if (map[i][c] == n && i != r) return false;
            if (map[r][i] == n && i != c) return false;
        }

        int start_row = r / 3 * 3;
        int start_col = c / 3 * 3;
        for (int i = start_row; i < start_row + 3; i++) {
            for (int j = start_col; j < start_col + 3; j++) {
                if (i != r && j != c && map[i][j] == n) return false;
            }
        }
        return true;
    }

    static int dominoNo(int d1, int d2) {
        if (d1 > d2) {
            return 10 * d1 + d2;
        } else {
            return 10 * d2 + d1;
        }
    }

    static Point strToPoint(String s) {
        int r = s.charAt(0) - 'A';
        int c = s.charAt(1) - '0' - 1;
        return new Point(r, c);
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

    static void showMap(int[][] map, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("Puzzle").append(" ").append(n).append("\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;

            }
            map = new int[SZ][SZ];
            visitedDomino = new boolean[100];

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                String[] ss = s.split(" ");

                for (int j = 0; j < 4; j++) {
                    int n1 = Integer.parseInt(ss[0]);
                    Point p1 = strToPoint(ss[1]);
                    int n2 = Integer.parseInt(ss[2]);
                    Point p2 = strToPoint(ss[3]);
                    map[p1.row][p1.col] = n1;
                    map[p2.row][p2.col] = n2;
                    int d = dominoNo(n1, n2);
                    visitedDomino[d] = true;
                }
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 9; i++) {
                Point p = strToPoint(st.nextToken());
                map[p.row][p.col] = i;
            }
            solve();
        }
    }
}
