package kakao.recruit2019;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 실패율 {

    private int N;
    private int[] stages;
    private final List<Stage> stageList = new ArrayList<>();

    public int[] solution(int N, int[] stages) {
        init(N, stages);
        return ans();
    }

    private int[] ans() {
        for (int i = 0; i < N; i++) {
            stageList.add(new Stage(i + 1, 0, 0, 0));
        }
        for (int s : stages) {
            for (int i = 0; i < s - 1; i++) {
                stageList.get(i).arrival++;
            }
            if (s - 1 < N) {
                stageList.get(s - 1).arrivalAndNonClear++;
                stageList.get(s - 1).arrival++;
            }
        }
        for (Stage s : stageList) {
            s.calFailRate();
        }
        Collections.sort(stageList);

        int[] ret = new int[stageList.size()];
        int retIdx = 0;
        for (Stage s : stageList) {
            ret[retIdx++] = s.stageNum;
        }
        return ret;
    }

    private void init(int N, int[] stages) {
        this.N = N;
        this.stages = stages;
    }

    private static class Stage implements Comparable<Stage> {
        int stageNum, arrival, arrivalAndNonClear;
        BigDecimal failRate;

        @Override
        public String toString() {
            return "Stage{" +
                    "stageNum=" + stageNum +
                    ", arrival=" + arrival +
                    ", arrivalAndNonClear=" + arrivalAndNonClear +
                    ", failRate=" + failRate +
                    '}';
        }

        public Stage(int stageNum, int arrivalAndClear, int arrivalAndNonClear, double failRate) {
            this.stageNum = stageNum;
            this.arrival = arrivalAndClear;
            this.arrivalAndNonClear = arrivalAndNonClear;
            this.failRate = new BigDecimal(String.valueOf(failRate));
        }

        private void calFailRate() {
            if (arrival == 0) {
                failRate = new BigDecimal(String.valueOf(0));
                return;
            }
            BigDecimal aac = new BigDecimal(String.valueOf(arrivalAndNonClear));
            BigDecimal a = new BigDecimal(String.valueOf(arrival));
            failRate = aac.divide(a, 15, BigDecimal.ROUND_CEILING);
        }

        public int compareTo(Stage s) {
            int compare = this.failRate.compareTo(s.failRate);
            if (compare != 0) return -compare;
            else return this.stageNum - s.stageNum;
        }
    }

}