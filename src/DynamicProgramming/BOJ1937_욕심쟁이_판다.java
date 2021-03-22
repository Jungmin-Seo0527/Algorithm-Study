package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ1937_욕심쟁이_판다 {
    static int N;
    static int[][] map, dp;

    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, doDFS(i, j));
            }
        }
        System.out.println(ans);
    }

    static int doDFS(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];

        int ret = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + v_r[i];
            int nc = c + v_c[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > map[r][c]) {
                ret = Math.max(ret, doDFS(nr, nc) + 1);
            }
        }
        return dp[r][c] = ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
