package BruteForce;

import java.io.*;
import java.util.*;

// BOJ14255_부분수열의_합
/*
    수열 S가 주어졌을 때, 수열 S의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를 구하는 프로그램을 작성하시오.

    예를 들어, S = [5, 1, 2]인 경우에 1, 2, 3(=1+2), 5, 6(=1+5), 7(=2+5), 8(=1+2+5)을 만들 수 있다.
    하지만, 4는 만들 수 없기 때문에 정답은 4이다.
    첫째 줄에 수열 S의 크기 N이 주어진다. (1 ≤ N ≤ 20)
    둘째 줄에는 수열 S가 주어진다. S를 이루고있는 수는 100,000보다 작거나 같은 자연수이다.

    조건에서 수열의 크기의 최대값이 20이고 수열을 이루는 각 숫자는 100,000이하의 자연수이다.
    즉 부분수열의 합의 최대값은 2000000 이므로 같은 크기의 배열을 만들어 존재 여부의 boolean 배열을 만들어서
    풀어도 된다.
    나는 Set을 사용해보고 싶어서 Set을 한번 써봤다. 당연히 처리시간은 훨씬 더 느리다.
 */
public class BOJ14255_부분수열의_합 {
    static int N;
    static int[] arr;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        for (int i = 0; i < N; i++) {
            doDFS(i, arr[i]);
        }
        int ret = 1;
        while (set.contains(ret)) {
            ret++;
        }
        System.out.println(ret);
    }

    static void doDFS(int idx, int sum) {
        set.add(sum);
        for (int i = idx + 1; i < N; i++) {
            doDFS(i, sum + arr[i]);
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}

