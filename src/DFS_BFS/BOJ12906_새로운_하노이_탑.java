/*
    BOJ12906_새로운_하노이_탑
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/12906

    난이도 : G3
    --------------------------------------------------------------------------------------------------------------------
    풀이

    중복 여부를 어떻게 판단하는가가 이 문제를 푸는 키 포인트이다.
    처음에는 A 를 1, B를 2, C를 3, 비어있으면 0으로 하여 현재 상태를 정수로 표현해 보려고 했다. 그런데 탑 하나
    에 최대 10개까지 원판이 들어갈 수 있다. 만약 C의 쟁반이 10개 모두 하나의 원판에 있다면 현재 상태는
    3333333333 이 되므로 배열을 만들수 있는 최대 범위를 넘어서버린다.
    평범한 배열로는 중복 여부를 기록할 수 없기에 hashSet을 이용하였다. set 은 중복을 허용하지 않고 자료를 저장
    할 수 있다.

    우선 Set을 이용하는 방법을 소개한다.
    이 문제에서는 3개의 원판의 상태와 쟁반 이동 횟수를 포함한 클래스를 Set에 저장했다.
    클래스를 Set에 저장하려면 2개의 메소드를 Override 해야 한다.
    1. equals()
    2. hashCode()

    equals() 메소드는 두개의 클래스가 같은지 다른지에 대한 판별이다. 중복을 허용하지 않기 때문에 필요한 메소드
    이다. 각 원판의 상태를 비교하면 된다.

    hashCode() 는 만약 equals() 메소드에서 false 가 반환되었다면 hashcode를 생성해서 부여하는 메소드이다.
    나는 3개의 원판의 상태를 하나의 문자열로 만들고 각 원판 마다 공백으로 구분하였다. 그 문자열을 통채로
    Objects.hash() 메소드에 전달하여 hashcode를 생성하였다.

    이 단계까지 왔다면 Set으로 3개의 원판의 상태를 기록하여 중복을 판별이 가능하다.
    이후에는 Queue를 이용한 BFS를 이용해서 완전 탐색을 진행하면 된다.

    완전 탐색 문제에서 중복 여부를 해시를 사용한 집합 혹은 맵을 사용해서 풀어내는 문제였다.
    이전에도 Set을 이용해서 중복을 판별한 적이 있었던 것으로 기억하는에 이번 기회에 재대로 Set 사용법과 문제
    풀이법을 정리 할 수 있었다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ12906_새로운_하노이_탑 {
    static class State {
        String[] top;
        int cnt;

        public State(String[] top, int cnt) {
            this.top = new String[3];
            for (int i = 0; i < 3; i++) {
                this.top[i] = new String(top[i]);
            }
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "State{" +
                    "top=" + Arrays.toString(top) +
                    ", cnt=" + cnt +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            for (int i = 0; i < 3; i++) {
                if (this.top[i].compareTo(state.top[i]) != 0) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(top[i]).append(" ");
            }
            return Objects.hash(sb.toString());
        }
    }

    static Set<State> set = new HashSet<>();
    static Queue<State> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        // inputAndSettingData();
        // solve();
        System.out.println(Long.MAX_VALUE);
    }

    static void solve() {
        while (!que.isEmpty()) {
            State cur = que.poll();
            if (check(cur)) {
                System.out.println(cur.cnt);
                break;
            }
            move(cur);
        }
    }

    static boolean check(State state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < state.top[i].length(); j++) {
                if (state.top[i].charAt(j) != 'A' + i) return false;
            }
        }
        return true;
    }

    static void move(State cur) {
        String[] temp = new String[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                if (cur.top[i].length() == 0) continue;
                for (int k = 0; k < 3; k++) {
                    temp[k] = new String(cur.top[k]);
                }
                char last = temp[i].charAt(temp[i].length() - 1);
                temp[i] = temp[i].substring(0, temp[i].length() - 1);
                temp[j] = temp[j] + last;
                State next = new State(temp, cur.cnt + 1);
                if (set.add(next)) {
                    que.add(next);
                }
            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] input = new String[3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) input[i] = "";
            else input[i] = st.nextToken();
        }
        State start = new State(input, 0);
        que.add(start);
        set.add(start);
    }
}
