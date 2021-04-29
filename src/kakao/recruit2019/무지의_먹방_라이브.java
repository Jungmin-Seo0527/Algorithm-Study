package kakao.recruit2019;

import java.util.*;

public class 무지의_먹방_라이브 {
    List<Food> foodList = new ArrayList<>();

    public int solution(int[] food_times, long k) {
        for (int i = 0; i < food_times.length; i++) {
            foodList.add(new Food(i + 1, food_times[i]));
        }

        foodList.sort((f1, f2) -> Integer.compare(f1.time, f2.time));

        int prevTime = 0;
        int curLen = food_times.length;
        int curIdx = 0;

        for (Food food : foodList) {
            int curTimeFood = food.time - prevTime;
            long removeColCnt = (long) curTimeFood * curLen;
            if (k >= removeColCnt) {
                k -= removeColCnt;
                prevTime = food.time;
            } else {
                List<Food> subFoodList = foodList.subList(curIdx, food_times.length);
                subFoodList.sort((f1, f2) -> Integer.compare(f1.idx, f2.idx));
                return subFoodList.get((int) (k % curLen)).idx;
            }
            curIdx++;
            curLen--;
        }

        return -1;
    }

    private static class Food {
        int time, idx;

        public Food(int idx, int time) {
            this.time = time;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Food{" +
                    "time=" + time +
                    ", idx=" + idx +
                    '}';
        }
    }
}
