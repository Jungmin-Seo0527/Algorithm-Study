package floyd_warshall;

import java.util.*;

public class PGM_순위 {
    private int[][] dp;

    public int solution(int n, int[][] results) {
        dp = new int[n + 1][n + 1];
        final int INF = (int) 1e4 + 1;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int[] r : results) {
            dp[r[0]][r[1]] = 1;
        }

        for (int m = 1; m <= n; m++) {
            for (int f = 1; f <= n; f++) {
                for (int t = 1; t <= n; t++) {
                    dp[f][t] = Math.min(dp[f][m] + dp[m][t], dp[f][t]);
                }
            }
        }
        int ans = 0;
        for (int f = 1; f <= n; f++) {
            boolean flag = true;
            for (int t = 1; t <= n; t++) {
                if (dp[f][t] == INF && dp[t][f] == INF) {
                    if (f == t) continue;
                    flag = false;
                    break;
                }
            }
            if (flag) ans++;
        }
        return ans;
    }
}