package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ5214_환승 {

    static int N, K, M;
    static List<Integer>[] connection;

    static void solve() {
        Queue<Integer> que = new LinkedList<>();
        int[] dist = new int[N + M + 1];
        Arrays.fill(dist, -1);
        dist[1] = 1;
        que.add(1);
        while (!que.isEmpty()) {
            Integer cur = que.poll();

            for (int i = 0; i < connection[cur].size(); i++) {
                int next = connection[cur].get(i);
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    que.add(next);
                }
            }
        }
        System.out.println(dist[N] > -1 ? dist[N] /2  + 1 : dist[N]);
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        connection = new List[N + M + 2];
        for (int i = 0; i < N + M + 2; i++) {
            connection[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int cur = Integer.parseInt(st.nextToken());
                connection[cur].add(N + 1 + i);
                connection[N + 1 + i].add(cur);
            }
        }
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input2.txt";
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
