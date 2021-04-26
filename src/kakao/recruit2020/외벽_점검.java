package kakao.recruit2020;

import java.util.*;

public class 외벽_점검 {
    private int n, ans = Integer.MAX_VALUE;
    private int[] weak, dist;
    List<List<Integer>> weakList = new ArrayList<>();

    public int solution(int n, int[] weak, int[] dist) {
        init(n, weak, dist);
        int answer = 0;

        boolean[] visited = new boolean[dist.length];
        for (int i = 0; i < dist.length; i++) {
            visited[i] = true;
            List<Integer> list = new ArrayList<>();
            list.add(dist[i]);
            permutationDist(list, visited);
            visited[i] = false;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void func(List<Integer> distList) {
        for (List<Integer> wList : weakList) {
            if (check(wList, distList)) {
                ans = Math.min(ans, distList.size());
            }
        }
    }

    private boolean check(List<Integer> wList, List<Integer> distList) {
        int ret = 1;
        int distIdx = 0;
        int curDist = distList.get(distIdx);
        for (int i = 1; i < wList.size(); i++) {
            int n = wList.get(i) - wList.get(i - 1);
            if (n <= curDist) {
                curDist -= n;
                ret++;
            } else {
                distIdx++;
                if (distIdx == distList.size()) break;
                curDist = distList.get(distIdx);
                ret++;
            }
        }

        return ret == wList.size();
    }

    private void permutationDist(List<Integer> list, boolean[] visited) {

        func(list);
        if (list.size() == this.dist.length) {
            return;
        }

        for (int i = 0; i < this.dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                List<Integer> nextList = new ArrayList<>(list);
                nextList.add(dist[i]);
                permutationDist(nextList, visited);
                visited[i] = false;
            }
        }
    }

    private void init(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;

        for (int i = 0; i < weak.length; i++) {
            weakList.add(new ArrayList<>());
            for (int j = 0; j < weak.length; j++) {
                int idx = i + j;
                int w = 0;
                if (idx >= weak.length) {
                    idx %= weak.length;
                    w = weak[idx] + n;
                } else {
                    w = weak[idx];
                }
                weakList.get(i).add(w);
            }
        }
    }
}
