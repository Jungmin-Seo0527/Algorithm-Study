package ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PGM_이중우선순위큐 {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int size = 0;
        for (String op : operations) {
            String[] strArr = op.split(" ");
            if (strArr[0].equals("I")) {
                maxHeap.add(Integer.parseInt(strArr[1]));
                minHeap.add(Integer.parseInt(strArr[1]));
                size++;
            } else {
                if (strArr[1].equals("1") && size > 0) {
                    Integer poll = maxHeap.poll();
                    minHeap.remove(poll);
                    size--;
                } else if (strArr[1].equals("-1") && size > 0) {
                    Integer poll = minHeap.poll();
                    maxHeap.remove(poll);
                    size--;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        if (size >= 1) {
            ans.add(maxHeap.poll());
            ans.add(minHeap.poll());
        } else {
            ans.add(0);
            ans.add(0);
        }
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
