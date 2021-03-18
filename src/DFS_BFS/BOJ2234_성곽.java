/*
    BOJ2234_성곽
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/2234

    난이도 : G4
    --------------------------------------------------------------------------------------------------------------------
    풀이

    이 문제는 기존의 그래프 탐색에서 벽이 한칸을 차지한것과는 다르게 벽이 칸을 차지하고 있지 않다.
    각 칸에 4방향의 벽에 대한 정보가 비트로 주어졌다. 이에 대한 설명은 문제에 잘 설명이 되어 있다.
    사실 비트를 핸들링해서 각 영역의 넓이를 구하는건 어렵지 않다. 조건 3가지를 체크를 해주면 된다.
    1. 경계값 (그래프 범위를 벗어난 값 조사)
    2. 처음 지나는 지점인가
    3. 현재 지점에서 다음 지점으로 가는데 벽이 없는가

    이중 아마 세번째 조건이 생소할 것이다. 벽의 유무는 우선 해당 방향에 벽이 있는가를 조사하면 된다.
    만약 서쪽으로 가려면 map[cr][cc]&1<<0 이 0 인 경우가 벽이 존재하지 않는 경우이다.
    그리고 벽을 하나 허문경우 가장 큰 넓이가 되는 경우를 출력해야 한다.
    이 조건은 visited 를 그룹의 번호로 지정해주고 각 그룹의 넓이를 List에 저장을 하면 된다.
    이후에 이 경우에도 현재 지점을 기준으로 3가지 조건을 체크해 주면 된다.
    1. 벽을 허물면 그 다음 지점이 범위 내에 있는가
    2. 벽이 존재 하는가?
    3. 그룹 번호가 다른가?

    그룹 번호가 같으면 같은 영역인데 더해버리는 불상사를 막기위한 3번 조건이고, 이 조건을 위해서 visited 를 boolean형이 아닌
    int 형으로 그룹 번호를 저장한 것이다. (0 = 그룹 없음 = 방문 이력 없음, 1~ 그룹 번호)
    비트를 잘 핸들링만 하면 어렵지 않은 문제였다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ2234_성곽 {

    static int rowSZ, colSZ;
    static int[][] map, visited;
    static int[] v_r = {0, -1, 0, 1};
    static int[] v_c = {-1, 0, 1, 0};
    static List<Integer> groupArea = new ArrayList<>();
    static Queue<Integer> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        groupArea.add(0);
        int gn = 1, max = 0, ans = 0;
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (visited[i][j] == 0) {
                    int temp = doBFS(i, j, gn++);
                    groupArea.add(temp);
                    max = Math.max(max, temp);
                }
            }
        }
        System.out.println(groupArea.size() - 1);
        System.out.println(max);
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                ans = Math.max(ans, plusArea(i, j));
            }
        }
        System.out.println(ans);
    }

    static int doBFS(int r, int c, int g) {
        int ret = 0;
        que.add(r * colSZ + c);
        visited[r][c] = g;
        while (!que.isEmpty()) {
            int cur = que.poll();
            ret++;
            int cr = cur / colSZ;
            int cc = cur % colSZ;
            for (int i = 0; i < 4; i++) {
                int nr = cr + v_r[i];
                int nc = cc + v_c[i];
                if (checkBoundary(nr, nc) && (map[cr][cc] & 1 << i) == 0 && visited[nr][nc] == 0) {
                    visited[nr][nc] = g;
                    que.add(nr * colSZ + nc);
                }
            }
        }
        return ret;
    }

    static int plusArea(int r, int c) {
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + v_r[i];
            int nc = c + v_c[i];
            if (checkBoundary(nr, nc) && (map[r][c] & 1 << i) != 0 && visited[r][c] != visited[nr][nc]) {
                ret = Math.max(ret, groupArea.get(visited[r][c]) + groupArea.get(visited[nr][nc]));
            }
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        colSZ = Integer.parseInt(st.nextToken());
        rowSZ = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];
        visited = new int[rowSZ][colSZ];

        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
