package mst;

import java.io.*;
import java.util.*;

public class BOJ1647_도시_분할_계획 {

    static int N;
    static List<List<Node>> list = new ArrayList<>();

    static int solve() {
        PriorityQueue<Node> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        boolean[] visited = new boolean[N + 1];
        que.add(new Node(1, 0));
        int max = 0;
        int sum = 0;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.adj]) {
                continue;
            }
            visited[cur.adj] = true;
            sum += cur.weight;
            max = Math.max(max, cur.weight);
            for (int i = 0; i < list.get(cur.adj).size(); i++) {
                Node next = list.get(cur.adj).get(i);
                if (!visited[next.adj]) {
                    que.add(next);
                }
            }
        }
        return sum - max;
    }

    static class Node {
        int adj, weight;

        public Node(int adj, int weight) {
            this.adj = adj;
            this.weight = weight;
        }

        @Override public String toString() {
            return "Node{" +
                    "adj=" + adj +
                    ", weight=" + weight +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // BufferedReader br = new BufferedReader(new FileReader("input/input1.txt"));
        // BufferedReader br2 = new BufferedReader(new FileReader("input/input1.txt"));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list.get(A).add(new Node(B, C));
            list.get(B).add(new Node(A, C));
        }
        System.out.println(solve());
    }
}
