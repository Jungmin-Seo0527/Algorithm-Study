package codeforces.R734_D3;

import java.io.*;
import java.util.*;

public class C_Interesting_Story {

    static int solve(char c, char[][] str, int N) {
        System.out.println("c = " + c);

        /**
         * 각 문자열에서 `c`인 문자와 그렇지 않는 문자를 카운트
         * `c`인 문자 갯수 - `c`가 아닌 문자의 갯수
         * f(s, c)
         */
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            int c1 = 0, c2 = 0;
            for (char x : str[i]) {
                if (x == c) c1++;
                else c2++;
            }
            a[i] = c1 - c2;
        }

        int sum = 0, count = N;
        for (int x : a) sum += x;

        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            if (sum > 0) break;
            count--;
            sum -= a[i];
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // BufferedReader br = new BufferedReader(new FileReader("input/input0.txt"));
        // BufferedReader br2 = new BufferedReader(new FileReader("input/input0.txt"));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            char[][] str = new char[n][];
            for (int i = 0; i < n; i++) {
                str[i] = br.readLine().trim().toCharArray();
            }

            int ans = 0;
            for (char c = 'a'; c <= 'e'; c++) {
                ans = Math.max(ans, solve(c, str, n));
            }
            out.append(ans).append("\n");
        }
        System.out.println(out);
    }
}