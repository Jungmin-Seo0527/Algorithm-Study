package codeforces.R732_D2;

import java.io.*;
import java.util.*;

public class A_AquaMoon_and_Two_Arrays {

    static List<int[]> solve(int n, long[] a, long[] b) {
        List<int[]> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] == b[i]) continue;
            for (int j = i + 1; j < n; j++) {
                if (i == j) continue;
                if (a[i] > b[i] && a[j] < b[j]) {
                    while (a[i] != b[i] && a[i] >= 0 && a[j] != b[j]) {
                        a[i]--;
                        a[j]++;
                        ret.add(new int[]{i + 1, j + 1});
                    }
                } else if (a[i] < b[i] && a[j] > b[j]) {
                    while (a[i] != b[i] && a[j] >= 0 && a[j] != b[j]) {
                        a[i]++;
                        a[j]--;
                        ret.add(new int[]{j + 1, i + 1});
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return null;
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long[] a = new long[n];
            long[] b = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                b[i] = Long.parseLong(st.nextToken());
            }

            List<int[]> ans = solve(n, a, b);
            if (ans == null) {
                out.append("-1").append("\n");
            } else {
                out.append(ans.size()).append("\n");
                for (int[] an : ans) {
                    out.append(an[0]).append(" ").append(an[1]).append("\n");
                }
            }
        }
        System.out.println(out);
    }
}