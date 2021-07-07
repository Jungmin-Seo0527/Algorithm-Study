package bruteForce;

import java.io.*;
import java.util.*;

public class BOJ16637_괄호_추가하기 {
    static int N, ans = Integer.MIN_VALUE;
    static char[] arr;
    static int[] num;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        permutation(new ArrayList<>(), 1);
        System.out.println(ans);
    }

    static void permutation(List<Integer> list, int cur) {
        if (cur > N - 2) {
            calculation(list);
            return;
        }
        List<Integer> next1 = new ArrayList<>(list);
        next1.add(cur);
        permutation(next1, cur + 4);

        List<Integer> next2 = new ArrayList<>(list);
        permutation(next2, cur + 2);
    }

    private static void calculation(List<Integer> list) {
        num = new int[N];
        char[] copyArr = new char[N];
        System.arraycopy(arr, 0, copyArr, 0, N);
        for (Integer idx : list) {
            int p = (int) copyArr[idx - 1] - '0';
            int n = (int) copyArr[idx + 1] - '0';
            if (copyArr[idx] == '+') {
                num[idx - 1] = p + n;
            } else if (copyArr[idx] == '-') {
                num[idx - 1] = p - n;
            } else if (copyArr[idx] == '*') {
                num[idx - 1] = p * n;
            }
            copyArr[idx - 1] = '!';
            copyArr[idx] = '!';
            copyArr[idx + 1] = '!';
        }

        for (int i = 0; i < arr.length; i++) {
            if (copyArr[i] >= '0' && copyArr[i] <= '9') {
                num[i] = copyArr[i] - '0';
            }
        }
        int sum = num[0];
        for (int i = 1; i < arr.length; i++) {
            if (copyArr[i] == '+') {
                sum += num[++i];
            } else if (copyArr[i] == '-') {
                sum -= num[++i];
            } else if (copyArr[i] == '*') {
                sum *= num[++i];
            } else if (copyArr[i] == '!') {
                i++;
            }
        }
        ans = Math.max(ans, sum);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N];
        arr = br.readLine().toCharArray();
    }
}
