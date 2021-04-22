package greedy;

import java.io.*;
import java.util.*;

// BOJ11399_ATM
// 사람들이 기다리는 시간의 총 합을 최소화 하는 문제
// 사람과 사람간의 걸리는 시간이 최소가 되도록 정렬해야 되는데 이는 곧 오름차순으로 정렬하면 된다

public class BOJ11399_ATM {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Arrays.sort(arr);
        int sum = 0;
        int ret = 0;
        for (int i = 0; i < N; i++) {
            sum = sum + arr[i];
            ret += sum;
        }
        System.out.println(ret);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}

