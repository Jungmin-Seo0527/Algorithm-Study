package floyd_warshall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1719_택배 {

    static int N, M;
    static int[][] ans, dp;

    static void solve() {
        for (int m = 0; m < N; m++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    if (dp[i][j] > dp[i][m] + dp[m][j]) {
                        dp[i][j] = dp[i][m] + dp[m][j];
                        ans[i][j] = ans[i][m];
                    }
                }
            }
        }

        showAns(ans);
    }

    static void showAns(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (ans[i][j] == -1) {
                    sb.append("-");
                } else sb.append(ans[i][j] + 1);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = getBufferedReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][N];
        ans = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 10000000);
            Arrays.fill(ans[i], -1);
            dp[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            if (dp[v1][v2] > w) {
                dp[v1][v2] = w;
                dp[v2][v1] = w;
                ans[v1][v2] = v2;
                ans[v2][v1] = v1;
            }
        }
        solve();
    }

    private static BufferedReader getBufferedReader() throws IOException {
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
