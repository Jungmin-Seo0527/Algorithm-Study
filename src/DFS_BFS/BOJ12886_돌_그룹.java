package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ12886_돌_그룹 {

    static class State {
        int A, B, C, cnt;

        @Override
        public String toString() {
            return "State{" +
                    "A=" + A +
                    ", B=" + B +
                    ", C=" + C +
                    ", cnt=" + cnt +
                    '}';
        }

        public State(int a, int b, int c, int cnt) {
            A = a;
            B = b;
            C = c;
            this.cnt = cnt;
        }
    }

    static boolean[][] visited;
    static Queue<State> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve(State start) {
        que.add(start);
        visited[start.A][start.B] = true;
        while (!que.isEmpty()) {
            State cur = que.poll();
            if (checkEnd(cur)) {
                System.out.println(1);
                return;
            }
            move(cur);
        }
        System.out.println(0);
    }

    static boolean checkEnd(State cur) {
        return cur.A == cur.B && cur.B == cur.C;
    }

    static void move(State cur) {
        int[] balls = new int[3];
        balls[0] = cur.A;
        balls[1] = cur.B;
        balls[2] = cur.C;
        int[] copy = new int[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                if (balls[i] >= balls[j]) continue;
                System.arraycopy(balls, 0, copy, 0, 3);

                int temp = copy[i];
                copy[i] += temp;
                copy[j] -= temp;
                if (!visited[copy[0]][copy[1]]) {
                    visited[copy[0]][copy[1]] = true;
                    que.add(new State(copy[0], copy[1], copy[2], cur.cnt + 1));
                }
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        State start = new State(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        visited = new boolean[1501][1501];
        solve(start);
    }
}
