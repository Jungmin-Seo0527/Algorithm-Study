package codeforces.R731_D3;

import java.io.*;
import java.util.*;

public class A_Shortest_Path_with_Obstacle {

    static long solve(int xa, int ya, int xb, int yb, int xf, int yf) {

        long ret = Math.abs(xb - xa) + Math.abs(yb - ya);
        if (xa == xb && xb == xf) {
            if ((ya > yf && yb < yf) || (ya < yf && yb > yf)) {
                ret += 2;
            }
        } else if (ya == yb && yb == yf) {
            if ((xa > xf && xb < xf) || (xa < xf && xb > xf)) {
                ret += 2;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            st = new StringTokenizer(br.readLine());
            int xa = Integer.parseInt(st.nextToken());
            int ya = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int xb = Integer.parseInt(st.nextToken());
            int yb = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int xf = Integer.parseInt(st.nextToken());
            int yf = Integer.parseInt(st.nextToken());

            out.append(solve(xa, ya, xb, yb, xf, yf)).append("\n");
        }
        System.out.println(out);
    }
}