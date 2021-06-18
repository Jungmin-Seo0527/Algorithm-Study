package math;

public class CD_MinAvgTwoSlice {
    public int solution(int[] A) {
        double minAvg = (A[0] + A[1]) / 2d;
        int ret = 0;
        for (int i = 2; i < A.length; i++) {
            double avg = (A[i - 2] + A[i - 1] + A[i]) / 3d;
            if (minAvg > avg) {
                minAvg = avg;
                ret = i - 2;
            }

            avg = (A[i - 1] + A[i]) / 2d;
            if (minAvg > avg) {
                minAvg = avg;
                ret = i - 1;
            }
        }
        return ret;
    }
}
