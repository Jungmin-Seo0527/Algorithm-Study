package greedy;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class BOJ27377_읽씹_멈춰 {

    static BigInteger solve(long n, BigInteger s, BigInteger t) {
        BigInteger ret = BigInteger.ZERO;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = ret.add(s);
                n--;
            } else {
                BigInteger temp = new BigInteger(String.valueOf(n / 2));
                if (temp.multiply(s).compareTo(t) < 0) {
                    ret = ret.add(temp.multiply(s));
                } else {
                    ret = ret.add(t);
                }
                n /= 2;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder ans = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            st = new StringTokenizer(br.readLine());
            BigInteger s = new BigInteger(String.valueOf(st.nextToken()));
            BigInteger t = new BigInteger(String.valueOf(st.nextToken()));
            ans.append(solve(n, s, t).toString()).append("\n");
        }
        System.out.println(ans);
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
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
