package twoPointer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9007_카누_선수 {

    static int T, K, N;
    static int[][] arr;

    static int solve() {
        int ans = 0;
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

        int min = Integer.MAX_VALUE;
        while (idx1 < N * N && idx2 >= 0) {
            int sum = sum1[idx1] + sum2[idx2];

            if (sum != K) {
                int abs = Math.abs(sum - K);
                if (min > abs) {
                    min = abs;
                    ans = sum;
                } else if (min == abs) {
                    if (ans > sum) {
                        ans = sum;
                    }
                }
                if (sum < K) idx1++;
                else if (sum > K) idx2--;
            } else {
                ans = sum;
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = getBufferedReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            arr = new int[4][N];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
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
