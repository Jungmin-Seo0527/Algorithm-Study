package binarySearch;

import java.io.*;
import java.util.*;

public class BOJ3066_브리징_시그널 {

    static int N, len;
    static int[] arr;

    static void solve(int target) {
        if (len == 0 || arr[len - 1] < target) {
            arr[len++] = target;
        } else {
            arr[lowerBound(target)] = target;
        }
    }

    static int lowerBound(int target) {
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            len = 0;
            for (int i = 0; i < N; i++) {
                solve(Integer.parseInt(br.readLine()));
            }
            answer.append(len).append("\n");
        }
        System.out.println(answer);
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
