package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ2138_전구와_스위치
/*
    전구의 상태가 나를 기준으로 왼쪽 오른쪽 전구들도 함께 상태가 바뀐다.
    즉 내가 전구의 상태를 바꿀지에 대한 판단은 나 이전의 전구를 보고 판단해야 한다.
    내 이전의 전구의 상태를 바꿀수 있는 마지막 기회가 나이기 때문이다.
    아마 여기까지는 누구나 쉽게 도달할 수 있는 결론이다. 하지만 문제는 첫 전구에 대한 처리이다.

    예를 들면 0000 0010 이 있다고 하면
    위의 아이디어로는 맨 마지막 전구만 상태를 변화 시킬수 있다. 그리고 그 마지막 전구의 상태가 서로 다르기에
    -1이 답이라고 생각할수 있다.
    하지만 이 예시는 동일한 상태로 만들 수 있다.

    0000(1번전구) -> 1100(2번전구) -> 0010 (동일) 2번으로 동일한 상태로 만들기가 가능하다.
    결국 첫번째 전구에 대한 처리가 관건이라는 것을 깨달았다.
    그렇다면 첫번째 전구에서 어느 범위 내의 전구의 상태가 서로 다른 경우에 바꾸는 것인가? 에대한 물음표가 던져졌다.
    다음 예시가 물음표를 지웠다.

    0000000 0000010 이 예시는 위의 예시와 비슷하게 동일한 상태로 바꿀수 있다.
    그리고 얼마든지 이 길이를 늘려서 바꾸기가 가능하다. 즉 첫번째 전구에 대한 처리가 첫번째 전구로부터 어느 범위
    내의 전구의 서로간 상태에 따라 변해야 한다는 전제는 옳지 못하다.

    결국 규칙성을 찾지 못하고 질문검색을 해보았다.

    이문제를 풀기 전에 첫번째 전구 상태를 변화시키지 못한다는 조건이 있는 문제가 존재한다고 한다.
    그리고 첫번째 전구에 대한 규칙을 찾지 못하여서 그냥 첫번째 전구의 상태를 변화키시는 경우와 변화시키지 않는 경우를
    모두 구하여서 비교하여 답을 출력했다.

    다른사람들의 블로그를 찾아보고 코드도 찾아보니 다들 비슷한 방법으로 풀어냈다.
    즉 이문제는 첫번째 전구의 상태를 변화시키는 경우, 변화시키지 안흔 경우 모두를 구해서 비교하면 된다.
 */
public class BOJ2138_전구와_스위치 {
    static int N;
    static char[] arr1, arr2;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        System.out.println(solve());
    }

    static int solve() {
        int cnt1 = 1;
        int cnt2 = 0;
        char[] copy = arr1.clone();
        change(arr1, 0);
        change(arr1, 1);
        for (int i = 1; i < N; i++) {
            if (arr1[i - 1] != arr2[i - 1]) {
                change(arr1, i - 1);
                change(arr1, i);
                change(arr1, i + 1);
                cnt1++;
            }
            if (copy[i - 1] != arr2[i - 1]) {
                change(copy, i - 1);
                change(copy, i);
                change(copy, i + 1);
                cnt2++;
            }
        }

        if (arr1[N - 1] != arr2[N - 1]) cnt1 = -1;
        if (copy[N - 1] != arr2[N - 1]) cnt2 = -1;
        if (cnt1 == -1 || cnt2 == -1) {
            return cnt1 != -1 ? cnt1 : cnt2;
        } else return Math.min(cnt1, cnt2);
    }

    static void change(char[] arr, int idx) {
        if (idx >= 0 && idx < N) {
            if (arr[idx] == '0') arr[idx] = '1';
            else arr[idx] = '0';
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr1 = new char[N];
        arr2 = new char[N];
        arr1 = br.readLine().toCharArray();
        arr2 = br.readLine().toCharArray();
    }
}

