
/*
    BOJ9328_열쇠
    ------------------------------------------------------------------------------
    문제
    상근이는 1층 빌딩에 침입해 매우 중요한 문서를 훔쳐오려고 한다. 상근이가 가지고 있는 평면도에는 문서의 위치가 모두 나타나 있다.
    빌딩의 문은 모두 잠겨있기 때문에, 문을 열려면 열쇠가 필요하다. 상근이는 일부 열쇠를 이미 가지고 있고, 일부 열쇠는 빌딩의 바닥에 놓여져 있다.

    상근이가 훔칠 수 있는 문서의 최대 개수를 구하는 프로그램을 작성하시오.
    ------------------------------------------------------------------------------
    입력
    첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 수는 100개를 넘지 않는다.

    각 테스트 케이스의 첫째 줄에는 지도의 높이와 너비 h와 w (2 ≤ h, w ≤ 100)가 주어진다. 다음 h개 줄에는 빌딩을 나타내는 w개의 문자가 주어지며, 각 문자는 다음 중 하나이다.

    '.'는 빈 공간을 나타낸다.
    '*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
    '$'는 상근이가 훔쳐야하는 문서이다.
    알파벳 대문자는 문을 나타낸다.
    알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
    마지막 줄에는 상근이가 이미 가지고 있는 열쇠가 공백없이 주어진다. 만약, 열쇠를 하나도 가지고 있지 않는 경우에는 "0"이 주어진다.

    상근이는 처음에는 빌딩의 밖에 있으며, 빌딩 가장자리의 빈 공간이나 문을 통해 빌딩 안팎을 드나들 수 있다.
    각각의 문에 대해서, 그 문을 열 수 있는 열쇠의 개수는 0개, 1개, 또는 그 이상이고, 각각의 열쇠에 대해서, 그 열쇠로 열 수 있는 문의 개수도 0개, 1개, 또는 그 이상이다.
    열쇠는 여러 번 사용할 수 있다.
    ------------------------------------------------------------------------------
    출력
    각 테스트 케이스 마다, 상근이가 훔칠 수 있는 문서의 최대 개수를 출력한다.

    ------------------------------------------------------------------------------
    풀이
    어려웠던 문제였다. 역시 Gold1 난이도
    이 문제는 문과 열쇠가 존재하며 특정 죄표로 갈수 있는가 없는가에 대한 문제이다.
    열쇠가 있어야 문을 지날수 있고 열쇠가 없으면 문을 지날 수 없다.
    처음에는 열쇠를 만나면 que를 초기화 시켜서 다시 처음부터 BFS를 진행하는 방식으로 하려 했으나 어림도 없었다.

    이 문제의 핵심 로직은 열쇠를 지니지 못해 지나가지 못하는 문들을 따로 저장하는 것이다.
    그리고 열쇠를 만났을때 지금까지 저장해온 문들의 리스트를 확인한다. 열수 있는 문이 존재하면 그 좌표를 que에 저장해서 그곳부터 진행이 가능토록 한다.

    주의할점이 두가지 있는데
    1) 열쇠를 만나서 해당 문들을 모두 열었는데 그 이후에 지금까지 만나지 못했던 해당문을 만나는 경우이다.
    즉 A 문들을 열지 못해 우선 저장을 하고 이후에 a 열쇠를 만나서 모두 열었다. 그런데 그 열쇠를 만난 이후에 또 다른 A 문을 만난 경우이다.
    문을 만났을때는 2가지 경우가 존재한다.
    열쇠를 가자고 있는 경우, 열쇠를 가지고 있지 않은 경우에 대해 모두 처리가 필요하다.

    2) 이 점은 구현에 따른 문제이다. (나만 해당하는 문제일지도...)
    처음 입력을 받았을때 시작점을 미리 que에 저장을 했다. 빈공간인 경우, 열쇠인 경우에 que에 저장하고 열쇠인 경우에만 열쇠를 저장했다.
    문인 경우에는 열쇠를 지니고 있을땐 que에 저장, 열쇠를 가지고 있지 않는 경우에는 doorList에 저장을 했다.
    그리고 로직을 돌렸는데 틀리다고 한다. 이유는 다음의 예시 때문이다.
    1
    3 4
    ****
    A$*a
    ****
    0

    입력을 보면 시작이 가능한 가장자리에 문이 있는데 해당 열쇠도 주어진다.
    처음 구현할때는 로직내처럼 열쇠면 저장, 문일때는 두가지 경우(열쇠가 있는경우, 열쇠가 없는경우)를 나누어 처리했다.
    문제는 입력이 끝난후에 가장자리에 위치한 문에서 시작이 가능한 경우가 있는 것이다.
    가장자리에 A 문을 만났을때는 a 열쇠를 가지고 있지 못했다. 하지만 이후 a 열쇠를 찾았다.
    이상태로 바로 로직을 돌리면 열쇠를 지니고 있는 상태와 해당 문을 열지 못한 상태로 더이상 움직일 곳이 없기애 끝나버린다.
    따라서 처음 로직을 시작할때 입력받은 열쇠와 문 정보를 토대로 열수 있는 문들을 모두 열어두고 시작을 해야한다.
    이 부분을 놓쳐서 시간을 많이 소모했다. 구현 문제인데 꼼꼼히 살펴보지 못했다.

    3) 같은 문이 여러개 있을수 있다.
    이 조건은 문제에도 명시되어 있는데 왜 처음부터 꼼꼼히 살펴보지 않았는가...
    열쇠또한 같은 열쇠가 여러개 있을수 있다는데 이는 어짜피 하나만 있으면 해당 모든 문을 열수 있기에 조심할 필요는 없다.
    하지만 열쇠를 만났을때 열수 있는 문들이 여러개가 있을 수 있다는 점은 주의해야 한다.
 */
