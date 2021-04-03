package kakaoCommerce2021;

import java.util.ArrayList;
import java.util.List;

public class S3 {
    private int n, ans1, ans2;
    private int[] passenger;
    private int[][] train;
    private boolean[] visited;
    private List<List<Integer>> list = new ArrayList<>();

    public int[] solution(int n, int[] passenger, int[][] train) {
        init(n, passenger, train);
        permutation(passenger[0], 1);

        return new int[]{ans1, ans2};
    }

    private void permutation(int cnt, int cur) {
        if (ans2 < cnt) {
            ans2 = cnt;
            ans1 = cur;
        }
        if (ans2 == cnt) {
            ans1 = Math.max(ans1, cur);
        }

        visited[cur] = true;
        for (Integer next : list.get(cur)) {
            if (!visited[next]) {
                permutation(cnt + passenger[next - 1], next);
            }
        }
        visited[cur] = false;
    }

    private void init(int n, int[] p, int[][] t) {
        this.n = n;
        this.passenger = p;
        this.train = t;
        visited = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] tt : this.train) {
            list.get(tt[0]).add(tt[1]);
            list.get(tt[1]).add(tt[0]);
        }
    }
}