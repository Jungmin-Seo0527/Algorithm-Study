package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ9694_무엇을_아느냐가_아니라_누구를_아느냐가_문제다 {

    static int N, M;
    static int[] visited;
    static int[] dist;
    static List<List<Node>> adjList;

    static String solve() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        dist[0] = 0;
        que.add(new Node(0, 0, 0));
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.v] != -1) continue;
            visited[cur.v] = cur.p;

            for (int i = 0; i < adjList.get(cur.v).size(); i++) {
                Node next = adjList.get(cur.v).get(i);
                if (visited[next.v] == -1 &&
                        (dist[next.v] == Integer.MAX_VALUE || dist[next.v] > dist[cur.v] + next.w)) {
                    dist[next.v] = dist[cur.v] + next.w;
                    que.add(new Node(next.v, dist[next.v], cur.v));
                }
            }
        }
        return getRoot();
    }

    private static String getRoot() {
        StringBuilder sb = new StringBuilder();
        if (visited[M - 1] == -1) {
            sb.append(" -1");
        } else {
            int cur = M - 1;
            Stack<Integer> stack = new Stack<>();
            while (true) {
                stack.push(cur);
                if (cur == 0) break;
                cur = visited[cur];
            }
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }
        }
        return sb.toString();
    }


    static class Node implements Comparable<Node> {
        int v, w, p;

        public Node(int v, int w, int p) {
            this.v = v;
            this.w = w;
            this.p = p;
        }


        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", w=" + w +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        int c = 1;
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dist = new int[M];
            visited = new int[M];
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(visited, -1);
            adjList = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                adjList.get(from).add(new Node(to, weight));
                adjList.get(to).add(new Node(from, weight));
            }
            answer.append("Case #").append(c++).append(":").append(solve()).append("\n");
        }
        System.out.println(answer);
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
