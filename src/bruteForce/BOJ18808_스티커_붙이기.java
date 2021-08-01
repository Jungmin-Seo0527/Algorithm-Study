package bruteForce;

import java.io.*;
import java.util.*;

public class BOJ18808_스티커_붙이기 {

    static int rowSZ, colSZ, K;
    static int[][] map;
    static Sticker[] stickers;

    static void solve() {
        for (int i = 0; i < K; i++) {
            Sticker sticker = stickers[i];

            loop:
            for (int d = 0; d < 4; d++) {
                for (int r = 0; r < rowSZ; r++) {
                    for (int c = 0; c < colSZ; c++) {
                        if (checkSticker(r, c, sticker)) {
                            putSticker(r, c, sticker);
                            break loop;
                        }
                    }
                }
                sticker.rotate();
            }
        }
        System.out.println(ans());
    }

    static int ans() {
        int cnt = 0;
        for (int i = 0; i < rowSZ; i++) {
            for (int j = 0; j < colSZ; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void putSticker(int r, int c, Sticker sticker) {
        for (int i = r; i < r + sticker.rowSZ; i++) {
            for (int j = c; j < c + sticker.colSZ; j++) {
                if (sticker.map[i - r][j - c] == 1)
                    map[i][j] = sticker.map[i - r][j - c];
            }
        }
    }

    static boolean checkSticker(int r, int c, Sticker sticker) {
        if (r + sticker.rowSZ >= rowSZ + 1 || c + sticker.colSZ >= colSZ + 1) return false;

        for (int i = r; i < r + sticker.rowSZ; i++) {
            for (int j = c; j < c + sticker.colSZ; j++) {
                if (map[i][j] == 1 && sticker.map[i - r][j - c] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Sticker {
        int rowSZ, colSZ;
        int[][] map;

        public Sticker(int rowSZ, int colSZ, int[][] map) {
            this.rowSZ = rowSZ;
            this.colSZ = colSZ;
            this.map = map;
        }

        public void showMap() {
            System.out.println("rowSZ = " + rowSZ + " colSZ = " + colSZ);
            for (int i = 0; i < rowSZ; i++) {
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println();
        }

        public void rotate() {
            int rrs = colSZ;
            int rcs = rowSZ;
            int[][] rm = new int[rrs][rcs];
            for (int i = 0; i < colSZ; i++) {
                for (int j = 0; j < rowSZ; j++) {
                    rm[i][j] = map[rowSZ - 1 - j][i];
                }
            }
            rowSZ = rrs;
            colSZ = rcs;
            map = rm;
        }
    }

    static void showMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
//        System.out.println("===== input =====");
//        String fileName = "input/input1.txt";
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
//        String s;
//        while ((s = br2.readLine()) != null) {
//            System.out.println(s);
//        }
//        System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stickers = new Sticker[K];
        map = new int[rowSZ][colSZ];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int rs = Integer.parseInt(st.nextToken());
            int cs = Integer.parseInt(st.nextToken());
            int[][] tMap = new int[rs][cs];
            for (int j = 0; j < rs; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < cs; k++) {
                    tMap[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            stickers[i] = new Sticker(rs, cs, tMap);
        }
        solve();
    }
}
