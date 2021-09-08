package kakao.internship2021;

import java.util.Stack;

public class 표_편집 {
    public String solution(int n, int k, String[] cmd) {
        DLL dll = new DLL(n, k);
        for (int i = 0; i < cmd.length; i++) {
            dll.command(cmd[i]);
        }
        return dll.ans();
    }

    private static class DLL {
        Node first, end, cur;
        Stack<Node> stack = new Stack<>();
        int n;

        public DLL(int n, int k) {
            this.n = n;
            for (int i = 0; i < n; i++) {
                insert(new Node(i));
            }
            cur = first;
            moveDownCur(k);
        }

        public void command(String cmd) {
            char c = cmd.charAt(0);
            if (c == 'U') {
                int n = Integer.parseInt(cmd.substring(2));
                moveUpCur(n);
            } else if (c == 'D') {
                int n = Integer.parseInt(cmd.substring(2));
                moveDownCur(n);
            } else if (c == 'C') {
                remove();
            } else if (c == 'Z') {
                restroe();
            }
        }

        public void insert(Node n) {
            if (isEmpty()) {
                first = n;
                end = n;
            } else {
                end.next = n;
                n.prev = end;
                end = end.next;
            }
        }

        public void moveUpCur(int n) {
            while (n-- > 0 && cur.hasPrev()) {
                cur = cur.prev;
            }
        }

        public void moveDownCur(int n) {
            while (n-- > 0 && cur.hasNext()) {
                cur = cur.next;
            }
        }

        public void remove() {
            stack.push(cur);
            cur.remove();

            if (cur == first) first = first.next;
            if (cur == end) end = end.prev;
            cur = cur.hasNext() ? cur.next : cur.prev;
        }

        public void restroe() {
            if (stack.isEmpty()) return;
            Node pop = stack.pop();
            pop.restore();

            if (first.hasPrev()) first = first.prev;
            if (end.hasNext()) end = end.next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public String ans() {
            Node temp = first;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (temp != null && temp.n == i) {
                    sb.append("O");
                    temp = temp.next;
                } else {
                    sb.append("X");
                }
            }
            return sb.toString();
        }

        public String checkAll() {
            StringBuilder sb = new StringBuilder();
            Node temp = first;
            while (temp != null) {
                sb.append(temp.n).append(" ");
                temp = temp.next;
            }
            return sb.toString();
        }
    }


    private static class Node {
        int n;
        Node prev, next;

        public Node(int n) {
            this.n = n;
        }

        public void remove() {
            if (hasPrev()) prev.next = hasNext() ? next : null;
            if (hasNext()) next.prev = hasPrev() ? prev : null;
        }

        public void restore() {
            if (hasPrev()) prev.next = this;
            if (hasNext()) next.prev = this;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public boolean hasPrev() {
            return this.prev != null;
        }
    }
}