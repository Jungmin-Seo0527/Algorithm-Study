/*
    BOJ4902_삼각형의_값
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/4902

    난이도 : G2
    --------------------------------------------------------------------------------------------------------------------
    풀이

    이 문제의 핵심은 각 삼각형에 숫자를 부여했을때 그 숫자들의 규칙, 즉 수열을 만들수 있는가가 관건이다.

    이 문제는 각 시작 삼각형에서 부터 재귀함수를 이용해서 각 단계의 삼각형의 합들을 구해주었다. 간단하게 재귀함수를 설명하자면
    1. 우선 각 단계의 재귀함수에서는 같은 행에 존재하는 삼각형들이 가진 숫자들의 합들을 더한다.
    2. 다음 재귀함수를 수행할 여부를 정한다.

    여기서 다음 재귀 함수를 수행할 여부를 판별하기 위해서는 3가지 조건이 필요하다.
    1. 다음 행의 범위가 주어진 N의 값 이내에 존재하는가
    2. 다음 행의 시작 삼각형의 번호가 다음 행의 처음 삼각형 번호 이상인가
    3. 다음 행의 마지막 삼각형의 번호가 다음 행의 마지막 삼각형 번호 이하인가

    이 조건들을 수열로 만들어 보자.
    우선 다음 행에서 합을 시작하는 삼각형의 번호를 구하는 수열이다.
    next = cur + (2 * row -1 ) * mode
    여기서 mode 는 역삼각형일때, 즉 다음단계의 재귀함수는 다음 행이 아닌 이전 행으로 진행되는 경우이다.

    그래서 다음 행으로 진행될때는 mode = 1, 이전 행으로 수행되는 역삼각형일때는 mode = -1 이 된다.
    이 mode 를 이용해서 다음 행을 구하는 식은
    nextRow = curRow + mode 가 된다.

    다음 행에 존재하는 삼각형 번호의 범위가 필요하다. 여기서 주의할 점이 있다.
    처음에 일반 삼각형만 생각했을때 다음 행의 첫번째 삼각형의 번호는 현재 행의 마지막 삼각형의 번호 +1 이 되므로
    nextRowFirstCol = curRow * curRow + 1 이라고 생각했다. 하지만 역삼각형일때는 적용되지 않는다.
    따라서 다음 행의 열의 범위를 구하는 공식에서 현재 행을 이용하는 것이 아닌 오직 다음 행번호만 이용해서 범위를 구하는 것이 좋다.
    실제로 이것때문에 몇번 틀렸다.

    우선 다음행의 마지막 열의 삼각형 번호는 당연히 다음 행의 제곱이다.
    nextRowLastCol = nextRow * nextRow
    다음 행의 첫번째 열의 삼각형 번호는
    nextRowFistCol = nextRow * nextRow - (2 * nextRow -1) + 1
    이 수식은 수열을 만들어서 점화식을 만들면 쉽게 도출할 수 있는 식이다.

    지금까지 구한 값들을 조건화 시켜서 다음 재귀 함수(doDFS())를 수행할 여부를 위에 말한 3가지 조건을 통해서 판별하면 된다.
    그리고 함수를 수행할 때마다 합들을 누적시켜 가장 큰 값을 출력하면 된다.
    --------------------------------------------------------------------------------------------------------------------
 */
package BruteForce;

import java.io.*;
import java.util.*;

public class BOJ4902_삼각형의_값 {
    static int N, ans = Integer.MIN_VALUE;
    static int[] tri, preSum;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve() {
        int r = 1;
        ans = Integer.MIN_VALUE;
        for (int i = 1; i <= N * N; i++) {
            if ((r % 2 == 1 && i % 2 == 1)
                    || (r % 2 == 0 && i % 2 == 0)) doDFS(i, r, 1, 0, 1);
            else doDFS(i, r, 1, 0, -1);
            if (r * r == i) r++;
        }
        return ans;
    }

    static void doDFS(int start, int r, int len, int sum, int mode) {
        sum += preSum[start + len - 1] - preSum[start - 1];
        ans = Math.max(ans, sum);
        int nextR = r + mode;
        int nextLen = len + 2;
        int nextStart = start + (2 * r - 1) * mode;

        if (nextR >= 1 && nextR <= N
                && nextStart + nextLen - 1 <= nextR * nextR
                && nextStart >= nextR * nextR - 2 * nextR + 2) {
            doDFS(nextStart, nextR, nextLen, sum, mode);
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = 0;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            tri = new int[N * N + 1];
            preSum = new int[N * N + 1];
            for (int i = 1; i <= N * N; i++) {
                tri[i] = Integer.parseInt(st.nextToken());
                preSum[i] = preSum[i - 1] + tri[i];
            }
            sb.append(++t).append(". ").append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
