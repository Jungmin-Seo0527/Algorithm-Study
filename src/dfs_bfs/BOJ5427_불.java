package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ5427_불 {

    static final char EMPTY = '.';
    static final char WALL = '#';
    static final char START = '@';
    static final char FIRE = '*';

    static int rowSZ, colSZ;
    static char[][] map;
    static boolean[][] visited;
    static Point start;
    static Queue<Point> que;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static String solve() {
        visited[start.r][start.c] = true;
        map[start.r][start.c] = EMPTY;
        que.add(start);
        int answer = 0;
        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];

                if (checkBoundary(nr, nc) && map[nr][nc] == EMPTY) {
                    if (cur.oj == FIRE) {
                        map[nr][nc] = FIRE;
                        que.add(new Point(nr, nc, cur.oj));
                    } else if (cur.oj == START && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        que.add(new Point(nr, nc, cur.oj, cur.timer + 1));
                    }
                } else if (!checkBoundary(nr, nc) && cur.oj == START) {
                    que.clear();
                    answer = cur.timer + 1;
                    break;
                }
            }
        }
        if (answer > 0) {
            return String.valueOf(answer);
        } else {
            return "IMPOSSIBLE";
        }
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Point {
        int r, c;
        char oj;
        int timer = 0;

        public Point(int r, int c, char oj) {
            this.r = r;
            this.c = c;
            this.oj = oj;
        }

        public Point(int r, int c, char oj, int timer) {
            this.r = r;
            this.c = c;
            this.oj = oj;
            this.timer = timer;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            colSZ = Integer.parseInt(st.nextToken());
            rowSZ = Integer.parseInt(st.nextToken());
            map = new char[rowSZ][colSZ];
            que = new LinkedList<>();
            visited = new boolean[rowSZ][colSZ];
            for (int i = 0; i < rowSZ; i++) {
                String row = br.readLine();
                for (int j = 0; j < colSZ; j++) {
                    char c = row.charAt(j);
                    map[i][j] = c;
                    if (c == FIRE) {
                        que.add(new Point(i, j, c));
                    } else if (c == START) {
                        start = new Point(i, j, c);
                    }
                }
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
