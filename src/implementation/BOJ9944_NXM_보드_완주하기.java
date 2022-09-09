package implementation;

import java.io.*;
import java.util.*;

public class BOJ9944_NXM_보드_완주하기 {

    static int rowSZ, colSZ, blankCount;
    static char[][] map;
    static boolean[][] visited;
    static final int MAX = 1_000_001;

    static int[] vr = {1, 0, -1, 0};
    static int[] vc = {0, 1, 0, -1};

    static int solve() {
        int ret = MAX;
        for (int r = 0; r < rowSZ; r++) {
            for (int c = 0; c < colSZ; c++) {
                if (isPossibleMove(r, c)) {
                    visited[r][c] = true;
                    ret = Math.min(ret, dfs(r, c, 1));
                    visited[r][c] = false;
                }
            }
        }
        return ret == MAX ? -1 : ret;
    }

    static int dfs(int r, int c, int cnt) {
        if (cnt == blankCount) {
            return 0;
        }
        int ret = MAX;
        for (int v = 0; v < 4; v++) {
            int nr = r;
            int nc = c;
            int moveCnt = 0;
            while (isPossibleMove(nr + vr[v], nc + vc[v])) {
                nr += vr[v];
                nc += vc[v];
                visited[nr][nc] = true;
                moveCnt++;
            }

            if (moveCnt > 0) {
                ret = Math.min(ret, dfs(nr, nc, cnt + moveCnt) + 1);
            }

            for (int m = 0; m < moveCnt; m++) {
                visited[nr][nc] = false;
                nr -= vr[v];
                nc -= vc[v];
            }
        }
        return ret;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static boolean checkObstacle(int r, int c) {
        return map[r][c] == '.';
    }

    static boolean isPossibleMove(int r, int c) {
        return checkBoundary(r, c) && checkObstacle(r, c) && !visited[r][c];
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();
        int Case = 0;
        while (true) {
            String brReadLine = br.readLine();
            if (brReadLine == null || brReadLine.equals("")) break;
            StringTokenizer st = new StringTokenizer(brReadLine);

            rowSZ = Integer.parseInt(st.nextToken());
            colSZ = Integer.parseInt(st.nextToken());
            map = new char[rowSZ][colSZ];
            visited = new boolean[rowSZ][colSZ];
            blankCount = 0;
            for (int i = 0; i < rowSZ; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < colSZ; j++) {
                    if (map[i][j] == '.') blankCount++;
                }
            }
            answer.append("Case ").append(++Case).append(": ").append(solve()).append("\n");
        }
        System.out.println(answer);
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
