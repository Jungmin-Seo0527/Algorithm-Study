/*
    BOJ14395_4연산
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/14395

    난이도 : G5
    --------------------------------------------------------------------------------------------------------------------
    풀이

    Set 으로 중복 처리를 하는 방법에 맛들려서 이 문제도 set으로 중복처리를 해서 쉽게 풀어내었다.
    그리고 일차원 배열로도 풀어보았다.
    사실 처음 문제를 보면 입력의 최대값이 1e9 이고 연산에서 +, * 가 있으므로 당연히 long 자료형을 사용해야 하고, 그러면
    결과값으로 나올수 있는 모든 값을 배열로 만들수 없다. (배열은 Integer.MAX_VALUE 만큼 만들수 있기 때문에...) 그래서
    처음에 Set을 이용했던 것이다. 하지만 잘 살펴보면 연산의 결과값이 1e9보다 큰 값은 버려도 된다. 이후 그 결과값이 더
    작아지는 경우는 0과 1 밖에 없기 때문이다. (s -= s, s /= s) 이를 이용해서 일차원 배열을 1e9+1 만큼 만들어서 중복을
    처리해 보았다. 당연히 문제는 통과했으나 처리 시간에서 많은 차이를 나타내었다.

    Set으로 풀었을때 처리시간은 80ms, 읿차원 배열로 풀어낼때는 620ms 가 나왔다. 솔직히 메모리는 당연히 일차원 배열이 훨씬
    많이 차지하겠지만 처리 속도는 더 빠를것이라고 생각했다. 하지만 set이 일차원 배열보다 5배는 빨랐다.
    이유는 솔직히 잘 모르겠다. 일차원 배열에서 중복 여부 확인은 O(1) 일텐데... 자료형 캐스팅에서 시간이 많이 먹나?
    --------------------------------------------------------------------------------------------------------------------
 */
package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ14395_4연산 {

    static class State {
        long num;
        String op;

        public State(long num, String op) {
            this.num = num;
            this.op = new String(op);
        }
    }

    static long S, T;
    static final long max = (long) 1e9 + 1;
    static char[] op = {'*', '+', '-', '/'};

    static Set<Long> set = new HashSet<>();
    static Queue<State> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        if (S == T) {
            System.out.println(0);
            return;
        }

        while (!que.isEmpty()) {
            State cur = que.poll();
            if (cur.num == T) {
                System.out.println(cur.op);
                return;
            }
            for (int i = 0; i < 4; i++) {
                long nextNum = cur.num;
                if (op[i] == '*') nextNum *= nextNum;
                if (op[i] == '+') nextNum += nextNum;
                if (op[i] == '-') nextNum -= nextNum;
                if (op[i] == '/' && nextNum > 0) nextNum /= nextNum;
                if (nextNum > max) continue;
                if (set.add(nextNum)) {
                    que.add(new State(nextNum, cur.op + op[i]));
                }
            }
        }
        System.out.println(-1);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());
        State start = new State(S, "");
        que.add(start);
        set.add(S);
    }
}
