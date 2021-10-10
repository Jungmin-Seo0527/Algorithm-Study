package implementation;

import java.io.*;
import java.util.*;

public class BOJ20165_인내의_도미노_장인_호석 {

    static int rowSZ, colSZ, R;
    static int[][] map;
    static char[][] ans;
    static int[] vr = {0, 0, 1, -1};
    static int[] vc = {1, -1, 0, 0};

    static int offence(int r, int c, char d) {
        int dir = getDir(d);
        int cnt = 0;
        int ret = 0;
        while (true) {
            if (ans[r][c] == 'S') {
                cnt = Math.max(cnt, map[r][c]);
                ret++;
            }
            cnt--;
            ans[r][c] = 'F';
            int nr = r + vr[dir];
            int nc = c + vc[dir];
            if (!checkBoundary(nr, nc) || cnt == 0) {
                break;
            }
            r = nr;
            c = nc;
        }
        return ret;
    }

    private static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    private static int getDir(char d) {
        int dir = 0;
        if (d == 'W') dir = 1;
        else if (d == 'S') dir = 2;
        else if (d == 'N') dir = 3;
        return dir;
    }

    static void defence(int r, int c) {
        ans[r][c] = 'S';
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];
        ans = new char[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            Arrays.fill(ans[i], 'S');
        }
        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int or = Integer.parseInt(st.nextToken());
            int oc = Integer.parseInt(st.nextToken());
            char od = st.nextToken().charAt(0);
            score += offence(or - 1, oc - 1, od);
            st = new StringTokenizer(br.readLine());
            int dr = Integer.parseInt(st.nextToken());
            int dc = Integer.parseInt(st.nextToken());
            defence(dr - 1, dc - 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(score).append("\n");
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
