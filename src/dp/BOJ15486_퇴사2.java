package dp;

import java.io.*;
import java.util.*;

public class BOJ15486_퇴사2 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if (i + t - 1 <= N) {
                dp[i + t - 1] = Math.max(dp[i - 1] + p, dp[i + t - 1]);
            }
        }
        System.out.println(dp[N]);
    }
}
