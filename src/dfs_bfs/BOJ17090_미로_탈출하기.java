package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ17090_미로_탈출하기 {

    static int rowSZ, colSZ;
    static char[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                visited[i][j] = true;
                dfs(i, j);
                visited[i][j] = false;
            }
        }

        System.out.println(getAns());
    }

    private static int getAns() {
        int ans = 0;
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (dp[i][j] == 1) ans++;
            }
        }
        return ans;
    }

    static int dfs(int r, int c) {
        if (dp[r][c] != -1) return dp[r][c];
        int dist = getDist(r, c);

        int nr = r + vr[dist];
        int nc = c + vc[dist];

        if (checkBoundary(nr, nc)) {
            if (visited[nr][nc]) {
                return dp[r][c] = 0;
            }
            visited[nr][nc] = true;
            int ret = dfs(nr, nc);
            visited[nr][nc] = false;
            return dp[r][c] = ret;
        } else {
            return dp[r][c] = 1;
        }
    }

    private static int getDist(int r, int c) {
        int dist = 0;
        if (map[r][c] == 'U') {
            dist = 1;
        } else if (map[r][c] == 'R') {
            dist = 2;
        } else if (map[r][c] == 'D') {
            dist = 0;
        } else if (map[r][c] == 'L') {
            dist = 3;
        }
        return dist;
    }

    static void showArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
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
