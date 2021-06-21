package codeforces.R725_D3;

import java.io.*;
import java.util.*;

public class F_Interesting_Function {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            out.append(solve(l, r)).append("\n");
        }
        System.out.print(out);
    }

    private static int solve(int l, int r) {
        String sr = String.valueOf(r);
        String sl = String.valueOf(l);
        int temp = 1;
        int ret = 0;
        for (int i = sr.length() - 1; i >= 0; i--) {
            ret += Integer.parseInt(String.valueOf(sr.charAt(i))) * temp;
            temp *= 10;
            temp += 1;
        }
        temp = 1;
        for (int i = sl.length() - 1; i >= 0; i--) {
            ret -= Integer.parseInt(String.valueOf(sl.charAt(i))) * temp;
            temp *= 10;
            temp++;
        }
        return ret;
    }
}
