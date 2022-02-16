package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2638_치즈 {

    static int[][] map;
    static int rowSZ, colSZ, chCnt;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static Queue<Point> que = new LinkedList<>();
    static boolean[][] visited;

    static void solve() {
        int time = 0;
        visited = new boolean[rowSZ][colSZ];
        que.add(new Point(0, 0));
        visited[0][0] = true;

        while (!que.isEmpty()) {
            time++;
            int meltCnt = bfs();
            if (chCnt - meltCnt == 0) {
                break;
            }
            chCnt -= meltCnt;
        }
        System.out.println(time);
        System.out.println(chCnt);
    }

    static int bfs() {
        List<Point> meltList = new ArrayList<>();
        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == 1) {
                        meltList.add(new Point(nr, nc));
                    } else {
                        que.add(new Point(nr, nc));
                    }
                }
            }
        }
        for (int i = 0; i < meltList.size(); i++) {
            Point p = meltList.get(i);
            map[p.r][p.c] = 0;
        }
        que.addAll(meltList);
        return que.size();
    }

    static void showMap() {
        for (int i = 0; i < rowSZ; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
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

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = getBufferedReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) chCnt++;
            }
        }
        solve();
    }

    private static BufferedReader getBufferedReader() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
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
