package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BOJ3425_고스택 {

    static final int MAX = (int) 1e9;

    static String solve(List<String> opList, int v) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        int sz = opList.size();
        for (int i = 0; i < sz; i++) {
            String[] op = opList.get(i).split(" ");
            if (op[0].equals("NUM")) {
                stack.push(Integer.parseInt(op[1]));
            } else if (op[0].equals("POP")) {
                if (stack.isEmpty()) return "ERROR";
                stack.pop();
            } else if (op[0].equals("INV")) {
                if (stack.isEmpty()) return "ERROR";
                stack.push(stack.pop() * -1);
            } else if (op[0].equals("DUP")) {
                if (stack.isEmpty()) return "ERROR";
                stack.push(stack.peek());
            } else if (op[0].equals("SWP")) {
                if (stack.size() < 2) return "ERROR";
                int pop1 = stack.pop();
                int pop2 = stack.pop();
                stack.push(pop1);
                stack.push(pop2);
            } else if (op[0].equals("ADD")) {
                if (stack.size() < 2) return "ERROR";
                int pop1 = stack.pop();
                int pop2 = stack.pop();
                if (Math.abs(pop1 + pop2) > MAX) return "ERROR";
                stack.push(pop1 + pop2);
            } else if (op[0].equals("SUB")) {
                if (stack.size() < 2) return "ERROR";
                int pop1 = stack.pop();
                int pop2 = stack.pop();
                if (Math.abs(pop2 - pop1) > MAX) return "ERROR";
                stack.push(pop2 - pop1);
            } else if (op[0].equals("MUL")) {
                if (stack.size() < 2) return "ERROR";
                long pop1 = stack.pop();
                long pop2 = stack.pop();
                if (Math.abs(pop1 * pop2) > MAX) return "ERROR";
                stack.push((int) (pop1 * pop2));
            } else if (op[0].equals("DIV")) {
                if (stack.size() < 2) return "ERROR";
                int pop1 = stack.pop();
                int pop2 = stack.pop();
                if (pop1 == 0) return "ERROR";
                int push = Math.abs(pop2) / Math.abs(pop1);
                if ((pop1 > 0 && pop2 < 0) || (pop1 < 0 && pop2 > 0)) push *= -1;
                stack.push(push);
            } else if (op[0].equals("MOD")) {
                if (stack.size() < 2) return "ERROR";
                int pop1 = stack.pop();
                int pop2 = stack.pop();
                if (pop1 == 0) return "ERROR";
                int push = Math.abs(pop2) % Math.abs(pop1);
                if (pop2 < 0) push *= -1;
                stack.push(push);
            }
        }

        if (stack.size() != 1) return "ERROR";
        return String.valueOf(stack.pop());
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
        StringBuilder sb = new StringBuilder();
        List<String> opList = new ArrayList<>();
        while (true) {
            String op = br.readLine();
            if (op.equals("")) continue;

            if (op.equals("QUIT")) break;
            if (op.equals("END")) {
                int N = Integer.parseInt(br.readLine());
                for (int i = 0; i < N; i++) {
                    int v = Integer.parseInt(br.readLine());
                    sb.append(solve(opList, v)).append("\n");
                }
                opList.clear();
                sb.append("\n");
            }
            opList.add(op);
        }
        System.out.println(sb);
    }
}
