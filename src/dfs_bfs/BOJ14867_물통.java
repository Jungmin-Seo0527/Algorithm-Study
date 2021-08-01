package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ14867_물통 {

    static int a, b, c, d;

    static void solve() {
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Water> que = new LinkedList<>();
        Map<Water, Integer> visited = new HashMap<>();

        Water start = new Water(0, 0);
        que.add(start);
        visited.put(start, 0);

        int ret = -1;

        while (!que.isEmpty()) {
            Water w = que.poll();
            int cnt = visited.get(w);

            if (w.x == c && w.y == d) {
                ret = cnt;
                break;
            }

            Water w1 = new Water(a, w.y);
            if (!visited.containsKey(w1)) {
                visited.put(w1, cnt + 1);
                que.add(w1);
            }

            Water w2 = new Water(w.x, b);
            if (!visited.containsKey(w2)) {
                visited.put(w2, cnt + 1);
                que.add(w2);
            }

            Water w3 = new Water(0, w.y);
            if (!visited.containsKey(w3)) {
                visited.put(w3, cnt + 1);
                que.add(w3);
            }

            Water w4 = new Water(w.x, 0);
            if (!visited.containsKey(w4)) {
                visited.put(w4, cnt + 1);
                que.add(w4);
            }

            if (w.x + w.y <= b) {
                Water w5 = new Water(0, w.x + w.y);
                if (!visited.containsKey(w5)) {
                    visited.put(w5, cnt + 1);
                    que.add(w5);
                }
            } else {
                Water w6 = new Water(w.x + w.y - b, b);
                if (!visited.containsKey(w6)) {
                    visited.put(w6, cnt + 1);
                    que.add(w6);
                }
            }

            if (w.x + w.y <= a) {
                Water w7 = new Water(w.x + w.y, 0);
                if (!visited.containsKey(w7)) {
                    visited.put(w7, cnt + 1);
                    que.add(w7);
                }
            } else {
                Water w8 = new Water(a, w.x + w.y - a);
                if (!visited.containsKey(w8)) {
                    visited.put(w8, cnt + 1);
                    que.add(w8);
                }
            }
        }

        return ret;
    }

    static class Water {
        int x, y;

        public Water(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Water water = (Water) o;
            return x == water.x && y == water.y;
        }

        @Override public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override public String toString() {
            return "Water{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
//        System.out.println("===== input =====");
//        String fileName = "input/input1.txt";
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
//        String s;
//        while ((s = br2.readLine()) != null) {
//            System.out.println(s);
//        }
//        System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        solve();
    }
}
