package implementation;

import java.io.*;
import java.util.*;

public class BOJ18500_미네랄_2 {

    static int rowSZ, colSZ, N;
    static char[][] map;
    static int[] throwArr;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        for (int i = 0; i < N; i++) {

            // 1. 미네랄 파괴
            destroy(i, throwArr[i]);

            // 2. 추락할 클러스터 찾기
            boolean[][] visited = new boolean[rowSZ][colSZ];
            List<Point> cluster = new ArrayList<>();
            for (int r = 0; r < rowSZ; r++) {
                for (int c = 0; c < colSZ; c++) {
                    if (!visited[r][c] && map[r][c] == 'x') {
                        cluster = bfs(r, c, visited);
                    }
                    if (!cluster.isEmpty()) break;
                }
                if (!cluster.isEmpty()) break;
            }

            if (cluster.isEmpty()) continue;

            // 3. 추락
            for (int c = 0; c < cluster.size(); c++) {
                map[cluster.get(c).r][cluster.get(c).c] = '.';
            }
            fallCluster(cluster);
        }
        showMap();

    }

    static void destroy(int n, int height) {
        int r = rowSZ - height;
        int c = 0;
        int dir = 2; // 오른쪽

        if (n % 2 == 0) {
            c = 0; // 순번이 짝수면 왼쪽
        } else {
            c = colSZ - 1; // 홀수면 오른쪽
            dir = 3;
        }

        while (map[r][c] == '.') {
            int nr = r + vr[dir];
            int nc = c + vc[dir];
            if (!checkBoundary(nr, nc)) break;
            r = nr;
            c = nc;
        }

        if (checkBoundary(r, c) && map[r][c] == 'x') {
            map[r][c] = '.';
        }
    }

    static boolean bfs(List<Point> start) {
        Queue<Point> que = new LinkedList<>(start);
        boolean floor = false;
        boolean[][] visited = new boolean[rowSZ][colSZ];
        for (int i = 0; i < start.size(); i++) {
            visited[start.get(i).r][start.get(i).c] = true;
        }
        while (!que.isEmpty()) {
            Point cur = que.poll();

            int nr = cur.r + vr[0];
            int nc = cur.c + vc[0];
            if (checkBoundary(nr, nc) && map[nr][nc] == 'x' && !visited[nr][nc]) {
                que.add(new Point(nr, nc));
                visited[nr][nc] = true;

                if (nr == rowSZ - 1 || map[nr][nc] == 'x') {
                    floor = true;
                    break;
                }
            }

        }
        return floor;
    }

    static List<Point> bfs(int r, int c, boolean[][] visited) {
        Queue<Point> que = new LinkedList<>();
        boolean floor = false;
        List<Point> list = new ArrayList<>();

        visited[r][c] = true;
        que.add(new Point(r, c));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            list.add(cur);
            if (cur.r == rowSZ - 1) floor = true;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && map[nr][nc] == 'x' && !visited[nr][nc]) {
                    que.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        if (floor) list.clear();

        return list;
    }

    static void fallCluster(List<Point> cluster) {
        while (true) {
            boolean b = false;
            for (int i = 0; i < cluster.size(); i++) {
                Point c = cluster.get(i);
                c.r++;
                if (c.r == rowSZ - 1) b = true;
            }
            if (b) break;
            if (bfs(cluster)) break;
        }
        for (int i = 0; i < cluster.size(); i++) {
            map[cluster.get(i).r][cluster.get(i).c] = 'x';
        }
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static void showMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowSZ; i++) {
            sb.append(map[i]).append("\n");
        }
        System.out.println(sb);
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


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            map[i] = br.readLine().toCharArray();
        }
        N = Integer.parseInt(br.readLine());
        throwArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            throwArr[i] = Integer.parseInt(st.nextToken());
        }

        solve();
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
