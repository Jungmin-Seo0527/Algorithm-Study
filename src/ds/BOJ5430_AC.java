package ds;

import java.io.*;
import java.util.*;

public class BOJ5430_AC {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static String solve(String p, int[] arr) {
        int leftIdx = 0;
        int rightIdx = arr.length - 1;
        int f = 1;

        for (int i = 0; i < p.length(); i++) {
            char cp = p.charAt(i);
            if (cp == 'D') {
                if (leftIdx > rightIdx) return "error";
                if (f == 1) leftIdx++;
                else rightIdx--;
            } else {
                f *= -1;
            }
        }
        return getAns(arr, leftIdx, rightIdx, f).toString();
    }

    private static StringBuilder getAns(int[] arr, int leftIdx, int rightIdx, int f) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (f == 1) {
            for (int i = leftIdx; i < rightIdx; i++) {
                sb.append(arr[i]).append(",");
            }
            if (leftIdx <= rightIdx) sb.append(arr[rightIdx]);
        } else {
            for (int i = rightIdx; i > leftIdx; i--) {
                sb.append(arr[i]).append(",");
            }
            if (leftIdx <= rightIdx) sb.append(arr[leftIdx]);
        }
        sb.append("]");
        return sb;
    }

    static int[] parsing(String input, int n) {
        int[] ret = new int[n];
        if (n == 0) {
            return ret;
        }
        int retIdx = 0;
        int number = 0;
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9') {
                number = number * 10 + c - '0';
            } else {
                ret[retIdx++] = number;
                number = 0;
            }
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();
            int[] arr = parsing(input, n);
            out.append(solve(p, arr)).append("\n");
        }
        System.out.println(out);
    }
}
