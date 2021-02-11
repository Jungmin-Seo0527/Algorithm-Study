package DFS_BFS;
/*
    BOJ1194_달이_차오른다_가자
    --------------------------------------------------------------------------------------------------------------------
    문제
    지금 민식이가 계획한 여행은 달이 맨 처음 뜨기 시작할 때 부터, 준비했던 여행길이다.
    하지만, 매번 달이 차오를 때마다 민식이는 어쩔 수 없는 현실의 벽 앞에서 다짐을 포기하고 말았다.
    민식이는 매번 자신의 다짐을 말하려고 노력했지만, 말을 하면 아무도 못 알아들을 것만 같아서, 지레 겁먹고 벙어리가 되어버렸다.
    결국 민식이는 모두 잠든 새벽 네시 반쯤 홀로 일어나, 창 밖에 떠있는 달을 보았다.
    하루밖에 남지 않았다. 달은 내일이면 다 차오른다. 이번이 마지막기회다. 이걸 놓치면 영영 못간다.
    영식이는 민식이가 오늘도 여태것처럼 그냥 잠 들어버려서 못 갈지도 모른다고 생각했다.
    하지만 그러기엔 민식이의 눈에는 저기 뜬 달이 너무나 떨렸다.
    민식이는 지금 미로 속에 있다. 미로는 직사각형 모양이고, 여행길을 떠나기 위해 미로를 탈출하려고 한다.
    미로는 다음과 같이 구성되어져있다.

    빈 곳 : 언제나 이동할 수 있다. ('.‘로 표시됨)
    벽 : 절대 이동할 수 없다. (‘#’)
    열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. (a - f)
    문 : 대응하는 열쇠가 있을 때만 이동할 수 있다. (A - F)
    민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다. (숫자 0)
    출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. (숫자 1)
    달이 차오르는 기회를 놓치지 않기 위해서, 미로를 탈출하려고 한다.
    한 번의 움직임은 현재 위치에서 수평이나 수직으로 한 칸 이동하는 것이다.

    민식이가 미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.
    --------------------------------------------------------------------------------------------------------------------
    입력

    첫째 줄에 미로의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 50) 둘째 줄부터 N개의 줄에 미로의 모양이 주어진다.
    같은 타입의 열쇠가 여러 개 있을 수 있고, 문도 마찬가지이다.
    그리고, 영식이가 열쇠를 숨겨놓는 다면 문에 대응하는 열쇠가 없을 수도 있다.
    0은 한 개, 1은 적어도 한 개 있다. 그리고, 열쇠는 여러 번 사용할 수 있다.
    --------------------------------------------------------------------------------------------------------------------
    출력

    첫째 줄에 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력한다. 만약 민식이가 미로를 탈출 할 수 없으면, -1을 출력한다.
    --------------------------------------------------------------------------------------------------------------------
    풀이

    이전에 풀었던 BOJ9328_열쇠 문제와 비슷한 문제이다. 미로에서 열쇠가 존재하고 열쇠로 열수있는 문이 주어진다.
    단지 그 문제와 다른점은 출구로 가는 최단거리를 구해야 한다는 점이다. 열쇠는 최단거리가 아닌 갈수 있는지 그 여부만을 알아내면
    되기에 열쇠를 가진 순간 열쇠가 없어서 열지 못하고 지나쳤던 문으로 순간이동이 가능했다. 하지만 이 문제는 순간이동이 아닌 그 문으로
    가는 경로까지 최단거리로 따져야 한다.
    이런 문제를 비트마스킹으로 풀어낼 수 있는데 점점 비트마스킹을 적용해야 하는 문제가 보이는것 같다.

    내 나름대로 비트마스킹의 적용 상황을 정리해 보자면 최단경로를 구해야 하는 문제에서 내 상황이 이전 상황과 다른 경우에는 지나왔던
    길을 다시 지날 수 있다.
    이 문제에서 적용해 보자면 내가 열쇠를 가지고 있는 상황에 따라 지나왔던 길을 다시 자나와도 무방하다. 그렇기에 그 상황을
    비트마스킹을 하여 문제를 풀어내는 것이 핵심이다.

    빈 곳에 대한 처리는 그냥 지나가면 된다.
    벽은 이동이 불가능 하다.
    열쇠를 만난 경우에는 현재 비트에 마스킹을 해둔다.
    문을 만났을때 비트를 확인하여 열수있는 열쇠가 주어지면 문을 열고 아니면 아무런 일을 하지 않는다.
    출구를 만나면 지금까지의 이동 횟수를 출력해준다.

    지나왔던 길에 대한 재방문 여부 visited 는 visited[rowSZ*colSZ][1<<(열쇠의 갯수)] 로 하여 열쇠를 지니고 있는 상황에 따라
    다르게 지나왔던 이동경로를 다시금 지날수 있도록 처리하였다.
 */

import java.io.*;
import java.util.*;

public class BOJ1194_달이_차오른다_가자 {
    static class Point {
        int row, col;
        int bit = 0;
        int cnt = 0;

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", bit=" + bit +
                    ", cnt=" + cnt +
                    '}';
        }

        public Point(int row, int col, int bit, int cnt) {
            this.row = row;
            this.col = col;
            this.bit = bit;
            this.cnt = cnt;
        }
    }

    static int rowSZ, colSZ;
    static char[][] map;
    static boolean[][] visited;
    static Point start;
    static Queue<Point> queue = new LinkedList<>();

    static int[] v_r = {1, -1, 0, 0};
    static int[] v_c = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        inputAndSettingData();
        solve();
    }

    static void solve() {
        goNext(start);
        map[start.row][start.col] = '.';
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                Point next = new Point(cur.row + v_r[i], cur.col + v_c[i], cur.bit, cur.cnt + 1);
                next.bit = cur.bit;
                next.cnt = cur.cnt + 1;
                if (check(next)) {
                    if (map[next.row][next.col] == '.') {
                        goNext(next);
                    } else if (map[next.row][next.col] == '1') {
                        System.out.println(next.cnt);
                        return;
                    } else if (map[next.row][next.col] >= 'a' && map[next.row][next.col] <= 'f') {
                        next.bit = next.bit | (1 << (map[next.row][next.col] - 'a'));
                        goNext(next);
                    } else if (map[next.row][next.col] >= 'A' && map[next.row][next.col] <= 'F') {
                        if ((next.bit & (1 << map[next.row][next.col] - 'A')) != 0) {
                            goNext(next);
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static boolean check(Point cur) {
        if (cur.row < 0 || cur.row >= rowSZ || cur.col < 0 || cur.col >= colSZ) return false;
        if (visited[cur.row * colSZ + cur.col][cur.bit]) return false;
        if (map[cur.row][cur.col] == '#') return false;

        return true;
    }

    static void goNext(Point next) {
        visited[next.row * colSZ + next.col][next.bit] = true;
        queue.add(next);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        visited = new boolean[rowSZ * colSZ][1 << 7];
        for (int i = 0; i < rowSZ; i++) {
            String input = br.readLine();
            for (int j = 0; j < colSZ; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == '0') {
                    start = new Point(i, j, 0, 0);
                }
            }
        }
    }
}

