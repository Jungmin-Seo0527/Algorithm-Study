package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ16920_확장_게임 {

    static int rowSZ, colSZ, P;
    static char[][] map;
    static int[] S;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static List<List<Point>> startingPoint;

    static void solve() {
        int[] ret = new int[P + 1];
        for (int i = 1; i <= P; i++) {
            ret[i] = startingPoint.get(i).size();
        }
        Queue<Point> que = new LinkedList<>();
        Queue<Point> move = new LinkedList<>();
        boolean[][] visited = new boolean[rowSZ][colSZ];

        for (int i = 1; i <= P; i++) {
            for (int j = 0; j < startingPoint.get(i).size(); j++) {
                Point start = startingPoint.get(i).get(j);
                que.add(start);
                visited[start.r][start.c] = true;
            }
        }

        while (!que.isEmpty()) {
            Point cur = que.peek();
            while (!que.isEmpty() && que.peek().pn == cur.pn) {
                move.add(que.poll());
            }
            while (!move.isEmpty()) {
                Point curMove = move.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = curMove.r + vr[i];
                    int nc = curMove.c + vc[i];
                    if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') {
                        visited[nr][nc] = true;
                        ret[curMove.pn]++;
                        if (curMove.cnt + 1 < S[cur.pn]) {
                            move.add(new Point(cur.pn, nr, nc, curMove.cnt + 1));
                        } else {
                            que.add(new Point(cur.pn, nr, nc));
                        }
                    }
                }
            }
        }
        printAns(ret);
    }

    private static void printAns(int[] ret) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) {
            sb.append(ret[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void showMap(char[][] map) {
        for (int i = 0; i < rowSZ; i++) {
            System.out.println(map[i]);
        }
        System.out.println();
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Point {
        int pn, r, c, cnt;

        public Point(int pn, int r, int c) {
            this.pn = pn;
            this.r = r;
            this.c = c;
        }

        public Point(int pn, int r, int c, int cnt) {
            this.pn = pn;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "pn=" + pn +
                    ", r=" + r +
                    ", c=" + c +
                    ", cnt=" + cnt +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input2.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        S = new int[P + 1];
        startingPoint = new ArrayList<>();
        for (int i = 0; i <= P; i++) {
            startingPoint.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] != '.' && map[i][j] != '#') {
                    int n = map[i][j] - '0';
                    startingPoint.get(n).add(new Point(n, i, j));
                }
            }
        }

        solve();
    }
}
