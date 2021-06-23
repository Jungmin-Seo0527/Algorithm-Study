package codeforces.R727_D2;

import java.io.*;
import java.util.*;

public class C_Stable_Groups {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long solve(List<Long> arr, int n, long k, long x) {
        Collections.sort(arr);
        List<Long> list = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (arr.get(i) - arr.get(i - 1) > x) {
                list.add(arr.get(i) - arr.get(i - 1));
            }
        }

        long cnt = 0;
        Collections.sort(list);
        for (Long s : list) {
            long temp = s / x;
            if (s % x == 0) temp--;
            if (k - temp >= 0) {
                k -= temp;
                cnt++;
            } else {
                break;
            }
        }

        return list.size() + 1 - cnt;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long x = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Long> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(Long.parseLong(st.nextToken()));
        }
        System.out.println(solve(arr, n, k, x));
    }
}
