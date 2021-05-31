package twoPointer;

import java.io.*;
import java.util.*;

public class BOJ1981_배열에서_이동 {
    static int N;
    static int[][] map;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static Set<Integer> set = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Iterator<Integer> iter = set.iterator();
        int[] arr = new int[set.size()];
        int arrIdx = 0;
        while (iter.hasNext()) {
            arr[arrIdx++] = iter.next();
        }

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

    static void solve2() {
        Iterator<Integer> iter = set.iterator();
        int[] arr = new int[set.size()];
        int arrIdx = 0;
        while (iter.hasNext()) {
            arr[arrIdx++] = iter.next();
        }
        int inputMin = arr[0];
        int inputMax = arr[arr.length - 1];
        int start = 0;
        int end = inputMax - inputMin;

        while (start < end) {
            int mid = (start + end) >>> 1;
            boolean flag = false;
            for (int min = inputMin; min <= inputMax - mid; min++) {
                int max = min + mid;
                if (doBFS(min, max)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(end);
    }

    static boolean doBFS(int min, int max) {
        Queue<Integer> que = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        if (map[0][0] >= min && map[0][0] <= max) {
            que.add(0);
            visited[0][0] = true;
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == N * N - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur / N + vr[i];
                int nc = cur % N + vc[i];
                if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] >= min && map[nr][nc] <= max) {
                    que.add(nr * N + nc);
                    visited[nr][nc] = true;
                }
            }
        }

        return false;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                set.add(map[i][j]);
            }
        }
    }
}
