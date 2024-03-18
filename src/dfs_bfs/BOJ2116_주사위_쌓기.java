package dfs_bfs;

import java.io.*;
import java.util.*;

public class BOJ2116_주사위_쌓기 {

    static int N, sum;
    static List<List<Integer>> dice;
    static int max = Integer.MIN_VALUE;

    static void solve() {
        for (int i = 0; i < 6; i++) {
            int downIdx = getOtherSideIdx(i);
            int downValue = dice.get(0).get(downIdx);
            sum = getMaxSideValue(i, downIdx, 0);
            doDFS(1, downValue);
        }

        System.out.println(max);
    }

    static void doDFS(int diceIdx, int downValue) {
        if (diceIdx == N) {
            max = Math.max(max, sum);
            sum = 0;
            return;
        }
        int upIdx = 0;
        for (int i = 0; i < 6; i++) {
            if (dice.get(diceIdx).get(i) == downValue) {
                upIdx = i;
                break;
            }
        }
        int downIdx = getOtherSideIdx(upIdx);
        sum += getMaxSideValue(upIdx, downIdx, diceIdx);
        doDFS(diceIdx + 1, dice.get(diceIdx).get(downIdx));
    }

    static int getOtherSideIdx(int diceIdx) {
        switch (diceIdx) {
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 0;
            default:
                return 0;
        }
    }

    static int getMaxSideValue(int up, int down, int diceIdx) {
        int ret = Integer.MIN_VALUE;
        for (int i = 0; i < 6; i++) {
            if (i == up || i == down) {
                continue;
            }
            ret = Math.max(ret, dice.get(diceIdx).get(i));
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dice = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dice.add(new ArrayList<>(6));
            for (int j = 0; j < 6; j++) {
                int diceValue = Integer.parseInt(st.nextToken());
                dice.get(i).add(diceValue);
            }
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
