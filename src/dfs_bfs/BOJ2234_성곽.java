package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2234_성곽 {

    static int rowSZ, colSZ;
    static int[][] map, visited;
    static int[] v_r = {0, -1, 0, 1};
    static int[] v_c = {-1, 0, 1, 0};
    static List<Integer> groupArea = new ArrayList<>();
    static Queue<Integer> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        groupArea.add(0);
        int gn = 1, max = 0, ans = 0;
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (visited[i][j] == 0) {
                    int temp = doBFS(i, j, gn++);
                    groupArea.add(temp);
                    max = Math.max(max, temp);
                }
            }
        }
        System.out.println(groupArea.size() - 1);
        System.out.println(max);
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                ans = Math.max(ans, plusArea(i, j));
            }
        }
        System.out.println(ans);
    }

    static int doBFS(int r, int c, int g) {
        int ret = 0;
        que.add(r * colSZ + c);
        visited[r][c] = g;
        while (!que.isEmpty()) {
            int cur = que.poll();
            ret++;
            int cr = cur / colSZ;
            int cc = cur % colSZ;
            for (int i = 0; i < 4; i++) {
                int nr = cr + v_r[i];
                int nc = cc + v_c[i];
                if (checkBoundary(nr, nc) && (map[cr][cc] & 1 << i) == 0 && visited[nr][nc] == 0) {
                    visited[nr][nc] = g;
                    que.add(nr * colSZ + nc);
                }
            }
        }
        return ret;
    }

    static int plusArea(int r, int c) {
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + v_r[i];
            int nc = c + v_c[i];
            if (checkBoundary(nr, nc) && (map[r][c] & 1 << i) != 0 && visited[r][c] != visited[nr][nc]) {
                ret = Math.max(ret, groupArea.get(visited[r][c]) + groupArea.get(visited[nr][nc]));
            }
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        colSZ = Integer.parseInt(st.nextToken());
        rowSZ = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];
        visited = new int[rowSZ][colSZ];

        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
