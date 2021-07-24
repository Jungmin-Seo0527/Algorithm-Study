package codeforces.R734_D3;

import java.io.*;
import java.util.*;

public class B2_Wonderful_Coloring_2 {

    static String solve(int n, int k, List<List<Integer>> list) {
        int cnt = 0;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            cnt += Math.min(k, list.get(i).size());
        }

        cnt -= cnt % k;

        int c = 0;
        for (int i = 0; i < n; i++) {
            if (list.get(i).size() >= k) {
                for (int j = 0; j < k; j++) {
                    ret[list.get(i).get(j)] = c++ % k + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (list.get(i).size() < k) {
                for (int j = 0; j < list.get(i).size() && c < cnt; j++) {
                    ret[list.get(i).get(j)] = c++ % k + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ret[i]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("input/input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            List<List<Integer>> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int a = Integer.parseInt(st.nextToken());
                list.get(a - 1).add(i);
            }

            out.append(solve(n, k, list)).append("\n");
        }
        System.out.println(out);
    }
}