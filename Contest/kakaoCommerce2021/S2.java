package kakaoCommerce2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class S2 {
    private int[][] needs;
    private int[] needsBit;
    private int r, rowSZ, colSZ, ans;

    public int solution(int[][] needs, int r) {
        init(needs, r);
        int needBitIdx = 0;
        for (int[] need : needs) {
            int bit = 0;
            for (int i = 0; i < need.length; i++) {
                if (need[i] == 1) {
                    bit = bit | (1 << i);
                }
            }
            needsBit[needBitIdx++] = bit;
        }

        permutation(1, 0, 0);
        permutation(1, 1, 1);

        return ans;
    }

    private void permutation(int curLen, int curRobot, int curbit) {
        if (curRobot == r) {
            int cnt = 0;
            for (int bit : needsBit) {
                if ((bit | curbit) == curbit) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }
        if (curLen == colSZ) {
            return;
        }
        permutation(curLen + 1, curRobot + 1, curbit | (1 << curLen));
        permutation(curLen + 1, curRobot, curbit);

    }

    private void init(int[][] needs, int r) {
        this.needs = needs;
        this.needsBit = new int[needs.length];
        this.r = r;
        this.rowSZ = needs.length;
        this.colSZ = needs[0].length;
    }
}