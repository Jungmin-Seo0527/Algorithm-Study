package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ1309_동물원 {

    static int N;
    static final int MOD = 9901;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        for (int i = 0; i < 3; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = (dp[i - 1][(j + 1) % 3] + dp[i - 1][(j + 2) % 3]) % MOD;
            }
            dp[i][0] += dp[i - 1][0];
            dp[i][0] %= MOD;
        }

        int ans = 0;
        for (int i = 0; i < 3; i++) {
            ans += dp[N - 1][i];
            ans %= MOD;
        }
        System.out.println(ans);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N][3];
    }
}
