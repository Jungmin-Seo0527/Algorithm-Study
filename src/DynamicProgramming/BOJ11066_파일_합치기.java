/*
    BOJ11066_파일_합치기
    --------------------------------------------------------------------------------------------------------------------
    문제링크
    https://www.acmicpc.net/problem/11066

    난이도 : G3
    --------------------------------------------------------------------------------------------------------------------
    풀이

    재귀와 메모제이션을 이용한 DP 문제이다.
    외판원 순회 문제와 비슷한 방법의 DP 풀이법으로 접근하는 문제이다.
    이 문제에서 떠올려야 하는 것은 어떠한 방법으로든 두 그룹으로 파일들을 나눌 수 있다는 것이다.
    즉 첫번째 테스트 케이스인 40, 30, 30, 50 의 경우를 보면 결국 마지막 단계에서는 두 그룹의 합이 답이 될 것이고, 그 그룹을 만들기
    위해 그 속에서도 두 그룹으로 계속 나누어 졌을 것이다. 최종답은 최소값을 구하는 문제이니, 두 그룹으로 나누어서 더한 값이 최소값이
    되어야 한다.
    그림으로 설명하면 쉽겠지만 주석에는 그림을 남길수 없어서 좀더 부가 설명을 한다.

    내가 필요로 하는 dp는 최종 단계에서의 두 그룹으로 나누었을때의 최소값들이다. 첫번째 케이스의 dp 값들을 살펴보면
    -1 140 260 -1
    -1  -1 120 280
    -1  -1  -1 160
    -1  -1  -1  50

    좌상단 측을 1행 1열로 보자. 실제 내 코드는 그렇게 가정하고 풀었다. 그 이유는 좀더 뒤에 설명하겠다.
    우선 이 dp의 의미를 살펴보면 1행 2열의 140 의 값이 있다. 이는 40, 30, 30, 50 의 배열에서 (이 배열도 가장 좌측의 인덱스를 1로
    가정) 인덱스 1번과 2번을 묶었을때 최소값이 된다. 단 주의할 것은 40과 30을 묶어서 나온 값이 아니라, 누적합으로 필요하다.
    이 배열의 정답은 300 이다. 인덱스 1번과 2번, 그리고 3번과 4번을 각각 묶고 그 묶음을 더해주면 최종답 300 으로 된다.
    즉 40+30, 30+50 그리고, 그 모든 값들을 더한 40+30+30+50, 각각 70, 80, 150 을 모두 더한 300이 답이 된다.
    파일을 하나로 합칠때, 드는 최종비용이 아닌 각각의 합친 과정에서의 비용까지 모두 더해주어야 함으로 누적합으로 구해야 한다.
    그래야만 최종 완성된 dp에서 두 그룹의 합을 더하기만 하면 최종 답을 도출해 낼 수 있다. 그리고 그 누적합을 계속 더해주어야 하기
    때문에 누적합 배열이 필요하다. 그리고 누적합 배열은 첫번째 인덱스의 예외 조건으로 인해 인덱스를 하나 더 더해서 완성시켰다.

    즉 만약 누적합 인덱스를 0부터 시작한다면 누적합 인덱스 1에서 2까지의 합은 preSum[2]-preSum[0] 으로 구할수 있다.
    즉 인덱스 x 에서 y 까지의 합은 preSum[y] - preSum[x-1] 인데 만약 x 가 0인 경우에는 배열에서 -1 인덱스를 가질수 없으므로
    이 예외를 처리하기 위해서 누적합 배열의 인덱스를 +1 씩 해주어 0 인덱스는 0으로 고정시켜 주었다. 그리고 그 인덱스와 같이 맞추어
    생각하기 편하게 처음 입력으로 주어진 파일을 배열로 저장할때도 인덱스를 1부터 시작해서 저장시켜 주었다.

    그리고 재귀를 수행하는 과정을 이해해야 한다.
    내가 지금 최종적으로 보고 있는 dp 는 마지막 두 그룹의 파일을 합치려고 할때 각 그룹의 최소값이다. 주의할 것은 두 그룹으로 나눌때
    이다. 최종 dp 값을 보고 인덱스 2 와 3 을 묶고 1을 묶고 4를 묶는, 즉 그룹이 3개가 되는 경우는 절대 알수 없다.(그 경우는 그 이전
    의 단계에서 수행후 재귀로 지금의 단계로 올라왔다고 이해하면 된다.)

    그리고 앞에서 말했듯이 이 문제는 파일을 어떠한 방식으로든 두 그룹으로 나눌수 있고 그 그룹은 또다시 두 그룹으로 나눌수 있다.
    그 과정을 계속 수행해보면 마지막 단에서는 이웃한 두 파일을 한 그룹으로 만드는 과정이 나올 것이다.
    이 과정을 재귀로 풀어내는 것이고, 시간을 단축시키기 위해서 메모제이션 기법을 추가한 것이다.

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
        for (int i = 1; i < N; i++) {
            ans = Math.min(ans, func(1, i) + func(i + 1, N));
        }
        return ans;
    }

    static int func(int idx1, int idx2) {
        if (idx1 == idx2) return arr[idx2];

        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];

        int ret = Integer.MAX_VALUE;
        for (int i = idx1; i < idx2; i++) {
            ret = Math.min(ret, func(idx1, i) + func(i + 1, idx2) + preSum[idx2] - preSum[idx1 - 1]);
        }
        return dp[idx1][idx2] = ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            preSum = new int[N + 1];
            dp = new int[N + 1][N + 1];
            for (int i = 0; i <= N; i++) {
                Arrays.fill(dp[i], -1);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                preSum[i] = preSum[i - 1] + arr[i];
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
