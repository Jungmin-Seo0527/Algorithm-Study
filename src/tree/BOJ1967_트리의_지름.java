package tree;

import java.io.*;
import java.util.*;

public class BOJ1967_트리의_지름 {
    static int N, ans, startNode;
    static List<List<Node>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        doDFS(0, 1, 0);
        ans = 0;
        doDFS(0, startNode, 0);
        System.out.println(ans);
    }

    static void doDFS(int weightSum, int cur, int prev) {
        if (ans < weightSum) {
            ans = weightSum;
            startNode = cur;
        }
        for (Node node : list.get(cur)) {
            if (node.node != prev) doDFS(weightSum + node.weight, node.node, cur);
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(n1).add(new Node(n2, w));
            list.get(n2).add(new Node(n1, w));
        }
    }

    static class Node {
        int node, weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}

