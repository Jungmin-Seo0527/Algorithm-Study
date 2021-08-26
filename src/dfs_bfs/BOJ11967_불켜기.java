package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ11967_불켜기 {

    static int N, M;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static List<List<Point>> points = new ArrayList<>();

    static void solve() {
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        boolean[][] light = new boolean[N][N];
        boolean[][] stop = new boolean[N][N];

        int ret = 1;
        visited[0][0] = true;
        light[0][0] = true;
        que.add(new Point(0, 0));

        while (!que.isEmpty()) {
            Point cur = que.poll();

            int c = cur.r * N + cur.c;
            for (int i = 0; i < points.get(c).size(); i++) {
                Point nl = points.get(c).get(i);
                if (!light[nl.r][nl.c]) {
                    light[nl.r][nl.c] = true;
                    ret++;
                    if (stop[nl.r][nl.c]) {
                        visited[nl.r][nl.c] = true;
                        que.add(nl);
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc]) {
                    Point next = new Point(nr, nc);
                    if (light[nr][nc]) {
                        visited[nr][nc] = true;
                        que.add(next);
                    } else {
                        stop[nr][nc] = true;
                    }
                }
            }
        }

        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        points = new ArrayList<>();
        for (int i = 0; i < N * N; i++) {
            points.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            points.get(x * N + y).add(new Point(a, b));
        }
        solve();
    }
}