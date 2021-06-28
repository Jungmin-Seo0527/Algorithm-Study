package codeforces.R728_D2;

import java.io.*;
import java.util.*;

public class A_Pretty_Permutation {

    static StringBuilder sb;
    static boolean end;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve(int N) {
        for (int i = 2; i <= N; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            doDFS(list, N, visited);
        }
    }

    static void doDFS(List<Integer> list, int N, boolean[] visited) {
        if (end) {
            return;
        }
        if (list.size() == N) {
            for (Integer integer : list) {
                sb.append(integer).append(" ");
            }
            end = true;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (list.size() != i - 1 && !visited[i]) {
                visited[i] = true;
                List<Integer> nextList = new ArrayList<>(list);
                nextList.add(i);
                doDFS(nextList, N, visited);
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            end = false;
            sb = new StringBuilder();
            solve(N);
            System.out.println(sb);
        }
    }
}
