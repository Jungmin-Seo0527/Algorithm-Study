/*
    BOJ5213_과외맨
    --------------------------------------------------------------------------------------------------------------------
    문제링크

    https://www.acmicpc.net/problem/5213

    난이도 : G3
    --------------------------------------------------------------------------------------------------------------------
    풀이

    이 문제의 특이한 점은 두개의 칸을 하나의 도미노로 생각하고 풀어야 한다. 그리고 정상적인 사각형의 배열이 아닌 짝수 행은 홀수 행보다
    열의 길이가 2만큼 적다.

    처음에는 한칸한칸 전진하면서 같은 도미노이면 제약없이 이동, 다른 도미노이면 인접한 면의 값이 같은지 조사하면서 이동을 했다.
    visited 배열도 각 칸마다 따로따로 체크했다. 하지만 이 방법은 잘못되었다. 각 칸마다 이동을 한다고 생각하면 그 도미노로 도달하는데
    더 긴 경로로 돌아서 가는 경우가 생긴다. 예시...???

    배열의 두칸이 하나의 도미노 이므로 해당 도미노에 도달하면 que 에는 도미노에 해당하는 두 칸을 모두 저장하는 방법으로 풀어내야
    한다. 또한 서로 같은 도미노인지 아닌지에 대한 판별은 처음 입력값을 받을때, 2개의 입력값을 받을때마사 tileNum++ 하여 타일 번호를
    저장하는 배열을 따로 만들어 주면 매우 편하다.

    가장 중요한 부분이 check 메소드 이다.
    우선 다음 좌표가 배열의 범위에 벗어나는지에 대한 판별이 이루어 져야 한다.
    다음으로 tile 번호를 부여 받았는지에 확인한다. tile 번호를 부여 받지 않았다는 것은 짝수 행의 1열, 혹은 2*N 즉 비어있는 부분을
    가리킨다. 다음으로 visited 방문여부를 확인한다. 방문여부는 칸 단위가 아닌 도미노 단위로 확인하는 것이 더 효율적이다.
    마지막으로 인접한 도미노와의 숫자를 비교한다.

    다른 풀이들을 참고해보니 계산을 통해서 그 칸에 도미노가 존재하는지 확인하는데 처음부터 도미노 번호를 부여해 주고 번호를 부여받지
    않은 곳이 도미노가 없는 곳이라고 하는 방법이 굉장히 편하고 구현도 간단했다.

    이동할 수 있는 도미노라면 방문여부를 체크해 주고, 해당 좌표를 que 에 저장한다. 중요한점은 같은 도미노의 다른 좌표도 동시에 que에
    저장을 해준다.
    방문 경로는 따로 root 라는 배열을 만들어 각 인덱스가 도미노 번호, 저장되는 숫자가 이전에 방문한 도미노 번호를 저장하여 경로를
    추적할 수 있다록 한다.

    주의할 점은 문제를 읽어보면 만약 마지막 타일에 도달하지 못하는 경우에는 번호가 가장 큰 타일로 이동하면 된다 라는 문구가 있다.
    조심할 것은 번호가 가장 큰 타일이다. 번호가 가장 큰 칸이 아니다. 즉 최대한 큰행, 최대한 큰 열로 이동하라는 의미이다.
    입력을 하는 과정에서 타일 번호를 부여 했는데 그 타일의 번호가 가장 큰 값으로 이동을 해야 한다.
    처음에 칸의 숫자가 가장 큰 곳으로 이동을 해서 틀렸다가 나왔다. (6% 부분이 그 부분인듯 하다)
    --------------------------------------------------------------------------------------------------------------------
 */
package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ5213_과외맨 {

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    static int N;
    static int[] visited;
    static int[][] map, groupNum;
    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        int end = 1;
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(1, 1));
        que.add(new Point(1, 2));
        visited[1] = -1;
        while (!que.isEmpty()) {
            Point cur = que.poll();
            end = Math.max(end, groupNum[cur.row][cur.col]);

            for (int i = 0; i < 4; i++) {
                Point next = new Point(cur.row + v_r[i], cur.col + v_c[i]);
                if (checkBoundary(next)
                        && visited[groupNum[next.row][next.col]] == 0
                        && map[cur.row][cur.col] == map[next.row][next.col]) {
                    visited[groupNum[next.row][next.col]] = groupNum[cur.row][cur.col];
                    que.add(next);
                    if (next.col + 1 <= 2 * N && groupNum[next.row][next.col + 1] == groupNum[next.row][next.col])
                        que.add(new Point(next.row, next.col + 1));
                    else que.add(new Point(next.row, next.col - 1));
                }
            }
        }
        showRoot(end);
    }

    static boolean checkBoundary(Point p) {
        return p.row > 0 && p.row <= N && p.col > 0 && p.col <= 2 * N;
    }

    static void showRoot(int end) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while (end != -1) {
            stack.add(end);
            end = visited[end];
        }
        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[2 * N + 1][2 * N + 1];
        groupNum = new int[2 * N + 1][2 * N + 1];

        int gn = 1, r = 1, c = 1;
        for (int i = 0; i < N * N - N / 2; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map[r][c] = n1;
            groupNum[r][c++] = gn;
            map[r][c] = n2;
            groupNum[r][c++] = gn++;
            if ((r % 2 == 1 && c > 2 * N) || (r % 2 == 0 && c > 2 * N - 1)) {
                r++;
                if (r % 2 == 0) c = 2;
                else c = 1;
            }
        }
        visited = new int[gn];

    }
}
