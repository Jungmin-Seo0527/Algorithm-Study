package greedy;

import java.io.*;
import java.util.*;

public class BOJ2876_코딩은_예쁘게 {

    static int N;
    static int[] arr1, arr2, arr3, arr4;

    static void solve() {
        arr3 = new int[N];
        arr4 = new int[N];
        int ans = 0;

        while (true) {
            initArr();
            getLongestLen();

            int max = 0;
            int maxIdx = 0;
            int tap = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (Math.abs(arr3[i]) > max) {
                    max = Math.abs(arr3[i]);
                    maxIdx = i;
                }
            }
            if (max == 0) break; // 더이상 작업할 줄 X

            tap = getTap(max, maxIdx, tap);
            settingArr1(max, maxIdx, tap);
            ans += tap;
        }
        System.out.println(ans);
    }

    /**
     * @param max 가장 긴 연속하는 음수 or 양수의 길이
     * @param idx 가장 긴 연속하는 음수 or 양수의 마지막 idx
     * @param tap 작업할 tap 갯수
     */
    private static void settingArr1(int max, int idx, int tap) {
        for (int i = idx - max + 1; i <= idx; i++) {
            if (arr3[idx] > 0) {
                arr1[i] -= tap;
            } else {
                arr1[i] += tap;
            }
        }
    }

    /**
     * @param max 가장 긴 연속하는 음수 or 양수의 길이
     * @param idx 가장 긴 연속하는 음수 or 양수의 마지막 idx
     * @param tap 현재 작업에서 필요로 하는 tap의 갯수
     *            <br></br>
     *            * 가장 긴 영속하는 음수 or 양수에서 현재 탭의 수(arr4)의 값이 가장 작은 값이 현재 작업할 tap의 갯수
     * @return tap
     */
    private static int getTap(int max, int idx, int tap) {
        for (int i = idx - max + 1; i <= idx; i++) {
            tap = Math.min(Math.abs(arr4[i]), tap);
        }
        return tap;
    }

    /**
     * arr3구하기: idx의 값이 현재 연속하는 음수 or 양수의 갯수(절대값)
     */
    private static void getLongestLen() {
        for (int i = 1; i < N; i++) {
            if (arr3[i] > 0) {
                if (arr3[i - 1] > 0) arr3[i] = arr3[i - 1] + 1;
                else arr3[i] = 1;
            } else if (arr3[i] < 0) {
                if (arr3[i - 1] < 0) arr3[i] = arr3[i - 1] - 1;
                else arr3[i] = -1;
            } else {
                arr3[i] = 0;
            }
        }
    }

    /**
     * arr3: 현재 탭의 갯수 - 올바른 탭의 갯수<br>
     * arr4: 각 탭의 갯수의 양수, 음수 저장<br>
     * - 절대값: 시작 idx부터 현재까지 연속하는 양수 or 음수의 길이<br><br>
     * arr4에서 양수일 경우: tap 삭제 필요<br>
     * arr4에서 음수일 경우: tap 추가 필요<br>
     * arr4에서 0일 경우: 작업X
     */
    private static void initArr() {
        for (int i = 0; i < N; i++) {
            arr3[i] = arr1[i] - arr2[i];
            arr4[i] = arr3[i];
        }

        if (arr3[0] < 0) arr3[0] = -1;
        else if (arr3[0] > 0) arr3[0] = 1;
        else arr3[0] = 0;
    }

    /**
     * arr는 현재 탭의 수 - 올바른 탭의 수(입력 방식 수정 필요)
     */
    static void solve2() {
        int ans = 0;
        int[] arr = new int[N];
        int prev = arr[0];
        for (int i = 1; i < N; i++) {
            if (arr[i] * prev < 0) { // 이전 값과 부호가 달라질 때
                ans += Math.abs(prev); // 이전 값이 올바른 탭의 수가 되기 위한 작업 수
            } else if (Math.abs(arr[i]) < Math.abs(prev)) { // 이전값가 부호가 같고 이전 값의 필요 작업 수가 더 많을 때
                ans += Math.abs(arr[i] - prev); // 이전 작업 수 - 현재 작업수
            }
            prev = arr[i];
        }
        ans += Math.abs(prev);
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr1 = new int[N];
        arr2 = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
