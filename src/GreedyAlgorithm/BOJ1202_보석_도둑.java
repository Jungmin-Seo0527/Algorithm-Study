package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ1202_보석_도둑
/*
    처음 생각해낸 방법은 lower_bound를 이용한 풀이였다.
    보석의 가격을 기준으로 내림차순, 가격이 같으면 무게를 기준으로 오름차순으로 우선순위큐에 저장을 한다.
    입력받은 가방들을 오름차순으로 정렬한다.
    queue.poll() 을 담을수 있는 가장 작은 가방 즉 lower_bound를 구한다.
    뽑힌 가방은 remove 하고 보석의 가격은 계속 더해준다.
    가방을 모두 사용 혹은 queue가 빌때까지 진행한다.
    실제 C++에서는 이 방법으로 시간초과에 걸리지 않는다고 한다. 단 lower_bound호출 방법을 다르게 해야된다는데
    C++에 대해 잘 모르니 패스

    하지만 나는 시간초과가 걸린다. 이유는 remove 가 시간복잡도 O(n)을 먹는다고 한다.
    하지만 같은 방법의 C++은 통과이다. vector.erase를 남발해도 말이다.

    두번째 방법은 보석을 기준으로 담을수 있는 가방의 최소값을 구하는것(위의 방법)이 아닌
    가방을 기준으로 담을수 있는 최대 가격의 보석을 구하는 방식으로 구현하는 방법이다.

    우선 가방과 보석을 오름차순으로 정렬한다(보석은 보석의 무게를 기준으로)
    가방을 처음부터 하나씩 꺼내어 담을수 없는 보석을 만날때까지 보석들을 pq에 담는다. (당연히 pq도 가격이 큰것을 우선순위로 한다)
    담을수 없는 보석을 만났을때 반복문을 빠져 나와 pq가 비어있지 않으면 poll() 을 진행한다.
    poll()한 보석이 현재 가방이 담을수 있는 가장 높은 가격의 보석이다.
    그리고 다음 가방을 진행할때 보석 탐색 인덱스를 이전 반복문에서 빠져나온 인덱스에서 부터 시작한다.
    어짜피 무게를 기준으로 오름차순으로 정렬되어 있으니 이전 가방이 담을수 있던 보석은 현재 가방이 모두 담을 수 있다.
    따라서 이전 가방에서 탐색한 보석의 인덱스는 그대로 유지, 그 인덱스부터 보석을 탐색하면 시간의 많이 줄일수 있다.

    다른 사람들의 풀이를 보니 이 방법이 가장 대중적인?! 방법인것 같다.
    lower_bound로 풀어내지 못한 점은 너무 아쉽다.
 */
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
    static long ans;
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

