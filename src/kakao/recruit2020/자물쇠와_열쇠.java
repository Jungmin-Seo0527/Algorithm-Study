package kakao.recruit2020;

import java.util.Arrays;

public class 자물쇠와_열쇠 {
    private int lockSZ, keySZ;
    private int[][] key, lock, resizeLock;

    public boolean solution(int[][] key, int[][] lock) {
        init(key, lock);
        return ans();
    }

    private boolean ans() {
        for (int d = 0; d < 4; d++) {
            this.key = rotateArr(this.key);
            for (int r = 0; r + keySZ < 3 * lockSZ; r++) {
                for (int c = 0; c + keySZ < 3 * lockSZ; c++) {
                    if (checkKey(r, c)) return true;
                }
            }
        }
        return false;
    }

    private boolean checkKey(int r, int c) {
        int[][] tempLock = new int[3 * lockSZ][3 * lockSZ];
        for (int i = r; i < r + keySZ; i++) {
            for (int j = c; j < c + keySZ; j++) {
                tempLock[i][j] = this.key[i - r][j - c];
            }
        }

        for (int i = lockSZ; i < 2 * lockSZ; i++) {
            for (int j = lockSZ; j < 2 * lockSZ; j++) {
                if (tempLock[i][j] == 0 && this.resizeLock[i][j] == 0) return false;
                if (tempLock[i][j] == 1 && this.resizeLock[i][j] == 1) return false;
            }
        }
        return true;
    }

    private void init(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        this.lockSZ = this.lock.length;
        this.keySZ = this.key.length;
        this.resizeLock = new int[3 * lockSZ][3 * lockSZ];
        for (int i = lockSZ; i < 2 * lockSZ; i++) {
            for (int j = lockSZ; j < 2 * lockSZ; j++) {
                resizeLock[i][j] = lock[i - lockSZ][j - lockSZ];
            }
        }
    }

    private int[][] rotateArr(int[][] arr) {
        int arrLen = arr.length;
        int[][] ret = new int[arrLen][arrLen];
        for (int i = 0; i < arrLen; i++) {
            for (int j = 0; j < arrLen; j++) {
                ret[i][j] = arr[arrLen - j - 1][i];
            }
        }
        return ret;
    }

    private void showArr(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
}
