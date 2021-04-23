package ds;

import java.util.*;

public class PGM_프린터 {
    private Queue<Integer> que = new LinkedList<>();

    public int solution(int[] priorities, int location) {
        int answer = 0;

        for (int i = 0; i < priorities.length; i++) {
            que.add(i);
        }

        while (!que.isEmpty()) {
            int qs = que.size();
            int cur = que.poll();
            for (Integer e : que) {
                if (priorities[e] > priorities[cur]) {
                    que.add(cur);
                    break;
                }
            }

            if (qs > que.size()) {
                answer++;
                if (cur == location) {
                    break;
                }
            }

        }
        return answer;
    }
}