package implementation;

import java.io.*;
import java.util.*;


public class BOJ2505_두_번_뒤집기 {

    static int N;
    static int[] arr, idx;
    static final int MAX_IDX = 10_001;

    static void solve() {
        StringBuilder ans = new StringBuilder();
        int cnt = cntFilp(true, ans);
        if (printAnswer(cnt, ans)) {
            return;
        }
        ans = new StringBuilder();
        cnt = cntFilp(false, ans);
        if (printAnswer(cnt, ans)) {
            return;
        }
    }

    private static boolean printAnswer(int cnt, StringBuilder ans) {
        if (cnt == 1) {
            ans.append("1 1").append("\n");
        } else if (cnt == 0) {
            ans.append("1 1").append("\n").append("1 1");
        }
        if (cnt == 0 || cnt == 1 || cnt == 2) {
            System.out.println(ans);
            return true;
        }
        return false;
    }

    static int cntFilp(boolean front, StringBuilder ans) {
        int[] copyArr = new int[N + 1];
        int[] copyIdx = new int[MAX_IDX];
        System.arraycopy(arr, 0, copyArr, 0, N + 1);
        System.arraycopy(idx, 0, copyIdx, 0, MAX_IDX);
        int cnt = 0;
        if (front) {
            for (int i = 1; i <= N && cnt < 3; i++) {
                cnt += getCnt(ans, copyArr, copyIdx, i);
            }
        } else {
            for (int i = N; i >= 1 && cnt < 3; i--) {
                cnt += getCnt(ans, copyArr, copyIdx, i);
            }
        }
        return cnt;
    }

    private static int getCnt(StringBuilder ans, int[] copyArr, int[] copyIdx, int arrIdx) {
        int ret = 0;
        if (copyArr[arrIdx] != arrIdx) {
            ans.append(Math.min(arrIdx, copyIdx[arrIdx]))
                    .append(" ")
                    .append(Math.max(arrIdx, copyIdx[arrIdx])).append("\n");

            flip(copyArr, copyIdx, Math.min(arrIdx, copyIdx[arrIdx]),
                    Math.max(arrIdx, copyIdx[arrIdx]));
            ret++;
        }
        return ret;
    }

    static void flip(int[] a, int[] idxArr, int start, int end) {
        for (int i = start, mid = (end + start) >>> 1; i <= mid; i++) {
            swap(a, idxArr, i, end - i + start);
        }
    }

    static void swap(int[] arr, int[] idxArr, int idx1, int idx2) {
        int tempA = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tempA;
        idxArr[arr[idx1]] = idx1;
        idxArr[arr[idx2]] = idx2;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = readInputFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        idx = new int[MAX_IDX];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            idx[arr[i]] = i;
        }
        solve();
    }

    private static BufferedReader readInputFile() throws IOException {
        System.out.println("===== input =====");
        String fileName = "input/input3.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        String s;
        while ((s = br2.readLine()) != null) {
            System.out.println(s);
        }
        System.out.println("===== output =====");
        return br;
    }
}
