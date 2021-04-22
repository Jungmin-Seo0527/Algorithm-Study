package greedy;

import java.io.*;
import java.util.*;

// BOJ1781_컵라면
/*
    이전에 풀었던 보석 도둑, 순회공연 문제와 거의 비슷한 문제
    데드라인과 보상을 보면 순회공연과 거의 똑같은 문제라고 봐도 무방하다
    이문제 또한 현재 시점에서 풀수있는 문제(데드라인이 현재 시점보다 크거나 같은 문제들) 중에
    보상이 가장 큰 문제들을 먼저 뽑는다.
 */
public class BOJ1781_컵라면 {
    static class Problem implements Comparable<Problem> {
        int deadLine, price;

        public Problem(int d, int p) {
            this.deadLine = d;
            this.price = p;
        }

        public int compareTo(Problem o) {
            return o.deadLine - this.deadLine;
        }
    }

    static int N, maxDay;
    static Problem[] problems;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = maxDay, j = 0; i > 0; i--) {
            while (j < N && i <= problems[j].deadLine) que.add(problems[j++].price);
            if (!que.isEmpty()) ans += que.poll();
        }
        System.out.println(ans);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        problems = new Problem[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(d, p);
            maxDay = Math.max(maxDay, d);
        }
        Arrays.sort(problems);
    }
}

