package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ12906_새로운_하노이_탑 {
    static class State {
        String[] top;
        int cnt;

        public State(String[] top, int cnt) {
            this.top = new String[3];
            for (int i = 0; i < 3; i++) {
                this.top[i] = new String(top[i]);
            }
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "State{" +
                    "top=" + Arrays.toString(top) +
                    ", cnt=" + cnt +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            for (int i = 0; i < 3; i++) {
                if (this.top[i].compareTo(state.top[i]) != 0) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(top[i]).append(" ");
            }
            return Objects.hash(sb.toString());
        }
    }

    static Set<State> set = new HashSet<>();
    static Queue<State> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        while (!que.isEmpty()) {
            State cur = que.poll();
            if (check(cur)) {
                System.out.println(cur.cnt);
                break;
            }
            move(cur);
        }
    }

    static boolean check(State state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < state.top[i].length(); j++) {
                if (state.top[i].charAt(j) != 'A' + i) return false;
            }
        }
        return true;
    }

    static void move(State cur) {
        String[] temp = new String[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                if (cur.top[i].length() == 0) continue;
                for (int k = 0; k < 3; k++) {
                    temp[k] = new String(cur.top[k]);
                }
                char last = temp[i].charAt(temp[i].length() - 1);
                temp[i] = temp[i].substring(0, temp[i].length() - 1);
                temp[j] = temp[j] + last;
                State next = new State(temp, cur.cnt + 1);
                if (set.add(next)) {
                    que.add(next);
                }
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] input = new String[3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) input[i] = "";
            else input[i] = st.nextToken();
        }
        State start = new State(input, 0);
        que.add(start);
        set.add(start);
    }
}

