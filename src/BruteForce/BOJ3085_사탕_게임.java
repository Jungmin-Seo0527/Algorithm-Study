/*
    BOJ3085_사탕_게임
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/3085

    난이도 : S4
    --------------------------------------------------------------------------------------------------------------------
    풀이

    생각보다는 귀찮았던 문제였다.
    그냥 문제가 시키는 데로 구현을 하면 되는 문제
    --------------------------------------------------------------------------------------------------------------------
 */
package BruteForce;

import java.io.*;
import java.util.*;

public class BOJ3085_사탕_게임 {

    static int N;
    static char[][] map;

    static int[] v_r = {0, 1, 0, -1};
    static int[] v_c = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, count(i, j));
                ans = Math.max(ans, doBFS(i, j));
            }
        }
        System.out.println(ans);
    }

    static int count(int r, int c) {
        int ret = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + v_r[i];
            int nc = c + v_c[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (map[r][c] == map[nr][nc]) continue;
                swap(r, c, nr, nc);
                ret = Math.max(ret, doBFS(r, c));
                swap(nr, nc, r, c);
            }
        }
        return ret;
    }

    static int doBFS(int r, int c) {
        int ret1 = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<Integer> que = new LinkedList<>();
        que.add(r * N + c);
        visited[r][c] = true;
        char temp = map[r][c];
        while (!que.isEmpty()) {
            int cur = que.poll();
            int cr = cur / N;
            int cc = cur % N;
            ret1++;
            for (int i = 0; i < 4; i += 2) {
                int nr = cr + v_r[i];
                int nc = cc + v_c[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == temp) {
                    que.add(nr * N + nc);
                    visited[nr][nc] = true;
                }
            }
        }

        int ret2 = 0;
        que.add(r * N + c);
        while (!que.isEmpty()) {
            int cur = que.poll();
            int cr = cur / N;
            int cc = cur % N;
            ret2++;
            for (int i = 1; i < 4; i += 2) {
                int nr = cr + v_r[i];
                int nc = cc + v_c[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == temp) {
                    que.add(nr * N + nc);
                    visited[nr][nc] = true;
                }
            }
        }

        return Math.max(ret1, ret2);
    }

    static void swap(int r, int c, int nr, int nc) {
        char temp = map[r][c];
        map[r][c] = map[nr][nc];
        map[nr][nc] = temp;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
}
