package floyd_warshall;

import java.io.*;
import java.util.*;

public class BOJ14938_서강그라운드 {

    static int N, M, R;
    static final int MAX = 3001;
    static int[] itemCntArr;
    static int[][] dp;

    static void solve() {
        for (int mid = 1; mid <= N; mid++) {
            for (int from = 1; from <= N; from++) {
                for (int to = 1; to <= N; to++) {
                    if (from == to) {
                        continue;
                    }
                    dp[from][to] = Math.min(dp[from][to], dp[from][mid] + dp[mid][to]);
                }
            }
        }

        int ret = 0;
        for (int i = 1; i <= N; i++) {
            int cnt = itemCntArr[i];
            for (int j = 1; j <= N; j++) {
                if (dp[i][j] <= M) {
                    cnt += itemCntArr[j];
                }
            }
            ret = Math.max(ret, cnt);
        }
        System.out.println(ret);
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        itemCntArr = new int[N + 1];
        dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], MAX);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            itemCntArr[i + 1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            dp[a][b] = l;
            dp[b][a] = l;
        }

        solve();
    }
}
