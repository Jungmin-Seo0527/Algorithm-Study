package bruteForce;

import java.io.*;
import java.util.*;

public class BOJ16638_괄호_추가하기_2 {
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
        priorityOperation(list, copyArr);

        for (int i = 0; i < arr.length; i++) {
            if (copyArr[i] >= '0' && copyArr[i] <= '9') {
                num[i] = copyArr[i] - '0';
            }
        }

        multiply(copyArr);

        int sum = operation(copyArr);
        ans = Math.max(ans, sum);
    }

    private static int operation(char[] copyArr) {
        int sum = 0;
        boolean flag = false;
        for (int i = 1; i < arr.length; i += 2) {
            if (copyArr[i] == '+') {
                sum += !flag ? num[i - 1] + num[i + 1] : num[i + 1];
                flag = true;
            } else if (copyArr[i] == '-') {
                sum += !flag ? num[i - 1] - num[i + 1] : -num[i + 1];
                flag = true;
            }
        }
        if (!flag) sum = num[num.length - 1];
        return sum;
    }

    private static void multiply(char[] copyArr) {
        for (int i = 1; i < arr.length; i += 2) {
            if (copyArr[i] == '*') {
                int p = num[i - 1];
                int n = num[i + 1];
                num[i - 1] = p * n;
                num[i + 1] = p * n;
                for (int j = -1; j <= 1; j++) {
                    copyArr[i + j] = '!';
                }

                int temp = 2;
                while (i + temp < arr.length && copyArr[i + temp] == '!') {
                    num[i + temp - 1] = num[i - 1];
                    num[i + temp + 1] = num[i - 1];
                    temp += 2;
                }
                temp = 2;
                while (i - temp >= 0 && copyArr[i - temp] == '!') {
                    num[i - temp - 1] = num[i - 1];
                    num[i - temp + 1] = num[i - 1];
                    temp += 2;
                }
            }
        }
    }

    private static void priorityOperation(List<Integer> list, char[] copyArr) {
        for (Integer idx : list) {
            int p = (int) copyArr[idx - 1] - '0';
            int n = (int) copyArr[idx + 1] - '0';
            int s;
            if (copyArr[idx] == '+') {
                s = p + n;
            } else if (copyArr[idx] == '-') {
                s = p - n;
            } else if (copyArr[idx] == '*') {
                s = p * n;
            } else {
                s = 0;
            }
            for (int i = -1; i <= 1; i++) {
                copyArr[idx + i] = '!';
                num[idx + i] = s;
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N];
        arr = br.readLine().toCharArray();
    }
}
