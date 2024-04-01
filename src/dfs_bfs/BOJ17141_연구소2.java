package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ17141_연구소2 {

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    static int N, M, blankCount;
    //static Queue<Point> que = new LinkedList<>();
    static int ans = Integer.MAX_VALUE;
    static List<Point> pointList;
    static int[][] map;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        doDFS(1 << pointList.size(), 1, 0);
        doDFS((1 << pointList.size()) | 1, 1, 1);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void doDFS(int cur, int cnt, int pickCnt) {
        if (pickCnt == M) {
            System.out.println(Integer.toBinaryString(cur));
            Queue<Point> que = new LinkedList<>();
            int[][] copyMap = copyMap();
            for (int i = 0; i < pointList.size(); i++) {
                if (cur % 2 == 1) {
                    que.add(pointList.get(i));
                    copyMap[pointList.get(i).r][pointList.get(i).c] = VIRUS;
                }
                cur = cur >>> 1;
            }
            int bfs = doBFS(que, copyMap);
            ans = Math.min(ans, bfs != -1 ? bfs : Integer.MAX_VALUE);
            return;
        } else if (pickCnt > M || cnt == pointList.size()) {
            return;
        }

        doDFS(cur, cnt + 1, pickCnt);
        doDFS(cur | (1 << cnt), cnt + 1, pickCnt + 1);
    }

    static int doBFS(Queue<Point> que, int[][] copyMap) {
        int time = -1;
        while (!que.isEmpty()) {
            time++;
            int queSZ = que.size();
            while (queSZ-- > 0) {
                Point cur = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + vr[i];
                    int nc = cur.c + vc[i];
                    if (checkBoundary(nr, nc) && copyMap[nr][nc] == BLANK) {
                        copyMap[nr][nc] = VIRUS;
                        que.add(new Point(nr, nc));
                    }
                }
            }
        }
        return checkMap(copyMap) ? time : -1;
    }

    static void showMap() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    static boolean checkMap(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    static int[][] copyMap() {
        int[][] ret = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, ret[i], 0, N);
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = readInputFile();
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        pointList = new ArrayList<>(M);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == VIRUS) {
                    pointList.add(new Point(i, j));
                    map[i][j] = BLANK;
                }
            }
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