package DFS_BFS;

import java.io.*;
import java.util.*;

public class BOJ9328_열쇠 {
    static class Point {
        int row, col;

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    static int rowSZ, colSZ, ans;
    static char[][] map;
    static boolean[][] visited;
    static boolean[] keys;
    static List<Point>[] doors;
    static Queue<Point> que = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static void solve() {
        for (int i = 0; i < 26; i++) {
            if (doors[i].size() > 0 && keys[i]) {
                for (int j = 0; j < doors[i].size(); j++) {
                    goNext(doors[i].get(j));
                }
            }
        }

        while (!que.isEmpty()) {
            Point cur = que.poll();
            for (int i = 0; i < 4; i++) {
                Point next = new Point(cur.row + v_r[i], cur.col + v_c[i]);
                if (check(next)) {
                    if (map[next.row][next.col] == '.') {
                        goNext(next);
                    } else if (map[next.row][next.col] == '$') {
                        ans++;
                        goNext(next);
                    } else if (map[next.row][next.col] >= 'A' && map[next.row][next.col] <= 'Z') {
                        if (keys[map[next.row][next.col] - 'A']) {
                            goNext(next);
                        } else doors[map[next.row][next.col] - 'A'].add(next);
                    } else if (map[next.row][next.col] >= 'a' && map[next.row][next.col] <= 'z') {
                        if (doors[map[next.row][next.col] - 'a'].size() > 0) {
                            for (int j = 0; j < doors[map[next.row][next.col] - 'a'].size(); j++) {
                                goNext(doors[map[next.row][next.col] - 'a'].get(j));
                            }
                        }
                        keys[map[next.row][next.col] - 'a'] = true;
                        goNext(next);
                    }
                }
            }
        }
    }

    static boolean check(Point point) {
        if (point.row < 0 || point.row >= rowSZ || point.col < 0 || point.col >= colSZ) return false;
        if (visited[point.row][point.col]) return false;
        if (map[point.row][point.col] == '*') return false;
        return true;
    }

    static void goNext(Point cur) {
        que.add(cur);
        map[cur.row][cur.col] = '.';
        visited[cur.row][cur.col] = true;
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());
            rowSZ = Integer.parseInt(st.nextToken());
            colSZ = Integer.parseInt(st.nextToken());
            ans = 0;
            map = new char[rowSZ][colSZ];
            visited = new boolean[rowSZ][colSZ];
            keys = new boolean[26];
            doors = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                doors[i] = new ArrayList<>();
            }
            for (int i = 0; i < rowSZ; i++) {
                String input = br.readLine();
                for (int j = 0; j < colSZ; j++) {
                    map[i][j] = input.charAt(j);
                    if (i == 0 || i == rowSZ - 1 || j == 0 || j == colSZ - 1) {
                        if (map[i][j] == '.') {
                            goNext(new Point(i, j));
                        } else if (map[i][j] >= 'a' && map[i][j] <= 'z') {
                            keys[map[i][j] - 'a'] = true;
                            goNext(new Point(i, j));
                        } else if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
                            doors[map[i][j] - 'A'].add(new Point(i, j));
                        } else if (map[i][j] == '$') {
                            ans++;
                            goNext(new Point(i, j));
                        }
                    }
                }
            }
            String key = br.readLine();
            if (key.compareTo("0") != 0) {
                for (int i = 0; i < key.length(); i++) {
                    keys[key.charAt(i) - 'a'] = true;
                }
            }
            solve();
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}

