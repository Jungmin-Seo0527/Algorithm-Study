package greedy;

import java.io.*;
import java.util.*;

// BOJ1946_신입사원
// 문제를 처음에 이해를 잘못했다.
// 우선 임의의 A 지원자가 다른 모든 지원자와 비교했을때 서류, 면접 등수가 모두 떨어지는 지원자가 존재하면 A는 절대 선발될 수 없다
// 이 조건을 만족시키면서 최대 선발 인원수를 구해야 한다.
// 내가 잘못 이해한것은 각각의 등수가 입력으로 주어지는것을 간과했다. 점수가 아닌 "등수" 이다.
// 즉 1, 1인 지원자가 모두 올킬할수 있다. 처음에 5, 5가 올킬시키는 사람인것으로 착각했다.
// 또한 무조건 모든 지원자와 비교햐여 둘다 떨어지는 등수가 없는 지원자만 가능하다.
// 최대값을 뽑겠다고 1, 1이 존재할때 그 사람을 그냥 버리는 일은 없다.
// 이때문에 처음에 서류면접의 오름차순대로 면접 등수를 나열후에 가장 큰 증가하는 부분수열의 길이를 구했다.
// 즉 너무 등수가 좋은 사람이 존재해서 이사람 때문에 떨어지는 사람이 너무 많은 경우에 성적이 너무 좋은 사람을 떨군것이다.

// 올바른 해법은 서류 등수의 오름차순대로 면접 등수를 나열하는것 까지는 맞다.
// 그리고 나열한 배열을 내림차순으로 나타낼수 있는 수열을 그대로 뽑는 것이다.
// 즉 처음의 등수부터 시작해서 그대로 내려가는 것이다.
// 시작 등수가 4이면 처음 만나는 그보다 작은 값(즉 등수개념이므로 성적이 좋은 사람)의 사람은 합격이다.
// 그리고 그 합격자 등수를 기준으로 계속 진행시킨다.
public class BOJ1946_신입사원 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve() {
        int cur = arr[0];
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (arr[i] < cur) {
                cnt++;
                cur = arr[i];
            }
        }
        return cnt;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                arr[n1 - 1] = n2;
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}

