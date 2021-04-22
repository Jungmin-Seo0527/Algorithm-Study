/*
    BOJ1495_기타리스트
    ---------------------------------------------------------------------------------------------------------------
    문제 링크

    https://www.acmicpc.net/problem/1495
    ---------------------------------------------------------------------------------------------------------------
    풀이

    처음에는 문제를 풀때 최다값을 구하는 문제이기 때문에 dp를 각 음악의 순번일때 볼륨이 최대값만을 기록했다.
    이차원으로 dp를 만들고 하나는 볼륨을 줄일때, 하나는 볼륨을 높일때의 각각 최대값들을 저장했다.
    하지만 이건 문제가 있는데 순간의 최대값만으로 볼륨을 조절하는 것이 목표 시점에서의 최대값을 보장할 수 없다.

    이 문제는 2차원 dp로 문제를 풀어낼때 행은 음악순번, 열은 볼륨으로 설정해야 한다.
    음악의 순번이 들어올때 범위내의 가능한 모든 볼륨들을 메모라이징 해야 하는 문제이다. 위에서 언급했듯이 음악의 순번일때
    이전의 최대값에서 볼륨을 조절하는 것이 마지막 음악에서의 볼륨의 최대값을 보장해줄 수 없기 때문에 모든 가능한 볼륨을
    저장하는 방법이 필요하다.

    점화식은 dp를 bool 형으로 해되 무관하다.
     if (dp[n - 1][m] && m + v >= 0 && m + v <= M) dp[n][m + v] = true;
     n 은 음악의순번, m 은 볼륨값, v 는 현재 음악의 볼륨 조절 가능값이다.

     범위를 벗어나는 볼륨은 버리고 범위내의 가능한 볼륨은 모두 체크를 해둔다.
     이후에 볼륨이 큰값부터 조회하여 처음 true 가 될때의 볼륨이 마지막 곡을 연주할 수 있는 볼륨 중 최대값이다.
    ---------------------------------------------------------------------------------------------------------------
 */
package dp;

import java.io.*;
import java.util.*;

public class BOJ1495_기타리스트 {
    static int N, S, M;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new boolean[N + 1][M + 1];
        dp[0][S] = true;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int v = Integer.parseInt(st.nextToken());
            for (int j = 0; j <= M; j++) {
                if (dp[i - 1][j] && j + v >= 0 && j + v <= M) dp[i][j + v] = true;
                if (dp[i - 1][j] && j - v >= 0 && j - v <= M) dp[i][j - v] = true;
            }
        }

        int ans = -1;
        for (int i = M; i >= 0; i--) {
            if (dp[N][i]) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
}
