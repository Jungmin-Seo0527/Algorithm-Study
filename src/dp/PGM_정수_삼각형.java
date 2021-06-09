package dp;

import java.util.Arrays;

public class PGM_정수_삼각형 {
    private int rowSZ, colSZ;
    private int[][] triangle, dp;

    public int solution(int[][] triangle) {
        this.rowSZ = triangle.length;
        this.colSZ = triangle[rowSZ - 1].length;
        this.triangle = triangle;

        dp = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            dp[i] = Arrays.copyOf(triangle[i], colSZ);
        }

        return ans();
    }

    private int ans() {
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < rowSZ; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] += getMax(i - 1, j - 1, j);
            }
        }

        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < colSZ; i++) {
            ret = Math.max(ret, dp[rowSZ - 1][i]);
        }
        return ret;
    }

    private int getMax(int row, int col1, int col2) {
        int n1, n2;
        if (col1 < 0 || col1 >= triangle[row].length) {
            n1 = Integer.MIN_VALUE;
        } else {
            n1 = dp[row][col1];
        }
        if (col2 < 0 || col2 >= triangle[row].length) {
            n2 = Integer.MIN_VALUE;
        } else {
            n2 = dp[row][col2];
        }
        return Math.max(n1, n2);
    }
}