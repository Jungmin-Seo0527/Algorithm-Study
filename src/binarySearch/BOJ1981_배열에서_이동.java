
package binarySearch;

import java.io.*;
import java.util.*;

// BOJ1981_배열에서_이동
// 배열내에서 (1, 1)에서 (N, N)으로 가는 모든 이동경로중에서
// 경로의 숫자의 최대값-최소값이 가장 작을때의 그 값을출력

// Bineary Search + BFS
// 각 배열의 입력값들중 최대값 최소값을 뽑는다.
// 답이 될수 있는 가장 큰수를 end로 두어 이분탐색으로 답이 될수 있는 경우를 탐색한다.
// BFS는 이분탐색의 조건이 된다. mid값 즉 최대값-최소값을 넘겨주어 이 경우에 출발지부터 도착지점까지
// 이동 가능한가에 대한 반환
// 최대값-최소값을 파라미터로 받은 doBFS는 실제 그 차이값이 될수 있는 모든 최소값 최대값에 대해 BFS를 시도한다.
public class BOJ1981_배열에서_이동 {

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, arr[][], ans, input_min = Integer.MAX_VALUE, input_max;

    static int v_r[] =
            {1, -1, 0, 0};
    static int v_c[] =
            {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        InputAndSettingData();
        solve();
        // if (doBFS(2)) System.out.println("ddd");
    }

    static void solve() {
        int start = 0;
        int end = input_max - input_min;
        int ans = 0;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            //System.out.println(mid);
            if (doBFS(mid)) {
                //System.out.println(start + " " + end + " " + mid);
                ans = mid;
                end = mid - 1;
            } else start = mid + 1;
        }
        System.out.println(ans);
    }

    // diff가 될수 있는 모든 최소값(min), 최대값(max) 에 대해 경로 탐색
    static boolean doBFS(int diff) {
        Queue<Point> que = new LinkedList<>();
        boolean visited[][];
        for (int i = input_min; i <= input_max; i++) {
            int min = i;
            int max = min + diff;
            //System.out.println(min + " " + max);
            if (max > input_max) continue;
            if (arr[0][0] < min || arr[0][0] > max) continue;
            que.clear();
            visited = new boolean[N][N];

            que.add(new Point(0, 0));
            visited[0][0] = true;
            while (que.isEmpty() == false) {
                Point cur = que.poll();
                if (cur.row == N - 1 && cur.col == N - 1) return true;
                for (int d = 0; d < 4; d++) {
                    int next_row = cur.row + v_r[d];
                    int next_col = cur.col + v_c[d];
                    if (check(next_row, next_col, visited, min, max)) {
                        visited[next_row][next_col] = true;
                        que.add(new Point(next_row, next_col));
                    }
                }
            }
        }
        return false;
    }

    static boolean check(int r, int c, boolean visited[][], int min, int max) {
        if (r < 0 || r >= N || c < 0 || c >= N) return false;
        if (visited[r][c]) return false;
        if (arr[r][c] < min || arr[r][c] > max) return false;
        return true;
    }

    static void InputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] > input_max) input_max = arr[i][j];
                if (arr[i][j] < input_min) input_min = arr[i][j];
            }
        }
    }
}
