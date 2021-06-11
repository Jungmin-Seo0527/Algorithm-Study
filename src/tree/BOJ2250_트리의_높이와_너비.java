package tree;

import java.io.*;
import java.util.*;

public class BOJ2250_트리의_높이와_너비 {
    static int N;
    static Tree tree = new Tree();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        tree.travelNode(tree.getRootNode(), 1);
        Pair ans = tree.ans();
        System.out.println(ans.min + " " + ans.max);
    }

    static class Tree {
        Node root;
        Node[] nodeList = new Node[10001];
        List<Pair> maxAndMinOfRowList = new ArrayList<>();
        int col = 1;

        public Tree() {
            for (int i = 0; i <= 10000; i++) {
                maxAndMinOfRowList.add(new Pair());
            }
        }

        public void addNode(int nodeNum, int leftNum, int rightNum) {
            Node cur = getNodeFromNodesArr(nodeNum);
            Node left = getNodeFromNodesArr(leftNum);
            Node right = getNodeFromNodesArr(rightNum);

            cur.left = left;
            cur.right = right;
            parentAndChildLink(cur, left);
            parentAndChildLink(cur, right);
        }

        private void parentAndChildLink(Node p, Node c) {
            if (c != null) {
                c.parent = p;
            }
        }

        private Node getRootNode() {
            for (Node node : nodeList) {
                if (node == null) continue;

                if (node.parent == null) {
                    return node;
                }
            }
            throw new IllegalArgumentException();
        }

        public void travelNode(Node node, int row) {
            if (node != null) {
                travelNode(node.left, row + 1);
                node.col = col++;
                node.row = row;
                getMaxAndMinCol(node, row);
                travelNode(node.right, row + 1);
            }
        }

        private void getMaxAndMinCol(Node node, int row) {
            Pair p = maxAndMinOfRowList.get(row);
            if (p.min == 0) {
                p.min = node.col;
                p.max = node.col;
            } else {
                p.min = Math.min(p.min, node.col);
                p.max = Math.max(p.max, node.col);
            }
        }

        private Node getNodeFromNodesArr(int num) {
            if (num == -1) {
                return null;
            } else if (nodeList[num] != null) {
                return nodeList[num];
            } else {
                return nodeList[num] = new Node(num);
            }
        }

        public Pair ans() {
            Pair ret = new Pair();
            ret.min = 1;
            ret.max = 1;

            for (int i = 2; i <= 10000; i++) {
                Pair p = maxAndMinOfRowList.get(i);
                if (p.min == 0 && p.max == 0) continue;
                if (ret.max < p.max - p.min + 1) {
                    ret.max = p.max - p.min + 1;
                    ret.min = i;
                }
            }
            return ret;
        }
    }

    static class Pair {
        int min, max;

        @Override
        public String toString() {
            return "Pair{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }


    static class Node {
        Node right, left, parent;
        int row, col, num;

        public Node() {
        }

        public Node(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", row=" + row +
                    ", col=" + col +
                    ", num=" + num +
                    '}';
        }
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            tree.addNode(n, l, r);
        }
    }
}
