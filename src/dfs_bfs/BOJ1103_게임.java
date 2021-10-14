package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ1103_게임 {

    static int rowSZ, colSZ, ans;
    static char[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        visited[0][0] = true;
        dfs(0, 0);
        if (dp[0][0] == Integer.MAX_VALUE) dp[0][0] = -1;
        System.out.println(dp[0][0]);
    }

    static int dfs(int r, int c) {
        int ret = 0;
        int dist = map[r][c] - '0';
        if (dp[r][c] != -1) return dp[r][c];

        for (int i = 0; i < 4; i++) {
            int nr = r + vr[i] * dist;
            int nc = c + vc[i] * dist;

            if (checkBoundary(nr, nc) && map[nr][nc] != 'H') {
                if (visited[nr][nc]) {
                    return dp[r][c] = Integer.MAX_VALUE;
                }
                visited[nr][nc] = true;
                ret = Math.max(ret, dfs(nr, nc));
                visited[nr][nc] = false;
            }
        }
        if (ret == Integer.MAX_VALUE) return dp[r][c] = ret;
        return dp[r][c] = ret + 1;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static void showArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
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
        map = new char[rowSZ][colSZ];
        dp = new int[rowSZ][colSZ];
        visited = new boolean[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
        }
        solve();
    }
}
