/*
    BOJ12865_평범한_배낭
    --------------------------------------------------------------------------------------------------------------------
    문제 링크

    https://www.acmicpc.net/problem/12865
    --------------------------------------------------------------------------------------------------------------------
    풀이

    문제 제목 그대로 평범한 배낭 문제이다.
    단 기존에는 dp를 이차원으로 할당하여 풀어내는 방법만을 고집했었는데 이 문제에서는 일차원으로 dp를 할당하여 시간을 더욱
    단축시켰다.

    기존에는 들어갈 물건순으로, 무게를 오름차순으로 검사하였다. 그래서 순수하게 현재 물건을 넣는것이 좋은지(이전에 남는 무게만큼 다른
    물건이 들어왔을 수 있다. 이미 최대값들을 넣었다) 아니면 이전 단계의 물건을 넣었을때의 무게가 더 최대값인지 비교를 했다.
    점화식으로 표현하면
    dp[n][w] = max(dp[n-1][w] , dp[n-1][w-curW]+curV) 였다.

    이차원은 만약에 현재 가방의 최대 무게에서 물건을 넣은후 그 값이 다름 무게의 가방에 넣을때에 영향을 주지 않기 위해서 이를 행으로
    분리한 것이다.
    예를 들면 만약 같은 방법으로 첫번째 물건인 무게 6, 가치 13 의 물건을 넣는다고 가정해보자
    우선 최대 가방 무게인 열이 6일때 물건을 넣을 수 있고 그 다음부터 문제에서 주어진 가방 최대 한도인 K만큼 13이 채워질 것이다.
    만일 K가 12 이상인 경우에, 최대 가방 무게가 12 일때는 이전에 무게 6일때 이미 13을 넣었는데 그 상태에서 또다시 13의 물건을 넣어
    버린다. (결과적으로 26이 되어 버린다. 같은 무게의 물건을 두번 넣은 꼴이 된다.)
    그래서 이 상황을 막고자 행으로 분리해서 현재 물건을 넣은 상태는 고려하지 않고 이전 단계의 물건을 넣은 상태를 고려한
    것이다.

    하지만 일차원 배열로 이를 해결할수 있는데, 방법은 간단하다. 물건을 넣을때 가방 최대 무게값, 즉 열을 최대값부터 시작하는 것이다.
    동일하게 무게 6, 가치 13의 무게를 넣는데 K 값이 12 라고 가정하자.
    열이 12 인 곳부터 시작하므로 12 - 6 인 6열의 값은 아직 아무것도 들어있지 않으므로 현재 물건만이 넣은것으로 간주할수 있다.
    즉 12열 부터 6열까지는 동일하게 현재 물건의 가치인 13 을 그대로 넣는 것이다.
    --------------------------------------------------------------------------------------------------------------------
 */
package dp;

import java.io.*;
import java.util.*;

public class BOJ12865_평범한_배낭 {
    static int N, K;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve(int n, int w, int v) {
        for (int i = K; i >= w; i--) {
            if (dp[i] < dp[i - w] + v) dp[i] = dp[i - w] + v;
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            solve(i, w, v);
        }
        System.out.println(dp[K]);
    }
}
