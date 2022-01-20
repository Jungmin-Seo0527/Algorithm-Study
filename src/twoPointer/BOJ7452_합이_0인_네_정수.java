package twoPointer;

import java.io.*;
import java.util.*;

public class BOJ7452_합이_0인_네_정수 {

    static int N;
    static int[][] arr;

    /**
     * meet in the middle
     */
    static void solve() {

        int[] sum1 = new int[N * N];
        int[] sum2 = new int[N * N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum1[i * N + j] = arr[0][i] + arr[1][j];
                sum2[i * N + j] = arr[2][i] + arr[3][j];
            }
        }

        Arrays.sort(sum1);
        Arrays.sort(sum2);
        int idx1 = 0;
        int idx2 = N * N - 1;
        long ans = 0;
        while (idx1 < N * N && idx2 >= 0) {
            int sum = sum1[idx1] + sum2[idx2];

            if (sum == 0) {
                int s1 = sum1[idx1];
                int s2 = sum2[idx2];
                long c1 = 0;
                long c2 = 0;
                while (idx1 < N * N && sum1[idx1] == s1) {
                    idx1++;
                    c1++;
                }
                while (idx2 >= 0 && sum2[idx2] == s2) {
                    idx2--;
                    c2++;
                }
                ans += c1 * c2;
            } else {
                if (sum > 0) {
                    idx2--;
                } else {
                    idx1++;
                }
            }
        }
        System.out.println(ans);
    }

    /**
     * binary search
     */
    static void solve2() {
        long ans = 0;
        int[] sum = new int[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum[i * N + j] = arr[0][i] + arr[1][j];
            }
        }
        Arrays.sort(sum);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int s = arr[2][i] + arr[3][j];
                ans += (long) upper_bound(sum, -s) - lower_bound(sum, -s);
            }
        }
        System.out.println(ans);
    }

    static int lower_bound(int[] arr, int n) {
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (arr[mid] >= n) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    static int upper_bound(int[] arr, int n) {
        int start = 0;
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (arr[mid] > n) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = getBufferedReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[4][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        // solve();
        solve2();
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
