package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ11049_행렬_곱셈_순서 {

    static class Matrix {
        int row, col;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N;
    static int[][] dp;
    static Matrix[] matrix;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        System.out.println(doDFS(0, N - 1));
    }

    static int doDFS(int left, int right) {
        if (left == right) return 0;
        if (dp[left][right] != 0) return dp[left][right];

        int ret = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            ret = Math.min(ret, doDFS(left, i) + doDFS(i + 1, right)
                    + matrix[left].row * matrix[i].col * matrix[right].col);
        }
        return dp[left][right] = ret;
    }

    static void solve2() {
        for (int d = 1; d < N; d++) {
            for (int l = 0; l + d < N; l++) {
                int r = l + d;
                dp[l][r] = Integer.MAX_VALUE;
                for (int mid = l; mid < r; mid++) {
                    dp[l][r] = Math.min(dp[l][r],
                            dp[l][mid] + dp[mid + 1][r] + matrix[l].row * matrix[mid].col * matrix[r].col);
                }
            }
        }
        System.out.println(dp[0][N - 1]);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        matrix = new Matrix[N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[i] = new Matrix(r, c);
        }
    }
}
