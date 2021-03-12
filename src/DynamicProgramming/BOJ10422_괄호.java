/*
    BOJ10422_괄호
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/10422

    난이도 : G4
    --------------------------------------------------------------------------------------------------------------------
    풀이

    카탈란 수를 알고 있으면 굉장히 쉽게 풀수 있는 문제이다. 나는 몰랐던 관계로 쉽게 풀어내지 못했다.

    처음에는 조합을 이용해서 dp 를 채우려고 했다. 아마 이 접근법의 도착지가 카탈란 수 이어야 했다.
    하지만 최적화된 조합법을 찾지 못했다.

    이 문제는 올바른 괄호 문자열 A 와 B 가 있을때, AB 도 올바른 문자열임을 힌트로 준다.
    만약 괄호의 순서쌍이 3개인 문자열의 갯수를 구한다고 가정해보자. (Cn : 순서쌍이 n개인 올바른 괄호 문자열)
    우선 길이가 0 인 문자열의 갯수도 1이라고 둔다.
    (C0)+C2 를 하여 C3 을 만든다. 마찬가지로 (C1)+C1, (C2)+C0 의 조합으로 C3 이 가능한 모든 경우의 수를 세어준다.

    이 방법으로 점화식을 세운다면
    dp[i] = dp[0] * dp[i-1] + dp[1] * dp[i-2] * ... dp[i-1] * dp[0]
    --------------------------------------------------------------------------------------------------------------------
 */
package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ10422_괄호 {
    static final long mod = (long) 1e9 + 7;
    static long[] dp = new long[5001];

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve() {
        dp[0] = 1;
        for (int i = 2; i <= 5000; i += 2) {
            for (int j = 0; j < i; j += 2) {
                dp[i] += dp[j] * dp[i - j - 2];
                dp[i] %= mod;
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        solve();
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }
}
