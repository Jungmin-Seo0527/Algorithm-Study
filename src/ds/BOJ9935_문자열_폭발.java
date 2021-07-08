package ds;

import java.io.*;
import java.util.*;

public class BOJ9935_문자열_폭발 {
    static String input, bomb;
    static int MAX = (int) 1e6;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        char[] stack = new char[MAX + 1];
        int stackIdx = 0;

        for (int i = 0; i < input.length(); i++) {
            stack[stackIdx++] = input.charAt(i);

            if (stack[stackIdx - 1] == bomb.charAt(bomb.length() - 1) && stackIdx >= bomb.length()) {
                boolean isBomb = true;
                for (int j = stackIdx - bomb.length(), bIdx = 0; j < stackIdx; j++, bIdx++) {
                    if (stack[j] != bomb.charAt(bIdx)) {
                        isBomb = false;
                        break;
                    }
                }
                if (isBomb) {
                    stackIdx -= bomb.length();
                }
            }
        }


        if (stackIdx == 0) {
            System.out.println("FRULA");
        } else {
            String ret = String.copyValueOf(stack, 0, stackIdx);
            System.out.println(ret);
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        bomb = br.readLine();
    }
}
