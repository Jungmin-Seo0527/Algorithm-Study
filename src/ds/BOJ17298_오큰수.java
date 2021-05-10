package ds;

import java.io.*;
import java.util.*;

public class BOJ17298_오큰수 {
    static int N;
    static int[] arr, ans;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
        showAns();
    }

    static void solve() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                ans[stack.pop()] = arr[i];
            }
            stack.add(i);
        }
    }

    private static void showAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        ans = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            ans[i] = -1;
        }
    }
}
