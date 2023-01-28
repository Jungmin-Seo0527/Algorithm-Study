package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ1175_배달 {

    static int rowSZ, colSZ;
    static char[][] map;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static Point S, C1, C2;

    static void solve() {
        boolean[][][][] visited = new boolean[rowSZ][colSZ][3][4];
        Queue<Point> que = new LinkedList<>();
        que.add(S);
        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                if (cur.dir == i) continue;
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && map[nr][nc] != '#' && !visited[nr][nc][cur.cnt][i]) {
                    visited[nr][nc][cur.cnt][i] = true;
                    if (map[nr][nc] == '.') {
                        que.add(new Point(nr, nc, cur.cnt, i, cur.dist + 1));
                    } else if (map[nr][nc] == 'C') {
                        Point next = new Point(nr, nc, cur.cnt, i, cur.dist + 1);
                        if (next.cnt == 0) { // 첫 배달
                            if (next.equals(C1)) next.cnt = 1;
                            if (next.equals(C2)) next.cnt = 2;
                        } else {
                            if ((next.cnt == 1 && next.equals(C1))
                                    || (next.cnt == 2 && next.equals(C2))) { // 배달 갔던 곳 한번 더감

                            } else {
                                System.out.println(next.dist);
                                return;
                            }
                        }
                        que.add(next);
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Point {
        int r, c, cnt, dir, dist;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
            this.dir = -1;
        }

        public Point(int r, int c, int cnt, int dir, int dist) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
            this.dist = dist;
        }

        public boolean equals(Point point) {
            return this.r == point.r && this.c == point.c;
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            String s = br.readLine();
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    S = new Point(i, j);
                    map[i][j] = '.';
                } else if (map[i][j] == 'C') {
                    if (C1 == null) {
                        C1 = new Point(i, j);
                    } else {
                        C2 = new Point(i, j);
                    }
                }
            }
        }
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input2.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
