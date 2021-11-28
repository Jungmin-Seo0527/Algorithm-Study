package samsung;

import java.io.*;
import java.util.*;

public class BOJ23288_주사위_굴리기2 {

    static int rowSZ, colSZ, K, dir;
    static int[] vr = {0, 0, 1, -1};
    static int[] vc = {1, -1, 0, 0};
    static int[][] map;
    static int[][] dice = {
            {0, 2, 0},
            {4, 1, 3},
            {0, 5, 0},
            {0, 6, 0}};

    static Point topOfDice = new Point(1, 1);
    static Point bottomOfDice = new Point(3, 1);
    static Point cur = new Point(0, 0);

    static void solve() {
        int ans = 0;
        for (int i = 0; i < K; i++) {
            move();
            ans += countSameScore() * map[cur.r][cur.c];
            setNextDir();
        }
        System.out.println(ans);
    }

    static void setNextDir() {
        int A = dice[bottomOfDice.r][bottomOfDice.c];
        int B = map[cur.r][cur.c];
        if (A > B) { // 시계 방향 90도
            if (dir == 0) dir = 2;
            else if (dir == 1) dir = 3;
            else if (dir == 2) dir = 1;
            else if (dir == 3) dir = 0;
        } else if (A < B) { // 반시계 방향 90도
            if (dir == 0) dir = 3;
            else if (dir == 1) dir = 2;
            else if (dir == 2) dir = 0;
            else if (dir == 3) dir = 1;
        }
    }

    static int countSameScore() {
        int ret = 1;
        int num = map[cur.r][cur.c];
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[rowSZ][colSZ];
        que.add(cur);
        visited[cur.r][cur.c] = true;
        while (!que.isEmpty()) {
            Point cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] == num) {
                    Point next = new Point(nr, nc);
                    visited[nr][nc] = true;
                    ret++;
                    que.add(next);
                }
            }
        }
        return ret;
    }

    static void move() {
        int nr = cur.r + vr[dir];
        int nc = cur.c + vc[dir];
        if (!checkBoundary(nr, nc)) {
            reverseDir();
            nr = cur.r + vr[dir];
            nc = cur.c + vc[dir];
        }
        rollTheDice();
        cur.r = nr;
        cur.c = nc;
    }

    static void reverseDir() {
        if (dir == 0) dir = 1;
        else if (dir == 1) dir = 0;
        else if (dir == 2) dir = 3;
        else if (dir == 3) dir = 2;
    }

    static void rollTheDice() {
        if (dir == 0) { // 동
            int temp = dice[1][2];
            dice[1][2] = dice[1][1];
            dice[1][1] = dice[1][0];
            dice[1][0] = dice[3][1];
            dice[3][1] = temp;
        } else if (dir == 1) { // 서
            int temp = dice[1][2];
            dice[1][2] = dice[3][1];
            dice[3][1] = dice[1][0];
            dice[1][0] = dice[1][1];
            dice[1][1] = temp;
        } else if (dir == 2) { // 남:
            int temp = dice[3][1];
            dice[3][1] = dice[2][1];
            dice[2][1] = dice[1][1];
            dice[1][1] = dice[0][1];
            dice[0][1] = temp;
        } else if (dir == 3) { // 북:
            int temp = dice[3][1];
            dice[3][1] = dice[0][1];
            dice[0][1] = dice[1][1];
            dice[1][1] = dice[2][1];
            dice[2][1] = temp;
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static void showArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
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
        K = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }
}
