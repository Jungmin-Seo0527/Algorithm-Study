package ds;

import java.io.*;
import java.util.*;

public class BOJ17299_오등큰수 {

    static int[] solve(int n, int[] arr, int[] cnt) {
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && cnt[arr[stack.peek()]] < cnt[arr[i]]) {
                ret[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] cnt = new int[1000001];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cnt[arr[i]]++;
        }

        int[] ans = solve(N, arr, cnt);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(ans).forEachOrdered(an -> sb.append(an).append(" "));
        System.out.println(sb);
    }
}