/*
    BOJ12886_돌_그룹
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/12886

    난이도 : G5
    --------------------------------------------------------------------------------------------------------------------
    풀이

    이 문제는 두가지 방법으로 풀수 있다. 정확히는 중복 처리를 두가지 방법으로 풀어낼 수 있다.
    하나는 해쉬set을 이용한 방법이다. 새로운 하노이 탑 문제와 이 문제는 굉장히 비슷하다. 3개의 지점에 있는 물건을 서로
    옮기는 문제이다. 따라서 현재 상태를 새로운 하노이 탑 문제와 동일하게 hashcode를 생성해서 set에 저장을 했다.

    두번재 방법은 3개의 지점의 돌의 총합은 항상 일정하다는 점을 이용한다.
    3개의 지점에는 최대 1500개의 돌이 들어있다. 그래서 처음에는 visited[1501][1501][1501]로 중복을 처리하려고 했다.
    하지만 메모리 초과가 났다. 생각해보면 첫번째, 두번째 그룹의 돌의 갯수를 알고 있으면 세번째 그룹의 돌의 갯수는 자동
    적으로 정해진다. 따라서 visited[1501][1501]만으로도 세 그룹의 상태 중복을 확인할 수 있다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ12886_돌_그룹 {
    static class State {
        int[] arr = new int[3];

        public State(int[] arr) {
            System.arraycopy(arr, 0, this.arr, 0, 3);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.equals(arr, state.arr);
        }

        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(arr[i]).append(" ");
            }
            return Objects.hash(sb.toString());
        }
    }

    // static Set<State> set = new HashSet<>();
    static Queue<State> que = new LinkedList<>();
    static boolean[][] visited = new boolean[1501][1501];

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        while (!que.isEmpty()) {
            State cur = que.poll();
            if (cur.arr[0] == cur.arr[1] && cur.arr[1] == cur.arr[2]) {
                ans = 1;
                break;
            }
            move(cur);
        }
        System.out.println(ans);
    }

    static void move(State cur) {
        int[] temp = new int[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                if (cur.arr[i] == cur.arr[j]) continue;
                if (cur.arr[i] > cur.arr[j]) continue;
                System.arraycopy(cur.arr, 0, temp, 0, 3);
                temp[j] -= temp[i];
                temp[i] += temp[i];
                if (!visited[temp[0]][temp[1]]) {
                    visited[temp[0]][temp[1]] = true;
                    que.add(new State(temp));
                }
                // if (set.add(next)) que.add(next);

            }
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] input = new int[3];
        for (int i = 0; i < 3; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        State start = new State(input);
        que.add(start);
        visited[start.arr[0]][start.arr[1]] = true;
        // set.add(start);
    }
}
