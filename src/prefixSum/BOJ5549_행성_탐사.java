package prefixSum;

import java.io.*;
import java.util.*;

public class BOJ5549_행성_탐사 {

    static int rowSZ, colSZ, K;
    static char[][] map;
    static int[][] jungle, sea, ice;

    static String solve(int a, int b, int c, int d) {
        return prefixSum(jungle, a, b, c, d) + " " +
                prefixSum(sea, a, b, c, d) + " " +
                prefixSum(ice, a, b, c, d) + "\n";
    }

    static int prefixSum(int[][] sum, int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        map = new char[rowSZ + 1][colSZ + 1];

        jungle = new int[rowSZ + 1][colSZ + 1];
        sea = new int[rowSZ + 1][colSZ + 1];
        ice = new int[rowSZ + 1][colSZ + 1];

        for (int i = 0; i < rowSZ; i++) {
            String str = br.readLine();
            for (int j = 0; j < colSZ; j++) {
                char c = str.charAt(j);
                if (c == 'J') {
                    jungle[i + 1][j + 1]++;
                } else if (c == 'I') {
                    ice[i + 1][j + 1]++;
                } else if (c == 'O') {
                    sea[i + 1][j + 1]++;
                }
            }
        }

        for (int i = 1; i <= rowSZ; i++) {
            for (int j = 1; j <= colSZ; j++) {
                jungle[i][j] += jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1];
                ice[i][j] += ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1];
                sea[i][j] += sea[i - 1][j] + sea[i][j - 1] - sea[i - 1][j - 1];

            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            sb.append(solve(a, b, c, d));
        }
        System.out.println(sb);
    }
}
