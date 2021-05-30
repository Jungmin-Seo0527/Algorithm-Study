package dp;

import java.io.*;
import java.util.*;

public class BOJ2133_타일_채우기 {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        if (N % 2 == 1) {
            System.out.println(0);
        } else {
            dp[0] = 1;
            dp[2] = 3;
            for (int i = 4; i <= N; i += 2) {
                dp[i] = dp[2] * dp[i - 2];
                for (int j = 4; j <= i; j += 2) {
                    dp[i] += 2 * dp[i - j];
                }
            }
            System.out.println(dp[N]);
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
    }
}
