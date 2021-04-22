package greedy;

import java.io.*;
import java.util.*;

// BOJ4796_캠핑
/*
    캠핑장을 연속하는 P일중 L일만 사용할수 있을때 V일 짜리 휴가로 최대 몇일동안 이용이 가능한지 묻는 문제

    한 덩어리가 P일 이므로 우선 휴가날인 V로 나누어 준다.
    그 몫을 실제 캠핑장을 이용할 수 있는 일수인 L로 곱해주면 실제 캠핑장을 이용하는 날짜가 나온다.
    그 다음, 남는 일수가 있을것이다. 그 일수는 V를 P로 나눈 나머지이다.
    그 나머지가 L보다 작거나 같으면 그 날짜만큼 한번더 이용이 가능하다. 단 L보다 클때도 L만큼만 이용이 가능하다.
    즉 Math.min(나머지, L)을 추가로 더해주면 된다.
 */
public class BOJ4796_캠핑 {
    public static void main(String[] args) throws IOException {
        inputAndSettingData();
    }

    static long solve(int L, int P, int V) {
        int na = V % P;
        int moc = V / P;
        return (long) moc * L + Math.min(na, L);
    }

    static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = 0;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            if (L == 0 && P == 0 && V == 0) break;
            sb.append("Case ").append(++T).append(": ").append(solve(L, P, V)).append("\n");
        }
        System.out.println(sb);
    }
}

