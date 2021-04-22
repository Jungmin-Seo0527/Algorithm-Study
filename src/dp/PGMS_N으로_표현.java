package dp;

import java.util.*;

public class PGMS_N으로_표현 {
    private Set<Integer>[] dp = new HashSet[9];

    public int solution(int N, int number) {
        if (N == number) return 1;

        StringBuilder n = new StringBuilder();

        for (int i = 1; i < 9; i++) {
            dp[i] = new HashSet<Integer>();
            dp[i].add(Integer.parseInt(n.append(N).toString()));
            if (i == 1) continue;

            for (int j = 1; j < i; j++) {
                unionSet(j, i);
            }
        }

        return ans(number);
    }

    private int ans(int number) {
        for (int i = 1; i < 9; i++) {
            for (Integer e : dp[i]) {
                if (e == number) return i;
            }
        }
        return -1;
    }

    private void unionSet(int setIdx1, int setIdx2) {
        for (Integer e1 : dp[setIdx1]) {
            for (Integer e2 : dp[setIdx2 - setIdx1]) {
                dp[setIdx2].add(e1 + e2);
                dp[setIdx2].add(e1 - e2);
                dp[setIdx2].add(e1 * e2);
                if (e2 > 0) dp[setIdx2].add(e1 / e2);
            }
        }
    }
}