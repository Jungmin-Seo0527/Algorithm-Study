package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ9376_탈옥 {

    static int rowSZ, colSZ;
    static char[][] inputMap, map;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    private static int solve() {
        settingMap();

        List<Point> prisoners = getPrisoners();

        int[][] v1 = bfs(rowSZ, colSZ, map, new Point(0, 0, 0));
        int[][] v2 = bfs(rowSZ, colSZ, map, prisoners.get(0));
        int[][] v3 = bfs(rowSZ, colSZ, map, prisoners.get(1));

        return getMinDestroyWall(v1, v2, v3);
    }

    private static int getMinDestroyWall(int[][] m1, int[][] m2, int[][] m3) {
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (m1[i][j] != -1 && m2[i][j] != -1 && m3[i][j] != -1) {
                    int temp = m1[i][j] + m2[i][j] + m3[i][j];
                    if (map[i][j] == '#') {
                        temp -= 2;
                    }
                    ret = Math.min(ret, temp);
                }
            }
        }
        return ret;
    }

    private static List<Point> getPrisoners() {
        List<Point> prisoners = new ArrayList<>();
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == '$') {
                    prisoners.add(new Point(i, j, 0));
                }
            }
        }
        return prisoners;
    }

    private static void settingMap() {
        map = new char[rowSZ + 2][colSZ + 2];
        for (int i = 0; i < rowSZ; i++) {
            System.arraycopy(inputMap[i], 0, map[i + 1], 1, colSZ);
        }
        rowSZ += 2;
        colSZ += 2;
        for (int i = 0; i < colSZ; i++) {
            map[0][i] = '.';
            map[rowSZ - 1][i] = '.';
        }
        for (int i = 0; i < rowSZ; i++) {
            map[i][0] = '.';
            map[i][colSZ - 1] = '.';
        }
    }

    private static int[][] bfs(int rowSZ, int colSZ, char[][] map, Point start) {
        int[][] visited = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            Arrays.fill(visited[i], -1);
        }
        Deque<Point> que = new LinkedList<>();
        que.add(start);
        visited[start.r][start.c] = start.w;
        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                int nw = cur.w;
                if (checkBoundary(rowSZ, colSZ, nr, nc) &&
                        map[nr][nc] != '*' &&
                        visited[nr][nc] == -1) {
                    if (map[nr][nc] == '#') {
                        nw++;
                        que.addLast(new Point(nr, nc, nw));
                    } else {
                        que.addFirst(new Point(nr, nc, nw));
                    }
                    visited[nr][nc] = nw;
                }
            }
        }
        return visited;
    }

    static boolean checkBoundary(int rs, int cs, int r, int c) {
        return r >= 0 && r < rs && c >= 0 && c < cs;
    }

    static class Point {
        int r, c, w;

        public Point(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            rowSZ = Integer.parseInt(st.nextToken());
            colSZ = Integer.parseInt(st.nextToken());
            inputMap = new char[rowSZ][colSZ];
            for (int i = 0; i < rowSZ; i++) {
                inputMap[i] = br.readLine().toCharArray();
            }
            out.append(solve()).append("\n");
        }
        System.out.println(out);
    }
}
