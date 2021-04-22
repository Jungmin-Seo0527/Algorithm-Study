/*
    BOJ3184_양
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/3184

    난이도 : S2
    --------------------------------------------------------------------------------------------------------------------
    풀이

    기분전환으로 쉬운 문제 한번 풀어보았다.
    나누어진 영역안에 양과 늑대의 갯수를 세면 된다. DFS로 영역 나누기의 가장 기본이 되는 문제였다.
    --------------------------------------------------------------------------------------------------------------------
 */
package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ3184_양 {
    static int rowSZ, colSZ, sheepCnt, wolfCnt;
    static char[][] map;
    static boolean[][] visited;

    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ansSheep = 0;
        int ansWolf = 0;
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] != '#' && !visited[i][j]) {
                    sheepCnt = 0;
                    wolfCnt = 0;
                    doDFS(i, j);
                    if (sheepCnt > wolfCnt) ansSheep += sheepCnt;
                    else ansWolf += wolfCnt;
                }
            }
        }
        System.out.println(ansSheep + " " + ansWolf);
    }

    static void doDFS(int r, int c) {
        visited[r][c] = true;
        if (map[r][c] == 'o') sheepCnt++;
        else if (map[r][c] == 'v') wolfCnt++;
        for (int i = 0; i < 4; i++) {
            int nr = r + v_r[i];
            int nc = c + v_c[i];
            if (nr >= 0 && nr < rowSZ && nc >= 0 && nc < colSZ && map[nr][nc] != '#' && !visited[nr][nc]) {
                doDFS(nr, nc);
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        visited = new boolean[rowSZ][colSZ];

        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }
}
