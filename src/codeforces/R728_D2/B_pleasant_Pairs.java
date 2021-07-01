package codeforces.R728_D2;

import java.io.*;
import java.util.*;

public class B_pleasant_Pairs {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve(int[] arr, int N) {
        int cnt = 0;
        for (int aiaj = 3; aiaj <= 2 * N; aiaj++) {
            int sq = (int) Math.sqrt(aiaj);
            for (int ai = 1; ai <= sq; ai++) {
                if (aiaj % ai == 0 && aiaj != ai * ai && arr[ai] + arr[aiaj / ai] == aiaj) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int SZ = 2 * N + 1;
            int[] arr = new int[SZ];
            Arrays.fill(arr, SZ);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[Integer.parseInt(st.nextToken())] = i + 1;
            }
            out.append(solve(arr, N)).append("\n");
        }
        System.out.println(out);
    }
}
