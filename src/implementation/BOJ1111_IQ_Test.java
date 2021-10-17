package implementation;

import java.io.*;
import java.util.*;

public class BOJ1111_IQ_Test {

    static int N;
    static int[] arr;

    static String solve() {
        if (N == 1) {
            return "A";
        }

        if (N == 2) {
            if (arr[0] != arr[1]) {
                return "A";
            } else {
                return arr[0] + "";
            }
        }

        int a = 0;
        int b = 0;
        if (arr[0] == arr[1]) {
            a = 1;
        } else {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - arr[0] * a;
        }
        for (int i = 1; i < N; i++) {
            if (arr[i] != arr[i - 1] * a + b) {
                return "B";
            }
        }
        return (arr[N - 1] * a + b) + "";
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }
}
