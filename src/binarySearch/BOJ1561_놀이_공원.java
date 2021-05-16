package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ1561_놀이_공원 {
    static int N, M;
    static long max;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        if (N <= M) {
            System.out.println(N);
            return;
        }
        long start = 0;
        long end = N * max;
        while (start < end) {
            long mid = (start + end) >>> 1;
            long cnt = cntChildren(mid);
            if (cnt >= N) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(lastPlay(end));
    }

    static long lastPlay(long time) {
        long cnt = M;
        for (int i = 0; i < M; i++) {
            cnt += (time - 1) / arr[i];
        }
        for (int i = 0; i < M; i++) {
            if (time % arr[i] == 0) {
                cnt++;
            }
            if (cnt == N) {
                return i + 1;
            }
        }
        return -1;
    }

    static long cntChildren(long time) {
        long cnt = M;
        for (int i = 0; i < M; i++) {
            cnt += time / arr[i];
        }
        return cnt;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
    }
}
