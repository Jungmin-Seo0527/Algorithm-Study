package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ3197_백조의_호수 {

    static int rowSZ, colSZ;
    static char[][] map;
    static List<Point> swans = new ArrayList<>(2);
    static Queue<Point> water = new LinkedList<>();
    static Queue<Point> moveSwanQue = new LinkedList<>();
    static boolean[][] visited;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        Point start = swans.get(0);
        int ans = 0;
        visited[start.r][start.c] = true;
        moveSwanQue.add(start);
        while (!moveSwan()) {
            ans++;
            meltIce();
        }
        System.out.println(ans);
    }

    static void meltIce() {
        int sz = water.size();
        while (sz-- > 0) {
            Point cur = water.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && map[nr][nc] == 'X') {
                    map[nr][nc] = '.';
                    water.add(new Point(nr, nc));
                }
            }
        }
    }

    static boolean moveSwan() {
        Queue<Point> temp = new LinkedList<>();
        while (!moveSwanQue.isEmpty()) {
            Point cur = moveSwanQue.poll();
            if (cur.r == swans.get(1).r && cur.c == swans.get(1).c) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == 'X') {
                        temp.add(new Point(nr, nc));
                        map[nr][nc] = '.';
                    } else if (map[nr][nc] == '.') {
                        moveSwanQue.add(new Point(nr, nc));
                    }
                }
            }
        }
        moveSwanQue = temp;
        return false;
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

        @Override public String toString() {
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
        map = new char[rowSZ][colSZ];
        visited = new boolean[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == 'L') {
                    map[i][j] = '.';
                    swans.add(new Point(i, j));
                }
                if (map[i][j] != 'X') {
                    water.add(new Point(i, j));
                }

            }
        }
        solve();
    }
}
