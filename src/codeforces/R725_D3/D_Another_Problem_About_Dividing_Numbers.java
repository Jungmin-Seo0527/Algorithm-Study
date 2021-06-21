package codeforces.R725_D3;

import java.io.*;
import java.util.*;

public class D_Another_Problem_About_Dividing_Numbers {

    static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        primes = primeList((int) (Math.sqrt((int) 1e9) + 1));
        inputAndSettingData();
    }

    static String solve(int a, int b, int k) {
        int N = cntPrime(a) + cntPrime(b);
        int M = 2;
        if (a == b) {
            M = 0;
        } else {
            int g = gcd(a, b);
            if (g == a || g == b) {
                M = 1;
            }
        }

        if (M <= k && k <= N) {
            if (k != 1 || M == 1) {
                return "YES";
            }
        }
        return "NO";
    }

    static int cntPrime(int n) {
        int cnt = 0;
        for (Integer prime : primes) {
            while (n % prime == 0) {
                cnt++;
                n /= prime;
            }
            if (n == 1) break;
        }

        return n != 1 ? cnt + 1 : cnt;
    }

    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    static List<Integer> primeList(int max) {
        boolean[] isPrime = new boolean[max + 1];
        List<Integer> ret = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (!isPrime[i]) {
                ret.add(i);
                for (int j = i + i; j <= max; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            ans.append(solve(a, b, k)).append("\n");
        }
        System.out.print(ans);
    }
}
