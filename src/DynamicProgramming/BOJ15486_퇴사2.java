/*
    BOJ15486_퇴사2
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/15486

    난이도 : S1
    --------------------------------------------------------------------------------------------------------------------
    풀이

    최대/최소 비용 dp 문제는 항상 처음 해결법을 떠올리는게 쉽지 않는것 같다.
    이 문제는 시간의 흐름으로 문제를 진행 시키면 안된다. 항상 dp 문제를 풀때 시간의 흐름데로 dp를 채우려고 하니깐
    어려움을 느낀다.

    이 문제는 두가지의 조건이 중요하다.
    우선 첫번째는 현재 입력값인 t, p 를 받았을때, 이 상담을 수행하는 것이 좋은지 아닌지를 판별해야 한다.
    즉 dp[i + t - 1] = Math.max(dp[i + t - 1], dp[i - 1] + p) 로 점화식을 새울 수 있다.
    점화식의 간단한 설명을 하자면 만약 현재 입력받는 상탐을 수행하는 경우에는 이전의 받은 돈 + 현재 상담을 진행해서 받을
    돈(P) 이 된다. 하지만 이전 입력값이서 해당 일수에서 상담을 한 경우가 더 큰 경우는 그대로 유지하는 것이다.
    사실 이 조건만으로는 문제를 풀어낼 수 없다. 여기서 사실은 dp[i-1]+p가 아니라 i 이전의 dp값중 최대값을 더해주어야
    한다. 그리고 그 최대값을 각 단계마다 일반적인 반복문으로 구해주려면 시간초과가 일어난다.
    따라서 이 문제는 이전 날의 최대값을 계속 가지고 가는것이 중요하다.
    즉 위의 점화식을 수행하기 이전에 dp[i] = Math.max(dp[i], dp[i-1])를 수행해야 한다.
    이 문제는 현재 일수에서 그 이후에 상담이 끝난다. 즉 그 다음 일수에서는 현재 일수로 돌아올 수 없다. (현재 입력값은 현재
    일수에서 상담을 시작해서 t일만큼 걸린다는 의미이다.)

    다시 정리하자면
    dp[i + t -1] = Math.max(dp[i + t - 1], max(dp[i 이전의 값]) + p) 가 점화식이며
    dp[i 이전의 값]들중 최대값을 계속해서 구하는 것이 아닌 이전의 dp값과 계속 비교해서(dp[i] = Math.max(dp[i], dp[i -1]
    )최대값을 계속 가져가는 방법으로 문제를 풀어야 시간초과가 일어나지 않는다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ15486_퇴사2 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if (i + t - 1 <= N) {
                dp[i + t - 1] = Math.max(dp[i - 1] + p, dp[i + t - 1]);
            }
        }
        System.out.println(dp[N]);
    }
}
