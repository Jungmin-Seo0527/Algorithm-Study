package dp;

import java.io.*;
import java.util.*;

public class BOJ4811_알약 {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long[] catal() {
        long[] dp = new long[31];
        dp[0] = 1;
        for (int i = 1; i < 31; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        long[] dp = catal();
        System.out.println(Arrays.toString(dp));
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            out.append(dp[N]).append("\n");
        }
        System.out.println(out);
    }
}
