package BinarySearch;

import java.io.*;
import java.util.*;

public class BOJ13397_구간_나누기_2 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve(int n) {
        int start = 0;
        int end = n;
        while (start < end) {
            int mid = (start + end) >>> 1;
            int cnt = grouping(mid);
            if (cnt <= M) end = mid;
            else start = mid + 1;
        }
        System.out.println(end);
    }

    static int grouping(int n) {
        int max = arr[0];
        int min = arr[0];
        int ret = 1;
        for (int i = 1; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if (max - min > n) {
                ret++;
                max = arr[i];
                min = arr[i];
            }
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        solve(max - min);
    }
}
