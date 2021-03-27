package kakao.recruit2021;

import java.util.Arrays;

public class 합승_택시_요금 {
    int n, s, a, b;
    int INF = (int) 1e5;
    int[][] fares, dp;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        this.s = s;
        this.a = a;
        this.b = b;
        this.fares = fares;

        initDP();
        return findAnd();
    }

    private int findAnd() {
        int ans = dp[s][a] + dp[s][b];
        for (int m = 1; m <= n; m++) {
            ans = Math.min(ans, dp[s][m] + dp[m][a] + dp[m][b]);
        }
        return ans;
    }

    private void initDP() {
        dp = new int[n + 1][n + 1];
        INF = INF * n + 1;
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int weight = fare[2];
            dp[from][to] = weight;
            dp[to][from] = weight;
        }

        for (int d = 1; d <= n; d++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) dp[i][j] = 0;
                    else dp[i][j] = Math.min(dp[i][j], dp[i][d] + dp[d][j]);
                }
            }
        }
    }

    private void showDP() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}