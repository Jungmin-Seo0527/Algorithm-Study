package greedy;

import java.io.*;
import java.util.*;

// BOJ12970_AB
/*
    A, B 로 이루어진 문자열이있을때
    0 <= i < j < N 이면서 s[i] == 'A' && s[j] == 'B'를 만족하는 (i, j) 쌍이 K개가 있다는 조건을 만족하는 문자열을 구하는 문제

    문제를 한참 뚫어지게 보고나서야 해결법이 생각났다.

    N = 3, K = 2 가 입력으로 주어졌을때를 생각해보자.
    A보다 왼쪽에 위치한 B의 갯수가 2개가 존재해야 한다. ABB 가 정답이 될 것이다.
    N = 10, K = 12 를 생각해보자.
    만약 A가 1개만 존재할때 B는 11개가 존재해야 한다. ABBBBBBBBBBB 이는 길이가 N (=10) 을 넘어가므로 실패
    만약 A가 2개만 존재하면 B는 6개가 존재하면 된다. AABBBBBB 각 A마다 만나는 B의 순서쌍이 6개니 6 * 2 = 12, K를 만족한다.
    여기서 맨 왼쪽에 나타나는 B, 맨 오른쪽 끝에 연속적으로 나타나는 A는 순서쌍에 아무런 영향이 없으므로 둘중 한 방법으로 길이를 맞추어 준다.
    그러면 내가 생각하는 방법으로 문자열은 AABBBBBBAA 가 된다.

    즉 A를 기준으로 K를 A의 갯수만큼 나누었을때의 몫이 B의 갯수가 된다. A와 B의 갯수의 합이 N보다 작거나 같으면 답이 가능하다.
    단 나머지가 존재하는 경우가 존재한다.
    5 5 가 입력으로 주어진 경우를 생각해보자.

    5를 2로 나누면 몫은 2, 나머지는 1이다. 즉 A의 갯수는 2, B의 갯수는 2개이다.
    AABB 나머지 1은 AABB로 만는 순서쌍 이외에 더 팔요한 순서쌍의 갯수가 된다. 즉 이 문자열에서는 순서쌍이 하나 더 필요하다.
    그렇다면 오른쪽부터 나머지만큼의 B의 갯수 이전에 A를 하나 삽입하면 된다.
    즉 AABAB 문자열을 만드는 것이다. 그렇다면 왼쪽부터 첫번째 A는 순서쌍 2개, 두번째는 2개, 세번째는 1개를 만들어 총 5개를 만들수 있다.
    즉 나머지가 의미하는 바는 나누는 숫자와 몫 그 외에 더 필요한 순서쌍의 갯수로 A 하나만 삽입하여 만들어낼 수 있다.
 */
public class BOJ12970_AB {
    static int N, K;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        for (int i = 1; i <= N / 2; i++) {
            int B_cnt = K / i;
            int A_plus = K % i;
            int temp = 0;
            if (A_plus != 0) temp = 1;
            if (i + B_cnt + temp <= N) {
                StringBuilder sb = new StringBuilder();
                for (int a = 0; a < i; a++) {
                    sb.append('A');
                }
                for (int b = 0; b < B_cnt - A_plus; b++) {
                    sb.append('B');
                }
                if (A_plus != 0) sb.append('A');
                for (int b = B_cnt - A_plus; b < B_cnt; b++) {
                    sb.append('B');
                }
                while (sb.length() < N) {
                    sb.append('A');
                }
                System.out.println(sb);
                return;
            }
        }
        System.out.println(-1);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
}

