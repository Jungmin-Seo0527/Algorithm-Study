package greedy;

import java.io.*;
import java.util.*;

public class BOJ3109_빵집 {

    static int rowSZ, colSZ;
    static int[] vr = {-1, 0, 1};
    static int[] vc = {1, 1, 1};
    static char[][] map;
    static boolean[][] visited;

    static void solve() {
        int ans = 0;
        visited = new boolean[rowSZ][colSZ];

        for (int i = 0; i < rowSZ; i++) {
            if (map[i][0] == '.' && dfs(i, 0)) ans++;
        }
        System.out.println(ans);
    }

    static boolean dfs(int r, int c) {
        visited[r][c] = true;
        if (c == colSZ - 1) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            int nr = r + vr[i];
            int nc = c + vc[i];
            if (checkBoundary(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
                if (dfs(nr, nc)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
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
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input2.txt";
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
