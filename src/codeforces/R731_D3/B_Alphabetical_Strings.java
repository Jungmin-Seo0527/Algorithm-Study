package codeforces.R731_D3;

import java.io.*;
import java.util.*;

public class B_Alphabetical_Strings {

    static String solve(String input) {
        int aIdx = -1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') {
                aIdx = i;
                break;
            }
        }
        if (aIdx == -1) {
            return "NO";
        }

        int left = aIdx;
        int right = aIdx;
        char c = 'b';

        while (true) {
            if (left - 1 >= 0 && input.charAt(left - 1) == c) {
                left--;
            } else if (right + 1 < input.length() && input.charAt(right + 1) == c) {
                right++;
            } else {
                break;
            }
            c++;
        }
        return (right - left) == input.length() - 1 ? "YES" : "NO";

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