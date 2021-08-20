package ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16120_PPAP {

    static char[] input;

    static void solve() {
        char[] arr = new char[input.length];
        int len = input.length;
        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (idx <= 2) {
                arr[idx++] = input[i];
            } else {
                if (input[i] == 'P' && arr[idx - 1] == 'A' && arr[idx - 2] == 'P' && arr[idx - 3] == 'P') {
                    idx -= 2;
                } else {
                    arr[idx++] = input[i];
                }
            }
        }

        if (idx == 1 && arr[idx - 1] == 'P') {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
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
        input = br.readLine().toCharArray();

        solve();
    }
}
