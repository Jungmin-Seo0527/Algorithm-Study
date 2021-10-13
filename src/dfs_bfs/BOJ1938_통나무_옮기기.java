package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ1938_통나무_옮기기 {

    static int N;
    static char[][] map;

    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static int[] rvr = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] rvc = {1, 0, -1, 1, -1, 1, 0, -1};


    static void solve() {
        Tree start = findTree('B');
        Tree end = findTree('E');
        Queue<Tree> que = new LinkedList<>();
        Set<Tree> set = new HashSet<>();
        int ans = 0;
        que.add(start);
        set.add(start);

        while (!que.isEmpty()) {
            Tree cur = que.poll();
            if (cur.equals(end)) {
                ans = cur.cnt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                Tree next = cur.move(vr[i], vc[i]);
                if (next.checkPossibleMoving(N) && !set.contains(next)
                        && checkExistTreeInMap(next)) {
                    set.add(next);
                    que.add(next);
                }
            }

            Tree rotateTree = cur.rotate();
            boolean possible = true;
            loop:
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    int nr = rotateTree.middle.r + rvr[r];
                    int nc = rotateTree.middle.c + rvc[c];
                    if (!checkBoundary(nr, nc) || map[nr][nc] == '1') {
                        possible = false;
                        break loop;
                    }
                }
            }
            if (possible && !set.contains(rotateTree)) {
                set.add(rotateTree);
                que.add(rotateTree);
            }
        }
        System.out.println(ans);
    }

    static Tree findTree(char c) {
        List<Point> list = new ArrayList<>(3);
        loop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == c) {
                    list.add(new Point(i, j));
                }
                if (list.size() == 3) {
                    break loop;
                }
            }
        }
        return new Tree(list.get(0), list.get(1), list.get(2));
    }

    static boolean checkExistTreeInMap(Tree tree) {
        return map[tree.side1.r][tree.side1.c] != '1'
                && map[tree.side2.r][tree.side2.c] != '1'
                && map[tree.middle.r][tree.middle.c] != '1';
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static class Tree {
        Point side1, middle, side2;
        int cnt;

        public Tree(Point side1, Point middle, Point side2) {
            this.side1 = side1;
            this.middle = middle;
            this.side2 = side2;
        }

        public Tree(Point side1, Point middle, Point side2, int cnt) {
            this.side1 = side1;
            this.middle = middle;
            this.side2 = side2;
            this.cnt = cnt;
        }

        public Tree move(int vr, int vc) {
            return new Tree(side1.move(vr, vc), middle.move(vr, vc), side2.move(vr, vc), cnt + 1);
        }

        public boolean checkPossibleMoving(int N) {
            return side1.checkBoundary(N) && middle.checkBoundary(N) && side2.checkBoundary(N);
        }

        public Tree rotate() {
            if (middle.r == side1.r && middle.r == side2.r) {
                return new Tree(new Point(middle.r - 1, middle.c),
                        new Point(middle.r, middle.c),
                        new Point(middle.r + 1, middle.c), cnt + 1);
            } else {
                return new Tree(new Point(middle.r, middle.c - 1),
                        new Point(middle.r, middle.c),
                        new Point(middle.r, middle.c + 1), cnt + 1);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tree tree = (Tree) o;
            return (middle.equals(tree.middle)) &&
                    ((side1.equals(tree.side1) && side2.equals(tree.side2)) || (side1.equals(tree.side2) && side2.equals(tree.side1)));
        }

        @Override
        public int hashCode() {
            return middle.hashCode() * 31 + side1.hashCode() + side2.hashCode();
        }

        @Override public String toString() {
            return "Tree{" +
                    "side1=" + side1 +
                    ", middle=" + middle +
                    ", side2=" + side2 +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point move(int vr, int vc) {
            return new Point(r + vr, c + vc);
        }

        public boolean checkBoundary(int N) {
            return r >= 0 && r < N && c >= 0 && c < N;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
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
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        solve();
    }
}
