package dp;

import java.io.*;
import java.util.*;

public class BOJ12869_뮤탈리스크 {

    static int N;
    static int[] arr;
    static int[][][] visited = new int[61][61][61];
    static int ans = Integer.MAX_VALUE;

    static void solve() {
        attack(arr[0], arr[1], arr[2], 0);
        System.out.println(ans);
    }

    static void attack(int s1, int s2, int s3, int cnt) {
        if (s1 <= 0 && s2 <= 0 && s3 <= 0) {
            ans = Math.min(ans, cnt);
            return;
        }
        if (s1 < 0) s1 = 0;
        if (s2 < 0) s2 = 0;
        if (s3 < 0) s3 = 0;

        if (visited[s1][s2][s3] <= cnt && visited[s1][s2][s3] != 0) return;
        visited[s1][s2][s3] = cnt;

        attack(s1 - 1, s2 - 3, s3 - 9, cnt + 1);
        attack(s1 - 1, s2 - 9, s3 - 3, cnt + 1);
        attack(s1 - 3, s2 - 1, s3 - 9, cnt + 1);
        attack(s1 - 3, s2 - 9, s3 - 1, cnt + 1);
        attack(s1 - 9, s2 - 1, s3 - 3, cnt + 1);
        attack(s1 - 9, s2 - 3, s3 - 1, cnt + 1);
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
        N = Integer.parseInt(st.nextToken());
        arr = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solve();
    }
}
