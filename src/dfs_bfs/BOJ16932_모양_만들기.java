package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ16932_모양_만들기 {

    static int rowSZ, colSZ;
    static int[][] map, group;

    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        List<Integer> groupCnt = new ArrayList<>();
        groupCnt.add(0);

        int groupNum = 1;
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == 1 && group[i][j] == 0) {
                    int b = bfs(i, j, groupNum++);
                    groupCnt.add(b);
                }
            }
        }

        int ret = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (group[i][j] == 0) {
                    int cnt = 1;
                    set.clear();

                    for (int d = 0; d < 4; d++) {
                        int r = i + vr[d];
                        int c = j + vc[d];
                        if (checkBoundary(r, c)) {
                            set.add(group[r][c]);
                        }
                    }
                    for (Integer gn : set) {
                        cnt += groupCnt.get(gn);
                    }
                    ret = Math.max(ret, cnt);
                }
            }
        }
        System.out.println(ret);
    }

    private static int bfs(int sr, int sc, int gn) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(sr, sc));
        group[sr][sc] = gn;
        int cnt = 1;

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && map[nr][nc] == 1 && group[nr][nc] == 0) {
                    group[nr][nc] = gn;
                    que.add(new Point(nr, nc));
                    cnt++;
                }
            }
        }
        return cnt;
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
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];
        group = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }
}
