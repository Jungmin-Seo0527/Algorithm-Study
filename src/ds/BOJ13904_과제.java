package ds;

import java.io.*;
import java.util.*;

public class BOJ13904_과제 {

    static int N;
    static List<Work> arr;

    static void solve() {
        arr.sort(Comparator.comparingInt(o -> o.dDay));
        PriorityQueue<Work> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.size(); i++) {
            Work cur = arr.get(i);
            if (pq.isEmpty()) {
                pq.add(cur);
            } else {
                if (cur.dDay > pq.size()) {
                    pq.add(cur);
                } else {
                    Work peek = pq.peek();
                    if (peek.point <= cur.point) {
                        pq.poll();
                        pq.add(cur);
                    }
                }
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Work work = pq.poll();
            ans += work.point;
        }
        System.out.println(ans);
    }

    static class Work implements Comparable<Work> {
        int dDay, point;

        public Work(int dDay, int point) {
            this.dDay = dDay;
            this.point = point;
        }

        @Override
        public String toString() {
            return "Work{" +
                    "dDay=" + dDay +
                    ", point=" + point +
                    '}';
        }

        @Override
        public int compareTo(Work o) {
            if (o.point != this.point) {
                return Integer.compare(this.point, o.point);
            } else {
                return Integer.compare(this.dDay, o.dDay);
            }
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
            int dDay = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());
            arr.add(new Work(dDay, point));
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
