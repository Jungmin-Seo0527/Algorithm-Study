package DFS_BFS;

import java.io.*;
import java.util.*;

// BOJ2206_벽_부수고_이동하기
// 벽을 부순 유무에 따른 visited
// 벽을 부술수 있는 만큼 3차원의 visited를 만든다 (visited[row_size][cur_size][wall_num])
// 최단거리로 특정 좌표에 도달했을때 벽을 부순적이 있는가 없는가에 따라 visited를 다르게 취급하는것이 핵심
// 좋은 예
/* 5 5
   00000
   11101
   00001
   01111
   00010

   벽을 부수고 도달한 좌표에 대해 벽을 부수지 않고 그 좌표에 도달했을때는 전혀 다른 경우임을 인지
   이 부분을 비트마스킹으로 해결할수 있는데 연구가 필요
   벽을 부술수 있는 만큼 visited가 커짐과는 다르게 비트마스킹을 이용하면 그대로 2차원으로 가능
   */
public class BOJ2206_벽_부수고_이동하기 {
    static class Point {
        int row, col, dist = 1;
        int wall = 0;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M, map[][];
    static int v_r[] = {1, -1, 0, 0};
    static int v_c[] = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        System.out.println(doDFS(0, 0));
    }

    static int doDFS(int r, int c) {
        Queue<Point> que = new LinkedList<>();
        int visited[][][] = new int[N][M][2];
        que.add(new Point(0, 0));
        visited[0][0][0] = 1;

        while (que.isEmpty() == false) {
            Point cur = que.poll();
            //System.out.println(cur.row + " " + cur.col + " " + cur.dist);
            if (cur.row == N - 1 && cur.col == M - 1) return cur.dist;
            for (int i = 0; i < 4; i++) {
                int next_row = cur.row + v_r[i];
                int next_col = cur.col + v_c[i];
                if (check(next_row, next_col, visited, cur)) {
                    //System.out.println(next_row + " " + next_col);
                    if (map[next_row][next_col] == 1) {
                        if (cur.wall == 0) {
                            visited[next_row][next_col][cur.wall] = 1;
                            Point next = new Point(next_row, next_col);
                            next.wall = 1;
                            next.dist = cur.dist + 1;
                            que.add(next);
                        }
                    } else {
                        visited[next_row][next_col][cur.wall] = 1;
                        Point next = new Point(next_row, next_col);
                        next.wall = cur.wall;
                        next.dist = cur.dist + 1;
                        que.add(next);
                    }
                }
            }
        }
        return -1;
    }

    static boolean check(int r, int c, int visited[][][], Point prev) {
        if (r < 0 || r >= N || c < 0 || c >= M) return false;
        if (visited[r][c][prev.wall] == 1) return false;
        //visited[r][c] = visited[r][c] | mask;
        return true;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = (int) input.charAt(j) - '0';
            }
        }
    }
}

