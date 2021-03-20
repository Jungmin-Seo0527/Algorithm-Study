package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ10942_팰린드롬 {

    static int N, M;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int doDFS(int left, int right) {
        if (left == right) return dp[left][right] = 1;
        if (dp[left][right] != 0) return dp[left][right];
        if (left + 1 == right && arr[left] == arr[right]) return dp[left][right] = 1;
        if (left + 1 < N && right - 1 >= 0 && arr[left] == arr[right] && doDFS(left + 1, right - 1) == 1)
            return dp[left][right] = 1;
        else return dp[left][right] = -1;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        dp = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int ret = doDFS(S, E);
            if (ret == -1) ret = 0;
            sb.append(ret).append("\n");
        }
        System.out.println(sb);
    }
}
