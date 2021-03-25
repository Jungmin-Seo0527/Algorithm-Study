package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ14395_4연산 {

    static class State {
        long num;
        String op;

        public State(long num, String op) {
            this.num = num;
            this.op = new String(op);
        }
    }

    static long S, T;
    static final long max = (long) 1e9 + 1;
    static char[] op = {'*', '+', '-', '/'};

    static Set<Long> set = new HashSet<>();
    static Queue<State> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        if (S == T) {
            System.out.println(0);
            return;
        }

        while (!que.isEmpty()) {
            State cur = que.poll();
            if (cur.num == T) {
                System.out.println(cur.op);
                return;
            }
            for (int i = 0; i < 4; i++) {
                long nextNum = cur.num;
                if (op[i] == '*') nextNum *= nextNum;
                if (op[i] == '+') nextNum += nextNum;
                if (op[i] == '-') nextNum -= nextNum;
                if (op[i] == '/' && nextNum > 0) nextNum /= nextNum;
                if (nextNum > max) continue;
                if (set.add(nextNum)) {
                    que.add(new State(nextNum, cur.op + op[i]));
                }
            }
        }
        System.out.println(-1);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());
        State start = new State(S, "");
        que.add(start);
        set.add(S);
    }
}
