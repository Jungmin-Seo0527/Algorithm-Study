package mst;

import java.io.*;
import java.util.*;

public class BOJ4386_별자리_만들기 {

    static int N;
    static Point[] stars;
    static List<List<Node>> adjList;

    static void solve() {
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
                adjList.get(i).add(new Node(j, dist));
                adjList.get(j).add(new Node(i, dist));
            }
        }

        System.out.println(mst());
    }

    static double mst() {
        PriorityQueue<Node> que = new PriorityQueue<>(((o1, o2) -> Double.compare(o1.w, o2.w)));
        boolean[] visited = new boolean[N];
        double ret = 0;
        que.add(new Node(0, 0));

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.v]) {
                continue;
            }
            visited[cur.v] = true;
            ret += cur.w;
            for (int i = 0; i < adjList.get(cur.v).size(); i++) {
                Node next = adjList.get(cur.v).get(i);
                if (!visited[next.v]) {
                    que.add(next);
                }
            }
        }
        return ret;
    }

    static class Node {
        int v;
        double w;

        public Node(int v, double w) {
            this.v = v;
            this.w = w;
        }
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
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
        stars = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Point(x, y);
        }
        solve();
    }
}
