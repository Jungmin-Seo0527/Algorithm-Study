package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ1744_수_묶기
// Arrays.sort(arr, fromIdx, toIdx) 에서 toIdx는 마지막 인덱스가 아니라 마지막 인덱스 +1이다.
// 즉 fromIdx에서 시작해서 toIdx바로 이전의 인덱스까지 정렬해준다. 이점을 알지 못해 해맸다.

/*
    문제를 푸는 방법은 쉽게 알아냈다.
    입력값들을 오름차순으로 정렬후

    1. 음수 다음 0이 나오면 곱하는것이 좋고(음수가 0이 되므로) 양수가 나오면 곱하지 않는것이 좋다(양수마저 음수가 되어 버리므로)
    2. 큰수부터 시작하여 양수 다음 0 혹은 음수가 나오면 곱하지 않는것이 좋다.
    3. 양수에서 1을 곱하는 짓은 손해이다. 즉 두쌍으로 묶으려는 숫자중 하나라도 1이라면 그냥 그 두수를 더해준다.

    양수와 음수 배열을 따로 두고 0은 음수 배열로 두고 풀었다.
    구현은 어렵지 않았으나 Array.sort() 에서 인덱스 범위 정하는 것에서 잘못 생각했다.
    toIdx는 그 인덱스 이전까지 정렬하겠다는 의미!!!
 */


public class BOJ1744_수_묶기 {
    static int N, m_len, p_len;
    static long[] minus, plus;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        long sum = 0;
        for (int i = 0; i < m_len - 1; i += 2) {
            sum += minus[i] * minus[i + 1];
        }
        if (m_len % 2 == 1) sum += minus[m_len - 1];

        for (int i = p_len - 1; i > 0; i -= 2) {
            if (plus[i] == 1 || plus[i - 1] == 1) {
                sum += plus[i] + plus[i - 1];
            } else sum += plus[i] * plus[i - 1];
        }
        if (p_len % 2 == 1) sum += plus[0];
        System.out.println(sum);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        plus = new long[N];
        minus = new long[N];
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            if (num > 0) plus[p_len++] = num;
            else minus[m_len++] = num;
        }
        if (p_len > 0) Arrays.sort(plus, 0, p_len);
        if (m_len > 0) Arrays.sort(minus, 0, m_len);
    }
}

