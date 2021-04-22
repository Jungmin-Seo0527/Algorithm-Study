/*
    BOJ10026_적록색약
    --------------------------------------------------------------------------------------------------------------------
    문제링크
    https://www.acmicpc.net/problem/10026

    난이도 : G5
    --------------------------------------------------------------------------------------------------------------------
    풀이

    간단한 그래프 탐색 문제
    영역 분리 문제이며, 단 R, G, B 그룹을 나눈 영역을 구한후에
    R과 G 영역이 같은 그룹으로 묶어서 한번더 탐색하는 과정이 필요함

    처음에 R, G, B 영역으로 분리해서 카운트 하는 과정에서 G 영역을 R로 변경후에 같은 과정을 다시 진행하면 쉽다.
    --------------------------------------------------------------------------------------------------------------------
 */
package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ10026_적록색약 {
    static int N;
    static char[][] map;
    static boolean[][] visited;

    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int cnt = 0;
        int cnt2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    doDFS(i, j, map[i][j]);
                    cnt++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    doDFS(i, j, map[i][j]);
                    cnt2++;
                }
            }
        }

        System.out.println(cnt + " " + cnt2);
    }

    static void doDFS(int r, int c, char color) {
        visited[r][c] = true;
        if (map[r][c] == 'G') map[r][c] = 'R';
        for (int i = 0; i < 4; i++) {
            int nr = r + v_r[i];
            int nc = c + v_c[i];
            if (check(nr, nc, color) && !visited[nr][nc]) {
                doDFS(nr, nc, color);
            }
        }
    }

    static boolean check(int r, int c, char color) {
        return r >= 0 && r < N && c >= 0 && c < N && map[r][c] == color;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
}
