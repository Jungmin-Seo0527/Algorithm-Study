package greedy;

import java.io.*;
import java.util.*;

// BOJ2847_게임을_만든_동준이
/*
    결국 주어진 숫자들을 오름차순으로 만들기 위해 각 숫자들을 빼야되며 그 빼는 숫자들의 합의 최소값을 구하는 문제
    역순으로 탐색을 하며 나보다 내 뒤에 있는 숫자가 더 클시 나-1로 만들어 준다.

 */
public class BOJ2847_게임을_만든_동준이 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        for (int i = N - 1; i > 0; i--) {
            if (arr[i] <= arr[i - 1]) {
                ans += arr[i - 1] - arr[i] + 1;
                arr[i - 1] = arr[i] - 1;
            }
        }
        System.out.println(ans);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}

