/*
    BOJ10422_괄호
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/10422

    난이도 : G4
    --------------------------------------------------------------------------------------------------------------------
    풀이

    DP로 조합론을 풀어내는 문제이다. 카탈린 수 알고리즘을 알고 있다면 매우 쉽게 풀 수 있는 문제이다.
    사실 어느정도 문제에서 카탈린 수를 암시하는 표현들이 숨어있다.
    1. 올바른 괄호 문자열 S가 있으면 (S) 는 올바른 괄호 문제열이다.
    2. S와 T가 올바른 괄호 문자열이면 ST는 올바른 괄호 문자열이다.
    이 두 조건이 가르키는 것이 카탈린 수 알고리즘이다.

    카탈린 수 알고리즘은 조합론을 바탕으로 하고 있다.
    핵심 아이디어는 우선 괄호 한쌍을 두고 그 안에 들어가는 괄호 문자열, 그 밖에 두는 괄호 문자열의 순서쌍을 구하는 것이다.
    예를 들어 C1(괄호쌍이 1개인 올바른 문자열) 을 구한다고 생각하자. (여기서 C0은 1로 고정한다.)
    1. 처음에 우선 괄호 한쌍을 준비 한다. ( )
    2. 이 괄호 안에 C0가 들어가는 경우, 밖에 C0가 들어가는 경우의 조합을 구한다. C0 * C0
    3. 이 괄호쌍은 0개의 괄호쌍 2개, 미리 준비한 괄호쌍 1개의 조합이므로 총 1개의 괄호쌍의 조합이 된다.

    C2 를 구해보자
    1. 처음 괄호쌍 한쌍을 준비한다.
    2. 조합으로는 (C0)C1, (C1)C0 의 조합을 만들 수 있다. (1*1 + 1*1 = 2)
    3. ()(), (())

    C3을 구해보자.
    1. 처음 괄호쌍 한쌍을 준비한다.
    2. (C0)C2, (C1)C1, (C2)C0 의 조합으로 만들 수 있다. (1*2 + 1*1 + 2*1 = 5)
    3. (C0)C2 -> ()()(), ()(()) // (C1)C1 -> (())() // (C2)C0 -> (()()), ((()))

    점화식으로 표현하자면
    Cn = C0*C(n-1) + C1*C(n-2) + C2*C(n-3) ... +C(n-1)*C0 이 된다.

   이 식을 바탕으로 dp값을 채워준다.
   단지 여기서는 문자열의 길이에 대한 올바른 괄호쌍 문자열의 갯수를 구하므로 괄호쌍*2에 대해 구해주면 된다.
   기존 -> 지금 문제(길이로 표현)
   C0 -> C0
   C1 -> C2
   C2 -> C4
   C3 -> C6
    --------------------------------------------------------------------------------------------------------------------
 */
package dp;

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
