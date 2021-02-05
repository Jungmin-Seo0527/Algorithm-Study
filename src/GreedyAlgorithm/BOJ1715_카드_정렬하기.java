package GreedyAlgorithm;

import java.io.*;
import java.util.*;

// BOJ1715_카드_정렬하기
/*
    처음 이 문제를 접할때 그냥 오름차순으로 정렬후에 더해주면 된다고 생각했으나 예외가 있다.
    모든 카드의 묶음이 같은 경우이다.
    예) 1 1 1 1 이라고 가정하면 그냥 정렬후 앞에서 부터 정렬한다고 하면
    (1+1)+(2+1)+(3+1) = 9 이다. 반면 첫번째 두번째 묶음을 묶고, 세번째 네번째 묶음을 묶고 생긴 두 묶음을 묶는 경우에
    (1+1)+(1+1)+(2+2) = 8 이다.

    방법은 두 묶음을 더해준 후에 그 묶음을 포함한 다른 묶음들중 최소값 두개를 뽑아서 묶어주는 것이다.
    즉 카드를 묶었다면 그 묶음은 새로운 묶음으로 다시 정렬해야 하는 것이다.
    여기까지 왔다면 우선순위 큐가 필요함을 느끼게 된다.
    우선순위큐에서 두개를 뽑아 더해주고 그 값을 다시 큐에 넣어준다.
    주의할점은 우선순위 큐가 비는 경우는 없다. 마지막 단계는 큐에 단 하나의 숫자만 있는 경우
    즉 카드가 한묶음으로 묶일때 반복문을 빠져나와야 한다.
 */
public class BOJ1715_카드_정렬하기 {
    static int N;
    static PriorityQueue<Long> que = new PriorityQueue<Long>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        long sum = 0;
        while (que.size() > 1) {
            long n1 = que.poll();
            long n2 = que.poll();
            sum += n1 + n2;
            que.add(n1 + n2);
        }
        System.out.println(sum);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            que.add(Long.parseLong(br.readLine()));
        }
    }
}

