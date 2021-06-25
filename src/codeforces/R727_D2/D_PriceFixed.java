package codeforces.R727_D2;

import java.io.*;
import java.util.*;

public class D_PriceFixed {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long solve(List<Food> foods) {
        Collections.sort(foods);
        int l = 0;
        int r = foods.size() - 1;
        long cnt = 0;
        long ret = 0;

        while (l <= r) {
            Food lf = foods.get(l);
            Food rf = foods.get(r);
            if (rf.a + cnt >= lf.b) {
                long temp = Math.max(lf.b - cnt, 0);
                ret += temp * 2 + lf.a;
                cnt += temp + lf.a;
                l++;
                rf.a -= temp;
            } else {
                cnt += rf.a;
                ret += rf.a * 2;
                r--;
            }
        }
        Food last = foods.get(l);
        if (last.b > cnt) {
            long temp = last.b - cnt;
            if (temp < last.a) ret += temp * 2 + last.a - temp;
            else ret += last.a * 2;
        } else {
            ret += last.a;
        }
        return ret;
    }

    static class Food implements Comparable<Food> {
        long a, b;

        public Food(long a, long b) {
            this.a = a;
            this.b = b;
        }

        @Override public String toString() {
            return "Food{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }

        @Override public int compareTo(Food o) {
            if (this.b != o.b) {
                return Long.compare(this.b, o.b);
            } else {
                return Long.compare(this.a, o.a);
            }
        }
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            foods.add(new Food(a, b));
        }
        System.out.println(solve(foods));
    }
}
