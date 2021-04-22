/*
    BOJ2210_숫자판_점프
    --------------------------------------------------------------------------------------------------------------------
    문제링크

    https://www.acmicpc.net/problem/2210

    난이도 : S2
    --------------------------------------------------------------------------------------------------------------------
    풀이

    힐링용으로 풀어본 쉬운 문제
    같은 지점을 다시 방문할수 있다는 조건에서 잠시 고민을 했지만 문제를 잘 살펴보면 이동이 5번만 할수 있으므로
    그냥 그대로 DFS 혹은 BFS 로 그래프를 탐색하면 된다.
    --------------------------------------------------------------------------------------------------------------------
 */
package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2210_숫자판_점프 {
    static class Point {
        int row, col, cnt;
        String num;

        public Point(int row, int col, int cnt, String num) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.num = num;
        }
    }

    static int N = 5;
    static int[][] map = new int[5][5];
    static boolean[] visited = new boolean[1000000];
    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += doDFS(i, j);
            }
        }
        System.out.println(ans);
    }

    static int doDFS(int r, int c) {
        int ret = 0;
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(r, c, 1, map[r][c] + ""));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.cnt == 6) {
                int strToNum = Integer.parseInt(cur.num);
                if (!visited[strToNum]) {
                    visited[strToNum] = true;
                    ret++;
                }
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.row + v_r[i];
                int nc = cur.col + v_c[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    que.add(new Point(nr, nc, cur.cnt + 1, cur.num + map[nr][nc]));
                }
            }
        }

        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
