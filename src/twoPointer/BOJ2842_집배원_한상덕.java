package twoPointer;

import java.io.*;
import java.util.*;

public class BOJ2842_집배원_한상덕 {
    static int N, hCnt;
    static char[][] map;
    static int[][] altitude;
    static int[] vr = {1, 1, 1, -1, -1, -1, 0, 0};
    static int[] vc = {1, 0, -1, 1, 0, -1, 1, -1};
    static Point post;
    static Set<Integer> altitudeSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static int[] setToArr() {
        Iterator<Integer> altitudeIter = altitudeSet.iterator();
        int[] altitudeArr = new int[altitudeSet.size()];
        int arrIdx = 0;
        while (altitudeIter.hasNext()) {
            altitudeArr[arrIdx++] = altitudeIter.next();
        }
        return altitudeArr;
    }

    static void solve() {
        // int[] arr = setToArr();
        Integer[] arr = altitudeSet.toArray(new Integer[0]);
        Arrays.sort(arr);
        int left = 0;
        int right = 0;
        int ans = Integer.MAX_VALUE;
        while (left <= right && right < arr.length) {
            int min = arr[left];
            int max = arr[right];
            if (doBFS(min, max)) {
                ans = Math.min(ans, max - min);
                left++;
            } else {
                right++;
            }
        }
        System.out.println(ans);
    }

    static boolean doBFS(int min, int max) {
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int homeCnt = 0;
        if (altitude[post.row][post.col] >= min && altitude[post.row][post.col] <= max) {
            que.add(post);
            visited[post.row][post.col] = true;
        }
        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 8; i++) {
                int nr = cur.row + vr[i];
                int nc = cur.col + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc] &&
                        altitude[nr][nc] >= min && altitude[nr][nc] <= max) {
                    if (map[nr][nc] == 'K') {
                        homeCnt++;
                    }
                    que.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        return homeCnt == hCnt;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        altitude = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'P') {
                    post = new Point(i, j);
                } else if (map[i][j] == 'K') {
                    hCnt++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                altitude[i][j] = Integer.parseInt(st.nextToken());
                altitudeSet.add(altitude[i][j]);
            }
        }
    }
}
