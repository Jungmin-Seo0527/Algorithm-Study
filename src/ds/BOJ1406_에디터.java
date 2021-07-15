package ds;

import java.io.*;
import java.util.*;

public class BOJ1406_에디터 {

    static void solve() {

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input = br.readLine();
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            left.push(input.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            if (op.equals("P")) {
                String s = st.nextToken();
                left.push(s.charAt(0));

            } else if (op.equals("L")) {
                if (!left.isEmpty()) {
                    right.push(left.pop());
                }

            } else if (op.equals("D")) {
                if (!right.isEmpty()) {
                    left.push(right.pop());
                }
            } else if (op.equals("B")) {
                if (!left.isEmpty()) {
                    left.pop();
                }
            }
        }
        while (!left.isEmpty()) {
            right.push(left.pop());
        }
        StringBuilder out = new StringBuilder();
        while (!right.isEmpty()) {
            out.append(right.pop());
        }
        System.out.println(out);
    }
}
