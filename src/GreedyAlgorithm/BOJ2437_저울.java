package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ2437_저울
/*
    입력받은 저울추의 무게를 오름차순으로 정렬후
    현재까지의 추의 합+1보다 다음추의 무게가 더 크다면 현재까지의 추의 합+1 의 무게는 만들지 못한다.

    그리디 알고리즘을 풀면서 생기는 고민이 다시 증폭되는 문제이다.
    이 생각을 혼자 힘으로 해내지 못하여 컨닝을 하였으며, 이 아이디어를 증명을 해내지 못하겠다.
    처음 이 생각을 했지만 증명을 못하고 반례가 있을거라 지례짐작하고 넘어가버린 아이디어이다.
    가장 근접하게 설명하는 접근은 다음에 나올수 있는 추의 최대값들을 나열하여 이진수로 바꾸어서 살펴보는것 같다.
    1 2 4 8 16 32 ... 이 수열에서 나오는 행렬들이 그 수까지의 무게를 추로 만들수 있는 값의 최대값이다.
    (즉 위에서 32가 아닌 33이 나와버리면 1+2+4+8+16 + 1 의 무게 즉 32는 만들지 못한다.
    이 숫자들을 이진수로 바꾸어보면
    1, 10, 100, 1000, 10000, 100000 ... 식으로 나온다. 각 숫자들을 더해주면 모든 자리에 1이 들어갈수 있다.
    이러한 식으로 접근하는게 옳은건지는 확실치 않다.

 */

public class BOJ2437_저울 {
    static int N;
    static long sum;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        Arrays.sort(arr);
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + 1 < arr[i]) {
                System.out.println(sum + 1);
                return;
            }
            sum += arr[i];
        }
        System.out.println(sum + 1);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            sum += arr[i];
        }
    }
}

