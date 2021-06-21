package codeforces.R725_D3;

import java.io.*;
import java.util.*;

public class C_Number_of_Pairs {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long solve(List<Integer> arr, int N, int l, int r) {
        Collections.sort(arr);
        return pairCnt(arr, r) - pairCnt(arr, l - 1);
    }

    static long pairCnt(List<Integer> arr, int target) {
        long ret = 0;
        int left = 0;
        int right = arr.size() - 1;
        while (left < right) {
            if (arr.get(left) + arr.get(right) <= target) {
                ret += (long) right - (long) left;
                left++;
            } else {
                right--;
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
            int N = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            List<Integer> arr = new ArrayList<>(N);
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }
            ans.append(solve(arr, N, l, r)).append("\n");
        }
        System.out.print(ans);
    }
}
