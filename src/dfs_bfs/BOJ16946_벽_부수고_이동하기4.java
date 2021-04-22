package dfs_bfs;

import java.io.*;
import java.util.*;

// BOJ16946_벽_부수고_이동하기4
// 벽을 부수었을때 그 위치에서 이동할수 있는 칸의 갯수를 구하기
// 벽이 아닌 곳에 대한 각 영역의 크기를 구하고 그룹을 만들어준다.
// 벽을 하나씩 부수었을때 4방향에 있는 영역들의 크기를 구하면 된다.
// 단 중복되는 경우(벽의 오른쪽과 연결되어 있는 영역과 아래와 연결되어 있는 영역이 같은 영역)를 처리

// 처음에 존재하는 벽 하나씩 부수고 그에 대한 BFS를 돌려 이동가능한 칸의 갯수를 구하려 했지만 어림없는 소리
// 당연히 시간초과 발생
// 기존에 있는 영역들의 넓이를 모두 구한후에 벽을 부술때는 벽과 인접한 영역들의 크기를 구함으로 시간절약
public class BOJ16946_벽_부수고_이동하기4 {
    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M;
    static int[][] group, ans;
    static char[][] map;

    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    static List<Integer> groupCntList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
        //showAns();
    }

    static void solve() {
        int group_num = 1;
        groupCntList.add(0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0' && group[i][j] == 0) {
                    doBFS(i, j, group_num++);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '1') {
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int row = i + v_r[k];
                        int col = j + v_c[k];
                        if (row < 0 || row >= N || col < 0 || col >= M) continue;
                        set.add(group[row][col]);
                    }
                    int cnt = 1;
                    for (int s : set) {
                        cnt += groupCntList.get(s);
                    }
                    ans[i][j] = cnt % 10;
                }
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void doBFS(int r, int c, int group_num) {
        Queue<Point> que = new LinkedList<>();
        group[r][c] = group_num;
        que.add(new Point(r, c));
        int cnt = 1;
        while (!que.isEmpty()) {
            Point cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int next_row = cur.row + v_r[i];
                int next_col = cur.col + v_c[i];
                if (check(next_row, next_col)) {
                    group[next_row][next_col] = group_num;
                    cnt++;
                    que.add(new Point(next_row, next_col));
                }
            }
        }
        groupCntList.add(cnt);
    }

    static boolean check(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M) return false;
        if (map[r][c] == '1') return false;
        return group[r][c] == 0;
    }

    static void showAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        group = new int[N][M];
        ans = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }
    }
}

