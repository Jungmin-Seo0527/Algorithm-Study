package dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20005_보스몬스터_전리품 {

    static final int MAX_SEC = 1000 * 1000 + 1;
    static final int playerNum = 26;

    static int rowSZ, colSZ, P;
    static Player boss;
    static char[][] map;
    static int[] arrDPS, arrCnt;
    static long sum;
    static Player[] players = new Player[26];
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};

    static void solve() {
        int ans = 0;
        bfs();
        for (int i = 1; i < MAX_SEC; i++) {
            arrDPS[i] += arrDPS[i - 1];
            sum += (long) arrDPS[i];
            ans += arrCnt[i];
            if (sum >= (long) boss.dps) {
                break;
            }
        }

        System.out.println(ans);
    }

    static void bfs() {
        Queue<Player> que = new LinkedList<>();
        boolean[][][] visited = new boolean[rowSZ][colSZ][playerNum];
        boolean[] end = new boolean[playerNum];
        for (int i = 0; i < playerNum; i++) {
            if (players[i] != null) {
                Player p = players[i];
                que.add(p);
                visited[p.r][p.c][p.id - 'a'] = true;
            }
        }
        while (!que.isEmpty()) {

            Player cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + vr[i];
                int nc = cur.c + vc[i];
                if (checkBoundary(nr, nc) && map[nr][nc] == '.'
                        && !visited[nr][nc][cur.id - 'a'] && !end[cur.id - 'a']) {
                    visited[nr][nc][cur.id - 'a'] = true;
                    if (boss.r == nr && boss.c == nc) {
                        arrDPS[cur.time + 1] += cur.dps;
                        arrCnt[cur.time + 1]++;
                        end[cur.id - 'a'] = true;
                    } else {
                        que.add(new Player(cur.id, cur.dps, nr, nc, cur.time + 1));
                    }
                }
            }
        }
    }

    static void solve2() {
        int ans = 0;
        long sum = 0;
        long totalDemage = 0;
        boolean[] end = new boolean[playerNum];
        Queue<Point> que = new LinkedList<>();
        boolean[][] visited = new boolean[rowSZ][colSZ];
        que.add(new Point(boss.r, boss.c));
        visited[boss.r][boss.c] = true;

        while (!que.isEmpty()) {
            int qz = que.size();
            while (qz-- > 0) {
                Point cur = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + vr[i];
                    int nc = cur.c + vc[i];
                    if (checkBoundary(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'X') {
                        visited[nr][nc] = true;
                        que.add(new Point(nr, nc));

                        if (map[nr][nc] != '.') {
                            char c = map[nr][nc];
                            if (!end[c - 'a']) {
                                end[c - 'a'] = true;
                                ans++;
                                sum += (long)players[c - 'a'].dps;
                            }
                        }
                    }
                }
            }
            totalDemage += (long)sum;
            if (totalDemage >= boss.dps) {
                break;
            }
        }
        System.out.println(ans);
    }


    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < rowSZ && c >= 0 && c < colSZ;
    }

    static class Player {
        char id;
        int time, dps, r, c;

        public Player(char id, int dps, int r, int c) {
            this.id = id;
            this.dps = dps;
            this.r = r;
            this.c = c;
        }

        public Player(char id, int dps, int r, int c, int time) {
            this.id = id;
            this.time = time;
            this.dps = dps;
            this.r = r;
            this.c = c;
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        map = new char[rowSZ][colSZ];
        arrDPS = new int[MAX_SEC];
        arrCnt = new int[MAX_SEC];

        for (int i = 0; i < rowSZ; i++) {
            String s = br.readLine();
            for (int j = 0; j < colSZ; j++) {
                char c = s.charAt(j);
                if (c == '.' || c == 'X') map[i][j] = c;
                else if (c == 'B') {
                    map[i][j] = '.';
                    boss = new Player('B', 0, i, j);
                } else {
                    // map[i][j] = '.';
                    map[i][j] = c;
                    players[c - 'a'] = new Player(c, 0, i, j);
                }
            }
        }
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            players[st.nextToken().charAt(0) - 'a'].dps = Integer.parseInt(st.nextToken());
        }
        boss.dps = Integer.parseInt(br.readLine());
        solve2();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input1.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
