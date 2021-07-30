package implementation;

import java.io.*;
import java.util.*;

public class BOJ1022_소용돌이_예쁘게_만들기 {

    static int r1, r2, c1, c2, rowSZ, colSZ;
    static int[][] map;
    static final int MAX_SIZE = 11;

    static int[] vr = {0, -1, 0, 1};
    static int[] vc = {1, 0, -1, 0};

    static void solve() {
        int max = initMap();
        int maxLen = (int) Math.log10(max) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                String s = String.valueOf(map[i][j]);
                while (s.length() < maxLen) {
                    s = " " + s;
                }
                sb.append(s).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int initMap() {
        map = new int[r2 - r1 + 1][c2 - c1 + 1];
        rowSZ = r2 - r1 + 1;
        colSZ = c2 - c1 + 1;
        int ret = 0;
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                map[r - r1][c - c1] = func(r, c);
                ret = Math.max(ret, map[r - r1][c - c1]);
            }
        }
        return ret;
    }

    static int func(int r, int c) {
        if (r < 0 && c >= r && c + r <= 0)
            return 4 * r * r + r + 1 - c;
        else if (r >= 0 && c + r >= 0 && c <= r)
            return 4 * r * r + 3 * r + 1 + c;
        else if (c >= 0)
            return 4 * c * c - 3 * c - r + 1;
        else
            return 4 * c * c + r - c + 1;
    }

    private static int initMap0() {
        map = new int[r2 - r1 + 1][c2 - c1 + 1];
        rowSZ = r2 - r1 + 1;
        colSZ = c2 - c1 + 1;
        int r = 0;
        int c = 0;
        int d = 0;
        int n = 1; // 배열에 채워지는 값
        int curMoveDist = -1; // 현재 움직인 거리
        int curDirDist = 1; // 현재 방향으로 움직여야 하는 거리
        int changeDirCnt = 0; // 방향 변환 카운트
        int cnt = 0; // 회전 배열에서 채워진 구성 요소 - 배열의 크기만큼 채워지면 메소드 종료
        while (true) {
            if (r >= r1 && r <= r2 && c >= c1 && c <= c2) {
                map[r - r1][c - c1] = n;
                cnt++;
            }
            if (cnt == rowSZ * colSZ) {
                break;
            }
            n++;
            curMoveDist++;

            if (curMoveDist == curDirDist) {
                d = (d + 1) % 4;
                curMoveDist = 0;
                changeDirCnt++;
                if (changeDirCnt == 2) {
                    curDirDist++;
                    changeDirCnt = 0;
                }
            }
            r = r + vr[d];
            c = c + vc[d];
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        solve();
    }
}
