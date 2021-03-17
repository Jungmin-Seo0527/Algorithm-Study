/*
    BOJ16954_움직이는_미로_탈출
    --------------------------------------------------------------------------------------------------------------------
    문제링크 : https://www.acmicpc.net/problem/16954

    난이도 : G4
    --------------------------------------------------------------------------------------------------------------------
    풀이

    이 문제는 벽이 움직이는 것과, 움직이는 객체가 대각선으로도 움직일 수 있고, 움직이지 않는 경우도 존재한다는 것이다.
    우선 이 문제를 BFS로 풀어내야 한다. 그리고 추가할것이 같은 깊이의 경우를 모두 탐색할때 벽을 움직여 주는 것이다.
    같은 깊이의 경우는 같은 시간때의 경우의 수이다. 이 시간때에 움직일 수 있는 모든 경우를 que에 저장한다. 그리고
    저장할때 움직이지 않는 경우도 저장해야 하니 visited 에서 시작 지점은 저장하지 않는것이 좋다.
    이렇게 같은 깊이의 움직이는 경우의 수를 모두 탐색했다면 시간이 1초 흐른것이기 때문에 벽을 한칸씩 내려준다.
    그리고 다음 깊이의 단계를 진행할때 큐에서 꺼낸 좌표가 현재 벽이면 움직일수 없으므로 버린다.

    같은 시간때를 구분짖는 것은 BFS의 깊이로 구분한다는 것, 그리고 그 깊이를 que의 사이즈의 변화를 통해알아낼 수 있다는
    것을 알아내면 쉽게 풀리는 문제였다.
    --------------------------------------------------------------------------------------------------------------------
 */
package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ16954_움직이는_미로_탈출 {

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    static char[][] map = new char[8][8];
    static int[] v_r = {1, -1, 0, 0, 1, 1, -1, -1, 0};
    static int[] v_c = {0, 0, 1, -1, 1, -1, 1, -1, 0};
    static Queue<Point> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int ans = 0;
        int depth = 1;
        que.add(new Point(7, 0));
        boolean[][] visited = new boolean[8][8];

        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.row == 0 && cur.col == 7) {
                ans = 1;
                break;
            }

            if (map[cur.row][cur.col] == '.') {
                for (int i = 0; i < 9; i++) {
                    Point next = new Point(cur.row + v_r[i], cur.col + v_c[i]);
                    if (checkBoundary(next) && !visited[next.row][next.col] && map[next.row][next.col] != '#') {
                        que.add(next);
                        visited[next.row][next.col] = true;
                    }
                }
            }

            if (--depth == 0) {
                moveWall();
                depth = que.size();
                for (int i = 0; i < 8; i++) {
                    Arrays.fill(visited[i], false);
                }
            }
        }
        System.out.println(ans);
    }

    static boolean checkBoundary(Point cur) {
        return cur.row >= 0 && cur.row < 8 && cur.col >= 0 && cur.col < 8;
    }

    static void moveWall() {
        char[][] copy = new char[8][8];
        for (int i = 0; i < 8; i++) {
            copy[i] = Arrays.copyOf(map[i], 8);
        }
        for (int i = 1; i < 8; i++) {
            map[i] = Arrays.copyOf(copy[i - 1], 8);
        }
        Arrays.fill(map[0], '.');
    }

    static void showMap() {
        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 8; i++) {
            System.arraycopy(br.readLine().toCharArray(), 0, map[i], 0, 8);
        }
    }
}
