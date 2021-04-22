package ds;

import java.util.*;

public class PGM_다리를_지나는_트럭 {
    private Queue<Integer> que = new LinkedList<>();

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int curWeight = 0;
        int truckIdx = 0;
        int truckCnt = 0;

        for (int i = 0; i < bridge_length; i++) {
            que.add(0);
        }
        while (truckIdx < truck_weights.length) {
            time++;
            int out = que.poll();
            if (out > 0) truckCnt--;
            curWeight -= out;
            if (curWeight + truck_weights[truckIdx] <= weight && truckCnt + 1 <= bridge_length) {
                curWeight += truck_weights[truckIdx];
                que.add(truck_weights[truckIdx++]);
                truckCnt++;
            } else que.add(0);
        }
        time += que.size();
        return time;
    }
}