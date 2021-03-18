/*
    BOJ11066_파일_합치기
    --------------------------------------------------------------------------------------------------------------------
    문제링크
    https://www.acmicpc.net/problem/11066

    난이도 : G3
    --------------------------------------------------------------------------------------------------------------------
    풀이

    재귀와 메모제이션을 이용한 DP 문제이다.
    BOJ11049_행렬_곱셈_순서 문제와 거의 비슷한 문제이다.
    문제를 분할해서 가장 최하단의 단계까지 분할시키면서 그 연산값을 dp에 저장을 하면서 그 결과값을 토대로 상위 단계를 연산하는
    Bottom-up 방식으로 풀어내는 dp문제이다.

    재귀를 이용하는 방법은 행렬 곱셈 순서 문제에서 자세히 설명했으며, 스스로도 충분히 이해를 했다고 생각한다.
    문제는 이 문제에서 점화식을 뽑아내는 것이 굉장히 어려웠다.
    점화식만 뽑을 수 있다면 재귀, 혹은 삼중 반복문 그 어떠한 것으로던지 풀어낼 수 있다.
    행렬 곱셈 순서에서도 설명했듯이 재귀와 삼중 반복문은 그 원리는 같으며 함수의 호출 빈도때문에 처리시간은 재기보다는 삼중 반복문이
    더 빠르다.

    --------------------------------------------------------------------------------------------------------------------
 */
package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ11066_파일_합치기 {

    static int N;
    static int[] arr, preSum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve() {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            ans = Math.min(ans, doDFS(0, i) + doDFS(i + 1, N - 1));
        }
        // showDP();
        return ans;
    }

    static int doDFS(int left, int right) {
        if (left == right) return dp[left][right] = arr[left];
        if (dp[left][right] != 0) return dp[left][right];

        int ret = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            ret = Math.min(ret, doDFS(left, i) + doDFS(i + 1, right) + preSum[right + 1] - preSum[left]);
        }
        return dp[left][right] = ret;
    }

    static int solve2() {
        for (int d = 1; d < N; d++) {
            for (int l = 0; l + d < N; l++) {
                int r = l + d;
                dp[l][r] = Integer.MAX_VALUE;
                for (int mid = l; mid < r; mid++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][mid] + dp[mid + 1][r] + preSum[r + 1] - preSum[l]);
                }
            }
        }

        return dp[0][N - 1];
    }

    static void showDP() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println("---------------");
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            N = Integer.parseInt(br.readLine());
            dp = new int[N][N];
            arr = new int[N];
            preSum = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                preSum[i + 1] = preSum[i] + arr[i];
            }
            sb.append(solve2()).append("\n");
            // showDP();
        }
        System.out.println(sb);
    }
}

