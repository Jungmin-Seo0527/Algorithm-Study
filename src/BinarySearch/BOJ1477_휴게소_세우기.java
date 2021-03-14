/*
    BOJ1477_휴게소_세우기
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/1477

    난이도 : G5
    --------------------------------------------------------------------------------------------------------------------
    풀이

    구간 나누기2 문제와 비슷한 형식의 parameter search 문제이다. 구간 나누기2 문제에서도 언급했듯이 매개변수탐색 유형의 문제는
    어떠한 것을 대상으로 탐색을 진행하는가와 start, end 값을 갱신하는 조건이다.

    우선 탐색 대상은 휴게소가 없는 구간의 최댓값이 된다. 매개변수에서 탐색의 대상은 그냥 출력할 값인가?
    그리고 start, end 값을 갱신하는 조건은 휴게소가 없는 구간의 최댓값을 임의로 정했을때 (mid) 나올수 있는 휴게소의 갯수이다.

    단 start와 end 값을 갱신하기 위헤 휴게소가 없는 구간의 거리를 파라미터로 갖고 휴게소의 갯수를 반환하는 restCnt 함수를 살펴보자.
    arr[i] 와 arr[i-1] 사이에 거리 dist 만큼 간격을 두고 휴게소를 지을때 나오는 휴게소의 갯수를 구하기 위해서 처음에 식을
    cnt = (arr[i] + arr[i - 1] ) / dist 로 하였다. 나머지의 개념은 dist 보다 작은 간격으로 휴게소를 짖는 경우이다.

    하지만 위 식에서 예외가 존재한다. 만약에 우항에서 몫이 아닌 나머지가 0인 경우에 예외가 발생하는 것이다.
    예를 들어서 arr[i] = 4, arr[i-1] = 2 , dist = 2 라고 하자. 그렇다면 이미 2와 4 지점은 거리가 2 이므로 추가로 휴게소를 지을
    필요가 없다. 하지만 기존에 만든 식에서는 몫이 1 이므로 1개의 휴게소를 지어 버린다.
    따라서 만약 나머지가 0 인 경우에는 몫 -1 을 해주는 과정이 필요하다.
    또한 그냥 cnt = (arr[i] + arr[i - 1] - 1) / dist 로 두어도 무방하다. 휴게소의 위치는 중복이 없고 모두 정수이기 때문이다.
    --------------------------------------------------------------------------------------------------------------------
 */
package BinarySearch;

import java.io.*;
import java.util.*;

public class BOJ1477_휴게소_세우기 {
    static int N, M, L;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int start = 1;
        int end = L;
        while (start < end) {
            int mid = (start + end) >>> 1;
            int cnt = restCnt(mid);
            if (cnt <= M) end = mid;
            else start = mid + 1;
        }
        System.out.println(end);
    }

    static int restCnt(int dist) {
        int ret = 0;
        for (int i = 1; i <= N + 1; i++) {
            ret += (arr[i] - arr[i - 1] - 1) / dist;
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N] = L;
        arr[N + 1] = 0;
        Arrays.sort(arr);
    }
}
