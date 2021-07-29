package codeforces.R734_D3;

import java.io.*;
import java.util.*;

public class B1_Wonderful_Coloring_1 {

    static long solve(String input) {
        int[] cnt = new int['z' - 'a' + 1];
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            cnt[c - 'a']++;
        }
        long ret = 0;
        long one = 0;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] >= 2) {
                ret++;
            } else if (cnt[i] == 1) {
                one++;
            }
        }
        ret += one / 2;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            String input = br.readLine();

            out.append(solve(input)).append("\n");
        }
        System.out.println(out);
    }
}