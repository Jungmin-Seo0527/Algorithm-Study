package greedy;

import java.io.*;
import java.util.*;

public class BOJ1826_연료_채우기 {

    static int N;
    static Point sung, town;
    static List<Point> arr;

    static void solve() {
        int ans = 0;
        arr.add(town);
        arr.sort(Comparator.comparing(o -> o.dist));
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0, sz = arr.size(); i < sz; i++) {
            Point cur = arr.get(i);
            if (sung.oil < cur.dist) {
                while (!pq.isEmpty() && sung.oil < cur.dist) {
                    Point poll = pq.poll();
                    sung.oil += poll.oil;
                    ans++;
                }
            }

            if (sung.oil >= cur.dist) {
                pq.add(cur);
            }
        }
        if (sung.oil < town.dist) ans = -1;
        System.out.println(ans);
    }

    static class Point implements Comparable<Point> {
        int dist, oil;

        public Point(int dist, int oil) {
            this.dist = dist;
            this.oil = oil;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(o.oil, this.oil);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "dist=" + dist +
                    ", oil=" + oil +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int oil = Integer.parseInt(st.nextToken());
            arr.add(new Point(dist, oil));
        }
        st = new StringTokenizer(br.readLine());
        int dist = Integer.parseInt(st.nextToken());
        int oil = Integer.parseInt(st.nextToken());
        sung = new Point(0, oil);
        town = new Point(dist, 0);
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input2.txt";
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
