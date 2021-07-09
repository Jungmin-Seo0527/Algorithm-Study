package ds;

import java.io.*;
import java.util.*;

public class BOJ1918_후위_표기식 {

    static String solve(String input) {
        StringBuilder ret = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isNum(c)) {
                ret.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    ret.append(stack.pop());
                }
                stack.pop();
            } else if (isOp(c)) {
                while (!stack.isEmpty() && priorityOp(c) <= priorityOp(stack.peek())) {
                    ret.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            ret.append(stack.pop());
        }

        return ret.toString();
    }

    static boolean isNum(char c) {
        return c >= 'A' && c <= 'Z';
    }

    static boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static int priorityOp(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        else return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(solve(input));
    }
}