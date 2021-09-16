package prefixSum;

import java.io.*;
import java.util.*;

public class BOJ1451_직사각형으로_나누기 {

    static int rowSZ, colSZ;
    static int[][] map;
    static long[][] sum;

    static long solve() {
        long ret = 0;

        // 1번 케이스(|||)
        for (int i = 1; i <= colSZ - 2; i++) {
            for (int j = i + 1; j <= colSZ - 1; j++) {
                long s1 = prefixSum(1, 1, rowSZ, i);
                long s2 = prefixSum(1, i + 1, rowSZ, j);
                long s3 = prefixSum(1, j + 1, rowSZ, colSZ);
                ret = Math.max(s1 * s2 * s3, ret);

            }
        }

        // 2번 케이스(三)
        for (int i = 1; i <= rowSZ - 2; i++) {
            for (int j = i + 1; j <= rowSZ - 1; j++) {
                long s1 = prefixSum(1, 1, i, colSZ);
                long s2 = prefixSum(i + 1, 1, j, colSZ);
                long s3 = prefixSum(j + 1, 1, rowSZ, colSZ);
                ret = Math.max(s1 * s2 * s3, ret);
            }
        }

        // 3번 케이스 (ㅑ)
        for (int i = 1; i <= colSZ - 1; i++) {
            for (int j = 1; j <= rowSZ - 1; j++) {
                long s1 = prefixSum(1, 1, rowSZ, i);
                long s2 = prefixSum(1, i + 1, j, colSZ);
                long s3 = prefixSum(j + 1, i + 1, rowSZ, colSZ);
                ret = Math.max(s1 * s2 * s3, ret);
            }
        }

        // 4번 케이스(ㅕ)
        for (int i = 1; i <= rowSZ - 1; i++) {
            for (int j = 1; j <= colSZ - 1; j++) {
                long s1 = prefixSum(1, 1, i, j);
                long s2 = prefixSum(i + 1, 1, rowSZ, j);
                long s3 = prefixSum(1, j + 1, rowSZ, colSZ);
                ret = Math.max(s1 * s2 * s3, ret);
            }
        }

        // 5번 케이스(ㅠ)
        for (int i = 1; i <= rowSZ - 1; i++) {
            for (int j = 1; j <= colSZ - 1; j++) {
                long s1 = prefixSum(1, 1, i, colSZ);
                long s2 = prefixSum(i + 1, 1, rowSZ, j);
                long s3 = prefixSum(i + 1, j + 1, rowSZ, colSZ);
                ret = Math.max(s1 * s2 * s3, ret);
            }
        }

        // 6번 케이스(ㅛ)
        for (int i = 1; i <= rowSZ - 1; i++) {
            for (int j = 1; j <= colSZ - 1; j++) {
                long s1 = prefixSum(1, 1, i, j);
                long s2 = prefixSum(1, j + 1, i, colSZ);
                long s3 = prefixSum(i + 1, 1, rowSZ, colSZ);
                ret = Math.max(s1 * s2 * s3, ret);
            }
        }

        return ret;
    }

    static long prefixSum(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r2][c1 - 1] - sum[r1 - 1][c2] + sum[r1 - 1][c1 - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());

        map = new int[rowSZ + 1][colSZ + 1];
        sum = new long[rowSZ + 1][colSZ + 1];
        for (int i = 1; i <= rowSZ; i++) {
            String input = br.readLine();
            for (int j = 0; j < colSZ; j++) {
                map[i][j + 1] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        for (int i = 1; i <= rowSZ; i++) {
            for (int j = 1; j <= colSZ; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + (long) map[i][j];
            }
        }

        System.out.println(solve());
    }
}
