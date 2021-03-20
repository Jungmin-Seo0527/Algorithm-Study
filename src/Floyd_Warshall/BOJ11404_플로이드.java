package Floyd_Warshall;

import java.io.*;
import java.util.*;

public class BOJ11404_플로이드 {

    static int N, M;
    static final int INF = (int) 1e7 + 1;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        initDP();
        showAns();
    }

    static void initDP() {
        for (int mid = 1; mid <= N; mid++) {
            for (int from = 1; from <= N; from++) {
                for (int to = 1; to <= N; to++) {
                    if (from == to) continue;
                    dp[from][to] = Math.min(dp[from][to], dp[from][mid] + dp[mid][to]);
                }
            }
        }
    }

    static void showAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dp[i][j] == INF) dp[i][j] = 0;
                sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dp[from][to] = Math.min(dp[from][to], weight);
        }
    }
}
