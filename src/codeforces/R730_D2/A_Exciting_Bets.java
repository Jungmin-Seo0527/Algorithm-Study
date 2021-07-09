package codeforces.R730_D2;

import java.io.*;
import java.util.*;

public class A_Exciting_Bets {

    static String solve(long a, long b) {
        long smaller = Math.min(a, b);
        long bigger = Math.max(a, b);
        long maxGcd = bigger - smaller;
        if (bigger == smaller) {
            return "0 0";
        }

        long c1 = bigger % maxGcd;
        long c2 = maxGcd - c1;
        long m2 = Math.min(c1, c2);

        if (smaller > m2) {
            return maxGcd + " " + m2;
        } else {
            return maxGcd + " " + smaller;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            out.append(solve(a, b)).append("\n");
        }
        System.out.println(out);
    }
}