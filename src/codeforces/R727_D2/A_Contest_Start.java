package codeforces.R727_D2;

import java.io.*;
import java.util.*;

public class A_Contest_Start {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long solve(long n, long x, long t) {
        long temp = t / x;
        if (n - temp < 0) {
            return (n - 1) * n / 2;
        }
        long cnt = (n - temp) * temp;
        temp--;
        cnt += (temp * (temp + 1)) / 2;
        return cnt;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            long t = Long.parseLong(st.nextToken());
            out.append(solve(n, x, t)).append("\n");
        }
        System.out.println(out);
    }
}
