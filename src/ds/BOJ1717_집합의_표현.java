package ds;

import java.io.*;
import java.util.*;

public class BOJ1717_집합의_표현 {
    static int[] par, rank;
    static int n, m;

    static String solve(int op, int a, int b) {
        if (op == 0) {
            union(a, b);
        } else {
            int af = find(a);
            int bf = find(b);
            return af == bf ? "yes" : "no";
        }
        return "";
    }

    static int find(int u) {
        return u == par[u] ? u : (par[u] = find(par[u]));
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        par[u] = v;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        par = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            par[i] = i;
            rank[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            String s = solve(op, a, b);
            if (op == 1) {
                out.append(s).append("\n");
            }
        }
        System.out.println(out);
    }
}
