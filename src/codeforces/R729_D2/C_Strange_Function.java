package codeforces.R729_D2;

import java.io.*;
import java.util.*;

public class C_Strange_Function {

    static long solve(long n) {
        long ret = 0;
        long G = 1;
        for (int i = 1; G <= n; i++, G = lcm(G, i)) {
            ret += n / G;
        }
        return (ret + n) % ((long) 1e9 + 7);
    }

    static long gcd(long n1, long n2) {
        return n2 == 0 ? n1 : gcd(n2, n1 % n2);
    }

    static long lcm(long n1, long n2) {
        return n1 / gcd(n1, n2) * n2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            long n = Long.parseLong(br.readLine());

            out.append(solve(n)).append("\n");
        }
        System.out.println(out);
    }
}