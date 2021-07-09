package codeforces.R730_D2;

import java.io.*;
import java.util.*;

public class B_Customising_the_Track {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            long sum = 0;
            for (int i = 0; i < N; i++)
                sum += Long.parseLong(st.nextToken());
            long na = sum % N;
            out.append(na * (N - na)).append("\n");
        }
        System.out.println(out);
    }
}