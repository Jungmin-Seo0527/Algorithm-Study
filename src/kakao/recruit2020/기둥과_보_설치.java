package kakao.recruit2020;

import java.util.ArrayList;
import java.util.List;

public class 기둥과_보_설치 {
    private int N;
    private int[][] build_frame;
    Map map;

    public int[][] solution(int n, int[][] build_frame) {
        init(n, build_frame);
        return ans();
    }

    private int[][] ans() {
        List<int[]> retList = new ArrayList<>();

        for (int[] info : build_frame) {
            // System.out.println(Arrays.toString(info));
            int x = info[0];
            int y = info[1];
            int a = info[2];
            int b = info[3];
            if (a == 0 && b == 0) map.removePillar(x, y);
            else if (a == 0 && b == 1) map.buildPillar(x, y);
            else if (a == 1 && b == 0) map.removeCloth(x, y);
            else if (a == 1 & b == 1) map.buildCloth(x, y);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map.map[i][j].pillarStart) retList.add(new int[]{i, j, 0});
                if (map.map[i][j].clothStart) retList.add(new int[]{i, j, 1});
            }
        }
        int[][] ret = new int[retList.size()][3];
        int retIdx = 0;
        for (int[] arr : retList) {
            ret[retIdx++] = arr;
        }

        return ret;
    }


    private void init(int n, int[][] b) {
        this.N = n + 1;
        this.build_frame = b;
        map = new Map(this.N);
    }

    static class Map {
        private int N;
        private Point[][] map;

        public Map(int N) {
            this.N = N;
            map = new Point[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = new Point();
                }
            }
        }

        public void buildPillar(int x, int y) {
            map[x][y].pillarStart = true;
            map[x][y + 1].pillarEnd = true;
            if (!checkPillar(x, y)) {
                map[x][y].pillarStart = false;
                map[x][y + 1].pillarEnd = false;
            }
        }

        public void buildCloth(int x, int y) {
            map[x][y].clothStart = true;
            map[x + 1][y].clothEnd = true;
            if (!checkCloth(x, y)) {
                map[x][y].clothStart = false;
                map[x + 1][y].clothEnd = false;
            }
        }

        public void removePillar(int x, int y) {
            map[x][y].pillarStart = false;
            map[x][y + 1].pillarEnd = false;
            if (!checkAll(x, y)) {
                map[x][y].pillarStart = true;
                map[x][y + 1].pillarEnd = true;
            }
        }

        public void removeCloth(int x, int y) {
            map[x][y].clothStart = false;
            map[x + 1][y].clothEnd = false;
            if (!checkAll(x, y)) {
                map[x][y].clothStart = true;
                map[x + 1][y].clothEnd = true;
            }
        }

        public boolean checkPillar(int x, int y) {
            if (y == 0) return true;
            if (map[x][y].clothStart || map[x][y].clothEnd) return true;
            if (map[x][y].pillarEnd) return true;
            return false;
        }

        public boolean checkCloth(int x, int y) {
            if (map[x][y].pillarEnd || map[x + 1][y].pillarEnd) return true;
            if (map[x][y].clothEnd && map[x + 1][y].clothStart) return true;
            return false;
        }

        public boolean checkAll() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j].pillarStart && !checkPillar(i, j)) return false;
                    if (map[i][j].clothStart && !checkCloth(i, j)) return false;
                }
            }
            return true;
        }

        public boolean checkAll(int x, int y) {
            int[] vr = {1, 1, 1, -1, -1, -1, 0, 0, 0};
            int[] vc = {1, 0, -1, 1, 0, -1, 1, 0, -1};
            for (int i = 0; i < 9; i++) {
                int nx = x + vr[i];
                int ny = y + vc[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (map[nx][ny].pillarStart && !checkPillar(nx, ny)) return false;
                    if (map[nx][ny].clothStart && !checkCloth(nx, ny)) return false;
                }
            }
            return true;
        }
    }

    static class Point {
        boolean pillarStart, pillarEnd, clothStart, clothEnd;

        public Point() {
            this.pillarStart = false;
            this.pillarEnd = false;
            this.clothStart = false;
            this.clothEnd = false;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "pillarStart=" + pillarStart +
                    ", pillarEnd=" + pillarEnd +
                    ", clothStart=" + clothStart +
                    ", clothEnd=" + clothEnd +
                    '}';
        }
    }
}
