/*
    BOJ13975_파일_합치기3
    --------------------------------------------------------------------------------------------------------------------
    문제링크
    https://www.acmicpc.net/problem/13975

    난이도 : G5
    --------------------------------------------------------------------------------------------------------------------
    풀이

    우선순위 큐로 풀수있는 쉬운 문제
    파일 합치기 문제와는 다르게 이 문제는 앞뒤로만 합치는 것이 아닌 자유롭게 합칠 수 있다.
    가장 최소의 값 2개를 뽑아서 더한 후 그 값을 다시 우선순위 큐에 넣은 과정을 반복하면 된다.
    --------------------------------------------------------------------------------------------------------------------
 */
package greedy;

import java.io.*;
import java.util.*;

public class BOJ13975_파일_합치기3 {
    static int N;
    static long[] arr;
    static PriorityQueue<Long> que = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long solve() {
        long ret = 0;
        while (que.size() > 1) {
            long n1 = que.poll();
            long n2 = que.poll();
            que.add(n1 + n2);
            ret += (n1 + n2);
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            N = Integer.parseInt(br.readLine());
            arr = new long[N];
            que.clear();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                que.add(Long.parseLong(st.nextToken()));
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}
