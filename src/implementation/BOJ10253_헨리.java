package implementation;

import java.io.*;
import java.util.*;

public class BOJ10253_헨리 {

    static int solve(int inputNumerator, int inputDenumerator) {
        Fraction start = new Fraction(inputNumerator, inputDenumerator);
        while (start.n != 1) {
            Fraction next = getMaxUnitFraction(start);
            start = start.sub(next)
                    .reduce();
        }
        return start.d;
    }

    static Fraction getMaxUnitFraction(Fraction f) {
        if (f.n == 1) return new Fraction(f.n, f.d);
        int moc = f.d / f.n;
        int na = f.d % f.n;
        if (na != 0) moc++;
        return new Fraction(1, moc);
    }

    static Fraction getMaxUnitFraction2(Fraction f) {
        for (int i = 2; i < f.d; i++) {
            if (f.compareTo(new Fraction(1, i)) >= 0) {
                return new Fraction(1, i);
            }
        }
        return new Fraction(1, f.d);
    }

    static class Fraction {
        int n, d;

        public Fraction(int n, int d) {
            this.n = n;
            this.d = d;
        }

        public Fraction sub(Fraction f) {
            int sd = d * f.d;
            int sn = n * f.d - f.n * d;
            return new Fraction(sn, sd);
        }

        public int compareTo(Fraction f) {
            Fraction sub = sub(f);
            return sub.n;
        }

        public Fraction reduce() {
            for (int i = n; i >= 2; i--) {
                if (d % i == 0 && n % i == 0) {
                    d /= i;
                    n /= i;
                    return new Fraction(n, d);
                }
            }
            return this;
        }

        @Override
        public String toString() {
            return n + "/" + d;
        }
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int inputNumerator = Integer.parseInt(st.nextToken());
            int inputDenumerator = Integer.parseInt(st.nextToken());
            sb.append(solve(inputNumerator, inputDenumerator)).append("\n");
        }
        System.out.println(sb);
    }
}
