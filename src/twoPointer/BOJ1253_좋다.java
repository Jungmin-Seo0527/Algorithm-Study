package twoPointer;

import java.io.*;
import java.util.*;

public class BOJ1253_좋다 {

    static int N;
    static int[] arr;

    static void solve() {
        Arrays.sort(arr);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (good(i)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static boolean good(int idx) {
        int g = arr[idx];
        int left = 0;
        int right = N - 1;
        if (left == idx) left++;
        if (right == idx) right--;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum < g) {
                left++;
                if (left == idx) left++;
            } else if (sum > g) {
                right--;
                if (right == idx) right--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solve();
    }
}
