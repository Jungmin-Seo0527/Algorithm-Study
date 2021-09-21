package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ18513_샘터 {

    static int N, K;
    static final int T = (int) 1e9;
    static Set<Integer> set = new HashSet<>();
    static Queue<Point> que = new LinkedList<>();

    static void solve() {
        long ret = 0;

        que:
        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = -1; i <= 1; i += 2) {
                int next = cur.n + i;
                if (!set.contains(next)) {
                    que.add(new Point(next, cur.cnt + 1));
                    set.add(next);
                    K--;
                    ret += cur.cnt + 1;
                    if(K==0) break que;
                }
            }
        }
        System.out.println(ret);
    }

    static class Point {
        int n, cnt;

        public Point(int n) {
            this.n = n;
        }

        public Point(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
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
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            que.add(new Point(n));
            set.add(n);
        }
        solve();
    }
}
