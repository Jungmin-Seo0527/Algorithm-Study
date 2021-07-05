package codeforces.R729_D2;

import java.io.*;
import java.util.*;

public class B_Plus_and_Multiply {

    static String solve(long n, long a, long b) {
        if (a == 1) {
            if ((n - 1) % b == 0) {
                return "Yes";
            } else {
                return "No";
            }
        } else {
            for (long c = 1; c <= n; c *= a) {
                if ((n - c) % b == 0) {
                    return "Yes";
                }
            }
            return "No";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());


            out.append(solve(n, a, b)).append("\n");
        }
        System.out.println(out);
    }
}