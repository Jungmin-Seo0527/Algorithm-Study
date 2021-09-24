package implementation;

import java.io.*;
import java.util.*;

public class BOJ14719_빗물 {

    static int rowSZ, colSZ;
    static int[] board;
    static int[][] map;

    static void solve() {
        int ret = 0;

        map = new int[rowSZ][colSZ];
        for (int c = 0; c < colSZ; c++) {
            for (int r = 0; r < board[c]; r++) {
                map[r][c] = 1;
            }
        }

        for (int i = 0; i < rowSZ; i++) {
            int col = 0;
            while (col < colSZ && map[i][col] == 0) {
                col++;
            }
            int sum = 0;
            for (int j = col + 1; j < colSZ; j++) {
                if (map[i][j] == 0) {
                    sum++;
                } else {
                    ret += sum;
                    sum = 0;
                }
            }
        }
        System.out.println(ret);
    }

    public static void main(String[] args) throws IOException {
        // System.out.println("===== input =====");
        // String fileName = "input/input1.txt";
        // BufferedReader br = new BufferedReader(new FileReader(fileName));
        // BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        // String s;
        // while ((s = br2.readLine()) != null) {
        //     System.out.println(s);
        // }
        // System.out.println("===== output =====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rowSZ = Integer.parseInt(st.nextToken());
        colSZ = Integer.parseInt(st.nextToken());
        board = new int[colSZ];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < colSZ; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        solve();
    }
}
