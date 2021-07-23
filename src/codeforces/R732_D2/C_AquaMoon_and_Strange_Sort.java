package codeforces.R732_D2;

import java.io.*;
import java.util.*;

public class C_AquaMoon_and_Strange_Sort {

    static String solve(int n, int[] arr) {
        int[] sortedArr = new int[n];
        System.arraycopy(arr, 0, sortedArr, 0, n);
        Arrays.sort(sortedArr);
        int[][] cnt = new int[sortedArr[n - 1] + 1][2];
        for (int i = 0; i < n; i++) {
            cnt[arr[i]][i % 2]++;
            cnt[sortedArr[i]][i % 2]--;
        }
        for (int i = 0; i < n; i++) {
            if (cnt[arr[i]][0] != 0 || cnt[arr[i]][1] != 0) {
                return "NO";
            }
        }
        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            out.append(solve(N, arr)).append("\n");
        }
        System.out.println(out);
    }
}