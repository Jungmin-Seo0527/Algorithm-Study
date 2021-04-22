package dfs_bfs;

import java.io.*;
import java.util.*;

// BOJ16933_벽_부수고_이동하기3
// 벽을 K개 부술수 있는데 낮에만 벽 부수기 가능
// 이동하지 않는 경우도 존재하는데 그때또한 cnt+1
// 이동하지 않는 경우는 대게 밤에 벽을 부수지 못하는 경우임

// visited[row size][col size][wall][2] (2: 낮, 밤)
// BFS 조건 세우기가 까다로웠던 문제
// 1. 벽 여부
// 1-1 (yes) 벽을 부술수 있는가? if(낮 && 현제 부순 벽의 갯수 < K && !visited[row][col][wall+1][day])
// 1-1-1 (yes) 벽을 부수고 전진
// 1-1-2 (No) 기다릴 것인가? if(밤 && !visited[row][col][wall][day]) -> wait
// 1-2 (No) 방문한 적이 없으면 전진

// 기존의 벽 부수고 이동하기2 문제에서 낮과 밤의 조건만 추가해서 방문 여부를 조사하면 되는 문제였다.
// 단 시간 제한에 아슬아슬하게 통과된것 같다. 시간 제한 2초, java8의 시간제한 = 시간제한x2+1 = 5초
// 내 풀이의 시간 4140ms 처리시간 순위를 봐도 하위권이다.
public class BOJ16933_벽_부수고_이동하기3 {
    static class Point {
        int row, col, wall, day, cnt;

        public Point(int row, int col, int wall, int day, int cnt) {
            this.row = row;
            this.col = col;
            this.wall = wall;
            this.day = day;
            this.cnt = cnt;
        }
    }

    static int N, M, K;
    static char[][] map;

    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        doBFS(0, 0);
    }

    static void doBFS(int r, int c) {
        Queue<Point> que = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][K + 1][2];
        visited[r][c][0][1] = true;
        que.add(new Point(r, c, 0, 1, 1));

        // day: 홀수=낮(벽 부수기 가능) 짝수=밤(벽 부수기 불가능)
        while (!que.isEmpty()) {
            Point cur = que.poll();
            //showPoint(cur);
            if (cur.row == N - 1 && cur.col == M - 1) {
                System.out.println(cur.cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int next_row = cur.row + v_r[i];
                int next_col = cur.col + v_c[i];
                if (check(next_row, next_col)) {
                    //System.out.println(next_row+" "+next_col);
                    if (map[next_row][next_col] == '1') {
                        if (cur.day % 2 == 1 && cur.wall < K &&
                                !visited[next_row][next_col][cur.wall + 1][cur.day]) {
                            visited[next_row][next_col][cur.wall + 1][cur.day] = true;
                            Point next = new Point(next_row, next_col, cur.wall + 1, (cur.day + 1) % 2, cur.cnt + 1);
                            que.add(next);
                        } else if (cur.day % 2 == 0
                                && !visited[cur.row][cur.col][cur.wall][cur.day]) {
                            //System.out.println(next_row+" "+next_col);
                            visited[cur.row][cur.col][cur.wall][cur.day] = true;
                            Point next = new Point(cur.row, cur.col, cur.wall, (cur.day + 1) % 2, cur.cnt + 1);
                            que.add(next);
                        }

                    } else {
                        // System.out.println(next_row+" "+next_col);
                        if (!visited[next_row][next_col][cur.wall][cur.day]) {
                            visited[next_row][next_col][cur.wall][cur.day] = true;
                            Point next = new Point(next_row, next_col, cur.wall, (cur.day + 1) % 2, cur.cnt + 1);
                            que.add(next);
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static void showPoint(Point p) {
        System.out.println("row: " + p.row + ", col: " + p.col + ", wall: " + p.wall + ", day: " + p.day + ", cnt: " + p.cnt);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }
    }
}

