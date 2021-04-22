package greedy;

import java.io.*;
import java.util.*;

// BOJ2109_순회공연
/*
    반성해야 한다.
    보석 도둑 문제와 거의 유사한 문제였는데 풀어내지 못했다.
    분명 보석 도둑문제와 유사하다는 것 까지 도달했는데 역순으로 진행해야 된다는 사실에 도달하지 못했다.

    보석 도둑 문제는 담을수 있는 가방이 주어졌었다. 그래서 가방를 오름차순으로 정렬후에 그 가방에 담을수
    있는 가장 가치가 있는 보석을 찾아가는 그리디 문제였다.

    이 문제는 가방의 무게 대신 날짜가 주어진다. 해당 날짜 이전에 강의를 해야 하는 것이다.
    그렇게 강의를 했을때 최대 이익을 구하는 문제이다.

    보석 도둑 문제도 가방을 기준으로 하였다. 해당 가방에 담을수 있는 보석은 가방보다 가벼운 보석들중 가치가 가장 큰 보석이다.
    순회 공연 문제에서는 해당 날짜에 강의할수 있는 가장 비싼 강의를 해야 한다. 단 주어진 날짜 이전에만 강의를 하면 된다.
    즉 5일이내이면 1일, 2일 3일 4일 5일중에 선택하면 된다.
    날짜를 기준으로 강의를 바라보면 현재 날짜가 만약 5일이면 데드라인이 5일 이상인 수업중에 가장 가치가 큰 수업을 고르면 된다.
    보석문제는 가방 무게 이하의 보석을 골라야 하지만 이 문제는 현재 날짜 이상의 데드라인 중에 골라야 한다.
    그러므로 날짜를 1부터 시작하는 것이 아닌 최대값인 10000부터 시작해야 하는 것이다.
    그리고 당연히 강의도 날짜순으로 내림차순으로 정렬해야 한다.

    또하나 주의할것이 있다. Comparable에서 compareTo 부분인데
    -1, 1만으로 반환하지 말고 기준이 되는 변수를 서로 빼주는 것으로 반환하는것이 좋다.
    -1, 1만으로 반환을 하니 런타임 에러(IllegalArgument)가 발생한다.

 */
public class BOJ2109_순회공연 {
    static class Lecture implements Comparable<Lecture> {
        int day, price;

        public Lecture(int p, int d) {
            this.price = p;
            this.day = d;
        }

        @Override
        public int compareTo(Lecture o) {
            return o.day - this.day;
        }
    }

    static int N;
    static Lecture[] lec;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = (int) 1e4, j = 0; i > 0; i--) {
            while (j < N && i <= lec[j].day) que.add(lec[j++].price);
            if (!que.isEmpty()) ans += que.poll();
        }
        System.out.println(ans);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        lec = new Lecture[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            lec[i] = new Lecture(p, d);
        }
        Arrays.sort(lec);
    }
}

