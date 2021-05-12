package mst;

import java.io.*;
import java.util.*;

public class BOJ1197_최소_스패닝_트리 {

    static int V, E;
    static List<List<Node>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        boolean[] visited = new boolean[V + 1];
        int ret = 0;
        que.add(new Node(1, 0));
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if(visited[cur.adjNode]) continue;
            visited[cur.adjNode] = true;
            ret += cur.weight;
            for (Node next : adjList.get(cur.adjNode)) {
                if(!visited[next.adjNode]) que.add(next);
            }
        }
        System.out.println(ret);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Node(to, weight));
            adjList.get(to).add(new Node(from, weight));
        }
    }

    static class Node {
        int adjNode, weight;

        public Node(int adjNode, int weight) {
            this.adjNode = adjNode;
            this.weight = weight;
        }
    }

}
