package binarySearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2539_모자이크 {

    static int rowSZ, colSZ, W, N;
    static List<Point> arr;

    static void solve() {
        Collections.sort(arr);
        int max = Math.min(rowSZ, colSZ);
        int min = 1;

        while (min < max) {
            int mid = (max + min) >>> 1;

            if (func(mid)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(max);
    }

    static boolean func(int len) {
        int arrIdx = 0;
        int cnt = 0;
        while (arrIdx < arr.size() && cnt <= N) {
            int xStart = arr.get(arrIdx).c;
            if (arr.get(arrIdx).r > len) return false;

            while (arrIdx < arr.size()) {
                if (arrIdx + 1 < arr.size() && arr.get(arrIdx + 1).c - xStart + 1 <= len) {
                    if (arr.get(arrIdx + 1).r > len) return false;
                    arrIdx++;
                } else {
                    break;
                }
            }

            cnt++;
            arrIdx++;
        }
        return cnt <= N;
    }


    public static class Point implements Comparable<Point> {
        int r, c;

        public Point(int x, int y) {
            this.r = x;
            this.c = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.c != o.c) {
                return Integer.compare(this.c, o.c);
            } else {
                return Integer.compare(this.r, o.r);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine().trim());
        W = Integer.parseInt(br.readLine().trim());
        arr = new ArrayList<>(W);
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr.add(new Point(r, c));
        }
        solve();
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
