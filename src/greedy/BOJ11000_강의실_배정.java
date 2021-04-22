package greedy;

import java.io.*;
import java.util.*;

// BOJ11000_강의실_배정
/*
    강의들의 시간표가 주어졌을때 강의실의 사용을 최소한으로 만드는 문제이다.
    즉 사용시간이 겹치는 경우에는 새로운 강의실을 할당 받는다.

    처음 문제를 보면 강의실 시간표 문제를 떠올리며 끝나는 시간대로 정렬 후 시간표를 짜게 된다.
    여기서 문제는 한 강의실에서 최대한 많은 강의를 수강할수 있도록 하는 문제가 아닌
    할당받는 강의실을 최소한으로 하는 것이다.

    4
    1 2
    1 4
    2 6
    4 5
    입력이 주어졌다고 보자
    만약 끝나는 시간을 기준으로 오름차순으로 정렬을 하면
    (1, 2) (1, 4) (4, 5) (2, 6) 순으로 정렬이 된다.
    그리고 하나씩 시간표를 짠다. 단 강의실 갯수를 세야하기 때문에 버리지는 강의들은 존재하지 않고
    끝나는 시간을 우선순위큐에 저장을 한다. 즉 한 강의실에서 최대한 길게 강의를 짜야하기 때문에
    강의들이 끝나는 시간을 우선순위 큐에 넣고 끝나는 시간이 짧을 강의일수록 강의를 할당받는 우선순위가 높아진다.
    큐의 변화를 살펴보면
    1) (1, 2) 강의가 강의실에 할당 받는다. que: 2
    2) 강의가 가장 일찍 끝나는 강의실이 2인데 현재 강의는 (1, 4) 이므로 같은 강의실에서 강의 불가능. que: 2, 4
    3) (4, 5) 강의는 4에 강의가 시작하므로 가장 일찍 끝나는 강의실인 2에서 다음 강의로 할당 가능 qud: 4, 5
    4) (2, 6) 강의는 2에 시작을 하는데 que에 있는 모든 강의실에서 할당 불가능 하므로 새로운 강의실 할당. que: 4, 5, 6
    5) 총 3개의 강의실이 할당받게 된다.
    여기서 주목해야 하는 만약 3번째 단계에서 (2, 6) 강의가 먼저 할당 받게 되면 (1, 2) 강의 후에 (2, 6)
    (1, 4) 강의 후에 (4, 5) 강의를 할수 있으므로 총 2개의 강의실이 필요하다.

    강의실을 할당받는 강의들을 시작시간 순서대로 할당을 받는것이 강의실을 최소화 할수 있다.
    우선순위 큐의 입장에서 보면 당연히 끝나는 시간이 빠른시간의 강의실에 우선적으로 강의를 배정하는것이
    강의를 할당할수 있는 확률이 높아진다.
    반면 강의의 입장에서는 시작시간이 빠른 강의가 이미 존재하는 강의들과 이어지는 확률이 높아진다.
    이 부분이 이문제의 최적의 해를 찾는 방법이다.
  */
public class BOJ11000_강의실_배정 {
    static class Lecture implements Comparable<Lecture> {
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.start - o.start;
        }
    }

    static int N;
    static Lecture[] lectures;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (!que.isEmpty() && que.peek() <= lectures[i].start) que.poll();
            que.add(lectures[i].end);
            System.out.println(que.toString());
        }
        System.out.println(que.size());
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(s, e);
        }
        Arrays.sort(lectures);
    }
}

