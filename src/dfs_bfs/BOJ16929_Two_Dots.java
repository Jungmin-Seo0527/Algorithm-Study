package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ16929_Two_Dots {
    static int rowSZ, colSZ;
    static char[][] map;
    static boolean[][] visited;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        String ret = "No";
        loop:
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (!visited[i][j]) {
                    if (doDFS(i * colSZ + j, -1, i * colSZ + j)) {
                        ret = "Yes";
                        break loop;
                    }
                }
            }
        }
        System.out.println(ret);
    }

    static boolean doDFS(int start, int prev, int cur) {
        int cr = cur / colSZ;
        int cc = cur % colSZ;
        visited[cr][cc] = true;
        for (int i = 0; i < 4; i++) {
            int nr = cr + vr[i];
            int nc = cc + vc[i];
            int next = nr * colSZ + nc;
            if (!checkBoundary(nr, nc) || prev == next || map[start / colSZ][start % colSZ] != map[nr][nc]) {
                continue;
            }
            if (visited[nr][nc]) {
                return true;
            }
            if (!visited[nr][nc] && doDFS(start, cur, next)) {
                return true;
            }
        }
        return false;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        visited = new boolean[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
}
