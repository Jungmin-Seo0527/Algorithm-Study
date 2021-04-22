package greedy;

import java.io.*;
import java.util.*;

// BOJ1449_수리공_항승
/*
    물이 세는 지점(파이프의 시작부터 물이 세는 지점까지의 거리)이 주어지고
    한 지점을 테이프로 붙여야 되는데 그때 테이프는 1만큼 소모된다.
    테이프의 갯수는 무한이지만 하나의 테이프의 길이는 L이다.
    모든 파이프를 연결하기 위해 필요한 테이프의 갯수를 구하는 문제

    파이프가 세는 지점 한곳다 1만큼 테이프를 소요한다. 현재부터 다음 파이프를 연결하기까지
    테이프 하나로만 연결할 수 있는 경우를 구하면 된다.
    현재 지점부터 계속 탐색을 하여 다음 파이프 지점까지가 테이프의 최대길이를 넘어버리면 그 이전까지 하나의
    테이프로 붙인다. 그리고 테이프의 최대 길이를 넘어버리는 시점부터 다시 시작해야 한다.
    단 파이프가 터진 지점은 당연히 오름차순이어야 한다.

    문제에서 오름차순으로 주어진다고 언급한 적이 없는데 멋대로 오름차순이라고 생각해버렸다.(주어진 예시가 오름차순이었다.)
 */
public class BOJ1449_수리공_항승 {
    static int N, L;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        System.out.println(solve());
    }

    static int solve() {
        int cnt = 0;
        for (int i = 0, j = 0; i < N; i = j) {
            while (j < N && arr[j] - arr[i] + 1 <= L) {
                j++;
            }
            //System.out.println(j + " " + cnt);
            cnt++;
        }
        return cnt;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
    }
}

