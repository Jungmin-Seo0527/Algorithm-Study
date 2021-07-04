package codeforces.R728_D2;

import java.io.*;
import java.util.*;

public class C_Great_Graphs {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long solve(long[] arr, int N) {
        long ans = 0;
        long sum = 0;
        Arrays.sort(arr);
        for (int i = 1; i < N; i++) {
            ans += arr[i] - arr[i - 1] + sum - i * arr[i];
            sum += arr[i];
        }
        return ans;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long[] arr = new long[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }
            out.append(solve(arr, N)).append("\n");
        }
        System.out.println(out);
    }
}
