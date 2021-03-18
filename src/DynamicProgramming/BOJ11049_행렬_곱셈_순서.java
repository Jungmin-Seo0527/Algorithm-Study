/*
    BOJ11049_행렬_곱셈_순서
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/11049

    난이도 : G3
    --------------------------------------------------------------------------------------------------------------------
    풀이

    생각해볼것이 많았던 문제이다. (사실 이부터 풀었던 문제들을 비슷한 방식으로 풀어냈는데 이 문제를 기점으로 확실하게 개념을 정리하고
    넘어간다.)
    우선 이 문제는 다양한 방식으로 풀어낼 수 있다. 플로이드-와샬, 분할정복, DP 등등등...
    여기서는 DP로 풀어내는 방법을 소개한다. (아마 이 셋중에는 가장 시간이 많이 걸리는 방법일 것이다.)

    이문제에서 DP로 풀어낸다는 판단울 세울수 있는것은 주어진 배열을 일정 부분씩 어떻게 나누어도 각 부분의 결과들이 다시 연산에 사용된
    다는 것이다.
    ABC 가 있다면 AB를 먼저 연산후에 C를 연산하거나, BC를 연산 후에 A를 연산한다. 이렇게 어떻게 나누어서 연산을 하던지 그 결과값은
    다음 연산에 이용이 된다. 이 사실이 DP 혹은 분할 정복으로 풀어내라는 의미이다. (혹은 플로이드 와샬...)

    이 문제에 dp[i][j]가 의미하는 것은 i번째 행렬에서 j번째 행렬까지의 연산중에 가장 큰값을 의미한다.
    그리고 그 최대값은 i부터 j가지를 2분할하여 각각 계산을 하는 방식이다.
    코드로 설명하자면
    for(int i = left; i < right; i++) {
        ret = Math.max(ret, doDFS(left, i) + doDFS(i+1, right));
    }
    이 코드가 의미하는 것이 left 인덱스에서 right인덱스까지의 행렬들의 연산중 가장 큰 값을 반환하는 부분이다.
    하지만 완성된 것이 아닌데, doDFS() 메소드를 살펴보자.
    우선 left==right 인 경우에는 0을 반환하였다. 행렬 A만으로는 어떠한 연산도 할수 없기 때문이다.
    그렇다면 만약에 left = 0, right = 1 인 경우에 위의 반복문에 적용해보자.
    ret = Math.max(ret, doDFS(0, 0) + doDFS(1, 1) 이 하나의 코드만 실행될 것이다. 그리고 if(left==right) return 0; 이기 때문에
    왼쪽의 doDFS(0, 0) 에서는 0을 반환한다. 그리고 doDFS(1, 1)에서도 0을 반환한다.
    left = 0 이고 right = 1 이 가르키는 의미는 0번째 행렬에서 1번째 행렬까지의 연산중 최대값을 구해야 하는데 사실 경우의 수는
    1가지이다. 즉 A, B 두 행열의 연산의 경우의 수는 (AB) 한가지 뿐이다.
    ret = Math.max(ret, doDFS(0, 0) + doDFS(1, 1) + 0.row * 0.col * 1.col 이 추가로 필요하다.
    반복문에서 일반화 시키셔 표현하자면

    ```java
    for(int i = left; i < right; i++) {
        ret = Math.max(ret, doDFS(left, i) + doDFS(i + 1, right) + left.row * i.col * right.col);
    }
    ```
    이 부분을 이해를 못해서 각 단계마다의 결과값을 손으로 써가면서 풀어보았다.
    이 방법은 Botton-up 방식으로 가장 밑단에서의 결과값을 참고하면서 그 상위의 단계의 결과값을 채워넣는 방식이다.
    즉 점화식을 일반화 시켜보려면 가장 밑단의 연산을 수행해보아야 한다.
    이 문제에서도 같은 인덱스일때는 어떻게 처리하는지, 그리고 가장 밑단에서의 메소드가 어떠한 연산을 수행해서 어떤 결과값을 반환하는지
    꼭 손으로 풀어가야 한다.

    나머지 부분은 메모제이션이다. 만약 doDFS(left, right) 연산을 통해서 left 번째 행렬부터 right 번째 행렬까지의 연산의 최대값을
    구한 이력이 있다면 다음 이 부분이 필요할때는 이미 dp에 저장되어 있는 값을 쓰는 것이다.

    다음에는 분할정복으로 푸는 방법, 플로이드 와샬 방법으로 푸는 방법을 소개할 예정이다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BOJ11049_행렬_곱셈_순서 {

    static class Matrix {
        int row, col;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N;
    static int[][] dp;
    static Matrix[] matrix;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        System.out.println(doDFS(0, N - 1));
    }

    static int doDFS(int left, int right) {
        if (left == right) return 0;
        if (dp[left][right] != 0) return dp[left][right];

        int ret = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            ret = Math.min(ret, doDFS(left, i) + doDFS(i + 1, right)
                    + matrix[left].row * matrix[i].col * matrix[right].col);
        }
        return dp[left][right] = ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        matrix = new Matrix[N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[i] = new Matrix(r, c);
        }
    }
}
