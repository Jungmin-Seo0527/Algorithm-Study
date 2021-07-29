package codeforces.R734_D3;

import java.io.*;
import java.util.*;

public class A_Polycarp_and_Coins {

    static String solve(int n) {
        int moc = n / 3;
        int na = n % 3;
        int ret1 = moc;
        int ret2 = moc;

        if (na == 1) {
            ret1++;
        } else if (na == 2) {
            ret2++;
        }
        return ret1 + " " + ret2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            out.append(solve(n)).append("\n");
        }
        System.out.println(out);
    }
}