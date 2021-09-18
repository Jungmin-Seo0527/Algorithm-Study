package disjointSet;

import java.io.*;
import java.util.*;

public class BOJ1043_거짓말 {

    static int N, M, liarCnt;
    static boolean[] liar;
    static int[] parent;
    static List<int[]> party;

    static void solve() {
        disjoint();
        System.out.println(getAns());
    }

    private static int getAns() {
        int ret = 0;
        for (int i = 0; i < M; i++) {
            int[] list = party.get(i);
            boolean l = false;
            for (int j = 0; j < list.length; j++) {
                if (liar[find(list[j])]) {
                    l = true;
                    break;
                }
            }
            if (!l) ret++;
        }
        return ret;
    }

    private static void disjoint() {
        for (int i = 0; i < M; i++) {
            int[] list = party.get(i);
            for (int j = 0; j < list.length - 1; j++) {
                if (find(list[j]) != find(list[j + 1])) {
                    union(list[j], list[j + 1]);
                }
            }
        }
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (liar[a] && !liar[b]) {
            parent[b] = a;
        } else if (!liar[a] && liar[b]) {
            parent[a] = b;
        } else if ((liar[a] && liar[b]) || (!liar[a] && !liar[b])) {
            if (a > b) parent[a] = b;
            else parent[b] = a;
        }
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input0.txt";
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
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        liarCnt = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        liar = new boolean[N + 1];
        party = new ArrayList<>(N);

        for (int i = 0; i < liarCnt; i++) {
            int l = Integer.parseInt(st.nextToken());
            liar[l] = true;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            party.add(new int[n]);
            for (int j = 0; j < n; j++) {
                int people = Integer.parseInt(st.nextToken());
                party.get(i)[j] = people;
            }
        }
        solve();
    }
}
