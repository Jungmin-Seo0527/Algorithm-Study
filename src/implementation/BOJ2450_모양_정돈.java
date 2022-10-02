package implementation;

import java.io.*;
import java.util.*;

public class BOJ2450_모양_정돈 {

    static int N, ans = Integer.MAX_VALUE;
    static int[] cnt, order, check, arr, brr;


    static void solve() {
        DFS(1);
        System.out.println(ans);
    }

    static int calc() {
        int cnt1 = 0, cnt2 = 0;
        int[][] pos = new int[4][4];

        int k = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < cnt[order[i]]; j++) {
                brr[k++] = order[i];
            }
        }

        for (int i = 0; i < N; i++) {
            pos[brr[i]][arr[i]]++;
        }

        for (int i = 1; i < 3; i++) {
            for (int j = i + 1; j <= 3; j++) {
                k = Math.min(pos[i][j], pos[j][i]);
                cnt1 += k;
                cnt2 += pos[i][j] + pos[j][i] - (k * 2);
            }
        }
        return cnt1 + cnt2 / 3 * 2;
    }

    static void DFS(int step) {
        if (step > 3) {
            ans = Math.min(ans, calc());
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (check[i] == 1) continue;
            check[i] = 1;
            order[step] = i;
            DFS(step + 1);
            check[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        brr = new int[N];
        cnt = new int[4];
        order = new int[4];
        check = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cnt[arr[i]]++;
        }
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
