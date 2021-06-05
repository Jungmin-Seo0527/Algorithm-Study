package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ6087_레이저_통신 {
    static int rowSZ, colSZ;
    static char[][] map;
    static List<Point> cPoint = new ArrayList<>(2);

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Deque<Point> que = new LinkedList<>();
        boolean[][][] visited = new boolean[rowSZ][colSZ][4];
        int[] vr = {1, 0, -1, 0};
        int[] vc = {0, -1, 0, 1};
        Point start = cPoint.get(0);
        Point end = cPoint.get(1);

        for (int i = 0; i < 4; i++) {
            que.add(new Point(start.row, start.col, start.mirror, i));
        }
        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (visited[cur.row][cur.col][cur.dir]) continue;
            visited[cur.row][cur.col][cur.dir] = true;
            if (cur.row == end.row && cur.col == end.col) {
                System.out.println(cur.mirror);
                break;
            }
            int nr = cur.row + vr[cur.dir];
            int nc = cur.col + vc[cur.dir];
            if (checkPoint(nr, nc)) {
                que.addFirst(new Point(nr, nc, cur.mirror, cur.dir));
                for (int i = 1; i <= 3; i += 2) {
                    int nd = (cur.dir + i) % 4;
                    que.addLast(new Point(nr, nc, cur.mirror + 1, nd));
                }
            }
        }
    }

    static boolean checkPoint(int r, int c) {
        if (r < 0 || r >= rowSZ || c < 0 || c >= colSZ) return false;
        if (map[r][c] == '*') return false;
        return true;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        colSZ = Integer.parseInt(st.nextToken());
        rowSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == 'C') {
                    cPoint.add(new Point(i, j, 0, 0));
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
    }
}
