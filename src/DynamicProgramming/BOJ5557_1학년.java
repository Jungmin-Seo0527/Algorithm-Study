/*
    BOJ5557_1학년
    --------------------------------------------------------------------------------------------------------------------
    문제링크

    https://www.acmicpc.net/problem/5557

    난이도 : G5
    --------------------------------------------------------------------------------------------------------------------
    풀이

    이 문제를 처음 보면 완전 탐색으로 풀어야 하는가에 대한 의문이 든다. 시간 복합도도 O(2^N) 이라 터무니 없을것이라 생각할수 있지만
    N의 최대값이 100인 점이 완전탐색의 유혹을 쉽사리 뿌리치지 못하는 요인이 된다. 당연히 시간초과...

    이 문제는 20보다 큰 수는 전혀 고려하지 않아야 한다는 점에서 DP 문제가 된다. 중간 계산 결과 20보다 큰 수가 나오거나, 음수가
    나오면 그 단계는 버려야 한다.
    따라서 각 단계에서 0부터 20까지 나타나는 빈도수를 메모제이션 하여 수행해면 된다.

    8 3 2 4 8 7 2 4 0 8 8 배열을 예로 들면
    dp[0][8] = 1, 나머지 0행은 모두 0이 된다. 그리고 dp[1][11], dp[1][5] 만 1개씩 증가하고 나머지는 그대로 0이 될 것이다.
    음수와 20 초과의 값은 버린다는 조건이 이 문제를 간단한 dp 문제로 만들어 준다.

    사실 처음에 20 초과는 안된다는 조건을 놓쳐서 조금 해맸다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ5557_1학년 {
    static int N;
    static int[] arr;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        dp[0][arr[0]]++;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] > 0 && j + arr[i] <= 20) dp[i][j + arr[i]] += dp[i - 1][j];
                if (dp[i - 1][j] > 0 && j - arr[i] >= 0) dp[i][j - arr[i]] += dp[i - 1][j];
            }
        }
        System.out.println(dp[N - 2][arr[N - 1]]);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        dp = new long[N][21];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
