package codeforces.R727_D2;

import java.io.*;
import java.util.*;

public class B_Love_Song {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long solve(String str, int l, int r) {
        long ret = 0;
        for (int i = l - 1; i < r; i++) {
            ret += (long) str.charAt(i) - 'a' + 1;
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        long[] preSum = new long[str.length() + 1];

        for (int i = 0; i < str.length(); i++) {
            preSum[i + 1] = preSum[i] + (long) str.charAt(i) - 'a' + 1;
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            out.append(preSum[r] - preSum[l - 1]).append("\n");
        }
        System.out.println(out);
    }
}
