package dijkstra;

import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ1261_알고스팟 {
    static int rowSZ, colSZ;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        System.out.println(solve());
    }

    static int solve() {
        // PriorityQueue<Point> que = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.cnt, p2.cnt));
        Deque<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[rowSZ][colSZ];
        int[] vr = {1, -1, 0, 0};
        int[] vc = {0, 0, 1, -1};
        int ret = 0;
        que.add(new Point(0, 0, 0));
        visited[0][0] = true;

        while (!que.isEmpty()) {
            Point cur = que.pollFirst();
            if (cur.row == rowSZ - 1 && cur.col == colSZ - 1) {
                ret = cur.cnt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                Point next = new Point(cur.row + vr[i], cur.col + vc[i], cur.cnt);
                if (check(next, visited)) {
                    visited[next.row][next.col] = true;
                    if (map[next.row][next.col] == '1') {
                        next.cnt++;
                        que.addLast(next);
                    } else {
                        que.addFirst(next);
                    }
                }
            }
        }
        return ret;
    }

    static boolean check(Point point, boolean[][] visited) {
        if (point.row < 0 || point.row >= rowSZ || point.col < 0 || point.col >= colSZ) return false;
        if (visited[point.row][point.col]) return false;
        return true;
    }

    static class Point {
        int row, col, cnt;

        public Point(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        colSZ = Integer.parseInt(st.nextToken());
        rowSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
}
