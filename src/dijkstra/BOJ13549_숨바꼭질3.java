package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ13549_숨바꼭질3 {
    static int N, K;
    static final int INF = (int) 2e5;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        Deque<State> que = new LinkedList<>();
        boolean[] visited = new boolean[INF + 1];
        que.addFirst(new State(N, 0));

        while (!que.isEmpty()) {
            State cur = que.pollFirst();
            if (visited[cur.point]) continue;
            visited[cur.point] = true;
            if (cur.point == K) {
                ans = cur.time;
                break;
            }
            for (int i = -1; i <= 1; i += 2) {
                int next = cur.point + i;
                if (next >= 0 && !visited[next]) {
                    que.addLast(new State(next, cur.time + 1));
                }
            }
            if (cur.point < K) {
                que.addFirst(new State(cur.point * 2, cur.time));
            }
        }
        System.out.println(ans);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static class State {
        int point, time;

        public State(int point, int time) {
            this.point = point;
            this.time = time;
        }
    }

}
