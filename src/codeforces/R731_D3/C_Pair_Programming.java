package codeforces.R731_D3;

import java.io.*;
import java.util.*;

public class C_Pair_Programming {

    static int[] solve(int k, int n, int m, int[] a, int[] b) {
        int aIdx = 0;
        int bIdx = 0;
        int[] ret = new int[n + m];
        int retIdx = 0;
        while (aIdx < n || bIdx < m || retIdx < n + m) {
            if (aIdx < n && a[aIdx] == 0) {
                ret[retIdx++] = 0;
                aIdx++;
                k++;
            } else if (bIdx < m && b[bIdx] == 0) {
                ret[retIdx++] = 0;
                bIdx++;
                k++;
            } else if (aIdx < n && a[aIdx] <= k) {
                ret[retIdx++] = a[aIdx++];
            } else if (bIdx < m && b[bIdx] <= k) {
                ret[retIdx++] = b[bIdx++];
            } else {
                ret[0] = -1;
                return ret;
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
            st = new StringTokenizer(br.readLine());
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] a = new int[n];
            int[] b = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            int[] ret = solve(k, n, m, a, b);
            if (ret[0] == -1) {
                out.append("-1").append("\n");
            } else {
                for (int i = 0; i < n + m; i++) {
                    out.append(ret[i]).append(" ");
                }
                out.append("\n");
            }
        }
        System.out.println(out);
    }
}