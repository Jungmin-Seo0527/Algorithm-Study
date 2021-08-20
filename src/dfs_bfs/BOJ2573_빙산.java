package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ2573_빙산 {

    static int rowSZ, colSZ;
    static int[][] map;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        int gc = 0;
        int cnt = 0;
        while (true) {
            List<List<Point>> meltIceList = initMeltIceList();
            gc = cntGroup(meltIceList);
            if (gc == 0 || gc > 1) {
                break;
            }
            cnt++;
            meltIce(meltIceList);
        }

        if (gc == 0) {
            System.out.println(0);
        } else {
            System.out.println(cnt);
        }
    }

    private static List<List<Point>> initMeltIceList() {
        List<List<Point>> metIceList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            metIceList.add(new ArrayList<>());
        }
        return metIceList;
    }


    static int cntGroup(List<List<Point>> metIceList) {
        int ret = 0;
        boolean[][] visited = new boolean[rowSZ][colSZ];

        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    bfs(i, j, visited, metIceList);
                    ret++;
                }
            }
        }
        return ret;
    }

    private static void bfs(int sr, int sc, boolean[][] visited, List<List<Point>> list) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(sr, sc));
        visited[sr][sc] = true;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (!checkBoundary(nr, nc)) continue;

                if (map[nr][nc] == 0) {
                    cnt++;
                } else if (map[nr][nc] > 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    que.add(new Point(nr, nc));
                }
            }
            if (cnt > 0) list.get(cnt).add(cur);
        }
    }

    private static void meltIce(List<List<Point>> list) {
        for (int i = 1; i < 5; i++) {
            int sz = list.get(i).size();
            for (int j = 0; j < sz; j++) {
                Point p = list.get(i).get(j);
                map[p.r][p.c] = Math.max(map[p.r][p.c] - i, 0);
            }
        }
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static void showMap(int[][] map) {
        for (int i = 0; i < rowSZ; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
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
        map = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }
}
