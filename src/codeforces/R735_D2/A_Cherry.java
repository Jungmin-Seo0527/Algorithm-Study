package codeforces.R735_D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Cherry {

    static long solve(int n, int[] arr) {
        long ret = 0;

        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, (long) arr[i - 1] * arr[i]);
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
//        System.out.println("===== input =====");
//        BufferedReader br = new BufferedReader(new FileReader("input/input1.txt"));
//        BufferedReader br2 = new BufferedReader(new FileReader("input/input1.txt"));
//        String s;
//        while ((s = br2.readLine()) != null) {
//            System.out.println(s);
//        }
//        System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            out.append(solve(N, arr)).append("\n");
        }
        System.out.println(out);
    }
}