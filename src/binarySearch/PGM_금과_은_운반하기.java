package binarySearch;

public class PGM_금과_은_운반하기 {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long start = 0;
        long end = (long) (1e9 * 2 * 2 * 1e5);

        while (start < end) {
            long mid = (start + end) >>> 1;
            if (func(mid, a, b, g, s, w, t)) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    boolean func(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int len = g.length;
        long gold = 0;
        long silver = 0;
        long sum = 0;
        for (int i = 0; i < len; i++) {
            long moveCnt = time / t[i];
            moveCnt = moveCnt % 2 != 0 ? moveCnt / 2 + 1 : moveCnt / 2;

            gold += Math.min(g[i], moveCnt * w[i]);
            silver += Math.min(s[i], moveCnt * w[i]);
            sum += Math.min(g[i] + s[i], moveCnt * w[i]);
        }
        return gold >= a && silver >= b && sum >= a + b;
    }
}