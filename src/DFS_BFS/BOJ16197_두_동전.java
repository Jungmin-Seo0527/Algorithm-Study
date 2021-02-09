package DFS_BFS;

import java.io.*;
import java.util.*;

// BOJ16197_두_동전
/*
    N×M 크기의 보드와 4개의 버튼으로 이루어진 게임이 있다. 보드는 1×1크기의 정사각형 칸으로 나누어져 있고,
    각각의 칸은 비어있거나, 벽이다. 두 개의 빈 칸에는 동전이 하나씩 놓여져 있고, 두 동전의 위치는 다르다.

    버튼은 "왼쪽", "오른쪽", "위", "아래"와 같이 4가지가 있다. 버튼을 누르면 두 동전이 버튼에 쓰여 있는 방향으로 동시에 이동하게 된다.

    동전이 이동하려는 칸이 벽이면, 동전은 이동하지 않는다.
    동전이 이동하려는 방향에 칸이 없으면 동전은 보드 바깥으로 떨어진다.
    그 외의 경우에는 이동하려는 방향으로 한 칸 이동한다.이동하려는 칸에 동전이 있는 경우에도 한 칸 이동한다.
    두 동전 중 하나만 보드에서 떨어뜨리기 위해 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.

    -------------------------------------------------------------------
    입력
    첫째 줄에 보드의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 20)

    둘째 줄부터 N개의 줄에는 보드의 상태가 주어진다.

    o: 동전
    .: 빈 칸
    #: 벽
    동전의 개수는 항상 2개이다.

    출력
    첫째 줄에 두 동전 중 하나만 보드에서 떨어뜨리기 위해 눌러야 하는 버튼의 최소 횟수를 출력한다. 만약, 두 동전을 떨어뜨릴 수 없거나, 버튼을 10번보다 많이 눌러야 한다면, -1을 출력한다.
    -------------------------------------------------------------------
    BFS를 이용하여 최단 거리를 구하는 문제
    단 두개의 객체가 움직이고, 벽을 만나면 이동하지 않는다.
    그리고 한개만 범위밖으로 떨어질때의 이동 횟수를 구하는 문제이다.

    즉 visited가 두 동전이 움직인 그 상태에 대한 재방문 여부 조사이다.
    N과 M의 범위가 최대 20으로 작다. 두개의 상태를 2차원 배열로 표현이 가능하다. row * colSZ + col
    각각의 2차원 배열을 1차원 배열로 표현하고 2개의 1차원 배열의 상태를 2차원 배열로 표현하는 것이다.

    그 외에는 조건의 순서만 잘 생각해버 풀어내면 어렵지 않은 문제
 */
public class BOJ16197_두_동전 {
    static class Coin {
        int row, col;

        public Coin(int r, int c) {
            this.row = r;
            this.col = c;
        }

        @Override
        public String toString() {
            return "Coin{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    static class State {
        Coin coin1, coin2;
        int cnt = 0;

        public State(Coin coin1, Coin coin2) {
            this.coin1 = coin1;
            this.coin2 = coin2;
        }
    }


    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    static int R, C;
    static char[][] map;
    static Coin start1, start2;

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        doBFS();
    }

    static void doBFS() {
        Queue<State> que = new LinkedList<>();
        boolean[][] visited = new boolean[R * C][R * C];

        que.add(new State(start1, start2));
        visited[start1.row * C + start2.col][start2.row * C + start2.col] = true;
        while (!que.isEmpty()) {
            State cur = que.poll();
            if (cur.cnt == 10) break;
            // System.out.println(cur.coin1.toString());
            // System.out.println(cur.coin2.toString());
            // System.out.println("//");
            for (int i = 0; i < 4; i++) {
                Coin next1 = new Coin(cur.coin1.row + v_r[i], cur.coin1.col + v_c[i]);
                Coin next2 = new Coin(cur.coin2.row + v_r[i], cur.coin2.col + v_c[i]);
                // System.out.println(i);
                // System.out.println(next1.toString());
                // System.out.println(next2.toString());
                // 1. 범위 검사
                if (checkOut(next1) && checkOut(next2)) continue;
                if ((checkOut(next1) && !checkOut(next2)) || (!checkOut(next1) && checkOut(next2))) {
                    System.out.println(cur.cnt + 1);
                    return;
                }
                // 2. 벽 여부 조사
                if (map[next1.row][next1.col] == '#') next1 = cur.coin1;
                if (map[next2.row][next2.col] == '#') next2 = cur.coin2;

                // 3. 재방문 조사
                if (visited[next1.row * C + next1.col][next2.row * C + next2.col]) continue;
                State next = new State(next1, next2);
                next.cnt = cur.cnt + 1;
                que.add(next);
            }
        }
        System.out.println(-1);
    }

    static boolean checkOut(Coin coin) {
        return coin.row < 0 || coin.row >= R || coin.col < 0 || coin.col >= C;
    }


    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'o') {
                    if (start1 == null) {
                        start1 = new Coin(i, j);
                    } else start2 = new Coin(i, j);
                }
            }
        }
    }
}

