package ds;

import java.util.*;

public class PGM_더_맵게 {
    private PriorityQueue<Integer> que = new PriorityQueue<>();

    public int solution(int[] scoville, int K) {
        int answer = -1;
        int cnt = 0;

        for (int e : scoville) {
            que.add(e);
        }

        while (que.size() >= 2) {
            int s1 = que.poll();
            int s2 = que.poll();
            if (s1 >= K && s2 >= K) {
                answer = cnt;
                break;
            }
            que.add(s1 + s2 * 2);
            cnt++;
        }
        if (que.size() == 1) {
            int s = que.poll();
            if (s >= K) answer = cnt;
        }

        return answer;
    }
}