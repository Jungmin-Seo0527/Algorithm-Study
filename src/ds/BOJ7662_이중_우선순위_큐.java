package ds;

import java.io.*;
import java.util.*;

public class BOJ7662_이중_우선순위_큐 {

    static TreeMap<Integer, Integer> que = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            que.clear();
            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int n = Integer.parseInt(st.nextToken());
                if (op.equals("I")) {
                    que.put(n, que.getOrDefault(n, 0) + 1);
                } else if (op.equals("D") && !que.isEmpty()) {
                    int p = 0;
                    if (n == 1) p = que.lastKey();
                    else p = que.firstKey();

                    Integer g = que.get(p);
                    if (g == 1) que.remove(p);
                    else que.put(p, g - 1);
                }
            }
            if (que.isEmpty()) {
                out.append("EMPTY");
            } else {
                out.append(que.lastKey()).append(" ").append(que.firstKey());
            }
            out.append("\n");
        }
        System.out.println(out);
    }
}
