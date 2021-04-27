package kakao.recruit2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 길_찾기_게임 {
    private int[][] nodeInfo;

    public int[][] solution(int[][] nodeinfo) {
        init(nodeinfo);
        return ans();
    } // 길_찾기_게임.md

    private int[][] ans() {
        int[][] ret = new int[2][nodeInfo.length];
        Node[] nodes = new Node[nodeInfo.length];
        BinaryTree binaryTree = new BinaryTree();

        for (int i = 0; i < nodeInfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeInfo[i][0], nodeInfo[i][1]);
        }
        Arrays.sort(nodes);
        for (Node node : nodes) {
            binaryTree.addNode(node);
        }
        binaryTree.preOrderSearch(binaryTree.root);
        binaryTree.postOrderSearch(binaryTree.root);
        for (int i = 0; i < nodeInfo.length; i++) {
            ret[0][i] = binaryTree.preOrderList.get(i);
            ret[1][i] = binaryTree.postOrderList.get(i);
        }

        return ret;
    }

    private void init(int[][] nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    private static class BinaryTree {
        Node root;
        List<Integer> preOrderList = new ArrayList<>();
        List<Integer> postOrderList = new ArrayList<>();

        public void addNode(Node newNode) {
            if (root == null) {
                root = newNode;
            } else {
                Node head = root;
                Node cur;
                while (true) {
                    cur = head;
                    if (cur.x > newNode.x) {
                        head = head.left;
                        if (head == null) {
                            cur.left = newNode;
                            break;
                        }
                    } else {
                        head = head.right;
                        if (head == null) {
                            cur.right = newNode;
                            break;
                        }
                    }
                }
            }
        }

        public void preOrderSearch(Node cur) {
            if (cur == null) return;
            preOrderList.add(cur.nodeNum);
            preOrderSearch(cur.left);
            preOrderSearch(cur.right);
        }

        public void postOrderSearch(Node cur) {
            if (cur == null) return;
            postOrderSearch(cur.left);
            postOrderSearch(cur.right);
            postOrderList.add(cur.nodeNum);
        }
    }

    private static class Node implements Comparable<Node> {
        int nodeNum, x, y;
        Node left, right;

        public Node(int nodeNum, int x, int y) {
            this.nodeNum = nodeNum;
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node o) {
            if (this.y != o.y) return o.y - this.y;
            else return this.x - o.x;
        }
    }
}

