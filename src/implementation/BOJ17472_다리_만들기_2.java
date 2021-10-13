package implementation;

import java.io.*;
import java.util.*;

public class BOJ17472_다리_만들기_2 {

    static int rowSZ, colSZ;
    static int[][] map;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static List<List<Point>> islandBoundaryList = new ArrayList<>();
    static List<List<Node>> adjList;

    static void solve() {
        groupingIsland();

        adjList = new ArrayList<>(islandBoundaryList.size());
        for (int i = 0; i < islandBoundaryList.size(); i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < islandBoundaryList.size(); i++) {
            installBridege(i);
        }
        System.out.println(mst());
    }

    static int mst() {
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
        boolean[] visited = new boolean[islandBoundaryList.size()];
        int ret = 0;
        que.add(new Node(1, 0));
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.v - 1]) continue;
            visited[cur.v - 1] = true;
            ret += cur.w;
            for (int i = 0; i < adjList.get(cur.v - 1).size(); i++) {
                Node next = adjList.get(cur.v - 1).get(i);
                if (!visited[next.v - 1]) que.add(next);
            }
        }
        for (int i = 0; i < islandBoundaryList.size(); i++) {
            if (!visited[i]) ret = -1;
        }
        return ret;
    }

    static void installBridege(int islandNum) {
        List<Integer> list = new ArrayList<>(islandBoundaryList.size());
        for (int i = 0; i < islandBoundaryList.size(); i++) {
            list.add(Integer.MAX_VALUE);
        }
        for (int ii = 0; ii < islandBoundaryList.get(islandNum).size(); ii++) {
            Point cur = islandBoundaryList.get(islandNum).get(ii);

            for (int i = 0; i < 4; i++) {
                int dist = 0;
                int findIslandNum = -1;
                while (true) {
                    int nr = cur.r + vr[i] * (dist + 1);
                    int nc = cur.c + vc[i] * (dist + 1);
                    if (!checkBoundary(nr, nc)) {
                        break;
                    }
                    if (map[nr][nc] != 0) {
                        findIslandNum = map[nr][nc];
                        break;
                    }
                    dist++;
                }
                if (dist > 1 && findIslandNum != -1 && list.get(findIslandNum - 1) > dist) {
                    list.set(findIslandNum - 1, dist);
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != Integer.MAX_VALUE) {
                adjList.get(islandNum).add(new Node(i + 1, list.get(i)));
            }
        }
    }

    static void groupingIsland() {
        int groupNum = 1;
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == -1) {
                    islandBoundaryList.add(groupBfs(groupNum++, i, j));
                }
            }
        }
    }

    static List<Point> groupBfs(int groupNum, int sr, int sc) {
        Queue<Point> que = new LinkedList<>();
        List<Point> retList = new ArrayList<>();
        boolean[][] retVisited = new boolean[rowSZ][colSZ];
        map[sr][sc] = groupNum;
        que.add(new Point(sr, sc));

        while (!que.isEmpty()) {
            Point cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc)) {
                    if (map[nr][nc] == -1) {
                        que.add(new Point(nr, nc));
                        map[nr][nc] = groupNum;
                    } else if (map[nr][nc] == 0 && !retVisited[cur.r][cur.c]) {
                        retVisited[cur.r][cur.c] = true;
                        retList.add(new Point(cur.r, cur.c));
                    }
                }
            }
        }
        return retList;
    }

    static void showArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
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
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new int[rowSZ][colSZ];
        for (int i = 0; i < rowSZ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) map[i][j] = -1;
            }
        }
        solve();
    }
}
