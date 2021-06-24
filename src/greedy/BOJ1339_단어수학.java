package greedy;

import java.io.*;
import java.util.*;

public class BOJ1339_단어수학 {
    static int[] cnt = new int['Z' - 'A' + 1];

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int temp = 1;
            for (int j = s.length() - 1; j >= 0; j--) {
                char c = s.charAt(j);
                cnt[c - 'A'] += temp;
                temp *= 10;
            }
        }

        Arrays.sort(cnt);
        int ans = 0;
        int temp = 9;
        for (int i = cnt.length - 1; i >= 0; i--) {
            if (cnt[i] == 0) {
                break;
            }
            ans += cnt[i] * temp--;
        }
        System.out.println(ans);
    }
}