/*
    BOJ5014_스타트링크
    --------------------------------------------------------------------------------------------------------------------
    문제링크
    https://www.acmicpc.net/problem/5014
    --------------------------------------------------------------------------------------------------------------------
    풀이

    간단한 그래프 탐색 문제
    일차원 그래프의 완전 탐색 문제로 BFS를 이용하여 최단 거리를 구하는 문제
    --------------------------------------------------------------------------------------------------------------------
 */
package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ5014_스타트링크 {
    static int F, S, G, U, D;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        doBFS();
    }

    static void doBFS() {
        int ans = 0;
        int[] dir = new int[2];
        dir[0] = U;
        dir[1] = -D;

        Queue<Integer> que = new LinkedList<>();
        que.add(S);
        visited[S] = 1;
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == G) break;
            for (int i = 0; i < 2; i++) {
                int nr = cur + dir[i];
                if (nr > 0 && nr <= F && visited[nr] == 0) {
                    visited[nr] = visited[cur] + 1;
                    que.add(nr);
                }
            }
        }

        if (visited[G] == 0) System.out.println("use the stairs");
        else System.out.println(visited[G] - 1);
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        visited = new int[F + 1];
    }
}
