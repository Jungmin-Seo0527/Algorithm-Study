package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ18809_Gaaaaaaaaaarden {

    static final int GREEN = 3;
    static final int RED = 4;
    static int rowSZ, colSZ, rCnt, gCnt, ans;

    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static int[][] map;
    static List<Point> brown = new ArrayList<>();

    static void solve() {
        dfs(new ArrayList<>(), new ArrayList<>(), 0);
        System.out.println(ans);
    }

    static void dfs(List<Point> green, List<Point> red, int curIdx) {
        if (green.size() == gCnt && red.size() == rCnt) {
            int[][] tempMap = makeMap(green, red);
            ans = Math.max(ans, bfs(tempMap, green, red));
            return;
        }
        if (curIdx == brown.size()) {
            return;
        }

        List<Point> nextGreen = new ArrayList<>(green);
        List<Point> nextRed = new ArrayList<>(red);
        nextGreen.add(brown.get(curIdx));
        nextRed.add(brown.get(curIdx));
        if (nextGreen.size() <= gCnt) dfs(nextGreen, red, curIdx + 1);
        if (nextRed.size() <= rCnt) dfs(green, nextRed, curIdx + 1);
        dfs(green, red, curIdx + 1);
    }

    static int bfs(int[][] map, List<Point> green, List<Point> red) {
        Queue<Point> que = new LinkedList<>();
        Queue<Point> que2 = new LinkedList<>();
        boolean[][] flower = new boolean[rowSZ][colSZ];
        int[][] time = new int[rowSZ][colSZ];
        int cnt = 0;

        for (Point g : green) {
            Point gg = new Point(g.r, g.c);
            gg.color = GREEN;
            que.add(gg);
        }
        for (Point r : red) {
            Point rr = new Point(r.r, r.c);
            rr.color = RED;
            que.add(rr);
        }

        while (!que.isEmpty()) {
            Point top = que.peek();

            while (!que.isEmpty() && que.peek().time == top.time) {
                que2.add(que.poll());
            }

            while (!que2.isEmpty()) {
                Point cur = que2.poll();
                if (flower[cur.r][cur.c]) continue;

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + vr[i];
                    int nc = cur.c + vc[i];
                    if (checkBoundary(nr, nc) && map[nr][nc] != 0) {
                        if (map[nr][nc] == GREEN && cur.color == RED
                                && time[nr][nc] == cur.time + 1 && !flower[nr][nc]) {
                            flower[nr][nc] = true;
                            cnt++;
                        } else if (map[nr][nc] == RED && cur.color == GREEN
                                && time[nr][nc] == cur.time + 1 && !flower[nr][nc]) {
                            flower[nr][nc] = true;
                            cnt++;
                        } else if (map[nr][nc] == 1 || map[nr][nc] == 2) {
                            time[nr][nc] = cur.time + 1;
                            Point next = new Point(nr, nc, cur.time + 1, cur.color);
                            map[nr][nc] = cur.color;
                            que.add(next);
                        }
                    }
                }
            }
        }
        return cnt;
    }

    static int[][] makeMap(List<Point> green, List<Point> red) {
        int[][] ret = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            System.arraycopy(map[i], 0, ret[i], 0, colSZ);
        }
        for (int i = 0; i < green.size(); i++) {
            Point g = green.get(i);
            ret[g.r][g.c] = GREEN;
        }
        for (int i = 0; i < red.size(); i++) {
            Point r = red.get(i);
            ret[r.r][r.c] = RED;
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Point {
        int r, c, time, color;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int time, int color) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.color = color;
        }

        @Override public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", time=" + time +
                    ", color=" + color +
                    '}';
        }
    }

    static void showMap(int[][] map) {
        for (int i = 0; i < rowSZ; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input2.txt";
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
        gCnt = Integer.parseInt(st.nextToken());
        rCnt = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];

        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    brown.add(new Point(i, j));
                }
            }
        }
        solve();
    }
}
