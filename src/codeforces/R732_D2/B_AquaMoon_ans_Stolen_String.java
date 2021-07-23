package codeforces.R732_D2;

import java.io.*;
import java.util.*;

public class B_AquaMoon_ans_Stolen_String {

    static String solve(int n, int m, String[] s1, String[] s2) {
        int[] ans = new int[m];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                ans[j] += s1[i].charAt(j) - s2[i].charAt(j);
            }
        }

        StringBuilder ret = new StringBuilder();
        for (int j = 0; j < m; j++) {
            ans[j] += s1[n - 1].charAt(j);
            ret.append((char) ans[j]);
        }
        return ret.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            String[] s1 = new String[n];
            String[] s2 = new String[n - 1];
            for (int i = 0; i < n; i++) {
                s1[i] = br.readLine();
            }
            for (int i = 0; i < n - 1; i++) {
                s2[i] = br.readLine();
            }
            out.append(solve(n, m, s1, s2)).append("\n");
        }
        System.out.println(out);
        System.out.flush();
    }
}