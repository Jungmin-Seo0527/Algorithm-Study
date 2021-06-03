package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ1377_버블_소트 {
    static int N;
    static int[] arr, sortedArr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Arrays.sort(sortedArr);
        int max = 0;
        for (int i = 0; i < N; i++) {
            int idx = upper_bound(arr[i], sortedArr);
            if (sortedArr[i] != arr[i]) {
                idx--;
            }
            max = Math.max(max, i - idx);
        }
        System.out.println(max + 1);
    }

    static int upper_bound(int target, int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + end >>> 1;
            if (arr[mid] > target) {
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
        sortedArr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sortedArr[i] = arr[i];
        }
    }
}
