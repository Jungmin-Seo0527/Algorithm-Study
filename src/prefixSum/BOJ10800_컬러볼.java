package prefixSum;

import java.io.*;
import java.util.*;

public class BOJ10800_컬러볼 {

    static int N;
    static int[] cSum, ans;
    static List<Ball> balls;

    static void solve() {
        StringBuilder out = new StringBuilder();
        balls.sort(Ball::compareTo);

        int sum = 0;
        int bIdx = 0;
        for (; bIdx < N; bIdx++) {
            Ball ball = balls.get(bIdx);
            ans[ball.num] = sum - cSum[ball.color];

            int sIdx = bIdx + 1;
            while (sIdx < N && balls.get(sIdx).size == ball.size) {
                Ball sameSizeBall = balls.get(sIdx++);
                ans[sameSizeBall.num] = sum - cSum[sameSizeBall.color];
            }

            for (int i = bIdx; i < sIdx; i++) {
                sum += balls.get(i).size;
                cSum[balls.get(i).color] += balls.get(i).size;
            }
            bIdx = sIdx - 1;
        }

        for (int i = 1; i <= N; i++) {
            out.append(ans[i]).append("\n");
        }
        System.out.println(out);
    }

    static class Ball implements Comparable<Ball> {
        int num, color, size;

        public Ball(int num, int color, int size) {
            this.num = num;
            this.color = color;
            this.size = size;
        }

        @Override public int compareTo(Ball o) {
            return this.size - o.size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        balls = new ArrayList<>(N);
        cSum = new int[N + 1];
        ans = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int ss = Integer.parseInt(st.nextToken());
            balls.add(new Ball(i + 1, c, ss));
        }
        solve();
    }
}
