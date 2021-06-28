package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2933_미네랄 {
    static int rowSZ, colSZ;
    static int[] removeHeight;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int d = 1;
        for (int h : removeHeight) {
            removeMineral(rowSZ - h, d);
            d *= -1;
            boolean[][] visited = new boolean[rowSZ][colSZ];
            loop:
            for (int i = rowSZ - 1; i > 0; i--) {
                for (int j = 0; j < colSZ; j++) {
                    if (map[i][j] == 'x' && !visited[i][j]) {
                        List<List<Integer>> list = findSplitedCluster(i, j, visited);
                        if (!list.isEmpty()) {
                            int downDist = getDownDist(list);
                            downCluster(list, downDist);
                            break loop;
                        }
                    }
                }
            }
        }
        showMap(map);
    }

    static void downCluster(List<List<Integer>> list, int downDist) {
        for (int i = 0; i < colSZ; i++) {
            if (list.isEmpty()) {
                continue;
            }
            for (Integer j : list.get(i)) {
                map[j + downDist][i] = map[j][i];
                map[j][i] = '.';
            }
        }
    }

    static int getDownDist(List<List<Integer>> list) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < colSZ; i++) {
            if (list.get(i).isEmpty()) {
                continue;
            }
            int d = 0;
            int r = list.get(i).get(0);
            while (r + d + 1 < rowSZ && map[r + d + 1][i] == '.') {
                d++;
            }
            min = Math.min(min, d);
        }
        return min;
    }

    static List<List<Integer>> findSplitedCluster(int sr, int sc, boolean[][] visited) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < colSZ; i++) {
            list.add(new ArrayList<>());
        }
        Queue<Point> que = new LinkedList<>();
        visited[sr][sc] = true;
        boolean possible = true;
        que.add(new Point(sr, sc));
        list.get(sc).add(sr);
        while (!que.isEmpty()) {
            Point cur = que.poll();

            if (cur.row == rowSZ - 1) {
                possible = false;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.row + vr[i];
                int nc = cur.col + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'x') {
                    visited[nr][nc] = true;
                    Point next = new Point(nr, nc);
                    que.add(next);
                    list.get(next.col).add(next.row);
                }
            }
        }
        if (!possible) {
            list.clear();
            return list;
        }

        list.forEach(l -> l.sort(Collections.reverseOrder()));

        return list;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    /**
     * @param height 막대기 시작 높이
     * @param dir    막대기가 움직이는 방향(1: 왼쪽으로, -1: 오른쪽으로)
     */
    static void removeMineral(int height, int dir) {
        Point p = new Point(height, 0);
        if (dir == -1) {
            p.col = colSZ - 1;
        }
        while (checkBoundary(p.row, p.col) && map[p.row][p.col] == '.') {
            p.col += dir;
        }
        if (checkBoundary(p.row, p.col)) {
            map[p.row][p.col] = '.';
        }
    }

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    static void showMap(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        removeHeight = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            removeHeight[i] = Integer.parseInt(st.nextToken());
        }
    }
}
