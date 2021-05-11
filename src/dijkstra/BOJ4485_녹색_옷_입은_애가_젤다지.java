package dijkstra;

import java.io.*;
import java.util.*;

public class BOJ4485_녹색_옷_입은_애가_젤다지 {

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static int solve(int N, int[][] map) {
        int ret = 0;
        int[] vr = {1, -1, 0, 0};
        int[] vc = {0, 0, 1, -1};
        boolean[][] visited = new boolean[N][N];
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        que.add(new Node(0, 0, map[0][0]));
        dist[0][0] = map[0][0];
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (visited[cur.row][cur.col]) continue;
            visited[cur.row][cur.col] = true;
            if (cur.row == N - 1 && cur.col == N - 1) {
                ret = cur.weight;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.row + vr[i];
                int nc = cur.col + vc[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && dist[nr][nc] > cur.weight + map[nr][nc]) {
                    dist[nr][nc] = cur.weight + map[nr][nc];
                    que.add(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }
        return ret;
    }

    static class Node {
        int row, col, weight;

        public Node(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int num = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if (N == 0) break;
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans.append("Problem ").append(num++).append(": ").append(solve(N, map)).append("\n");
        }
        System.out.println(ans);
    }
}

