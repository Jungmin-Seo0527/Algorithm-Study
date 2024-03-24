package math;

import java.io.*;
import java.util.*;

public class BOJ2877_4와7 {

    static int K;

    static void solve() {
        StringBuilder ans = new StringBuilder();
        String stBinary = Integer.toBinaryString(K + 1);
        for (int i = 1; i < stBinary.length(); i++) {
            if (stBinary.charAt((i)) == '0') {
                ans.append("4");
            } else {
                ans.append("7");
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input4.txt";
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
