package mst;

import java.io.*;
import java.util.*;

public class BOJ10423_전기가_부족해 {

    static int N, M, K;
    static List<Integer> powerPlant;
    static List<List<Node>> nodeList;

    static void solve() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        int ret = 0;
        for (int i = 0; i < powerPlant.size(); i++) {
            que.add(new Node(powerPlant.get(i), 0));
        }
        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (visited[cur.node]) continue;
            visited[cur.node] = true;
            ret += cur.weight;
            for (int i = 0; i < nodeList.get(cur.node).size(); i++) {
                Node next = nodeList.get(cur.node).get(i);
                if (!visited[next.node]) {
                    que.add(next);
                }
            }
        }
        System.out.println(ret);
    }

    static class Node implements Comparable<Node> {
        int node, weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        powerPlant = new ArrayList<>(K);
        nodeList = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            nodeList.add(new ArrayList<>(N + 1));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            powerPlant.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodeList.get(u).add(new Node(v, w));
            nodeList.get(v).add(new Node(u, w));
        }
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input3.txt";
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
