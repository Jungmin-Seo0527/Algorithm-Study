package dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BOJ12919_A와_B_2 {

    static String S, T;

    static void solve() {
        dfs(T);
        System.out.println("0");
    }

    static void dfs(String cur) {
        if (cur.length() <= S.length()) {
            if (cur.equals(S)) {
                System.out.println("1");
                System.exit(0);
            }
            return;
        }

        if (cur.charAt(cur.length() - 1) == 'A') {
            dfs(cur.substring(0, cur.length() - 1));
        }
        if (cur.charAt(0) == 'B') {
            dfs(new StringBuilder(cur.substring(1, cur.length())).reverse().toString());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        solve();
    }

    private static BufferedReader getBufferedReader() throws IOException {
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
