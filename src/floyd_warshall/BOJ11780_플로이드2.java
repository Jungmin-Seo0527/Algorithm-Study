package floyd_warshall;

import java.io.*;
import java.util.*;

public class BOJ11780_플로이드2 {

    static int N, M;
    static final int MAX = (int) 1e5 + 1;
    static int[][] root;
    static int[][] dp;
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        for (int mid = 1; mid <= N; mid++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if (dp[i][j] > dp[i][mid] + dp[mid][j]) {
                        dp[i][j] = dp[i][mid] + dp[mid][j];
                        root[i][j] = root[mid][j];
                    }
                }
            }
        }
        showDP();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                showRoot(i, j);
            }
        }
        System.out.println(ans);
    }

    static void showDP() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dp[i][j] == MAX) ans.append(0).append(" ");
                else ans.append(dp[i][j]).append(" ");
            }
            ans.append("\n");
        }
    }

    static void showRoot(int from, int to) {
        while (from != to) {
            stack.add(to);
            to = root[from][to];
        }
        if (stack.size() == 0) {
            ans.append(0);
        } else {
            stack.add(from);
            ans.append(stack.size()).append(" ");
            while (!stack.isEmpty()) {
                ans.append(stack.pop()).append(" ");
            }
        }
        ans.append("\n");
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        root = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == j) dp[i][j] = 0;
                else dp[i][j] = MAX;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (dp[from][to] > weight) {
                dp[from][to] = weight;
                root[from][to] = from;
            }
        }
    }
}
