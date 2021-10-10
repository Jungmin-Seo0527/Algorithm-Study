package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20164_홀수_홀릭_호석 {

    static int maxAns = Integer.MIN_VALUE;
    static int minAns = Integer.MAX_VALUE;

    static void solve(String n) {
        int cnt = 0;
        for (int i = 0; i < n.length(); i++) {
            int tempN = Integer.parseInt(String.valueOf(n.charAt(i)));
            if (isOddNum(tempN)) cnt++;
        }
        dfs(n, cnt);
        System.out.println(minAns + " " + maxAns);
    }

    static void dfs(String N, int cnt) {
        if (N.length() == 1) {
            getAns(cnt);
        } else if (N.length() == 2) {
            int n1 = Integer.parseInt(String.valueOf(N.charAt(0)));
            int n2 = Integer.parseInt(String.valueOf(N.charAt(1)));
            dfs(String.valueOf(n1 + n2), countOddNum(cnt, n1 + n2));
        } else {
            for (int i = 1; i < N.length(); i++) {
                for (int j = i + 1; j < N.length(); j++) {
                    int n1 = Integer.parseInt(N.substring(0, i));
                    int n2 = Integer.parseInt(N.substring(i, j));
                    int n3 = Integer.parseInt(N.substring(j));
                    dfs(String.valueOf(n1 + n2 + n3), countOddNum(cnt, n1 + n2 + n3));
                }
            }
        }
    }

    private static int countOddNum(int cnt, int num) {
        String n = String.valueOf(num);
        for (int i = 0; i < n.length(); i++) {
            int temp = Integer.parseInt(String.valueOf(n.charAt(i)));
            if (isOddNum(temp)) cnt++;
        }
        return cnt;
    }

    private static void getAns(int n) {
        maxAns = Math.max(maxAns, n);
        minAns = Math.min(minAns, n);
    }

    static boolean isOddNum(int n) {
        return n % 2 == 1;
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        solve(N);
    }
}
