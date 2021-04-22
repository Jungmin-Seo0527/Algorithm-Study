package dfs_bfs;


import java.io.*;
import java.util.*;

// BOJ14442_벽_부수고_이동하기2
// 1 문제에서는 벽을 부술수 있는 갯수가 2개
// 이 문제에서는 최대 K (K<=10) 개
// visited[row_size][col_size][K]
public class BOJ14442_벽_부수고_이동하기2 {
    static class Point {
        int row, col, dist = 1;
        int wall = 0;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M, K, map[][];
    static int v_r[] = {1, -1, 0, 0};
    static int v_c[] = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        System.out.println(doDFS(0, 0));
    }

    static int doDFS(int r, int c) {
        Queue<Point> que = new LinkedList<>();
        int visited[][][] = new int[N][M][K + 1];
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
                        if (cur.wall < K) {
                            visited[next_row][next_col][cur.wall] = 1;
                            Point next = new Point(next_row, next_col);
                            next.wall = cur.wall + 1;
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
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = (int) input.charAt(j) - '0';
            }
        }
    }
}

