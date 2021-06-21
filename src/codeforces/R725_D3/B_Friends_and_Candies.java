package codeforces.R725_D3;

import java.io.*;
import java.util.*;

public class B_Friends_and_Candies {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve(int[] arr, int N) {
        int ret = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        if (sum % N != 0) {
            return -1;
        }
        sum /= N;
        for (int i = 0; i < N; i++) {
            if (arr[i] > sum) {
                ret++;
            }
        }

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
