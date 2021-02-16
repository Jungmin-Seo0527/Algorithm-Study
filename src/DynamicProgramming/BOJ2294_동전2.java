/*
    BOJ2294_동전2
    --------------------------------------------------------------------------------------------------------------------
    문제

    n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다.
    그러면서 동전의 개수가 최소가 되도록 하려고 한다. 각각의 동전은 몇 개라도 사용할 수 있다.

    사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
    --------------------------------------------------------------------------------------------------------------------
    입력

    첫째 줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다.
    동전의 가치는 100,000보다 작거나 같은 자연수이다. 가치가 같은 동전이 여러 번 주어질 수도 있다.
    --------------------------------------------------------------------------------------------------------------------
    출력

    첫째 줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.
    --------------------------------------------------------------------------------------------------------------------
    예제 입력 1

    3 15
    1
    5
    12
    --------------------------------------------------------------------------------------------------------------------
    예제 출력 1

    3
    --------------------------------------------------------------------------------------------------------------------
    풀이

    전형적인 다이나믹 프로그래밍
    1부터 K 까지 각각 최소의 동전을 사용하는 방법을 메모라이징

    dp[i] = min(dp[i - coins]) + 1
    --------------------------------------------------------------------------------------------------------------------
 */
package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ2294_동전2 {
    static int N, K;
    static int[] dp, coins;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        for (int i = 1; i <= K; i++) {
            dp[i] = min(i);
        }
        //System.out.println(Arrays.toString(dp));
        System.out.println(dp[K]);
    }

    static int min(int idx) {
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (idx - coins[i] >= 0 && dp[idx - coins[i]] != -1) {
                ret = Math.min(ret, dp[idx - coins[i]]);
            }
        }
        if (ret == Integer.MAX_VALUE) ret = -2;
        return ret + 1;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
    }
}
