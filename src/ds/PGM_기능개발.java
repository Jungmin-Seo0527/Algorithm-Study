package ds;

import java.util.*;

public class PGM_기능개발 {
    private Stack<Integer> stack = new Stack<>();
    private Deque<Integer> ans = new LinkedList<>();

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int[] day = new int[progresses.length];
        for (int i = 0; i < day.length; i++) {
            int d = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) d++;
            day[i] = d;
        }

        for (int i = 0; i < day.length; i++) {
            if (stack.isEmpty() || stack.peek() < day[i]) {
                stack.push(day[i]);
                ans.addLast(1);
            } else {
                int lastAns = ans.pollLast();
                ans.addLast(lastAns + 1);
            }
        }

        answer = new int[ans.size()];

        int ansIdx = 0;
        while (!ans.isEmpty()) {
            answer[ansIdx++] = ans.pollFirst();
        }


        return answer;
    }
}
