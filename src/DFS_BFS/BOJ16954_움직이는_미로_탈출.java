package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ16954_움직이는_미로_탈출 {

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

    static char[][] map = new char[8][8];
    static int[] v_r = {1, -1, 0, 0, 1, 1, -1, -1, 0};
    static int[] v_c = {0, 0, 1, -1, 1, -1, 1, -1, 0};
    static Queue<Point> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        int depth = 1;
        que.add(new Point(7, 0));
        boolean[][] visited = new boolean[8][8];

        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.row == 0 && cur.col == 7) {
                ans = 1;
                break;
            }

            if (map[cur.row][cur.col] == '.') {
                for (int i = 0; i < 9; i++) {
                    Point next = new Point(cur.row + v_r[i], cur.col + v_c[i]);
                    if (checkBoundary(next) && !visited[next.row][next.col] && map[next.row][next.col] != '#') {
                        que.add(next);
                        visited[next.row][next.col] = true;
                    }
                }
            }

            if (--depth == 0) {
                moveWall();
                depth = que.size();
                for (int i = 0; i < 8; i++) {
                    Arrays.fill(visited[i], false);
                }
            }
        }
        System.out.println(ans);
    }

    static boolean checkBoundary(Point cur) {
        return cur.row >= 0 && cur.row < 8 && cur.col >= 0 && cur.col < 8;
    }

    static void moveWall() {
        char[][] copy = new char[8][8];
        for (int i = 0; i < 8; i++) {
            copy[i] = Arrays.copyOf(map[i], 8);
        }
        for (int i = 1; i < 8; i++) {
            map[i] = Arrays.copyOf(copy[i - 1], 8);
        }
        Arrays.fill(map[0], '.');
    }

    static void showMap() {
        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 8; i++) {
            System.arraycopy(br.readLine().toCharArray(), 0, map[i], 0, 8);
        }
    }
}
