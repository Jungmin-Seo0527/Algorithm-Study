package greedy;

import java.io.*;
import java.util.*;

public class BOJ1202_보석_도둑 {
    static class Dia implements Comparable<Dia> {
        int weight, price;

        public Dia(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Dia o) {
            return this.weight - o.weight;
        }
    }

    static int N, K;
    static int[] bag;
    static Dia[] dia;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        long ans = 0;
        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && dia[j].weight <= bag[i]) {
                que.add(dia[j++].price);
            }
            if (!que.isEmpty()) ans += (long) que.poll();
        }
        System.out.println(ans);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dia = new Dia[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            dia[i] = new Dia(w, p);
        }

        bag = new int[K];
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);
        Arrays.sort(dia);
    }
}

