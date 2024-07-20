package ad_hoc;

import java.io.*;
import java.util.*;

public class BOJ10589_마법의_체스판 {

    static int n, m;

    static void solve() {
        StringBuilder sb = new StringBuilder();
        int k = n / 2 + m / 2;
        sb.append(k).append("\n");
        for (int i = 1; 1 + i <= n; i += 2) {
            sb.append(1 + i).append(" ").append(1).append(" ").append(1 + i).append(" ").append(m).append("\n");
        }
        for (int i = 1; 1 + i <= m; i += 2) {
            sb.append(1).append(" ").append(1 + i).append(" ").append(n).append(" ").append(1 + i).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        solve();
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
