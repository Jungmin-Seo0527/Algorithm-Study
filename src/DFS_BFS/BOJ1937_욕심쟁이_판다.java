/*
    BOJ1937_욕심쟁이_판다
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/1937

    난이도 : G38
    --------------------------------------------------------------------------------------------------------------------
    풀이

    DFS와 dp를 혼합한 대표적인 문제라고 할 수 있다. 이 문제 풀이법은 이전에 외판원 순회 문제풀이에서도 볼수 있다. 이 문제에서
    방문여부를 비트마스킹 하면 외판원 순회 문제 풀이 방법이 된다.
    DFS와 dp를 혼합한 문제는 한 지점에서 시작해서 나오는 결과를 메모제이션 하여 다음에 다시 그 지점에 방문한다면 다시 계산을 수행하는
    것이 아닌 메모제이션된 값을 출력해야 한다.

    만약 이 문제의 예제에서 (0, 1)에서 시작할때 값을 구하면, 이 지점보다 큰 값이 있는 (0, 1), (1, 1), (1, 2), (0, 0)으로 이동이
    가능하다. 그렇다면 재귀함수를 이용해서 각 지점에서 만약 시작할때 판다가 살수 있는 최대 일수를 또 구한다. 만약 이전에 구했던 이력이
    있다면 그 값을 그대로 출력하고, 그렇지 않으면 계산을 수행하는 것이다. 이 방법으로 중복되는 계산과정을 생략할 수 있어서 시간을 비약
    적으로 절약할 수 있다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ1937_욕심쟁이_판다 {
    static int N;
    static int[][] map, dp;

    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, doDFS(i, j));
            }
        }
        System.out.println(ans);
    }

    static int doDFS(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];

        int ret = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + v_r[i];
            int nc = c + v_c[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > map[r][c]) {
                ret = Math.max(ret, doDFS(nr, nc) + 1);
            }
        }
        return dp[r][c] = ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
