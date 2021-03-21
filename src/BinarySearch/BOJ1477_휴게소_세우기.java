package BinarySearch;

import java.io.*;
import java.util.*;

public class BOJ1477_휴게소_세우기 {
    static int N, M, L;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int start = 1;
        int end = L;
        while (start < end) {
            int mid = (start + end) >>> 1;
            int cnt = restCnt(mid);
            if (cnt <= M) end = mid;
            else start = mid + 1;
        }
        System.out.println(end);
    }

    static int restCnt(int dist) {
        int ret = 0;
        for (int i = 1; i <= N + 1; i++) {
            ret += (arr[i] - arr[i - 1] - 1) / dist;
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N] = L;
        arr[N + 1] = 0;
        Arrays.sort(arr);
    }
}
