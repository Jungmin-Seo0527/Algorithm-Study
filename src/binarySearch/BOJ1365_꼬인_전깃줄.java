package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ1365_꼬인_전깃줄 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (list.isEmpty() || list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
            } else {
                list.set(lower_bound(list, arr[i]), arr[i]);
            }
        }
        System.out.println(N - list.size());
    }

    static int lower_bound(List<Integer> list, int target) {
        int start = 0;
        int end = list.size();
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (list.get(mid) >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }
    }
}
