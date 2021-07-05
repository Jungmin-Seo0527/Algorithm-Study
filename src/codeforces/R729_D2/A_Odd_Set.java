package codeforces.R729_D2;

import java.io.*;
import java.util.*;

public class A_Odd_Set {

    static String solve(long cnt1, long cnt2, int N) {
        if (cnt1 == cnt2 && cnt1 >= N) {
            return "Yes";
        } else {
            return "No";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            long cnt1 = 0;
            long cnt2 = 0;
            for (int i = 0; i < 2 * N; i++) {
                int input = Integer.parseInt(st.nextToken());
                if (input % 2 == 0) {
                    cnt2++;
                } else {
                    cnt1++;
                }
            }
            out.append(solve(cnt1, cnt2, N)).append("\n");
        }
        System.out.println(out);
    }
}