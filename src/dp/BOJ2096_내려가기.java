package dp;

import java.io.*;
import java.util.*;

public class BOJ2096_내려가기 {

    static int N;
    static int[][] arr, dp1, dp2;

    static void solve() {
        for (int i = 0; i < 3; i++) {
            dp1[0][i] = arr[0][i];
            dp2[0][i] = arr[0][i];
        }
        for (int i = 1; i < N; i++) {
            dp1[i][0] = arr[i][0] + Math.max(dp1[i - 1][0], dp1[i - 1][1]);
            dp1[i][1] = arr[i][1] + Math.max(dp1[i - 1][0], Math.max(dp1[i - 1][1], dp1[i - 1][2]));
            dp1[i][2] = arr[i][2] + Math.max(dp1[i - 1][1], dp1[i - 1][2]);

            dp2[i][0] = arr[i][0] + Math.min(dp2[i - 1][0], dp2[i - 1][1]);
            dp2[i][1] = arr[i][1] + Math.min(dp2[i - 1][0], Math.min(dp2[i - 1][1], dp2[i - 1][2]));
            dp2[i][2] = arr[i][2] + Math.min(dp2[i - 1][1], dp2[i - 1][2]);
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp1[N - 1][i]);
            min = Math.min(min, dp2[N - 1][i]);
        }
        System.out.println(max + " " + min);
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][3];
        dp1 = new int[N][3];
        dp2 = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
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
