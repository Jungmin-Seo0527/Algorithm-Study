/*
    CF_R690_D_Add_to_Neighbor_and_Remove
    -------------------------------------------------------------------------------------------------------------------
    문제 링크

    https://codeforces.com/contest/1462/problem/D
    -------------------------------------------------------------------------------------------------------------------
    풀이

    그리디와 브루트 포스를 함께 이용해야 풀수 있는 문제

    처음에는 문제 그대로 구현해 보려고 했다. 그리디적으로 접근하여 현재 배열에서 최소값을 찾아내서 양쪽의 숫자중 작은쪽과 더하면서
    배열의 길이를 하나씩 줄이는 방식이었다.
    설명은 간단하지만 이미 최소값을 찾아내기, 그리고 배열의 길이를 하나씩 줄이는 단계 부터가 O(N) 을 가진다.
    이 단계를 N번 시행 해여 하니 O(N^N) 의 시간 복잡도가 난다. 택도 없다.

    이 문제는 결과적으로 나올수 있는 합을 먼저 정한후에 그 합이 만들어 지는지 확인을 해야 한다.
    배열의 모든 수를 더한다. N 부터 1 까지 순서대로 나눈 값(sum/i, 1<=i<=N), (단 나머지가 0일때)0 이 이웃한 숫자들 끼리 합했을때
    모두 같은 숫자가 나오는 경우 그 숫자의 후보군이 된다.
    그리고 배열을 처음부터 누적하여 더해준다. 만약 더한 합이 sum/i 와 같다면 최종 숫자를 하나 만들었기 때문에 누적합은 0부터
    시작한다. 만일 sum/i 보다 큰 누적합이 나온 경우에는 sum/i 로 통일시킬수 없다는 것이므로 다음 i 로 넘어간다.
    누적합이 sum/i 보다 작은 경우에만 합을 진행하므로 카운트한다.
    카운트 값이 가장 작은 경우를 출력해야 한다. 시작을 i=N 부터 시작했기 때문에 카운트 값이 가장 작은 경우부터 탐색을 하였기에
    가장 처음 답이 되는( 즉 처음으로 이웃간 합들이 모두 같은 값이 나왔을 경우) 가 최종 답이 된다.

    처음에 문제를 접했을 때는 쉬운 문제인것 같다고 생각했는데 생각보다 어려웠던건지, 아니면 이러한 유형의 문제를 많이 풀어 보지
    않았던 건지는 모르겠지만 꽤나 고전했던 문제였다.
    사실 그리디 + 브루트 유형의 문제를 이후에는 몇가지 풀었기 때문에 그 이후에 풀어봤으면 다른 결과가 나오지 않았을까 하는 아쉬움이
    있다.
    -------------------------------------------------------------------------------------------------------------------
 */
package Codeforces.R690D3;

import java.io.*;
import java.util.*;

public class D_Add_to_Neighbor_and_Remove {
    static int N, sum;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve() {
        for (int i = N; i >= 1; i--) {
            if (sum % i != 0) continue;
            int cur = sum / i;
            int arrSum = 0;
            int cnt = 0;
            boolean fail = false;
            for (int j = 0; j < N; j++) {
                arrSum += arr[j];
                if (arrSum == cur) {
                    arrSum = 0;
                } else if (arrSum > cur) {
                    fail = true;
                    break;
                } else cnt++;
            }
            if (!fail) return cnt;
        }
        return 0;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            sum = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum += arr[i];
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
