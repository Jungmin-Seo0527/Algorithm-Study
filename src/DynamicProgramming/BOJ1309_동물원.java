/*
    BOJ1309_동물원
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/1309

    난이도 : S1
    --------------------------------------------------------------------------------------------------------------------
    풀이

    기분전환용으로 쉬운문제 한문제 풀어보았다.
    가로로 2칸, 세로로 N 칸의 우리에서 서로 인접한 칸에는 동시에 사자가 존재하지 않도록 사자를 배치할 수 있는 경우의 수를
    구하는 문제
    나는 dp[3][N] 으로 풀어냈는데
    0 : 사자를 두지 않는 경우
    1 : 사자를 첫번째 우리에 두는 경우
    2 : 사자를 두번째 우리에 두는 경우

    점화식을 나타내면
    dp[i][j] = dp[(i+1)%3][j-1]+dp[(i+2)%3][j-1] 이 된다.
    즉 다음 단계의 사자를 두지 않는 경우는 이전의 사자를 첫번째 칸, 두번째 칸에 넣는 경우를 더한 값이고
    다음 단계의 사자를 첫번째 칸에 두는 경우는 이전단계에서 사자를 두지 않는 경우와 사자를 두번째 칸에 두는 경우를 더하고
    다음 단계의 사자를 두번째 칸에 두는 경우는 이전단계에서 사자를 두지 않는 경우와 첫번째 칸에 두는 경우를 더한 값이다.
    단 사자를 두지 않는 경우는 이전 단계에서 사자를 두지 않는 경우에도 가능한 경우이므로 추가로 더해주면 된다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ1309_동물원 {

    static int N;
    static final int mod = 9901;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        for (int i = 0; i < 3; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[j][i] += dp[(j + 1) % 3][i - 1];
                dp[j][i] += dp[(j + 2) % 3][i - 1];
            }
            dp[0][i] += dp[0][i - 1];
            for (int j = 0; j < 3; j++) {
                dp[j][i] %= mod;
            }
        }
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            ans += dp[i][N - 1];
        }
        ans %= mod;
        System.out.println(ans);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[3][N];
    }
}
