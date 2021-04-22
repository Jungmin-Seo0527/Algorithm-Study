/*
    BOJ11058_크리보드
    --------------------------------------------------------------------------------------------------------------------
    문제링크

    https://www.acmicpc.net/problem/11058
    --------------------------------------------------------------------------------------------------------------------
    풀이

    dp값을 채우는데 2가지를 고려하면 된다.
    첫번째는 이전 dp값에 +1
    두번째는 이전 단계 어디선게 계속 복사를 해왔을 경우이다.

    두번째 경우에 대한 점화식은 아래와 같다.
    dp[i]=max(dp[i-1]+1, dp[i-j]*(i-j-1)) j>=3

    복사를 하기 위해서는 3가지 단계(전체 선택, 전체 복사, 전체 붙여넣기) 가 필요하다.
    즉 Ctrl + v 단계를 수행하기 위해서는 최소한 3단계 이전부터 시작해야 한다.
    그리고 그 이전 단계가 하나씩 늘어날수록 복사 ctrl v 횟수가 늘어난다.
    즉 3단계 이전의 A 의 최대값이 5 였다면 현제 단계에서는 ctrl v 로 5 * 2 = 10 개를 만들수 있고
    4단계 이전의 A 의 최대값이 4 였다면 ctrl a, ctrl c ctrl v, ctrl v 로 복사 2번 가능하므로 4 * 3 = 12 가 된다.
    이 검사를 인덱스가 1인곳까지 해야 한다.
    당연히 시간복잡도가 좋지는 못히다. O(N^N)
    하지만 N의 최대값이 100 이므로 충분이 처리시간 제한을 지킬수 있다.

    --------------------------------------------------------------------------------------------------------------------
 */
package dp;

import java.io.*;
import java.util.*;

public class BOJ11058_크리보드 {
    static int N;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j <= i - 3; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }
        System.out.println(dp[N]);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new long[N + 1];
    }
}
