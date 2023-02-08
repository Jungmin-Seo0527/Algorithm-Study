package dp;

import java.io.*;
import java.util.*;

public class BOJ9084_동전 {

    static int N, M;
    static int[] arr;
    static int[][] dp;

    static int solve() {
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = arr[i]; j <= M; j++) {
                int sum = 0;
                for (int k = 0; k <= i; k++) {
                    sum += dp[k][j - arr[i]];
                }
                dp[i][j] = sum;
            }
        }
        for (int i = 1; i < N; i++) {
            dp[i][M] += dp[i - 1][M];
        }

        return dp[N - 1][M];
    }

    static int solve2() {
        int[] dp2 = new int[M + 1];
        dp2[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = arr[i]; j <= M; j++) {
                dp2[j] += dp2[j - arr[i]];
            }
        }
        return dp2[M];
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            dp = new int[N + 1][M + 1];
            ans.append(solve2()).append("\n");
        }
        System.out.println(ans);
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
