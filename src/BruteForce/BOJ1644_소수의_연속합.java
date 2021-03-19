package BruteForce;

import java.io.*;
import java.util.*;

public class BOJ1644_소수의_연속합 {

    static int N, primeCnt;
    static boolean[] isPrime;
    static int[] primeArr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        initPrimeArr();
        int start = 0, end = 0, sum = 0, ans = 0;
        while (start <= end && end <= primeCnt) {
            if (sum < N) {
                sum += primeArr[end++];
            } else if (sum > N) {
                sum -= primeArr[start++];
            } else {
                sum -= primeArr[start++];
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void initPrimeArr() {
        for (int i = 2; i * i <= N; i++) {
            if (!isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (!isPrime[i]) {
                primeArr[primeCnt++] = i;
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        isPrime = new boolean[N + 1];
        primeArr = new int[N + 1];
    }
}

