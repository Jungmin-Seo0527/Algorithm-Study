package math;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class BOJ17205_진우의_비밀번호 {

    static int N;

    static BigInteger solve(char cur, int n) {
        BigInteger ans = new BigInteger(String.valueOf(cur - 'a'));
        BigInteger temp = new BigInteger(String.valueOf(0));
        for (int i = n - 1; i >= 0; i--) {
            temp = temp.add(BigInteger.valueOf((long) Math.pow(26, i)));
        }
        return ans.multiply(temp).add(BigInteger.ONE);
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i < str.length(); i++) {
            ans = ans.add(solve(str.charAt(i), N--));
        }
        System.out.println(ans);
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input3.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
