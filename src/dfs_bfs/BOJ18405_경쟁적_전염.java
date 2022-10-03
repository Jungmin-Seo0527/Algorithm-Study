package dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18405_경쟁적_전염 {

    static int N, K, S, X, Y;
    static int[] vr = {1, -1, 0, 0};
    static int[] vc = {0, 0, 1, -1};
    static int[][] map;

    static void solve() {
        Queue<Virus> que = settingQueue();
        while (!que.isEmpty() && S-- > 0) {
            int queSize = que.size();
            while (queSize-- > 0) {
                Virus cur = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + vr[i];
                    int nc = cur.c + vc[i];
                    if (checkBoundary(nr, nc) && map[nr][nc] == 0) {
                        map[nr][nc] = map[cur.r][cur.c];
                        que.add(new Virus(nr, nc));
                    }
                }
            }
        }

        System.out.println(map[X - 1][Y - 1]);
    }

    static void solve2() {
        int minDist = Integer.MAX_VALUE;
        int minVirus = Integer.MAX_VALUE;
        X--;
        Y--;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    int dist = getDist(i, j, X, Y);
                    if (minDist > dist) {
                        minDist = dist;
                        minVirus = map[i][j];
                    } else if (minDist == dist && minVirus > map[i][j]) {
                        minVirus = map[i][j];
                    }
                }
            }
        }
        if (minDist > S) minVirus = 0;
        System.out.println(minVirus);
    }

    static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static Queue<Virus> settingQueue() {
        Queue<Virus> que = new LinkedList<>();
        List<List<Virus>> virusList = new ArrayList<>();
        for (int i = 0; i < K + 1; i++) {
            virusList.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                virusList.get(map[i][j]).add(new Virus(i, j));
            }
        }

        for (int i = 1; i <= K; i++) {
            List<Virus> list = virusList.get(i);
            que.addAll(list);
        }

        return que;
    }

    static class Virus {
        int r, c;

        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        // solve();
        solve2();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input2.txt";
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
