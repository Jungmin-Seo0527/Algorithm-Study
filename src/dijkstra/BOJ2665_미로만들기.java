package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ2665_미로만들기 {
    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = dijkstra();
        System.out.println(ans);
    }

    static int dijkstra() {
        int ret = 0;
        boolean[][] visited = new boolean[N][N];
        int[][] dist = new int[N][N];
        int[] vr = {1, -1, 0, 0};
        int[] vc = {0, 0, 1, -1};
        // PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        Deque<Node> que = new LinkedList<>();
        int w = map[0][0] - '0';
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = w;
        que.add(new Node(0, 0, w));
        while (!que.isEmpty()) {
            Node cur = que.pollFirst();
            // System.out.println(cur.toString());
            if (visited[cur.row][cur.col]) continue;
            visited[cur.row][cur.col] = true;
            if (cur.row == N - 1 && cur.col == N - 1) {
                ret = cur.weight;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.row + vr[i];
                int nc = cur.col + vc[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N &&
                        !visited[nr][nc] && dist[nr][nc] > dist[cur.row][cur.col] + (map[nr][nc] - '0')) {
                    int nw = map[nr][nc] - '0';
                    dist[nr][nc] = dist[cur.row][cur.col] + nw;
                    if (nw == 0) {
                        que.addFirst(new Node(nr, nc, dist[nr][nc]));
                    } else {
                        que.addLast(new Node(nr, nc, dist[nr][nc]));
                    }
                }
            }
        }
        return ret;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                if (c == '1') {
                    map[i][j] = '0';
                } else {
                    map[i][j] = '1';
                }
            }
        }
    }

    static class Node {
        int row, col, weight;

        public Node(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    ", weight=" + weight +
                    '}';
        }
    }

}
