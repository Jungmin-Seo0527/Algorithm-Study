package codeforces.R725_D3;

import java.io.*;
import java.util.*;

public class A_Stone_Game {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve(int[] arr, int N) {
        int max = 0;
        int min = 101;
        int maxIdx = 0;
        int minIdx = 0;

        for (int i = 0; i < N; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIdx = i;
            }
            if (max < arr[i]) {
                max = arr[i];
                maxIdx = i;
            }
        }

        int ret = Math.max(minIdx + 1, maxIdx + 1); // 둘다 왼쪽으로 빼기
        ret = Math.min(ret, Math.max(N - minIdx, N - maxIdx)); // 둘다 오른쪽으로 빼기
        ret = Math.min(ret, minIdx + 1 + N - maxIdx);
        ret = Math.min(ret, maxIdx + 1 + N - minIdx);

        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            ans.append(solve(arr, N)).append("\n");
        }
        System.out.print(ans);
    }
}
