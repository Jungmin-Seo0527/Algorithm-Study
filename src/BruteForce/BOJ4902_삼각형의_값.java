package BruteForce;

import java.io.*;
import java.util.*;

public class BOJ4902_삼각형의_값 {
    static int N, ans = Integer.MIN_VALUE;
    static int[] tri, preSum;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve() {
        int r = 1;
        ans = Integer.MIN_VALUE;
        for (int i = 1; i <= N * N; i++) {
            if ((r % 2 == 1 && i % 2 == 1)
                    || (r % 2 == 0 && i % 2 == 0)) doDFS(i, r, 1, 0, 1);
            else doDFS(i, r, 1, 0, -1);
            if (r * r == i) r++;
        }
        return ans;
    }

    static void doDFS(int start, int r, int len, int sum, int mode) {
        sum += preSum[start + len - 1] - preSum[start - 1];
        ans = Math.max(ans, sum);
        int nextR = r + mode;
        int nextLen = len + 2;
        int nextStart = start + (2 * r - 1) * mode;

        if (nextR >= 1 && nextR <= N
                && nextStart + nextLen - 1 <= nextR * nextR
                && nextStart >= nextR * nextR - 2 * nextR + 2) {
            doDFS(nextStart, nextR, nextLen, sum, mode);
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = 0;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            tri = new int[N * N + 1];
            preSum = new int[N * N + 1];
            for (int i = 1; i <= N * N; i++) {
                tri[i] = Integer.parseInt(st.nextToken());
                preSum[i] = preSum[i - 1] + tri[i];
            }
            sb.append(++t).append(". ").append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
