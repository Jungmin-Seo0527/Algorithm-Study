package samsung;

import java.io.*;
import java.util.*;

public class BOJ21611_마법사_상어와_블리자드 {
    static int N, M;
    static int[][] map, magic;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        for (int i = 0; i < M; i++) {
            blizzardMagic(magic[i][0], magic[i][1]);
            int[] arr = mapTo1dArr();
            moveBeadToBlank(arr);

            while (true) {
                int b = bombBead(arr);
                if (b == 0) break;
                ans += b;
                moveBeadToBlank(arr);
            }
            arr = groupingBean(arr);
            map = arrToMap(arr);
        }
        System.out.println(ans);
    }

    static int[] groupingBean(int[] arr) {
        int[] ret = new int[arr.length];
        int retIdx = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.add(i);
            } else if (arr[stack.peek()] == arr[i]) {
                stack.add(i);
            } else {
                int A = stack.size();
                int B = arr[stack.peek()];
                stack.clear();
                ret[retIdx++] = A;
                if (retIdx == ret.length) break;
                ret[retIdx++] = B;
                if (retIdx == ret.length) break;
                stack.add(i);
            }
        }
        return ret;
    }

    static int bombBead(int[] arr) {
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < arr.length; i++) {
            if (stack.isEmpty()) {
                stack.add(i);
            } else if (arr[stack.peek()] == arr[i]) {
                stack.add(i);
            } else {
                if (stack.size() < 4) {
                    stack.clear();
                } else {
                    while (!stack.isEmpty()) {
                        int idx = stack.pop();
                        ret += arr[idx];
                        arr[idx] = 0;
                    }
                }
                stack.add(i);
            }
        }
        return ret;
    }

    static void moveBeadToBlank(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) continue;

            int cur = i;
            while (arr[cur - 1] == 0 && cur - 1 >= 1) {
                arr[cur - 1] = arr[cur];
                arr[cur] = 0;
                cur--;
            }
        }
    }

    static void blizzardMagic(int d, int s) {
        int[] vr = {-1, 1, 0, 0};
        int[] vc = {0, 0, -1, 1};
        int r = N / 2;
        int c = N / 2;
        d--;
        for (int i = 1; i <= s; i++) {
            r += vr[d];
            c += vc[d];
            if (checkBoundary(r, c)) {
                map[r][c] = 0;
            }
        }
    }

    static int[][] arrToMap(int[] arr) {
        int[][] ret = new int[N][N];
        int[] vr = {0, 1, 0, -1};
        int[] vc = {-1, 0, 1, 0};
        int dir = 0;
        int curDistCnt = 0;
        int dist = 1;
        int distCnt = 0;
        int r = N / 2;
        int c = N / 2;
        int arrIdx = 1;
        while (checkBoundary(r + vr[dir], c + vc[dir])) {
            r += vr[dir];
            c += vc[dir];
            ret[r][c] = arr[arrIdx++];
            curDistCnt++;
            if (curDistCnt == dist) {
                curDistCnt = 0;
                distCnt++;
                dir = (dir + 1) % 4;
                if (distCnt == 2) {
                    distCnt = 0;
                    dist++;
                }
            }
        }
        return ret;
    }

    static int[] mapTo1dArr() {
        int[] ret = new int[N * N];
        int[] vr = {0, 1, 0, -1};
        int[] vc = {-1, 0, 1, 0};
        int dir = 0;
        int curDistCnt = 0;
        int dist = 1;
        int distCnt = 0;
        int r = N / 2;
        int c = N / 2;
        int retIdx = 1;
        while (checkBoundary(r + vr[dir], c + vc[dir])) {
            r += vr[dir];
            c += vc[dir];
            ret[retIdx++] = map[r][c];
            curDistCnt++;
            if (curDistCnt == dist) {
                curDistCnt = 0;
                distCnt++;
                dir = (dir + 1) % 4;
                if (distCnt == 2) {
                    distCnt = 0;
                    dist++;
                }
            }
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void showMap(int[][] map) {
        for (int[] m : map) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        magic = new int[M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            magic[i][0] = Integer.parseInt(st.nextToken());
            magic[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
