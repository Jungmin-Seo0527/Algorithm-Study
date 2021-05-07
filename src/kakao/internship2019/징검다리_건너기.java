package kakao.internship2019;

public class 징검다리_건너기 {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int start = 0;
        int end = 200_000_000;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (!avail(mid, stones, k)) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return answer;
    }

    private boolean avail(int m, int[] stones, int k) {
        int cnt = 0;
        for (int i = 0; i < stones.length && cnt < k; i++) {
            if (stones[i] - m <= 0) cnt++;
            else cnt = 0;
        }
        return cnt < k;
    }
}