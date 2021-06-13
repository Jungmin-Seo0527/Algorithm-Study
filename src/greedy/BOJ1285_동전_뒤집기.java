package greedy;

import java.io.*;
import java.util.*;

public class BOJ1285_동전_뒤집기 {
    static int N, ans = Integer.MAX_VALUE;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        doDFS(0);
        System.out.println(ans);
    }

    static void doDFS(int row) {
        if (row == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += getLeastCoins(i);
            }
            ans = Math.min(ans, cnt);
            return;
        }
        doDFS(row + 1);
        coins[row] = ~coins[row];
        doDFS(row + 1);
    }

    static int getLeastCoins(int col) {
        int T = 0;
        int bit = 1 << col;
        for (int i = 0; i < N; i++) {
            if ((coins[i] & bit) != 0) {
                T++;
            }
        }
        return Math.min(T, N - T);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c == 'T') {
                    coins[i] |= 1 << j;
                }
            }
        }
    }
}