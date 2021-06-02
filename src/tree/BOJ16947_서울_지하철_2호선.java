package tree;

import java.io.*;
import java.util.*;

public class BOJ16947_서울_지하철_2호선 {
    static int N;
    static int[] ans;
    static boolean[] cycleStations;
    static List<List<Integer>> adjList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        boolean[] visited = new boolean[N + 1];
        doDFS(1, 1, visited);
        calDistFromRoot();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < ans.length; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    static void calDistFromRoot() {
        for (int i = 1; i <= N; i++) {
            if (cycleStations[i] && adjList.get(i).size() > 2) {
                doDFSForDist(i);
            }
        }
    }

    static void doDFSForDist(int cur) {
        for (int i = 0; i < adjList.get(cur).size(); i++) {
            int next = adjList.get(cur).get(i);
            if (!cycleStations[next] && ans[next] == 0) {
                ans[next] = ans[cur] + 1;
                doDFSForDist(next);
            }
        }
    }

    static boolean doDFS(int cur, int prev, boolean[] visited) {
        if (visited[cur]) return true;
        visited[cur] = true;
        for (Integer next : adjList.get(cur)) {
            if (next != prev && doDFS(next, cur, visited)) {
                if (cycleStations[next]) return false;
                return cycleStations[next] = true;
            }
        }
        return false;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        cycleStations = new boolean[N + 1];
        ans = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList.get(A).add(B);
            adjList.get(B).add(A);
        }
    }
}
