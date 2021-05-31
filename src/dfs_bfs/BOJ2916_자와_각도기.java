package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2916_자와_각도기 {
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static boolean solve(int angle) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[361];
        for (int i = 0; i < N; i++) {
            que.add(arr[i]);
            visited[arr[i]] = true;
        }
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == angle) {
                return true;
            }
            for (int i = 0; i < N; i++) {
                for (int j = -1; j <= 1; j += 2) {
                    int next = cur + arr[i] * j;
                    if (next > 360) {
                        next -= 360;
                    } else if (next < 0) {
                        next += 360;
                    }
                    if (!visited[next]) {
                        visited[next] = true;
                        que.add(next);
                    }
                }
            }
        }
        return false;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int angle = Integer.parseInt(st.nextToken());
            if (solve(angle)) {
                ans.append("YES");
            } else {
                ans.append("NO");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
