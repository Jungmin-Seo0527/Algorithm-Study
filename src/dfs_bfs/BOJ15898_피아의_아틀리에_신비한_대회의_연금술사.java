package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ15898_피아의_아틀리에_신비한_대회의_연금술사 {

    static int N;
    static final int METARIAL_SIZE = 4;
    static final int BOMB_SIZE = 5;
    static final int METARIAL_CNT = 3;

    static List<Metarial> metarialList;
    static boolean[] visited;
    static int[] seq;
    static int ans = Integer.MIN_VALUE;

    static void solve() {
        dfsMetarialSeq(0);
        System.out.println(ans);
    }

    static void dfsMakeBomb(int cnt, Metarial b) {
        if (cnt == METARIAL_CNT) {
            ans = Math.max(ans, bombQualiry(b));
        } else {
            Metarial m = metarialList.get(seq[cnt]);
            for (int i = 0; i < 4; i++) {
                m.rotate();
                for (int r = 0; r < 2; r++) {
                    for (int c = 0; c < 2; c++) {
                        if (cnt == 0 && (r > 0 || c > 0)) continue;

                        Metarial nextB = new Metarial(b);
                        plus(nextB, m, r, c);
                        dfsMakeBomb(cnt + 1, nextB);
                    }
                }
            }
        }
    }

    static void dfsMetarialSeq(int cnt) {
        if (cnt == METARIAL_CNT) {
            dfsMakeBomb(0, new Metarial(BOMB_SIZE));
        } else {
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    seq[cnt] = i;
                    visited[i] = true;
                    dfsMetarialSeq(cnt + 1);
                    visited[i] = false;
                    seq[cnt] = 0;
                }
            }
        }
    }

    static void plus(Metarial b, Metarial m, int sr, int sc) {
        for (int r = sr; r < BOMB_SIZE + sr - 1; r++) {
            for (int c = sc; c < BOMB_SIZE + sc - 1; c++) {
                if (m.elements[r - sr][c - sc].color != 'W') {
                    b.elements[r][c].color = m.elements[r - sr][c - sc].color;
                }

                b.elements[r][c].quality += m.elements[r - sr][c - sc].quality;
                if (b.elements[r][c].quality < 0) b.elements[r][c].quality = 0;
                if (b.elements[r][c].quality > 9) b.elements[r][c].quality = 9;
            }
        }

    }

    static int bombQualiry(Metarial b) {
        int ans = 0;
        for (int i = 0; i < BOMB_SIZE; i++) {
            for (int j = 0; j < BOMB_SIZE; j++) {
                Element e = b.elements[i][j];
                if (e.color == 'R') {
                    ans += e.quality * 7;
                } else if (e.color == 'B') {
                    ans += e.quality * 5;
                } else if (e.color == 'G') {
                    ans += e.quality * 3;
                } else if (e.color == 'Y') {
                    ans += e.quality * 2;
                }
            }
        }

        return ans;
    }


    static void showMetarialList() {
        for (int i = 0; i < N; i++) {
            System.out.println(metarialList.get(i).toString());
        }
    }

    private static class Metarial {
        Element[][] elements;
        int sz = 0;

        public Metarial(int sz) {
            this.sz = sz;
            elements = new Element[sz][sz];
            for (int i = 0; i < sz; i++) {
                for (int j = 0; j < sz; j++) {
                    elements[i][j] = new Element();
                }
            }
        }

        public Metarial(Metarial m) {
            this.sz = m.sz;
            elements = new Element[sz][sz];
            for (int i = 0; i < sz; i++) {
                for (int j = 0; j < sz; j++) {
                    elements[i][j] = new Element(m.elements[i][j].quality, m.elements[i][j].color);

                }
            }
        }

        public void rotate() {

            Element[][] copy = new Element[sz][sz];
            for (int i = 0; i < sz; i++) {
                for (int j = 0; j < sz; j++) {
                    copy[i][j] = new Element();
                    copy[i][j].quality = elements[i][j].quality;
                    copy[i][j].color = elements[i][j].color;
                }
            }

            for (int i = 0; i < sz; i++) {
                for (int j = 0; j < sz; j++) {
                    elements[j][sz - i - 1] = copy[i][j];
                }
            }

        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < sz; i++) {
                for (int j = 0; j < sz; j++) {
                    sb.append(elements[i][j].quality).append(elements[i][j].color).append(" ");
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }


    private static class Element {
        int quality;
        char color;

        public Element() {
            this.color = 'W';
            this.quality = 0;
        }

        public Element(int quality, char color) {
            this.quality = quality;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "quality=" + quality +
                    ", color=" + color +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        seq = new int[METARIAL_CNT];
        metarialList = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            Metarial metarial = new Metarial(METARIAL_SIZE);
            for (int r = 0; r < METARIAL_SIZE; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < METARIAL_SIZE; c++) {
                    metarial.elements[r][c].quality = Integer.parseInt(st.nextToken());
                }
            }
            for (int r = 0; r < METARIAL_SIZE; r++) {
                char[] colors = br.readLine().replaceAll(" ", "").toCharArray();
                for (int c = 0; c < METARIAL_SIZE; c++) {
                    metarial.elements[r][c].color = colors[c];
                }
            }
            metarialList.add(metarial);
        }

        solve();
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
