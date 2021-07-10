package ds;

import java.io.*;
import java.util.*;

public class BOJ1976_여행_가자 {
    static int n, m;
    static int[] par, root;
    static int[][] info;

    static void solve() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (info[i][j] == 1) {
                    union(i + 1, j + 1);
                }
            }
        }

        int r = find(root[0]);
        for (int i = 1; i < m; i++) {
            if (find(root[i]) != r) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static int find(int x) {
        return x == par[x] ? x : (par[x] = find(par[x]));
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        par[x] = y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        info = new int[n][n];
        par = new int[n + 1];
        root = new int[m];
        for (int i = 0; i <= n; i++) {
            par[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            root[i] = Integer.parseInt(st.nextToken());
        }
        solve();
    }
}
