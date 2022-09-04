package kakao.recruit2022;

public class k진수에서_소수_개수_구하기 {
    public int solution(int n, int k) {
        int answer = 0;
        int[] arr = conversion(n, k);
        long temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                temp = temp * 10 + arr[i];
            } else {
                if (isPrime(temp)) {
                    answer++;
                }
                temp = 0;
            }
        }

        // 마지막 남은 수 계산(예: 110111 일때 왼쪽 111을 따로 계산 필요)
        if (temp != 0 && isPrime(temp)) {
            answer++;
        }


        return answer;
    }

    /**
     * @param n 10진수
     * @param k k진수로 변환
     * @return int 배열로 반환
     */
    public int[] conversion(int n, int k) {
        int sz = getConArrSZ(n, k);
        int[] ret = new int[sz];
        for (int i = sz - 1; i >= 0 && n >= 0; i--) {
            ret[i] = n % k;
            n /= k;
        }
        return ret;
    }

    /**
     * @param n 10진수 n
     * @param k k 진수로 변환
     * @return 자릿수
     */
    private int getConArrSZ(int n, int k) {
        int temp = 1;
        int sz = 1;
        while (temp * k <= n) {
            temp *= k;
            sz++;
        }
        return sz;
    }

    /**
     * @param n 십진수 n
     * @return 소수판별
     */
    private boolean isPrime(long n) {
        if (n == 0) return false;
        if (n == 1) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}