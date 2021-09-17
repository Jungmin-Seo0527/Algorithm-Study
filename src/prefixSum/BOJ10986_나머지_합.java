package prefixSum;

import java.io.*;
import java.util.*;

public class BOJ10986_나머지_합 {

    static int N, M;
    static long[] sum;

    static void solve() {
        int[] cnt = new int[M + 1];

        for (int i = 1; i <= N; i++) {
            int idx = (int) (sum[i] % M);
            cnt[idx]++;
        }

        long ret = cnt[0];
        for (int i = 0; i <= M; i++) {
            ret += (long) cnt[i] * (cnt[i] - 1) / 2;
        }
        System.out.println(ret);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = Long.parseLong(st.nextToken()) + sum[i - 1];
        }
        solve();
    }
}
