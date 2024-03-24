package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ6593_상범_빌딩 {

    static int L, R, C;
    static char[][][] map;

    static int[] start, end;
    static int[] vl = {1, -1, 0, 0, 0, 0};
    static int[] vr = {0, 0, 1, -1, 0, 0};
    static int[] vc = {0, 0, 0, 0, 1, -1};

    static String solve() {
        boolean[][][] visited = new boolean[L][R][C];
        int ans = 0;
        Queue<int[]> que = new LinkedList<>();
        que.add(start);
        visited[start[0]][start[1]][start[2]] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();

            for (int i = 0; i < 6; i++) {
                int nl = cur[0] + vl[i];
                int nr = cur[1] + vr[i];
                int nc = cur[2] + vc[i];
                if (checkBoundery(nl, nr, nc) && map[nl][nr][nc] != '#' && !visited[nl][nr][nc]) {
                    if (map[nl][nr][nc] == 'E') {
                        ans = cur[3] + 1;
                        que.clear();
                    } else {
                        int[] next = new int[4];
                        next[0] = nl;
                        next[1] = nr;
                        next[2] = nc;
                        next[3] = cur[3] + 1;
                        que.add(next);
                        visited[nl][nr][nc] = true;
                    }
                }
            }
        }

        if (ans == 0) {
            return "Trapped!";
        } else {
            return "Escaped in " + ans + " minute(s).";
        }
    }

    static boolean checkBoundery(int l, int r, int c) {
        return l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C;
    }

    static void showMap() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                System.out.println(map[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) {
                break;
            }
            map = new char[L][R][C];
            start = new int[4];
            end = new int[4];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String c = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = c.charAt(k);
                        if (map[i][j][k] == 'S') {
                            start = new int[4];
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                        } else if (map[i][j][k] == 'E') {
                            end[0] = i;
                            end[1] = j;
                            end[2] = k;
                        }
                    }
                }
                br.readLine();
            }
            answer.append(solve()).append("\n");
        }
        System.out.println(answer);
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
