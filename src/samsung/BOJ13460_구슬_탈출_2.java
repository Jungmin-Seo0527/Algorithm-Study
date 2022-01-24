package samsung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ13460_구슬_탈출_2 {

    static int rowSZ, colSZ;
    static char[][] map;
    static Point red, blue, hole;

    static int[] vr = {0, 1, 0, -1};
    static int[] vc = {1, 0, -1, 0};

    static void solve() {
        Queue<Balls> que = new LinkedList<>();
        Set<Balls> visited = new HashSet<>();
        int ans = 0;
        Balls balls = new Balls(red, blue, 0);
        que.add(balls);
        visited.add(balls);

        loop:
        while (!que.isEmpty()) {
            Balls cur = que.poll();
            for (int i = 0; i < 4; i++) {
                Point nextRed = new Point(-1, -1);
                Point nextBlue = new Point(-1, -1);
                Balls nextBalls;
                if (i == 0) {
                    if (cur.red.c < cur.blue.c) {
                        nextBlue = moveBall(cur.blue, cur.red, i);
                        nextRed = moveBall(cur.red, nextBlue, i);
                    } else {
                        nextRed = moveBall(cur.red, cur.blue, i);
                        nextBlue = moveBall(cur.blue, nextRed, i);
                    }
                } else if (i == 1) {
                    if (cur.red.r < cur.blue.r) {
                        nextBlue = moveBall(cur.blue, cur.red, i);
                        nextRed = moveBall(cur.red, nextBlue, i);
                    } else {
                        nextRed = moveBall(cur.red, cur.blue, i);
                        nextBlue = moveBall(cur.blue, nextRed, i);
                    }
                } else if (i == 2) {
                    if (cur.red.c < cur.blue.c) {
                        nextRed = moveBall(cur.red, cur.blue, i);
                        nextBlue = moveBall(cur.blue, nextRed, i);
                    } else {
                        nextBlue = moveBall(cur.blue, cur.red, i);
                        nextRed = moveBall(cur.red, nextBlue, i);
                    }
                } else if (i == 3) {
                    if (cur.red.r < cur.blue.r) {
                        nextRed = moveBall(cur.red, cur.blue, i);
                        nextBlue = moveBall(cur.blue, nextRed, i);
                    } else {
                        nextBlue = moveBall(cur.blue, cur.red, i);
                        nextRed = moveBall(cur.red, nextBlue, i);
                    }
                }
                nextBalls = new Balls(nextRed, nextBlue, cur.cnt + 1);
                if (nextBalls.cnt > 10) continue;
                // System.out.println("nextBalls = " + nextBalls);
                if (!nextBlue.equals(hole) && nextRed.equals(hole)) {
                    ans = nextBalls.cnt;
                    break loop;
                } else if (!visited.contains(nextBalls) && !nextBlue.equals(hole) && !nextRed.equals(hole)) {
                    que.add(nextBalls);
                    visited.add(nextBalls);
                }
            }
        }

        if (ans == 0) ans = -1;
        System.out.println(ans);
    }

    static Point moveBall(Point moveBall, Point stopBall, int d) {
        Point ret = new Point(moveBall.r, moveBall.c);
        while (moveBallOneStep(ret, stopBall, d)) {
        }
        return ret;
    }

    static boolean moveBallOneStep(Point moveBall, Point stopBall, int d) {
        int nr = moveBall.r + vr[d];
        int nc = moveBall.c + vc[d];

        if (checkBoundary(nr, nc)) {
            if (hole.r == nr && hole.c == nc) {
                moveBall.r = nr;
                moveBall.c = nc;
                moveBall.hole = true;
                return false;
            } else if (stopBall.r == nr && stopBall.c == nc) {
                return false;
            } else if (map[nr][nc] == '.') {
                moveBall.r = nr;
                moveBall.c = nc;
                return true;
            }
        }
        return false;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Point {
        int r, c;
        boolean hole = false;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static class Balls {
        Point red, blue;
        int cnt;

        public Balls(Point r, Point c) {
            this.red = r;
            this.blue = c;
        }

        public Balls(Point red, Point blue, int cnt) {
            this.red = red;
            this.blue = blue;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Balls balls = (Balls) o;
            return Objects.equals(red, balls.red) && Objects.equals(blue, balls.blue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(red, blue);
        }

        @Override
        public String toString() {
            return "Balls{" +
                    "red=" + red +
                    ", blue=" + blue +
                    ", cnt=" + cnt +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = getBufferedReader();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == 'R') {
                    red = new Point(i, j);
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    blue = new Point(i, j);
                    map[i][j] = '.';
                } else if (map[i][j] == 'O') {
                    hole = new Point(i, j);
                    map[i][j] = '.';
                }
            }
        }

        solve();
    }

    private static BufferedReader getBufferedReader() throws IOException {
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
