package ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10799_쇠막대기 {

    static void solve(String input) {
        int cnt = 0;
        int ret = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                cnt++;
            } else {
                char p = input.charAt(i - 1);
                if (p == '(') {
                    ret += cnt;
                } else {
                    ret += 1;
                }
                cnt--;
            }
        }
        System.out.println(ret);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        solve(input);
    }
}
