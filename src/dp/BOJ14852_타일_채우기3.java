package dp;

import java.io.*;
import java.util.*;

public class BOJ14852_타일_채우기3 {

    static int N;
    static long[] dp;
    static final int MOD = (int) 1e9 + 7;

    static void solve() {
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;
        long sum = 0;
        for (int i = 3; i <= N; i++) {
            sum = (sum + dp[i - 3]) % MOD;
            dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3 + sum * 2) % MOD;
        }
        System.out.println(dp[N]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new long[1000001];
        solve();
    }
}
