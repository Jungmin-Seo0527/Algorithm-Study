package samsung;

import java.io.*;
import java.util.*;

public class BOJ21610_마법사_상어와_비바라기 {
    static int N, M;
    static int[][] map;
    static List<MoveInfo> moveInfos;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Cloud cloud = new Cloud(N);
        for (MoveInfo moveInfo : moveInfos) {
            cloud.moveCloud(moveInfo);
            raining(cloud);
            copyWaterMagic(cloud);
            minusWater(cloud);
        }
        System.out.println(countWaterInMap());
    }


    static int countWaterInMap() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += map[i][j];
            }
        }
        return cnt;
    }

    static void minusWater(Cloud c) {
        boolean[][] visited = new boolean[N][N];
        List<Point> tempC = c.copyCloudsList();
        for (Point cloud : tempC) {
            visited[cloud.row][cloud.col] = true;
        }

        c.clearCloud();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] >= 2) {
                    map[i][j] -= 2;
                    c.addCloud(i, j);
                }
            }
        }
    }

    static void copyWaterMagic(Cloud c) {
        List<Integer> plusWater = new ArrayList<>(4);
        for (Point cloud : c.clouds) {
            int cnt = 0;
            for (int i = 1; i < 8; i += 2) {
                int nr = cloud.row + c.vr[i];
                int nc = cloud.col + c.vc[i];
                if (checkBoundary(nr, nc) && map[nr][nc] > 0) {
                    cnt++;
                }
            }
            plusWater.add(cnt);
        }

        int idx = 0;
        for (Point cloud : c.clouds) {
            map[cloud.row][cloud.col] += plusWater.get(idx++);
        }
    }

    static void raining(Cloud cloud) {
        for (Point point : cloud.clouds) {
            map[point.row][point.col]++;
        }
    }

    static class Cloud {
        List<Point> clouds = new ArrayList<>();
        int N;
        int[] vr = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] vc = {-1, -1, 0, 1, 1, 1, 0, -1};

        public Cloud(int N) {
            this.N = N;
            this.clouds.add(new Point(N - 2, 0));
            this.clouds.add(new Point(N - 2, 1));
            this.clouds.add(new Point(N - 1, 0));
            this.clouds.add(new Point(N - 1, 1));
        }

        public List<Point> copyCloudsList() {
            return new ArrayList<>(clouds);
        }

        public void addCloud(int r, int c) {
            clouds.add(new Point(r, c));
        }

        public void clearCloud() {
            clouds.clear();
        }

        public void moveCloud(MoveInfo moveInfo) {
            for (Point c : clouds) {
                c.row = movePoint(c.row, moveInfo, vr);
                c.col = movePoint(c.col, moveInfo, vc);
            }
        }

        private int movePoint(int p, MoveInfo moveInfo, int[] d) {
            p = p + moveInfo.s * d[moveInfo.d];
            while (p < 0) {
                p += N;
            }
            p %= N;
            return p;
        }

        @Override
        public String toString() {
            return "Cloud{" +
                    "clouds=" + clouds +
                    '}';
        }
    }

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void showMap(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int[] m : map) {
            sb.append(Arrays.toString(m)).append("\n");
        }
        System.out.println(sb);
    }

    static class MoveInfo {
        int d, s;

        public MoveInfo(int d, int s) {
            this.d = d;
            this.s = s;
        }
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        moveInfos = new ArrayList<>(M);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            moveInfos.add(new MoveInfo(d, s));
        }
    }
}
