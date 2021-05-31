package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ1654_랜선_자르기 {
    static int N, K;
    static long[] arr;
    static long max;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        long start = 0;
        long end = Integer.MAX_VALUE;
        while (start <= end) {
            long mid = (start + end) >>> 1;
            if (count(mid) < N) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(end);
    }

    static long count(long m) {
        long ret = 0;
        for (int i = 0; i < K; i++) {
            ret += arr[i] / m;
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new long[N];
        for (int i = 0; i < K; i++) {
            arr[i] = Long.parseLong(br.readLine());
            max = Math.max(max, arr[i]);
        }
    }
}
