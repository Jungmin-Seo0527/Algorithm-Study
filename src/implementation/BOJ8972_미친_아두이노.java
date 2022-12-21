package implementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ8972_미친_아두이노 {

    static int rowSZ, colSZ;
    static char[][] map;
    static Point jongsoo;

    static int[] dirArr;
    static int[] vr = {-9, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] vc = {-9, -1, 0, 1, -1, 0, 1, -1, 0, 1};


    static void solve() {
        int count = 1;
        for (int i = 0; i < dirArr.length; i++, count++) {
            jongsoo.move(dirArr[i]);
            if (map[jongsoo.r][jongsoo.c] == 'R') {
                jongsoo.dead();
                break;
            }
            moveCrazyArduino();
            if (jongsoo.isDead()) {
                break;
            }
            map[jongsoo.r][jongsoo.c] = 'I';
        }
        if (jongsoo.isDead()) {
            System.out.println("kraj " + count);
        } else {
            showMap();
        }
    }

    static void moveCrazyArduino() {
        map[jongsoo.r][jongsoo.c] = '.';
        int[][] cnt = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == '.') continue;
                int dir = getMinDir(i, j);
                int nr = i + vr[dir];
                int nc = j + vc[dir];
                cnt[nr][nc]++;
            }
        }

        for (int i = 0; i < rowSZ; i++) {
            Arrays.fill(map[i], '.');
        }

        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (cnt[i][j] >= 1 && jongsoo.r == i && jongsoo.c == j) {
                    jongsoo.dead();
                }
                if (cnt[i][j] == 1) {
                    map[i][j] = 'R';
                }
            }
        }
    }

    static int getMinDir(int r, int c) {
        int minDist = Integer.MAX_VALUE;
        int minDir = 0;

        for (int i = 1; i <= 9; i++) {
            int nr = r + vr[i];
            int nc = c + vc[i];

            if (checkBoundary(nr, nc)) {
                int dist = getDist(nr, nc);
                if (minDist > dist) {
                    minDist = dist;
                    minDir = i;
                }
            }
        }

        return minDir;
    }

    static int getDist(int r, int c) {
        return Math.abs(jongsoo.r - r) + Math.abs(jongsoo.c - c);
    }

    static void showMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowSZ; i++) {
            sb.append(map[i]).append("\n");
        }
        System.out.println(sb);
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];

        for (int i = 0; i < rowSZ; i++) {
            String col = br.readLine();
            for (int j = 0; j < colSZ; j++) {
                char c = col.charAt(j);
                map[i][j] = c;
                if (c == 'I') {
                    jongsoo = new Point(i, j);
                }
            }
        }
        String stDirArr = br.readLine();
        dirArr = new int[stDirArr.length()];
        for (int i = 0; i < stDirArr.length(); i++) {
            dirArr[i] = stDirArr.charAt(i) - '0';
        }

        solve();
    }


    static class Point {
        int r, c;
        boolean isDead = false;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void move(int dir) {
            map[this.r][this.c] = '.';
            this.r += vr[dir];
            this.c += vc[dir];
        }

        public void dead() {
            isDead = true;
        }

        public boolean isDead() {
            return isDead;
        }
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
